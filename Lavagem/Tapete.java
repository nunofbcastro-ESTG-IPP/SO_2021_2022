package Lavagem;

import java.util.concurrent.Semaphore;

import Enums.EstadoLavagem;
import Layout.TapeteWindow;
import Utils.Configs;
import Utils.Log;
import Utils.Utils;

public class Tapete extends TapeteWindow implements Runnable {

    private final SharedObjLavagem sharedObjTapete;
    private final Semaphore semTapete;
    private final Semaphore semTapeteMain;
    private final Semaphore semTapeteThreadMain;

    public Tapete(SharedObjLavagem sharedObjTapete, Semaphore semTapeteThreadMain, Semaphore semTapeteMain,
            Semaphore semTapete) {
        this.sharedObjTapete = sharedObjTapete;
        this.semTapeteThreadMain = semTapeteThreadMain;
        this.semTapeteMain = semTapeteMain;
        this.semTapete = semTapete;
        this.VisibleFrame();
    }

    private void waitLavagem(int time, String tipo, Log log) throws InterruptedException {
        super.tapeteEmUtilizacao(log.getModeloCarro(), log.getNomeProprietario());
        while (time >= 0) {
            super.mudarTempo(time, tipo);
            try {
                Thread.sleep(1000);
                time--;
            } catch (InterruptedException ex) {
                this.interruptedAction();
            }
        }
    }

    public void pullNextCar() {
        Log log = sharedObjTapete.getNextCar();
        if (log != null) {
            try {
                // Ligar tapete
                this.waitLavagem(Configs.getDuracaoArranqueTapete(), defaultTempoIniciar, log);
                // Abizar que ja pode iniciar a lavagem
                this.semTapete.release();
                super.tapeteEspera();
                // Espera termino de lavagem
                this.acquireHelper(this.semTapeteMain);
                // Finalazição do tapete
                this.waitLavagem(Configs.getDuracaoFimProcesso(), defaultTempoAcabar, log);
                // Avisar da finalização
                this.semTapete.release();
                // Esperar que o sistema receba a mensagem
                this.acquireHelper(this.semTapeteMain);
                // Saida do carro
                this.waitLavagem(Configs.getDuracaoAposTerminoLavagemCarro(), defaultTempoAposTerminoLavagemCarro, log);
                // Avisar que o carro já saiu
                this.semTapete.release();
                // Espera que o main avise se o tapete vai parar ou não
                this.acquireHelper(this.semTapeteMain);
            } catch (InterruptedException e) {
            }
        }
        // Alterar estado tapete caso necessario
        if (this.sharedObjTapete.getEstadoTapete() == EstadoLavagem.Parado) {
            super.tapeteVazio();
        }
    }

    private void selectAction() {
        switch (this.sharedObjTapete.getEstadoTapete()) {
            case Ativo:
                this.pullNextCar();
                break;
            case Parado:
                super.tapeteVazio();
                break;
        }
    }

    public void acquireHelper(Semaphore sem) throws InterruptedException {
        Boolean interrupted;
        do {
            interrupted = false;
            try {
                sem.acquire();
            } catch (InterruptedException e) {
                if (this.sharedObjTapete.isEmergencia()) {
                    interrupted = true;
                }
                this.interruptedAction();
            }
        } while (interrupted);
    }

    private void resetThread() {
        super.tapeteVazio();
    }

    private void interruptedAction() throws InterruptedException {
        if (this.sharedObjTapete.isEmergencia()) {
            Utils.stopThread();
        } else {
            this.resetThread();
            throw new InterruptedException("Interrupted");
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.semTapeteThreadMain.acquire();
                this.selectAction();
            } catch (InterruptedException e) {
                super.tapeteVazio();
            }
        }
    }
}

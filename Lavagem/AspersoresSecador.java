package Lavagem;

import java.util.concurrent.Semaphore;

import Layout.AspersoresSecadorWindow;
import Utils.Utils;
import Utils.Configs;
import Utils.Log;

public class AspersoresSecador extends AspersoresSecadorWindow implements Runnable {

    private final Semaphore semAspersoresSecadorMain;
    private final Semaphore semAspersoresSecador;
    private final SharedObjLavagem sharedObjAspersoresSecador;

    public AspersoresSecador(SharedObjLavagem sharedObjAspersoresSecador, Semaphore semAspersoresSecadorMain,
            Semaphore semAspersoresSecador) {
        this.semAspersoresSecadorMain = semAspersoresSecadorMain;
        this.semAspersoresSecador = semAspersoresSecador;
        this.sharedObjAspersoresSecador = sharedObjAspersoresSecador;
    }

    private void waitLavagem(int time, String tipo, Log log) {
        boolean interruptReset = false;
        super.aspersoresSecadorEmUtilizacao(log.getModeloCarro(), log.getNomeProprietario());
        while (time >= 0 && !interruptReset) {
            super.mudarTempo(time, tipo);
            try {
                Thread.sleep(1000);
                time--;
            } catch (InterruptedException ex) {
                interruptReset = this.interruptedAction();
            }
        }
    }

    private void pullNextCarSecador() {
        Log log = sharedObjAspersoresSecador.getNextCar();
        if (log != null) {
            int duracaoSecador = Utils.randomInt(Configs.getDuracaoAtivacaoSecadoresMin(),
                    Configs.getDuracaoAtivacaoSecadoresMax());
            this.waitLavagem(duracaoSecador, defaultSecador, log);
            this.semAspersoresSecador.release();
        }
        super.aspersoresSecadorParados();
    }

    private void pullNextCarAspersores() {
        Log log = sharedObjAspersoresSecador.getNextCar();
        if (log != null) {
            this.waitLavagem(Configs.getDuracaoAtivacaoAspersores(), defaultAspersores, log);
            this.semAspersoresSecador.release();
        }
        super.aspersoresSecadorParados();
    }

    private void selectAction() {
        switch (this.sharedObjAspersoresSecador.getEstadoAspersoresSecador()) {
            case EmAspiracao:
                this.pullNextCarAspersores();
                break;
            case EmSecagem:
                this.pullNextCarSecador();
                break;
            case Parado:
                super.aspersoresSecadorParados();
                break;
        }
    }

    private boolean interruptedAction() {
        if (this.sharedObjAspersoresSecador.isEmergencia()) {
            Utils.stopThread();
        } else {
            super.aspersoresSecadorParados();
            return true;
        }
        return false;
    }

    @Override
    public void run() {
        this.VisibleFrame();

        while (true) {
            try {
                this.semAspersoresSecadorMain.acquire();
                this.selectAction();
            } catch (InterruptedException e) {
                this.interruptedAction();
            }
        }
    }

}

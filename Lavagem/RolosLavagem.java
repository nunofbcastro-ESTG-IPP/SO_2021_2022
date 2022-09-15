package Lavagem;

import java.util.concurrent.Semaphore;

import Layout.RolosLavagemWindow;
import Utils.Configs;
import Utils.Log;
import Utils.Utils;

public class RolosLavagem extends RolosLavagemWindow implements Runnable {

    private final Semaphore semRolosLavagemMain;
    private final Semaphore semRolosLavagem;
    private final SharedObjLavagem sharedObjRolosLavagem;

    public RolosLavagem(SharedObjLavagem sharedObjRolosLavagem, Semaphore semRolosLavagemMain,
            Semaphore semRolosLavagem) {
        this.semRolosLavagemMain = semRolosLavagemMain;
        this.semRolosLavagem = semRolosLavagem;
        this.sharedObjRolosLavagem = sharedObjRolosLavagem;
    }

    private void waitLavagem(int time, Log log) {
        boolean interruptReset = false;
        super.rolosLavagemEmUtilizacao(log.getModeloCarro(), log.getNomeProprietario());
        while (time >= 0 && !interruptReset) {
            super.mudarTempo(time);
            try {
                Thread.sleep(1000);
                time--;
            } catch (InterruptedException ex) {
                interruptReset = this.interruptedAction();
            }
        }
    }

    public void pullNextCar() {
        Log log = sharedObjRolosLavagem.getNextCar();
        if (log != null) {
            int duracaoRolos = Utils.randomInt(Configs.getDuracaoMovimentoRolosMin(),
                    Configs.getDuracaoMovimentoRolosMax());
            this.waitLavagem(duracaoRolos, log);
            // Avisar o main que os rolos acabaram
            this.semRolosLavagem.release();
        }
        super.rolosLavagemParado();
    }

    private boolean interruptedAction() {
        if (this.sharedObjRolosLavagem.isEmergencia()) {
            Utils.stopThread();
        } else {
            super.rolosLavagemParado();
            return true;
        }
        return false;
    }

    private void selectAction() {
        switch (this.sharedObjRolosLavagem.getEstadoRolos()) {
            case Ativo:
                this.pullNextCar();
                break;
            case Parado:
                this.interruptedAction();
                break;
        }
    }

    @Override
    public void run() {
        this.VisibleFrame();

        while (true) {
            try {
                // O main fornece um recurso para ativar os rolos
                this.semRolosLavagemMain.acquire();
                this.selectAction();
            } catch (InterruptedException e) {
                this.interruptedAction();
            }
        }
    }

}

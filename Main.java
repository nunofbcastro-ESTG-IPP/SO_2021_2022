
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import Lavagem.AspersoresSecador;
import Lavagem.InterfaceMoedeiro;
import Lavagem.RolosLavagem;
import Lavagem.SharedObjMoedeiro;
import Lavagem.SharedObjLavagem;
import Lavagem.Tapete;
import Utils.Configs;
import Utils.Log;
import Utils.Logs;
import Enums.*;

public class Main implements Runnable {

    private Semaphore semMoedeiroMain;
    private Semaphore semMoedeiro;
    private SharedObjMoedeiro sharedObjMoedeiro;
    private SharedObjLavagem sharedObjLavagem;
    private Semaphore semTapeteThreadMain;
    private Thread threadTapete;
    private Thread threadRolosLavagem;
    private Thread threadAspersoresSecador;

    public Main(Semaphore semMoedeiroMain, Semaphore semMoedeiro, Semaphore semTapeteThreadMain,
            SharedObjMoedeiro sharedObjMoedeiro, SharedObjLavagem sharedObjTapete, Thread threadTapete,
            Thread threadRolosLavagem, Thread threadAspersoresSecador) {
        this.semMoedeiroMain = semMoedeiroMain;
        this.semMoedeiro = semMoedeiro;
        this.semTapeteThreadMain = semTapeteThreadMain;
        this.sharedObjMoedeiro = sharedObjMoedeiro;
        this.sharedObjLavagem = sharedObjTapete;
        this.threadTapete = threadTapete;
        this.threadRolosLavagem = threadRolosLavagem;
        this.threadAspersoresSecador = threadAspersoresSecador;
    }

    private void iniciar() {
        if (this.sharedObjMoedeiro.getEstado() != Estado.Fechado) {
            if(this.sharedObjMoedeiro.getNomeProprietario().length()<3 || this.sharedObjMoedeiro.getModeloCarro().length()<2){
                this.sharedObjMoedeiro.setDadosValidos(false);
            }else{
                this.sharedObjMoedeiro.setDadosValidos(true);
                double troco = this.sharedObjMoedeiro.getDinheiroInserido() - Configs.getPreco();
                this.sharedObjMoedeiro.setTroco(troco);
                if (troco >= 0) {
                    Log log = new Log(Configs.getPreco(),
                            this.sharedObjMoedeiro.getDinheiroInserido(),
                            troco, this.sharedObjMoedeiro.getModeloCarro(), this.sharedObjMoedeiro.getNomeProprietario());
                    this.sharedObjLavagem.addLog(log);
                    this.sharedObjMoedeiro.setEstado(Estado.Ocupado);
                    this.sharedObjLavagem.setEstadoTapete(EstadoLavagem.Ativo);
                    this.semTapeteThreadMain.release();
                }
            }
        }
    }

    private void cancelar() {
        this.sharedObjMoedeiro.setTroco(this.sharedObjMoedeiro.getDinheiroInserido());
    }

    private void emergencia() {
        this.sharedObjLavagem.setEmergencia(!this.sharedObjLavagem.isEmergencia());
        this.threadTapete.interrupt();
        this.threadRolosLavagem.interrupt();
        this.threadAspersoresSecador.interrupt();
        if (this.sharedObjLavagem.isEmergencia()) {
            this.sharedObjMoedeiro.setEstado(Estado.Fechado);
        } else {
            this.sharedObjMoedeiro
                    .setEstado(this.sharedObjLavagem.getNextCar() != null ? Estado.Ocupado : Estado.Livre);
        }
    }

    private void reset() {
        this.sharedObjMoedeiro.initValues();
        this.sharedObjLavagem.initValues();
        this.sharedObjLavagem.setReset(true);

        this.threadTapete.interrupt();
        this.threadRolosLavagem.interrupt();
        this.threadAspersoresSecador.interrupt();

        // Chamar o garbage collector
        System.gc();
    }

    private void aberto() {
        if (this.sharedObjMoedeiro.getEstado() == Estado.Fechado && this.sharedObjLavagem.isEmptyListCars()) {
            this.sharedObjMoedeiro.setEstado(Estado.Livre);
        } else {
            this.sharedObjMoedeiro.setEstado(Estado.Ocupado);
        }
    }

    private void fechado() {
        this.sharedObjMoedeiro.setEstado(Estado.Fechado);
    }

    @Override
    public void run() {
        while (true) {
            try {
                this.semMoedeiro.acquire();

                switch (this.sharedObjMoedeiro.getBotaoSelecionado()) {
                    case Iniciar:
                        this.iniciar();
                        break;
                    case Cancelar:
                        this.cancelar();
                        break;
                    case Emergencia:
                        this.emergencia();
                        break;
                    case Reset:
                        this.reset();
                        break;
                    case Aberto:
                        this.aberto();
                        break;
                    case Fechado:
                        this.fechado();
                        break;
                    default:
                        break;
                }

                this.semMoedeiroMain.release();
            } catch (InterruptedException ie) {
            }
        }
    }

    private static void acquireMain(Semaphore sem, SharedObjLavagem sharedObjLavagem) throws InterruptedException {
        boolean recibido = false;
        do {
            try {
                recibido = sem.tryAcquire(100, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
        } while (!sharedObjLavagem.isReset() && recibido == false);
        if (sharedObjLavagem.isReset()) {
            sharedObjLavagem.setReset(false);
            throw new InterruptedException("Interrupted");
        }
    }

    public static void main(String[] args) {

        new Configs();

        Semaphore semMoedeiroMain = new Semaphore(0);
        Semaphore semMoedeiro = new Semaphore(0);
        Semaphore semTapeteMain = new Semaphore(0);
        Semaphore semTapeteThreadMain = new Semaphore(0);
        Semaphore semTapete = new Semaphore(0);
        Semaphore semRolosMain = new Semaphore(0);
        Semaphore semRolos = new Semaphore(0);
        Semaphore semAspersoresMain = new Semaphore(0);
        Semaphore semAspersores = new Semaphore(0);

        SharedObjMoedeiro sharedObjMoedeiro = new SharedObjMoedeiro();
        SharedObjLavagem sharedObjLavagem = new SharedObjLavagem();

        Thread threadMoedeiro = new Thread(new InterfaceMoedeiro(semMoedeiroMain, semMoedeiro, sharedObjMoedeiro),
                "ThreadMoedeiro");
        Thread threadTapete = new Thread(new Tapete(sharedObjLavagem, semTapeteThreadMain, semTapeteMain, semTapete),
                "ThreadTapete");
        Thread threadRolosLavagem = new Thread(new RolosLavagem(sharedObjLavagem, semRolosMain, semRolos),
                "ThreadRolosLavagem");
        Thread threadAspersoresSecador = new Thread(
                new AspersoresSecador(sharedObjLavagem, semAspersoresMain, semAspersores), "ThreadAspersoresSecador");
        Thread threadMoedeiroMain = new Thread(
                new Main(semMoedeiroMain, semMoedeiro, semTapeteThreadMain, sharedObjMoedeiro, sharedObjLavagem,
                        threadTapete,
                        threadRolosLavagem, threadAspersoresSecador),
                "ThreadMoedeiroMain");

        threadMoedeiro.start();
        threadTapete.start();
        threadRolosLavagem.start();
        threadAspersoresSecador.start();
        threadMoedeiroMain.start();

        while (true) {
            try {
                // Esperar que o tapete ligue~
                acquireMain(semTapete, sharedObjLavagem);
                // Ativar aspersores
                sharedObjLavagem.setEstadoAspersoresSecador(EstadoAtividadeAspersoresSecador.EmAspiracao);
                semAspersoresMain.release();
                // Esperar que os aspersores acabem
                acquireMain(semAspersores, sharedObjLavagem);
                sharedObjLavagem.setEstadoAspersoresSecador(EstadoAtividadeAspersoresSecador.Parado);
                // Ativar rolos
                sharedObjLavagem.setEstadoRolos(EstadoLavagem.Ativo);
                semRolosMain.release();
                // Esperar que os rolos acabem
                acquireMain(semRolos, sharedObjLavagem);
                sharedObjLavagem.setEstadoRolos(EstadoLavagem.Parado);
                // Ativar secador
                sharedObjLavagem.setEstadoAspersoresSecador(EstadoAtividadeAspersoresSecador.EmSecagem);
                semAspersoresMain.release();
                // Esperar que o secador acabe
                acquireMain(semAspersores, sharedObjLavagem);
                sharedObjLavagem.setEstadoAspersoresSecador(EstadoAtividadeAspersoresSecador.Parado);
                // Ativar tapete finalizacao
                semTapeteMain.release();
                // Esperar que acabe finalizacao
                acquireMain(semTapete, sharedObjLavagem);
                // Escrever log no ficheiro
                Log log = sharedObjLavagem.removeCurrentCar();
                if (log != null) {
                    Logs.addLog(log);
                }
                // Avisar o tapete do proximo estado
                semTapeteMain.release();
                // Espera que carro saia
                acquireMain(semTapete, sharedObjLavagem);
                // Verificar se ja nao existem mais carros
                if (sharedObjLavagem.getNextCar() == null) {
                    sharedObjLavagem.setEstadoTapete(EstadoLavagem.Parado);
                    sharedObjMoedeiro.setEstado(Estado.Livre);
                    // Avisar que o estado do sistema foi alterado
                    semMoedeiroMain.release();
                }
                // Avisar estado tapete
                semTapeteMain.release();
            } catch (InterruptedException e) {
                semAspersores.drainPermits();
                semAspersoresMain.drainPermits();
                semRolos.drainPermits();
                semRolosMain.drainPermits();
                semTapeteMain.drainPermits();
                semTapeteThreadMain.drainPermits();
                semTapete.drainPermits();
            }
        }
    }

}

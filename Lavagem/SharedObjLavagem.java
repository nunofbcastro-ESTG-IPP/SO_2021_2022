package Lavagem;

import Enums.EstadoAtividadeAspersoresSecador;
import Enums.EstadoLavagem;
import Exceptions.EmptyCollectionException;
import Interfaces.QueueADT;
import Queue.LinkedQueue;
import Utils.Log;

public class SharedObjLavagem {

    protected QueueADT<Log> filaCarros;
    protected Log atual;
    private EstadoLavagem estadoTapete;
    private EstadoLavagem estadoRolos;
    private EstadoAtividadeAspersoresSecador estadoAspersoresSecador;
    private boolean emergencia;
    private boolean reset;

    {
        this.initValues();
    }

    public void initValues() {
        this.estadoTapete = EstadoLavagem.Parado;
        this.estadoRolos = EstadoLavagem.Parado;
        this.estadoAspersoresSecador = EstadoAtividadeAspersoresSecador.Parado;
        this.emergencia = false;
        this.filaCarros = new LinkedQueue<>();
        this.reset = false;
    }

    public void addLog(Log log) {
        if (log != null) {
            this.filaCarros.enqueue(log);
        }
    }

    public boolean isEmptyListCars() {
        return filaCarros.isEmpty();
    }

    public Log getNextCar() {
        if (!this.filaCarros.isEmpty()) {
            try {
                this.atual = this.filaCarros.first();
            } catch (EmptyCollectionException e) {
                this.atual = null;
            }
        } else {
            this.atual = null;
        }
        return this.atual;
    }

    public Log removeCurrentCar() {
        this.atual = null;
        if (!this.filaCarros.isEmpty()) {
            try {
                Log log = this.filaCarros.dequeue();
                log.setHoraFimLavagem();
                return log;
            } catch (EmptyCollectionException e) {
            }
        }
        return null;
    }

    public void setEstadoTapete(EstadoLavagem estadoTapete) {
        this.estadoTapete = estadoTapete;
    }

    public EstadoLavagem getEstadoTapete() {
        return this.estadoTapete;
    }

    public void setEstadoAspersoresSecador(EstadoAtividadeAspersoresSecador estadoAspersoresSecador) {
        this.estadoAspersoresSecador = estadoAspersoresSecador;
    }

    public EstadoAtividadeAspersoresSecador getEstadoAspersoresSecador() {
        return this.estadoAspersoresSecador;
    }

    public void setEstadoRolos(EstadoLavagem estadoRolos) {
        this.estadoRolos = estadoRolos;
    }

    public EstadoLavagem getEstadoRolos() {
        return this.estadoRolos;
    }

    public boolean isEmergencia() {
        return this.emergencia;
    }

    public void setEmergencia(boolean emergencia) {
        this.emergencia = emergencia;
    }

    public boolean isReset() {
        return this.reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

}

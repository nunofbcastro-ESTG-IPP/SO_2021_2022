package Lavagem;

import Enums.Estado;
import Enums.TipoBotao;

public class SharedObjMoedeiro {

    private double dinheiroInserido;
    private double troco;
    private Estado estado;
    private String modeloCarro;
    private String nomeProprietario;
    //Variavel utilizada para manipular se o modelo do carro e o nome do proprietario foram inseridos
    private boolean dadosValidos;
    private TipoBotao botaoSelecionado;

    {
        this.initValues();
    }

    public void initValues() {
        this.dinheiroInserido = 0;
        this.troco = 0;
        this.estado = Estado.Fechado;
        this.modeloCarro = "";
        this.nomeProprietario = "";
        this.dadosValidos = false;
        this.botaoSelecionado = TipoBotao.NaoSelecionado;
    }

    public double getDinheiroInserido() {
        return this.dinheiroInserido;
    }

    public void setDinheiroInserido(double dinheiroInserido) {
        this.dinheiroInserido = dinheiroInserido;
    }

    public double getTroco() {
        return this.troco;
    }

    public void setTroco(double troco) {
        this.troco = troco;
    }

    public Estado getEstado() {
        return this.estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getModeloCarro() {
        return this.modeloCarro;
    }

    public void setModeloCarro(String modeloCarro) {
        this.modeloCarro = modeloCarro;
    }

    public String getNomeProprietario() {
        return this.nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public boolean isDadosValidos() {
        return this.dadosValidos;
    }

    public void setDadosValidos(boolean dadosValidos) {
        this.dadosValidos = dadosValidos;
    }

    public TipoBotao getBotaoSelecionado() {
        return this.botaoSelecionado;
    }

    public void setBotaoSelecionado(TipoBotao botaoSelecionado) {
        this.botaoSelecionado = botaoSelecionado;
    }

}

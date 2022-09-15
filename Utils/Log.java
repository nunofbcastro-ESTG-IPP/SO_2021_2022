package Utils;

public class Log {

    // Variaveis que constituem uma Log
    private final String horaPagamento;
    private String horaFimLavagem;
    private final String modeloCarro;
    private final String nomeProprietario;
    private final double preco;
    private final double valorInserido;
    private double troco;

    public Log(double preco, double valorInserido, double troco, String modeloCarro, String nomeProprietario) {
        this.modeloCarro = modeloCarro;
        this.nomeProprietario = nomeProprietario;
        this.horaPagamento = Utils.getDateTime();
        this.preco = preco;
        this.valorInserido = valorInserido;
        this.troco = troco;
        this.horaFimLavagem = null;
    }

    public String getHoraFimLavagem() {
        return this.horaFimLavagem;
    }

    public void setHoraFimLavagem() {
        if (this.horaFimLavagem == null) {
            this.horaFimLavagem = Utils.getDateTime();
        }
    }

    public String getHoraPagamento() {
        return this.horaPagamento;
    }

    public String getModeloCarro() {
        return this.modeloCarro;
    }

    public String getNomeProprietario() {
        return this.nomeProprietario;
    }

    public double getPreco() {
        return this.preco;
    }

    public double getValorInserido() {
        return this.valorInserido;
    }

    public double getTroco() {
        return this.troco;
    }

}

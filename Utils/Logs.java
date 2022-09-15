package Utils;

public class Logs {

    /**
     * Localização do ficheiro de logs
     */
    private static final String logsFile = "./Files/logs.txt";

    /**
     * Método responsavel por adicionar uma log ao ficheiro
     *
     * @param log Log a adicionar
     */
    public static void addLog(Log log) {
        StringBuilder value = new StringBuilder();
        value.append("HoraPagamento: ").append(log.getHoraPagamento()).append(";");
        value.append("HoraFimLavagem: ").append(log.getHoraFimLavagem()).append(";");
        value.append("ModeloCarro: ").append(log.getModeloCarro()).append(";");
        value.append("NomeProprietario: ").append(log.getNomeProprietario()).append(";");
        value.append("PrecoPagar: ").append(log.getPreco()).append(";");
        value.append("ValorInserido: ").append(log.getValorInserido()).append(";");
        value.append("Troco: ").append(log.getTroco()).append(";");
        Files.writeFile(logsFile, value.toString());
    }
}

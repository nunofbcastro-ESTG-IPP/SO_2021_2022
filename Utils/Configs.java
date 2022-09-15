package Utils;

import java.util.List;

public class Configs {

    /**
     * Varivel com o nome do ficheiro com a configuracao
     */
    private static final String fileName = "./Files/configs.txt";

    // Variveis com os nomes das tags do ficheiro configuracao (Parte inicial antes
    // do valor associado)
    private static final String tagPreco = "Preco: ";
    private static final String tagDuracaoAposTerminoLavagemCarro = "DuracaoAposTerminoLavagemCarro: ";
    private static final String tagDuracaoArranqueTapete = "DuracaoArranqueTapete: ";
    private static final String tagDuracaoMovimentoRolosMin = "DuracaoMovimentoRolosMin: ";
    private static final String tagDuracaoMovimentoRolosMax = "DuracaoMovimentoRolosMax: ";
    private static final String tagDuracaoAtivacaoAspersores = "DuracaoAtivacaoAspersores: ";
    private static final String tagDuracaoAtivacaoSecadoresMin = "DuracaoAtivacaoSecadoresMin: ";
    private static final String tagDuracaoAtivacaoSecadoresMax = "DuracaoAtivacaoSecadoresMax: ";
    private static final String tagDuracaoFimProcesso = "DuracaoFimProcesso: ";

    /**
     * Preço por lavagem
     */
    private static double preco;

    private static int duracaoAposTerminoLavagemCarro;

    private static int duracaoArranqueTapete;

    private static int duracaoMovimentoRolosMin;
    private static int duracaoMovimentoRolosMax;

    private static int duracaoAtivacaoAspersores;

    private static int duracaoAtivacaoSecadoresMin;
    private static int duracaoAtivacaoSecadoresMax;

    private static int duracaoFimProcesso;

    /**
     * Método executado obrigatoriamente por qualquer construtor para instanciar
     * as variaveis com os devidos valores
     */
    {
        importConfig();
    }

    /**
     * Associa valores por defeito às variaveis em questão quando o ficheiro é
     * inválido
     */
    private static void defaultValues() {
        preco = 5;
        duracaoAposTerminoLavagemCarro = 5;
        duracaoArranqueTapete = 2;
        duracaoMovimentoRolosMin = 4;
        duracaoMovimentoRolosMax = 8;
        duracaoAtivacaoAspersores = 5;
        duracaoAtivacaoSecadoresMin = 3;
        duracaoAtivacaoSecadoresMax = 6;
        duracaoFimProcesso = 3;
    }

    /**
     * Verifica se a estrutura do ficheiro é valida
     *
     * @param data Lista que contem todas as linhas do ficheiro de configuração
     * @return True se o ficheiro é valido, False caso contrário
     */
    private static boolean isValidStructConfigFile(List<String> data) {
        if (data != null && data.size() < 9) {
            return false;
        }
        if (!data.get(0).contains(tagPreco)) {
            return false;
        }
        if (!data.get(1).contains(tagDuracaoAposTerminoLavagemCarro)) {
            return false;
        }
        if (!data.get(2).contains(tagDuracaoArranqueTapete)) {
            return false;
        }
        if (!data.get(3).contains(tagDuracaoMovimentoRolosMin)) {
            return false;
        }
        if (!data.get(4).contains(tagDuracaoMovimentoRolosMax)) {
            return false;
        }
        if (!data.get(5).contains(tagDuracaoAtivacaoAspersores)) {
            return false;
        }
        if (!data.get(6).contains(tagDuracaoAtivacaoSecadoresMin)) {
            return false;
        }
        if (!data.get(7).contains(tagDuracaoAtivacaoSecadoresMax)) {
            return false;
        }
        if (!data.get(8).contains(tagDuracaoFimProcesso)) {
            return false;
        }
        return true;
    }

    /**
     * Converter os valores do ficheiro de configurações para os tipos
     * compativeis, bem como a associação dos mesmos às respetivas variaveis
     *
     * @param data Lista que contem todas as linhas do ficheiro de configuração
     * @return True caso todos os valores sejam válidos, False caso contrário
     */
    public static boolean convertValues(List<String> data) {
        if (isValidStructConfigFile(data)) {
            try {
                preco = Double.parseDouble(data.get(0).split(tagPreco)[1]);
                duracaoAposTerminoLavagemCarro = Integer.parseInt(data.get(1).split(tagDuracaoAposTerminoLavagemCarro)[1]);
                duracaoArranqueTapete = Integer.parseInt(data.get(2).split(tagDuracaoArranqueTapete)[1]);
                duracaoMovimentoRolosMin = Integer.parseInt(data.get(3).split(tagDuracaoMovimentoRolosMin)[1]);
                duracaoMovimentoRolosMax = Integer.parseInt(data.get(4).split(tagDuracaoMovimentoRolosMax)[1]);
                duracaoAtivacaoAspersores = Integer.parseInt(data.get(5).split(tagDuracaoAtivacaoAspersores)[1]);
                duracaoAtivacaoSecadoresMin = Integer.parseInt(data.get(6).split(tagDuracaoAtivacaoSecadoresMin)[1]);
                duracaoAtivacaoSecadoresMax = Integer.parseInt(data.get(7).split(tagDuracaoAtivacaoSecadoresMax)[1]);
                duracaoFimProcesso = Integer.parseInt(data.get(8).split(tagDuracaoFimProcesso)[1]);
                return true;
            } catch (Exception e) {
            }
        }
        return false;
    }

    /**
     * Importar os valores do ficheiro de configuração
     *
     * @return True caso os valores sejam importados com sucesso, False caso
     * contrário
     */
    public static boolean importConfig() {
        List<String> data = Files.readFile(fileName);
        if (!convertValues(data)) {
            System.out.println("Ficheiro invalido");
            defaultValues();
            return false;
        }
        return true;
    }

    public static double getPreco() {
        return preco;
    }

    public static int getDuracaoAposTerminoLavagemCarro() {
        return duracaoAposTerminoLavagemCarro;
    }

    public static int getDuracaoArranqueTapete() {
        return duracaoArranqueTapete;
    }

    public static int getDuracaoMovimentoRolosMin() {
        return duracaoMovimentoRolosMin;
    }

    public static int getDuracaoMovimentoRolosMax() {
        return duracaoMovimentoRolosMax;
    }

    public static int getDuracaoAtivacaoAspersores() {
        return duracaoAtivacaoAspersores;
    }

    public static int getDuracaoAtivacaoSecadoresMin() {
        return duracaoAtivacaoSecadoresMin;
    }

    public static int getDuracaoAtivacaoSecadoresMax() {
        return duracaoAtivacaoSecadoresMax;
    }

    public static int getDuracaoFimProcesso() {
        return duracaoFimProcesso;
    }

}

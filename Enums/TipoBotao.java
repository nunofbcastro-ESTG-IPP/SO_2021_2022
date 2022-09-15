package Enums;

public enum TipoBotao {
    Iniciar, Cancelar, Emergencia, Reset, Aberto, Fechado, NaoSelecionado;

    public static String ToTipoBotaoString(TipoBotao value) {
        switch (value) {
            case Iniciar:
                return "Parado";
            case Cancelar:
                return "Cancelar";
            case Emergencia:
                return "Emergencia";
            case Reset:
                return "Reset";
            case Aberto:
                return "Aberto";
            case Fechado:
                return "Fechado";
            case NaoSelecionado:
                return "Não Selecionado";
            default:
                return null;
        }
    }
}

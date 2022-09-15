package Enums;

public enum EstadoLavagem {
    Parado, Ativo;

    public static String ToEstadoAtividadeString(EstadoLavagem value) {
        switch (value) {
            case Parado:
                return "Parado";
            case Ativo:
                return "Ativo";
            default:
                return null;
        }
    }
}

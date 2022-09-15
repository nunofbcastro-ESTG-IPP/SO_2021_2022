package Enums;

public enum EstadoAtividadeAspersoresSecador {
    Parado, EmAspiracao, EmSecagem;

    public static String ToEstadoAtividadeAspersoresSecadorString(EstadoAtividadeAspersoresSecador value) {
        switch (value) {
            case Parado:
                return "Parado";
            case EmAspiracao:
                return "Em aspiração";
            case EmSecagem:
                return "Em secagem";
            default:
                return null;
        }
    }
}

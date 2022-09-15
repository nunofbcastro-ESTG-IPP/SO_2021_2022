package Enums;

public enum Estado {
    Livre, Ocupado, Fechado;

    public static String ToEstadoString(Estado value) {
        switch (value) {
            case Livre:
                return "Livre";
            case Ocupado:
                return "Ocupado";
            case Fechado:
                return "Fechado";
            default:
                return null;
        }
    }
}

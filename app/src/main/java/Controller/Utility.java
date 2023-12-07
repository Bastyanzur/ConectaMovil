package Controller;

public class Utility {
    public static final String TABLA_USUARIO = "User";
    public static final String CAMPO_NOMBRE = "nombreContacto";
    public static final String CAMPO_EDAD = "edadContacto";
    public static final String CAMPO_CORREO = "correoContacto";
    public static final String CAMPO_NACIONALIDAD = "nacionalidadContacto";

    public static final String CREAR_TABLA_USUARIO = "CREATE TABLE " +
            TABLA_USUARIO + " (" +
            CAMPO_NOMBRE + " TEXT, " +
            CAMPO_EDAD + " TEXT, " +
            CAMPO_CORREO + " TEXT, " +
            CAMPO_NACIONALIDAD + " TEXT);";
}

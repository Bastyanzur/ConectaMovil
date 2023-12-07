package Model;

public class User {
    private String nombreContacto;
    private String correoContacto;
    private String edadContacto;
    private String nacionalidadContacto;

    public User(String nombreContacto, String correoContacto, String edadContacto, String nacionalidadContacto) {
        this.nombreContacto = nombreContacto;
        this.correoContacto = correoContacto;
        this.edadContacto = edadContacto;
        this.nacionalidadContacto = nacionalidadContacto;
    }

    public String getNombreContacto() {
        return nombreContacto;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreContacto = nombreContacto;
    }

    public String getCorreoContacto() {
        return correoContacto;
    }

    public void setCorreoContacto(String correoContacto) {
        this.correoContacto = correoContacto;
    }

    public String getEdadContacto() {
        return edadContacto;
    }

    public void setEdadContacto(String edadContacto) {
        this.edadContacto = edadContacto;
    }

    public String getNacionalidadContacto() {
        return nacionalidadContacto;
    }

    public void setNacionalidadContacto(String nacionalidadContacto) {
        this.nacionalidadContacto = nacionalidadContacto;
    }

    @Override
    public String toString() {
        return "User{" +
                "nombreContacto='" + nombreContacto + '\'' +
                ", correoContacto='" + correoContacto + '\'' +
                ", edadContacto='" + edadContacto + '\'' +
                ", nacionalidadContacto='" + nacionalidadContacto + '\'' +
                '}';
    }
}

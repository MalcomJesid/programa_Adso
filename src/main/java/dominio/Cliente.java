package dominio;

public class Cliente {

    private int idcliente;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private double saldo;

    public Cliente() {
    }

    public Cliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(String nombre, String apellido, String correo, String telefono, double saldo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public Cliente(int idcliente, String nombre, String apellido, String correo, String telefono, double saldo) {
        this.idcliente = idcliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.telefono = telefono;
        this.saldo = saldo;
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "cliente{" +
                "idcliente=" + idcliente +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono=" + telefono +
                ", saldo=" + saldo +
                '}';
    }
}

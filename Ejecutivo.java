// Integrantes: matias gutierrez y mauricio gonzalez

public class Ejecutivo extends Persona {
    private String usuario;
    private String password;

    public Ejecutivo() {
        super();
        usuario = "";
        password = "";
    }

    public String getUsuario() {
        return usuario;
    }

    public boolean setUsuario(String usuario) {
        if (usuario == null || usuario.length() != 10) {
            return false;
        }
        this.usuario = usuario;
        return true;
    }

    public String getPassword() {
        return password;
    }

    public boolean setPassword(String password) {
        if (password == null || password.length() != 10) {
            return false;
        }
        this.password = password;
        return true;
    }

    public String toString() {
        return super.toString() + ", Usuario: " + usuario;
    }
}
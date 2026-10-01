// Integrantes: matias gutierrez y mauricio gonzalez
import java.util.ArrayList;

public class Cliente extends Persona {
    private String password;
    private ArrayList<String> productos;

    public Cliente() {
        super();
        password = "";
        productos = new ArrayList<String>();
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

    public ArrayList<String> getProductos() {
        return productos;
    }

    public void setProductos(ArrayList<String> productos) {
        if (productos != null) {
            this.productos = productos;
        }
    }

    public boolean agregarProducto(String producto) {
        if (productos.contains(producto)) {
            return false;
        }
        productos.add(producto);
        return true;
    }

    public String toString() {
        return super.toString() + ", Productos: " + productos;
    }
}
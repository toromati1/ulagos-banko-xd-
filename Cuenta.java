// Integrantes: matias gutierrez y mauricio gonzalez
import java.util.ArrayList;

public class Cuenta {
    private Cliente titular;
    protected double saldo;
    private ArrayList<Double> movimientos;

    public Cuenta() {
        titular = null;
        saldo = 0;
        movimientos = new ArrayList<Double>();
    }

    // para crear una cuenta hace falta el titular, el saldo puede ser 0
    public Cuenta(Cliente titular, double saldo) {
        this.titular = titular;
        this.saldo = saldo;
        movimientos = new ArrayList<Double>();
        if (saldo > 0) {
            registrarMovimiento(saldo);
        }
    }

    public Cliente getTitular() {
        return titular;
    }

    public boolean setTitular(Cliente titular) {
        if (titular == null) {
            return false;
        }
        this.titular = titular;
        return true;
    }

    // el saldo no tiene setter, solo cambia con deposito y retiro
    public double getSaldo() {
        return saldo;
    }

    public ArrayList<Double> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(ArrayList<Double> movimientos) {
        if (movimientos != null) {
            this.movimientos = movimientos;
        }
    }

    public boolean deposito(double monto) {
        if (monto <= 0) {
            return false;
        }
        saldo = saldo + monto;
        registrarMovimiento(monto);
        return true;
    }

    public boolean retiro(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false;
        }
        saldo = saldo - monto;
        registrarMovimiento(-monto);
        return true;
    }

    // los depositos se guardan positivos y los retiros negativos
    public void registrarMovimiento(double monto) {
        movimientos.add(monto);
    }

    public String toString() {
        String nombreTitular = "sin titular";
        if (titular != null) {
            nombreTitular = titular.getNombre() + " (" + titular.getRut() + ")";
        }
        return "Cuenta de " + nombreTitular + ", Saldo: $" + saldo + ", Movimientos: " + movimientos;
    }
}
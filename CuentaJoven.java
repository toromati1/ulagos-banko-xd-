// Integrantes: matias gutierrez y mauricio gonzalez
public class CuentaJoven extends Cuenta {
    private double bonificacion;   // en porcentaje, por ejemplo 5 es un 5%

    public CuentaJoven(Cliente titular, double saldo, double bonificacion) {
        super(titular, saldo);
        this.bonificacion = bonificacion;
    }

    public double getBonificacion() {
        return bonificacion;
    }

    public boolean setBonificacion(double bonificacion) {
        if (bonificacion < 0 || bonificacion > 100) {
            return false;
        }
        this.bonificacion = bonificacion;
        return true;
    }

    // el titular tiene que tener entre 18 y 25 anios
    public boolean esTitularValido() {
        if (getTitular() == null) {
            return false;
        }
        int edad = getTitular().calcularEdad();
        return edad >= 18 && edad <= 25;
    }

    public double bonificar(double monto) {
        return monto + monto * bonificacion / 100;
    }

    // sobrescribe el deposito de Cuenta para sumar la bonificacion
    public boolean deposito(double monto) {
        if (monto <= 0) {
            return false;
        }
        return super.deposito(bonificar(monto));
    }

    public String toString() {
        return "Cuenta Joven de " + getTitular().getNombre() + " (" + getTitular().getRut() + ")"
                + ", Saldo: $" + getSaldo() + ", Bonificacion: " + bonificacion + "%"
                + ", Movimientos: " + getMovimientos();
    }
}
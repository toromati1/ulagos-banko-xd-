// Integrantes: matias gutierrez y mauricio gonzalez
import java.time.LocalDate;

public class Persona {
    private String nombre;
    private int dia;
    private int mes;
    private int anio;
    private String rut;

    public Persona() {
        nombre = "";
        dia = 0;
        mes = 0;
        anio = 0;
        rut = "";
    }

    public String getNombre() {
        return nombre;
    }

    public boolean setNombre(String nombre) {
        if (nombre == null) {
            return false;
        }
        nombre = nombre.trim();
        if (nombre.length() == 0) {
            return false;
        }
        for (int i = 0; i < nombre.length(); i++) {
            char c = nombre.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        this.nombre = nombre;
        return true;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public boolean setFechaNacimiento(int dia, int mes, int anio) {
        LocalDate hoy = LocalDate.now();
        if (mes < 1 || mes > 12) {
            return false;
        }
        if (dia < 1 || dia > 31) {
            return false;
        }
        if ((mes == 4 || mes == 6 || mes == 9 || mes == 11) && dia > 30) {
            return false;
        }
        if (mes == 2 && dia > 29) {
            return false;
        }
        if (anio < 1900 || anio > hoy.getYear()) {
            return false;
        }
        if (anio == hoy.getYear()) {
            if (mes > hoy.getMonthValue()) {
                return false;
            }
            if (mes == hoy.getMonthValue() && dia > hoy.getDayOfMonth()) {
                return false;
            }
        }
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
        return true;
    }

    public String getRut() {
        return rut;
    }

    // el rut se escribe asi: 12345678-5 (los puntos se ignoran)
    public boolean setRut(String rut) {
        if (rut == null) {
            return false;
        }
        rut = rut.replace(".", "").trim().toUpperCase();
        int largo = rut.length();
        if (largo < 9 || largo > 10) {
            return false;
        }
        if (rut.charAt(largo - 2) != '-') {
            return false;
        }
        String numero = rut.substring(0, largo - 2);
        for (int i = 0; i < numero.length(); i++) {
            if (!Character.isDigit(numero.charAt(i))) {
                return false;
            }
        }

        // algoritmo del digito verificador
        int suma = 0;
        int multiplicador = 2;
        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = numero.charAt(i) - '0';
            suma = suma + digito * multiplicador;
            multiplicador++;
            if (multiplicador > 7) {
                multiplicador = 2;
            }
        }
        int resto = 11 - (suma % 11);
        char esperado;
        if (resto == 11) {
            esperado = '0';
        } else if (resto == 10) {
            esperado = 'K';
        } else {
            esperado = (char) (resto + '0');
        }

        if (rut.charAt(largo - 1) != esperado) {
            return false;
        }
        this.rut = rut;
        return true;
    }

    public int calcularEdad() {
        if (anio == 0) {
            return 0;
        }
        LocalDate hoy = LocalDate.now();
        int edad = hoy.getYear() - anio;
        if (hoy.getMonthValue() < mes || (hoy.getMonthValue() == mes && hoy.getDayOfMonth() < dia)) {
            edad--;
        }
        return edad;
    }

    public boolean esMayorDeEdad() {
        return calcularEdad() >= 18;
    }

    public String toString() {
        return "Nombre: " + nombre + ", RUT: " + rut + ", Fecha de nacimiento: " + dia + "/" + mes + "/" + anio
                + ", Edad: " + calcularEdad();
    }
}
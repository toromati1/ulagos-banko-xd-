// Integrantes: matias gutierrez y mauricio gonzalez
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    static ArrayList<Ejecutivo> ejecutivos = new ArrayList<Ejecutivo>();
    static ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

    public static void main(String[] args) {
        sc.useLocale(Locale.US);   // los decimales se escriben con punto

        // ejecutivo para poder entrar la primera vez
        Ejecutivo admin = new Ejecutivo();
        admin.setNombre("Admin");
        admin.setFechaNacimiento(1, 1, 1990);
        admin.setRut("11111111-1");
        admin.setUsuario("admin12345");
        admin.setPassword("clave12345");
        ejecutivos.add(admin);

        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("Bienvenido a ULagosBank");
            System.out.println("1. Entrar como ejecutivo");
            System.out.println("2. Entrar como cliente");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                loginEjecutivo();
            } else if (opcion == 2) {
                loginCliente();
            } else if (opcion != 0) {
                System.out.println("Opcion no valida");
            }
        }
        System.out.println("Chao");
    }

    static int leerEntero() {
        int n = sc.nextInt();
        sc.nextLine();
        return n;
    }

    static double leerDecimal() {
        double n = sc.nextDouble();
        sc.nextLine();
        return n;
    }

    static void loginEjecutivo() {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Password: ");
        String password = sc.nextLine();

        for (int i = 0; i < ejecutivos.size(); i++) {
            Ejecutivo e = ejecutivos.get(i);
            if (e.getUsuario().equals(usuario) && e.getPassword().equals(password)) {
                menuEjecutivo(e);
                return;
            }
        }
        System.out.println("Usuario o password incorrectos");
    }

    static void loginCliente() {
        System.out.print("RUT: ");
        String rut = sc.nextLine().replace(".", "").trim().toUpperCase();
        System.out.print("Password: ");
        String password = sc.nextLine();

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getRut().equals(rut) && c.getPassword().equals(password)) {
                menuCliente(c);
                return;
            }
        }
        System.out.println("RUT o password incorrectos");
    }

    static void menuEjecutivo(Ejecutivo ejecutivo) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("Menu ejecutivo: " + ejecutivo.getNombre());
            System.out.println("1. Crear cliente");
            System.out.println("2. Crear ejecutivo");
            System.out.println("3. Crear cuenta");
            System.out.println("4. Crear cuenta joven");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                crearCliente();
            } else if (opcion == 2) {
                crearEjecutivo();
            } else if (opcion == 3) {
                crearCuenta();
            } else if (opcion == 4) {
                crearCuentaJoven();
            } else if (opcion != 0) {
                System.out.println("Opcion no valida");
            }
        }
    }

    // pide nombre, fecha y rut, sirve para cliente y ejecutivo
    static void pedirDatosPersona(Persona p) {
        System.out.print("Nombre: ");
        while (!p.setNombre(sc.nextLine())) {
            System.out.print("Nombre no valido, intenta de nuevo: ");
        }

        boolean fechaOk = false;
        while (!fechaOk) {
            System.out.print("Dia de nacimiento: ");
            int dia = leerEntero();
            System.out.print("Mes de nacimiento: ");
            int mes = leerEntero();
            System.out.print("Anio de nacimiento: ");
            int anio = leerEntero();
            fechaOk = p.setFechaNacimiento(dia, mes, anio);
            if (!fechaOk) {
                System.out.println("Fecha no valida, intenta de nuevo");
            }
        }

        System.out.print("RUT (ej: 12345678-5): ");
        while (!p.setRut(sc.nextLine())) {
            System.out.print("RUT no valido, intenta de nuevo: ");
        }
    }

    static void crearCliente() {
        Cliente c = new Cliente();
        pedirDatosPersona(c);
        System.out.print("Password (10 caracteres): ");
        while (!c.setPassword(sc.nextLine())) {
            System.out.print("Tiene que tener 10 caracteres, intenta de nuevo: ");
        }
        clientes.add(c);
        System.out.println("Cliente creado: " + c);
    }

    static void crearEjecutivo() {
        Ejecutivo e = new Ejecutivo();
        pedirDatosPersona(e);
        System.out.print("Usuario (10 caracteres): ");
        while (!e.setUsuario(sc.nextLine())) {
            System.out.print("Tiene que tener 10 caracteres, intenta de nuevo: ");
        }
        System.out.print("Password (10 caracteres): ");
        while (!e.setPassword(sc.nextLine())) {
            System.out.print("Tiene que tener 10 caracteres, intenta de nuevo: ");
        }
        ejecutivos.add(e);
        System.out.println("Ejecutivo creado: " + e);
    }

    static Cliente buscarCliente() {
        System.out.print("RUT del titular: ");
        String rut = sc.nextLine().replace(".", "").trim().toUpperCase();
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getRut().equals(rut)) {
                return clientes.get(i);
            }
        }
        System.out.println("No existe un cliente con ese RUT, primero hay que crearlo");
        return null;
    }

    static void crearCuenta() {
        Cliente titular = buscarCliente();
        if (titular == null) {
            return;
        }
        System.out.print("Saldo inicial (0 si va vacia): ");
        double saldo = leerDecimal();
        if (saldo < 0) {
            System.out.println("El saldo no puede ser negativo");
            return;
        }
        Cuenta cuenta = new Cuenta(titular, saldo);
        cuentas.add(cuenta);
        titular.agregarProducto("Cuenta");
        System.out.println("Cuenta creada: " + cuenta);
    }

    static void crearCuentaJoven() {
        Cliente titular = buscarCliente();
        if (titular == null) {
            return;
        }
        System.out.print("Saldo inicial (0 si va vacia): ");
        double saldo = leerDecimal();
        System.out.print("Bonificacion en porcentaje: ");
        double bonificacion = leerDecimal();
        if (saldo < 0 || bonificacion < 0 || bonificacion > 100) {
            System.out.println("Saldo o bonificacion no validos");
            return;
        }
        CuentaJoven cuenta = new CuentaJoven(titular, saldo, bonificacion);
        if (!cuenta.esTitularValido()) {
            System.out.println("El titular tiene que tener entre 18 y 25 anios, no se puede crear la cuenta");
            return;
        }
        cuentas.add(cuenta);
        titular.agregarProducto("Cuenta Joven");
        System.out.println("Cuenta joven creada: " + cuenta);
    }

    static void menuCliente(Cliente cliente) {
        int opcion = -1;
        while (opcion != 0) {
            System.out.println();
            System.out.println("Menu cliente: " + cliente.getNombre());
            System.out.println("1. Seleccionar producto bancario");
            System.out.println("2. Realizar deposito");
            System.out.println("3. Realizar retiro");
            System.out.println("0. Cerrar sesion");
            System.out.print("Opcion: ");
            opcion = leerEntero();

            if (opcion == 1) {
                seleccionarProducto(cliente);
            } else if (opcion == 2) {
                operar(cliente, true);
            } else if (opcion == 3) {
                operar(cliente, false);
            } else if (opcion != 0) {
                System.out.println("Opcion no valida");
            }
        }
    }

    static void seleccionarProducto(Cliente cliente) {
        String[] productos = {"Cuenta", "Cuenta Joven", "Tarjeta de Credito", "Prestamo"};
        System.out.println("Productos disponibles:");
        for (int i = 0; i < productos.length; i++) {
            System.out.println((i + 1) + ". " + productos[i]);
        }
        System.out.print("Elige uno: ");
        int opcion = leerEntero();
        if (opcion < 1 || opcion > productos.length) {
            System.out.println("Opcion no valida");
            return;
        }
        if (cliente.agregarProducto(productos[opcion - 1])) {
            System.out.println("Producto agregado, tus productos: " + cliente.getProductos());
        } else {
            System.out.println("Ya tienes ese producto");
        }
    }

    // si esDeposito es true hace un deposito, si no un retiro
    static void operar(Cliente cliente, boolean esDeposito) {
        ArrayList<Cuenta> propias = new ArrayList<Cuenta>();
        for (int i = 0; i < cuentas.size(); i++) {
            if (cuentas.get(i).getTitular() == cliente) {
                propias.add(cuentas.get(i));
            }
        }
        if (propias.size() == 0) {
            System.out.println("No tienes cuentas, pidele a un ejecutivo que te cree una");
            return;
        }

        for (int i = 0; i < propias.size(); i++) {
            System.out.println((i + 1) + ". " + propias.get(i));
        }
        System.out.print("Elige una cuenta: ");
        int opcion = leerEntero();
        if (opcion < 1 || opcion > propias.size()) {
            System.out.println("Opcion no valida");
            return;
        }
        Cuenta cuenta = propias.get(opcion - 1);

        System.out.print("Monto: ");
        double monto = leerDecimal();
        boolean resultado;
        if (esDeposito) {
            resultado = cuenta.deposito(monto);
        } else {
            resultado = cuenta.retiro(monto);
        }

        if (resultado) {
            System.out.println("Operacion realizada: " + cuenta);
        } else if (esDeposito) {
            System.out.println("El deposito tiene que ser mayor a 0");
        } else {
            System.out.println("Monto no valido o saldo insuficiente");
        }
    }
}
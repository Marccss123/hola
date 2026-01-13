package proyecto2;

import java.time.LocalDate;
import java.util.*;

public class MainProducto {

    private static double presupuestoDisponible = 5000.00;
    private static int espacioAlmacenDisponible = 100;

    public static void main(String[] args) {
        Map<String, Producto> inventario = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        int opc;


        do {
            try {
                System.out.println("\n=== SISTEMA PIXELZONE MANAGER ===");
                System.out.printf("Capital: $%.2f | Espacio Disponible: %d unidades%n", presupuestoDisponible, espacioAlmacenDisponible);
                System.out.println("1. Agregar Nuevo Producto");
                System.out.println("2. Consultar Informacion");
                System.out.println("3. Procesar Venta");
                System.out.println("4. Reporte de Inventario");
                System.out.println("5. Salir");
                System.out.print("Ingrese una opcion: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Error: Ingrese un numero valido.");
                    sc.next();
                }
                opc = sc.nextInt();
                sc.nextLine();

                switch (opc) {
                    case 1:
                        System.out.println("\n--- REGISTRO DE PRODUCTO ---");

                        int tipo;
                        do {
                            System.out.println("1. Producto Fisico");
                            System.out.println("2. Producto Digital");
                            System.out.print("Seleccione tipo: ");
                            while (!sc.hasNextInt()) {
                                System.out.println("Ingrese 1 o 2.");
                                sc.next();
                            }
                            tipo = sc.nextInt();
                            sc.nextLine();
                            if (tipo != 1 && tipo != 2) System.out.println("Opcion incorrecta.");
                        } while (tipo != 1 && tipo != 2);

                        String sku;
                        do {
                            System.out.print("Ingrese SKU: ");
                            sku = sc.nextLine().trim();
                            if (sku.isEmpty()) System.out.println("El SKU no puede estar vacio.");
                            if (inventario.containsKey(sku)) {
                                System.out.println("Error: El SKU ya existe.");
                                sku = "";
                            }
                        } while (sku.isEmpty());

                        String nom;
                        do {
                            System.out.print("Nombre: ");
                            nom = sc.nextLine().trim();
                        } while (nom.isEmpty());

                        String plat;
                        boolean platValida = false;
                        do {
                            System.out.print("Plataforma (PC, Xbox, PS5, Switch): ");
                            plat = sc.nextLine().trim();
                            if (plat.equalsIgnoreCase("PC") || plat.equalsIgnoreCase("Xbox") ||
                                    plat.equalsIgnoreCase("PS5") || plat.equalsIgnoreCase("Switch")) {
                                platValida = true;
                            } else {
                                System.out.println("Plataforma no reconocida (PC, Xbox, PS5, Switch).");
                            }
                        } while (!platValida);

                        double costo = -1;
                        do {
                            System.out.print("Precio de Compra: ");
                            while (!sc.hasNextDouble()) {
                                System.out.println("Ingrese un numero valido.");
                                sc.next();
                            }
                            costo = sc.nextDouble();
                            if (costo <= 0) System.out.println("El costo debe ser positivo.");
                        } while (costo <= 0);

                        double venta = -1;
                        do {
                            System.out.print("Precio de Venta: ");
                            while (!sc.hasNextDouble()) {
                                System.out.println("Ingrese un numero valido.");
                                sc.next();
                            }
                            venta = sc.nextDouble();
                            if (venta <= 0) System.out.println("El precio debe ser positivo.");
                        } while (venta <= 0);
                        sc.nextLine();

                        if (tipo == 1) {
                            int stock = -1;
                            do {
                                System.out.print("Stock inicial: ");
                                while (!sc.hasNextInt()) {
                                    System.out.println("Ingrese un entero.");
                                    sc.next();
                                }
                                stock = sc.nextInt();
                                if (stock <= 0) System.out.println("Stock debe ser mayor a 0.");
                            } while (stock <= 0);

                            double costoTotal = costo * stock;
                            if (costoTotal > presupuestoDisponible) {
                                System.out.println("Error: Presupuesto insuficiente.");
                                System.out.printf("Requerido: $%.2f | Disponible: $%.2f%n", costoTotal, presupuestoDisponible);
                                break;
                            }
                            if (stock > espacioAlmacenDisponible) {
                                System.out.println("Error: Almacen lleno.");
                                System.out.printf("Intenta ingresar: %d | Disponible: %d%n", stock, espacioAlmacenDisponible);
                                break;
                            }

                            double peso = -1;
                            do {
                                System.out.print("Peso en Kg: ");
                                while (!sc.hasNextDouble()) { sc.next(); }
                                peso = sc.nextDouble();
                            } while (peso <= 0);
                            sc.nextLine();

                            System.out.print("Dimensiones: ");
                            String dim = sc.nextLine();

                            ProductoFisico pf = new ProductoFisico(sku, nom, plat, costo, venta, peso, dim, stock);
                            inventario.put(sku, pf);

                            presupuestoDisponible -= costoTotal;
                            espacioAlmacenDisponible -= stock;
                            System.out.println("Producto fisico registrado correctamente.");

                        } else {
                            System.out.print("Tamano archivo (GB): ");
                            double gb = sc.nextDouble();
                            sc.nextLine();
                            System.out.print("URL de descarga: ");
                            String url = sc.nextLine();

                            if (costo > presupuestoDisponible) {
                                System.out.println("Error: Presupuesto insuficiente.");
                                break;
                            }

                            ProductoDigital pd = new ProductoDigital(sku, nom, plat, costo, venta, gb, url);

                            System.out.print("Ingrese la primera Clave (Key): ");
                            String key = sc.nextLine();
                            pd.agregarClave(key);

                            inventario.put(sku, pd);
                            presupuestoDisponible -= costo;
                            System.out.println("Producto digital registrado correctamente.");
                        }
                        break;

                    case 2:
                        if (inventario.isEmpty()) {
                            System.out.println("Inventario vacio.");
                        } else {
                            System.out.print("Ingrese SKU a buscar: ");
                            String b = sc.nextLine();
                            if (inventario.containsKey(b)) {
                                System.out.println(inventario.get(b));
                            } else {
                                System.out.println("Producto no encontrado.");
                            }
                        }
                        break;

                    case 3:
                        if (inventario.isEmpty()) {
                            System.out.println("No hay productos para vender.");
                        } else {
                            System.out.print("Ingrese SKU a vender: ");
                            String vSku = sc.nextLine();

                            if (inventario.containsKey(vSku)) {
                                Producto p = inventario.get(vSku);

                                System.out.println("\n--- DATOS DE VENTA ---");
                                System.out.println("Fecha: " + LocalDate.now());
                                System.out.print("Nombre Cliente: ");
                                String cli = sc.nextLine();

                                String mail;
                                do {
                                    System.out.print("Correo Electronico: ");
                                    mail = sc.nextLine();
                                    if(mail.isEmpty()) System.out.println("El correo es obligatorio.");
                                } while (mail.isEmpty());

                                System.out.println("--------------------------------");
                                System.out.println("Cliente: " + cli + " (" + mail + ")");
                                System.out.println("Producto: " + p.getNombre());

                                p.procesarEntrega();

                                presupuestoDisponible += p.getPrecioVenta();
                                if (p instanceof ProductoFisico) {
                                    espacioAlmacenDisponible++;
                                }

                            } else {
                                System.out.println("Producto no encontrado.");
                            }
                        }
                        break;

                    case 4:
                        if (inventario.isEmpty()) {
                            System.out.println("Inventario vacio.");
                        } else {
                            System.out.println("\n--- REPORTE DE INVENTARIO ---");
                            for (Producto p : inventario.values()) {
                                System.out.println(p.toString());
                                System.out.println("-----------------------------");
                            }
                        }
                        break;

                    case 5:
                        System.out.println("Saliendo del sistema...");
                        break;

                    default:
                        System.out.println("Opcion incorrecta.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error critico de entrada.");
                sc.nextLine();
                opc = 0;
            }
        } while (opc != 5);
    }
}
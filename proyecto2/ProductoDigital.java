package proyecto2;

import java.util.ArrayList;
import java.util.List;

public class ProductoDigital extends Producto {

    // --- CONSTANTES ---
    private static final int STOCK_MINIMO_KEYS = 5;

    // Atributos exclusivos Digitales
    private double tamanoGB;
    private String urlDescarga;
    private List<String> clavesDisponibles;

    public ProductoDigital(String sku, String nombre, String plataforma,
                           double precioCompra, double precioVenta,
                           double tamanoGB, String urlDescarga) {

        super(sku, nombre, plataforma, precioCompra, precioVenta);
        this.tamanoGB = tamanoGB;
        this.urlDescarga = urlDescarga;
        // Inicializamos la lista vacía para ir agregando claves después
        this.clavesDisponibles = new ArrayList<>();
    }

    public void agregarClave(String clave) {
        clavesDisponibles.add(clave);
    }

    public boolean necesitaReabastecimiento() {
        // El stock aquí es el tamaño de la lista
        return clavesDisponibles.size() <= STOCK_MINIMO_KEYS;
    }

    @Override
    public void procesarEntrega() {
        if (!clavesDisponibles.isEmpty()) {
            // Lógica FIFO: Sacamos la primera clave de la lista y la eliminamos
            String claveEntregada = clavesDisponibles.remove(0);

            System.out.println("--- ENTREGA DIGITAL AUTOMÁTICA ---");
            System.out.printf("Producto: %s (%.1f GB)%n", nombre, tamanoGB);
            System.out.println("URL Oficial: " + urlDescarga);
            System.out.println(">> CLAVE DE ACTIVACIÓN: " + claveEntregada);
            System.out.println("Estado: ENVIADO AL CORREO DEL CLIENTE (Costo Envío: $0.00)");

            if (necesitaReabastecimiento()) {
                System.out.println(">>> ALERTA: Quedan pocas claves disponibles (" + clavesDisponibles.size() + ").");
            }
        } else {
            System.out.println("ERROR CRÍTICO: Venta cancelada. No quedan claves digitales en el servidor.");
        }
    }

    public double getTamanoGB() { return tamanoGB; }
    public void setTamanoGB(double tamanoGB) { this.tamanoGB = tamanoGB; }

    public String getUrlDescarga() { return urlDescarga; }
    public void setUrlDescarga(String urlDescarga) { this.urlDescarga = urlDescarga; }

    public List<String> getClavesDisponibles() { return clavesDisponibles; }
    // No hacemos setter de la lista completa por seguridad, solo agregamos una por una

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Digital] Peso: %.1f GB - Keys Disp: %d", tamanoGB, clavesDisponibles.size());
    }
}
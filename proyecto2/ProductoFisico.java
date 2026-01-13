package proyecto2;

public class ProductoFisico extends Producto {

    private static final double TARIFA_ENVIO_POR_KG = 2.50;
    private static final int STOCK_MINIMO_ALERTA = 5;

    private double peso;
    private String dimensiones;
    private int stockBodega;
    private int puntoReorden;

    public ProductoFisico(String sku, String nombre, String plataforma,
                          double precioCompra, double precioVenta,
                          double peso, String dimensiones, int stockBodega) {

        super(sku, nombre, plataforma, precioCompra, precioVenta);
        this.peso = peso;
        this.dimensiones = dimensiones;
        this.stockBodega = stockBodega;
        this.puntoReorden = STOCK_MINIMO_ALERTA;
    }

    public boolean necesitaReabastecimiento() {
        return stockBodega <= puntoReorden;
    }

    @Override
    public void procesarEntrega() {
        if (stockBodega > 0) {
            stockBodega--;
            double costoEnvio = peso * TARIFA_ENVIO_POR_KG;

            System.out.println("--- GUÍA DE DESPACHO ---");
            System.out.printf("Producto: %s (Dimensiones: %s)%n", nombre, dimensiones);
            System.out.printf("Peso: %.2f kg | Tarifa: $%.2f/kg | Total Flete: $%.2f%n",
                    peso, TARIFA_ENVIO_POR_KG, costoEnvio);
            System.out.println("Estado: ENVIADO A PAQUETERÍA");

            if (necesitaReabastecimiento()) {
                System.out.println(">>> ALERTA: Stock bajo (" + stockBodega + " unid).");
            }
        } else {
            System.out.println("ERROR: No hay stock en bodega.");
        }
    }

    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }

    public String getDimensiones() { return dimensiones; }
    public void setDimensiones(String dimensiones) { this.dimensiones = dimensiones; }

    public int getStockBodega() { return stockBodega; }
    public void setStockBodega(int stockBodega) { this.stockBodega = stockBodega; }

    public int getPuntoReorden() { return puntoReorden; }
    public void setPuntoReorden(int puntoReorden) { this.puntoReorden = puntoReorden; }

    @Override
    public String toString() {
        return super.toString() + String.format(" | [Físico] Stock: %d - Peso: %.2fkg", stockBodega, peso);
    }
}
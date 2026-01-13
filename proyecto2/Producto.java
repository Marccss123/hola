package proyecto2;

import java.time.LocalDate;

public abstract class Producto {

    protected static final int DIAS_ROTACION_ESTANDAR = 15;

    protected String sku;
    protected String nombre;
    protected String plataforma;
    protected double precioCompra;
    protected double precioVenta;
    protected LocalDate fechaUltimaCompra;
    protected LocalDate fechaReposicion;

    public Producto(String sku, String nombre, String plataforma, double precioCompra, double precioVenta) {
        this.sku = sku;
        this.nombre = nombre;
        this.plataforma = plataforma;
        this.precioCompra = precioCompra;
        this.precioVenta = precioVenta;
        this.fechaUltimaCompra = LocalDate.now();
        this.fechaReposicion = LocalDate.now().plusDays(DIAS_ROTACION_ESTANDAR);
    }

    public abstract void procesarEntrega();

    public double calcularGanancia() {
        return precioVenta - precioCompra;
    }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(double precioCompra) { this.precioCompra = precioCompra; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public LocalDate getFechaUltimaCompra() { return fechaUltimaCompra; }

    public LocalDate getFechaReposicion() { return fechaReposicion; }
    public void setFechaReposicion(LocalDate fechaReposicion) { this.fechaReposicion = fechaReposicion; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s ($%.2f) - Margen: $%.2f",
                sku, nombre, plataforma, precioVenta, calcularGanancia());
    }
}
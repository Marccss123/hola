package ejercalcul;

import java.time.LocalDate;
import java.time.Period;

public class FrecuenciasCardiacas {
    private String nombre;
    private String apellido;
    private int diaNacimiento;
    private int mesNacimiento;
    private int anioNacimiento;

    public FrecuenciasCardiacas(String nombre, String apellido, int dia, int mes, int anio) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.diaNacimiento = dia;
        this.mesNacimiento = mes;
        this.anioNacimiento = anio;
    }

    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public int getDiaNacimiento() { return diaNacimiento; }
    public int getMesNacimiento() { return mesNacimiento; }
    public int getAnioNacimiento() { return anioNacimiento; }

    public int calcularEdad() {
        LocalDate nacimiento = LocalDate.of(anioNacimiento, mesNacimiento, diaNacimiento);
        LocalDate hoy = LocalDate.now();
        return Period.between(nacimiento, hoy).getYears();
    }

    public int frecuenciaMaxima() {
        return 220 - calcularEdad();
    }

    public double frecuenciaEsperadaMin() {
        return frecuenciaMaxima() * 0.50;
    }

    public double frecuenciaEsperadaMax() {
        return frecuenciaMaxima() * 0.85;
    }

    public void mostrarInformacion() {
        System.out.println("Nombre: " + nombre + " " + apellido);
        System.out.println("Fecha de nacimiento: " + diaNacimiento + "/" + mesNacimiento + "/" + anioNacimiento);
        System.out.println("Edad: " + calcularEdad() + " años");
        System.out.println("Frecuencia cardíaca máxima: " + frecuenciaMaxima() + " pulsos/min");
        System.out.printf("Rango esperado: %.1f - %.1f pulsos/min%n", frecuenciaEsperadaMin(), frecuenciaEsperadaMax());
    }
}
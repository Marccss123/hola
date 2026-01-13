package ejercalcul;

public class MainFrecuencias {
    public static void main(String[] args) {
        // Valores fijos (simulan los datos del estudiante)
        FrecuenciasCardiacas persona = new FrecuenciasCardiacas("Juan", "Pérez", 15, 6, 2000);

        // Mostrar resultados
        persona.mostrarInformacion();
    }
}

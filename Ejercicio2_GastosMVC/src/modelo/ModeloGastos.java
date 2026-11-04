package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;

public class ModeloGastos {
    private ArrayList<Gasto> gastos = new ArrayList<>();
    private Path archivo = Path.of("datos", "gastos.txt");

    public ModeloGastos() throws IOException {
        Files.createDirectories(archivo.getParent());
        if (!Files.exists(archivo)) Files.createFile(archivo);
        cargar();
    }

    public void registrar(String concepto, String categoria, double valor) throws IOException {
        concepto = concepto.trim(); categoria = categoria.trim();
        if (concepto.isEmpty() || categoria.isEmpty()) throw new IllegalArgumentException("Complete el concepto y la categoría.");
        if (valor <= 0 || Double.isNaN(valor) || Double.isInfinite(valor)) throw new IllegalArgumentException("El valor debe ser mayor que cero.");
        gastos.add(new Gasto(LocalDate.now().toString(), concepto, categoria, valor));
        guardar();
    }

    public ArrayList<Gasto> listar() { return new ArrayList<>(gastos); }
    public double total() { double suma = 0; for (Gasto g : gastos) suma += g.getValor(); return suma; }

    private void cargar() throws IOException {
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
            String[] d = linea.split(";", -1);
            if (d.length == 4) {
                try { gastos.add(new Gasto(d[0], d[1], d[2], Double.parseDouble(d[3]))); }
                catch (NumberFormatException e) { /* Ignora una fila incompleta o inválida. */ }
            }
        }
    }

    private void guardar() throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Gasto g : gastos) lineas.add(g.getFecha()+";"+g.getConcepto()+";"+g.getCategoria()+";"+g.getValor());
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }
}

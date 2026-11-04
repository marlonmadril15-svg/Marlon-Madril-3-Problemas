package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class ModeloPlantas {
    private ArrayList<Planta> plantas = new ArrayList<>();
    private Path archivo = Path.of("datos", "plantas.txt");

    public ModeloPlantas() throws IOException {
        Files.createDirectories(archivo.getParent());
        if (!Files.exists(archivo)) Files.createFile(archivo);
        cargar();
    }

    public void registrar(String codigo, String nombre, String tipo, int diasRiego) throws IOException {
        codigo = codigo.trim(); nombre = nombre.trim(); tipo = tipo.trim();
        if (codigo.isEmpty() || nombre.isEmpty() || tipo.isEmpty()) throw new IllegalArgumentException("Complete todos los campos.");
        if (diasRiego <= 0) throw new IllegalArgumentException("Los días entre riegos deben ser mayores que cero.");
        for (Planta p : plantas) if (p.getCodigo().equalsIgnoreCase(codigo)) throw new IllegalArgumentException("Ese código ya está registrado.");
        plantas.add(new Planta(codigo, nombre, tipo, diasRiego));
        guardar();
    }

    public ArrayList<Planta> listar() { return new ArrayList<>(plantas); }

    private void cargar() throws IOException {
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
            String[] d = linea.split(";", -1);
            if (d.length == 4) {
                try { plantas.add(new Planta(d[0], d[1], d[2], Integer.parseInt(d[3]))); }
                catch (NumberFormatException e) { /* Ignora una fila incompleta o inválida. */ }
            }
        }
    }

    private void guardar() throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Planta p : plantas) lineas.add(p.getCodigo()+";"+p.getNombre()+";"+p.getTipo()+";"+p.getDiasRiego());
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }
}

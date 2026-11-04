package modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class ModeloReservas {
    private ArrayList<Reserva> reservas = new ArrayList<>();
    private Path archivo = Path.of("datos", "reservas.txt");

    public ModeloReservas() throws IOException {
        Files.createDirectories(archivo.getParent());
        if (!Files.exists(archivo)) Files.createFile(archivo);
        cargar();
    }

    public void registrar(String cliente, String deporte, String fechaTexto, String horaTexto, int cancha) throws IOException {
        cliente = cliente.trim(); deporte = deporte.trim();
        if (cliente.isEmpty() || deporte.isEmpty()) throw new IllegalArgumentException("Complete el nombre y el deporte.");
        if (cancha < 1 || cancha > 3) throw new IllegalArgumentException("Seleccione una cancha entre 1 y 3.");
        LocalDate fecha; LocalTime hora;
        try { fecha = LocalDate.parse(fechaTexto.trim()); }
        catch (DateTimeParseException e) { throw new IllegalArgumentException("La fecha debe tener el formato AAAA-MM-DD."); }
        try { hora = LocalTime.parse(horaTexto.trim()); }
        catch (DateTimeParseException e) { throw new IllegalArgumentException("La hora debe tener el formato HH:MM."); }
        String fechaValida = fecha.toString(); String horaValida = hora.format(DateTimeFormatter.ofPattern("HH:mm"));
        for (Reserva r : reservas) {
            if (r.getCancha() == cancha && r.getFecha().equals(fechaValida) && r.getHora().equals(horaValida))
                throw new IllegalArgumentException("Esa cancha ya está reservada para esa fecha y hora.");
        }
        reservas.add(new Reserva(cliente, deporte, fechaValida, horaValida, cancha)); guardar();
    }

    public ArrayList<Reserva> listar() { return new ArrayList<>(reservas); }
    private void cargar() throws IOException {
        for (String linea : Files.readAllLines(archivo, StandardCharsets.UTF_8)) {
            String[] d = linea.split(";", -1);
            if (d.length == 5) {
                try { reservas.add(new Reserva(d[0], d[1], LocalDate.parse(d[2]).toString(), LocalTime.parse(d[3]).format(DateTimeFormatter.ofPattern("HH:mm")), Integer.parseInt(d[4]))); }
                catch (RuntimeException e) { /* Ignora una fila incompleta o inválida. */ }
            }
        }
    }
    private void guardar() throws IOException {
        ArrayList<String> lineas = new ArrayList<>();
        for (Reserva r : reservas) lineas.add(r.getCliente()+";"+r.getDeporte()+";"+r.getFecha()+";"+r.getHora()+";"+r.getCancha());
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }
}

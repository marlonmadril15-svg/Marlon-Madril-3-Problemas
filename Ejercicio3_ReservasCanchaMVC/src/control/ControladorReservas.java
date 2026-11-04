package control;

import java.io.IOException;
import modelo.ModeloReservas;
import vista.VentanaReservas;

public class ControladorReservas {
    private ModeloReservas modelo; private VentanaReservas vista;
    public ControladorReservas(ModeloReservas modelo, VentanaReservas vista) {
        this.modelo = modelo; this.vista = vista; vista.setControlador(this); vista.actualizar(modelo.listar());
    }
    public void iniciar() { vista.setVisible(true); }
    public void registrar(String cliente, String deporte, String fecha, String hora, String canchaTexto) {
        try {
            int cancha = Integer.parseInt(canchaTexto); modelo.registrar(cliente, deporte, fecha, hora, cancha);
            vista.actualizar(modelo.listar()); vista.limpiar(); vista.mensaje("Reserva guardada.");
        } catch (NumberFormatException e) { vista.mensaje("Seleccione una cancha válida."); }
          catch (IllegalArgumentException | IOException e) { vista.mensaje(e.getMessage()); }
    }
}

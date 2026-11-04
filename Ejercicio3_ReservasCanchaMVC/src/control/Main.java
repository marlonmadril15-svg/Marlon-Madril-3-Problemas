package control;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.ModeloReservas;
import vista.VentanaReservas;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ModeloReservas modelo = new ModeloReservas();
                VentanaReservas vista = new VentanaReservas();
                new ControladorReservas(modelo, vista).iniciar();
            } catch (Exception e) { JOptionPane.showMessageDialog(null, "No se pudieron cargar las reservas: " + e.getMessage()); }
        });
    }
}

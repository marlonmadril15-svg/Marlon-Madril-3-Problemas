package control;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.ModeloGastos;
import vista.VentanaGastos;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ModeloGastos modelo = new ModeloGastos();
                VentanaGastos vista = new VentanaGastos();
                new ControladorGastos(modelo, vista).iniciar();
            } catch (Exception e) { JOptionPane.showMessageDialog(null, "No se pudieron cargar los gastos: " + e.getMessage()); }
        });
    }
}

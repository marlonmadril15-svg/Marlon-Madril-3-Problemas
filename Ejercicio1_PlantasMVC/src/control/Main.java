package control;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import modelo.ModeloPlantas;
import vista.VentanaPlantas;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ModeloPlantas modelo = new ModeloPlantas();
                VentanaPlantas vista = new VentanaPlantas();
                new ControladorPlantas(modelo, vista).iniciar();
            } catch (Exception e) { JOptionPane.showMessageDialog(null, "No se pudieron cargar las plantas: " + e.getMessage()); }
        });
    }
}

package control;

import java.io.IOException;
import java.util.ArrayList;
import modelo.ModeloPlantas;
import modelo.Planta;
import vista.VentanaPlantas;

public class ControladorPlantas {
    private ModeloPlantas modelo;
    private VentanaPlantas vista;

    public ControladorPlantas(ModeloPlantas modelo, VentanaPlantas vista) {
        this.modelo = modelo; this.vista = vista;
        vista.setControlador(this);
        vista.actualizar(modelo.listar());
    }

    public void iniciar() { vista.setVisible(true); }

    public void registrar(String codigo, String nombre, String tipo, String diasTexto) {
        try {
            int dias = Integer.parseInt(diasTexto.trim());
            modelo.registrar(codigo, nombre, tipo, dias);
            vista.actualizar(modelo.listar()); vista.limpiar(); vista.mensaje("Planta registrada.");
        } catch (NumberFormatException e) { vista.mensaje("Escriba un número entero para los días."); }
          catch (IllegalArgumentException | IOException e) { vista.mensaje(e.getMessage()); }
    }
}

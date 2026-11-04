package control;

import java.io.IOException;
import modelo.Gasto;
import modelo.ModeloGastos;
import vista.VentanaGastos;

public class ControladorGastos {
    private ModeloGastos modelo; private VentanaGastos vista;
    public ControladorGastos(ModeloGastos modelo, VentanaGastos vista) {
        this.modelo = modelo; this.vista = vista; vista.setControlador(this); vista.actualizar(modelo.listar(), modelo.total());
    }
    public void iniciar() { vista.setVisible(true); }
    public void registrar(String concepto, String categoria, String valorTexto) {
        try {
            double valor = Double.parseDouble(valorTexto.trim().replace(',', '.'));
            modelo.registrar(concepto, categoria, valor);
            vista.actualizar(modelo.listar(), modelo.total()); vista.limpiar(); vista.mensaje("Gasto registrado.");
        } catch (NumberFormatException e) { vista.mensaje("Escriba un valor numérico, por ejemplo 2.50."); }
          catch (IllegalArgumentException | IOException e) { vista.mensaje(e.getMessage()); }
    }
}

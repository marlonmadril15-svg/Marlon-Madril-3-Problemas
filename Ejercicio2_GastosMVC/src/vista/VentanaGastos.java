package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import control.ControladorGastos;
import modelo.Gasto;

public class VentanaGastos extends JFrame {
    private JTextField concepto = new JTextField(); private JTextField valor = new JTextField();
    private JComboBox<String> categoria = new JComboBox<>(new String[] { "Comida", "Transporte", "Estudio", "Otro" });
    private JLabel total = new JLabel("Total de gastos: $0.00");
    private DefaultTableModel datos = new DefaultTableModel(new String[] { "Fecha", "Concepto", "Categoría", "Valor" }, 0) {
        public boolean isCellEditable(int fila, int columna) { return false; }
    };
    private ControladorGastos controlador;

    public VentanaGastos() {
        setTitle("Registro de gastos diarios"); setSize(760, 500); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel principal = new JPanel(new BorderLayout(10, 10)); principal.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        JLabel titulo = new JLabel("Registro de gastos diarios", SwingConstants.CENTER); titulo.setFont(titulo.getFont().deriveFont(20f)); principal.add(titulo, BorderLayout.NORTH);
        JPanel formulario = new JPanel(new GridLayout(2, 3, 8, 6)); formulario.setBorder(BorderFactory.createTitledBorder("Nuevo gasto"));
        formulario.add(new JLabel("Concepto")); formulario.add(new JLabel("Categoría")); formulario.add(new JLabel("Valor ($)"));
        formulario.add(concepto); formulario.add(categoria); formulario.add(valor);
        JButton guardar = new JButton("Registrar gasto"); JButton limpiar = new JButton("Limpiar"); JPanel botones = new JPanel(); botones.add(guardar); botones.add(limpiar);
        JPanel superior = new JPanel(new BorderLayout(5, 5)); superior.add(formulario, BorderLayout.CENTER); superior.add(botones, BorderLayout.SOUTH);
        JTable tabla = new JTable(datos); tabla.setRowHeight(24); JPanel tablaPanel = new JPanel(new BorderLayout(5, 5)); tablaPanel.add(total, BorderLayout.NORTH); tablaPanel.add(new JScrollPane(tabla), BorderLayout.CENTER);
        JPanel contenido = new JPanel(new BorderLayout(8, 8));
        contenido.add(superior, BorderLayout.NORTH); contenido.add(tablaPanel, BorderLayout.CENTER);
        principal.add(contenido, BorderLayout.CENTER); setContentPane(principal);
        guardar.addActionListener(e -> controlador.registrar(concepto.getText(), categoria.getSelectedItem().toString(), valor.getText()));
        limpiar.addActionListener(e -> limpiar());
    }

    public void setControlador(ControladorGastos controlador) { this.controlador = controlador; }
    public void actualizar(ArrayList<Gasto> gastos, double suma) {
        datos.setRowCount(0); for (Gasto g : gastos) datos.addRow(new Object[] { g.getFecha(), g.getConcepto(), g.getCategoria(), String.format("$%.2f", g.getValor()) });
        total.setText(String.format("Total de gastos: $%.2f", suma));
    }
    public void limpiar() { concepto.setText(""); valor.setText(""); concepto.requestFocus(); }
    public void mensaje(String texto) { JOptionPane.showMessageDialog(this, texto); }
}

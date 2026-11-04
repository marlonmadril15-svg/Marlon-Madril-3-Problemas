package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import control.ControladorPlantas;
import modelo.Planta;

public class VentanaPlantas extends JFrame {
    private JTextField codigo = new JTextField();
    private JTextField nombre = new JTextField();
    private JComboBox<String> tipo = new JComboBox<>(new String[] { "Hortaliza", "Aromática", "Frutal", "Otra" });
    private JTextField dias = new JTextField();
    private DefaultTableModel datos = new DefaultTableModel(new String[] { "Código", "Nombre", "Tipo", "Días entre riegos" }, 0) {
        public boolean isCellEditable(int fila, int columna) { return false; }
    };
    private ControladorPlantas controlador;

    public VentanaPlantas() {
        setTitle("Registro de plantas del huerto"); setSize(760, 500); setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel principal = new JPanel(new BorderLayout(10, 10)); principal.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        JLabel titulo = new JLabel("Registro de plantas del huerto", SwingConstants.CENTER); titulo.setFont(titulo.getFont().deriveFont(20f));
        principal.add(titulo, BorderLayout.NORTH);
        JPanel formulario = new JPanel(new GridLayout(2, 4, 8, 6)); formulario.setBorder(BorderFactory.createTitledBorder("Datos de la planta"));
        formulario.add(new JLabel("Código")); formulario.add(new JLabel("Nombre")); formulario.add(new JLabel("Tipo")); formulario.add(new JLabel("Días entre riegos"));
        formulario.add(codigo); formulario.add(nombre); formulario.add(tipo); formulario.add(dias);
        JButton guardar = new JButton("Registrar planta"); JButton limpiar = new JButton("Limpiar");
        JPanel norte = new JPanel(new BorderLayout(8, 8)); norte.add(formulario, BorderLayout.CENTER);
        JPanel botones = new JPanel(); botones.add(guardar); botones.add(limpiar); norte.add(botones, BorderLayout.SOUTH);
        JTable tabla = new JTable(datos); tabla.setRowHeight(24);
        JPanel contenido = new JPanel(new BorderLayout(8, 8));
        contenido.add(norte, BorderLayout.NORTH); contenido.add(new JScrollPane(tabla), BorderLayout.CENTER);
        principal.add(contenido, BorderLayout.CENTER); setContentPane(principal);
        guardar.addActionListener(e -> controlador.registrar(codigo.getText(), nombre.getText(), tipo.getSelectedItem().toString(), dias.getText()));
        limpiar.addActionListener(e -> limpiar());
    }

    public void setControlador(ControladorPlantas controlador) { this.controlador = controlador; }
    public void actualizar(ArrayList<Planta> plantas) {
        datos.setRowCount(0); for (Planta p : plantas) datos.addRow(new Object[] { p.getCodigo(), p.getNombre(), p.getTipo(), p.getDiasRiego() });
    }
    public void limpiar() { codigo.setText(""); nombre.setText(""); dias.setText(""); codigo.requestFocus(); }
    public void mensaje(String texto) { JOptionPane.showMessageDialog(this, texto); }
}

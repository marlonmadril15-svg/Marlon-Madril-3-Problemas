package vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import control.ControladorReservas;
import modelo.Reserva;

public class VentanaReservas extends JFrame {
    private JTextField cliente = new JTextField(); private JTextField fecha = new JTextField(LocalDate.now().toString()); private JTextField hora = new JTextField("18:00");
    private JComboBox<String> deporte = new JComboBox<>(new String[] { "Fútbol", "Básquet", "Vóley" });
    private JComboBox<String> cancha = new JComboBox<>(new String[] { "1", "2", "3" });
    private DefaultTableModel datos = new DefaultTableModel(new String[] { "Cliente", "Deporte", "Fecha", "Hora", "Cancha" }, 0) {
        public boolean isCellEditable(int fila, int columna) { return false; }
    };
    private ControladorReservas controlador;

    public VentanaReservas() {
        setTitle("Reservas de cancha deportiva"); setSize(820, 520); setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel principal = new JPanel(new BorderLayout(10, 10)); principal.setBorder(BorderFactory.createEmptyBorder(16, 18, 16, 18));
        JLabel titulo = new JLabel("Reservas de cancha deportiva", SwingConstants.CENTER); titulo.setFont(titulo.getFont().deriveFont(20f)); principal.add(titulo, BorderLayout.NORTH);
        JPanel formulario = new JPanel(new GridLayout(2, 5, 8, 6)); formulario.setBorder(BorderFactory.createTitledBorder("Datos de la reserva"));
        formulario.add(new JLabel("Cliente")); formulario.add(new JLabel("Deporte")); formulario.add(new JLabel("Fecha (AAAA-MM-DD)")); formulario.add(new JLabel("Hora (HH:MM)")); formulario.add(new JLabel("Cancha"));
        formulario.add(cliente); formulario.add(deporte); formulario.add(fecha); formulario.add(hora); formulario.add(cancha);
        JButton guardar = new JButton("Registrar reserva"); JButton limpiar = new JButton("Limpiar"); JPanel botones = new JPanel(); botones.add(guardar); botones.add(limpiar);
        JPanel superior = new JPanel(new BorderLayout(5, 5)); superior.add(formulario, BorderLayout.CENTER); superior.add(botones, BorderLayout.SOUTH);
        JTable tabla = new JTable(datos); tabla.setRowHeight(24);
        JPanel contenido = new JPanel(new BorderLayout(8, 8));
        contenido.add(superior, BorderLayout.NORTH); contenido.add(new JScrollPane(tabla), BorderLayout.CENTER);
        principal.add(contenido, BorderLayout.CENTER); setContentPane(principal);
        guardar.addActionListener(e -> controlador.registrar(cliente.getText(), deporte.getSelectedItem().toString(), fecha.getText(), hora.getText(), cancha.getSelectedItem().toString()));
        limpiar.addActionListener(e -> limpiar());
    }

    public void setControlador(ControladorReservas controlador) { this.controlador = controlador; }
    public void actualizar(ArrayList<Reserva> reservas) {
        datos.setRowCount(0); for (Reserva r : reservas) datos.addRow(new Object[] { r.getCliente(), r.getDeporte(), r.getFecha(), r.getHora(), r.getCancha() });
    }
    public void limpiar() { cliente.setText(""); fecha.setText(LocalDate.now().toString()); hora.setText("18:00"); cliente.requestFocus(); }
    public void mensaje(String texto) { JOptionPane.showMessageDialog(this, texto); }
}

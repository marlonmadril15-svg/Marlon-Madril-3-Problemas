package modelo;

public class Reserva {
    private String cliente; private String deporte; private String fecha; private String hora; private int cancha;
    public Reserva(String cliente, String deporte, String fecha, String hora, int cancha) {
        this.cliente = cliente; this.deporte = deporte; this.fecha = fecha; this.hora = hora; this.cancha = cancha;
    }
    public String getCliente() { return cliente; }
    public String getDeporte() { return deporte; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public int getCancha() { return cancha; }
}

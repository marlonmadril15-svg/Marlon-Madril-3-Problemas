package modelo;

public class Gasto {
    private String fecha;
    private String concepto;
    private String categoria;
    private double valor;

    public Gasto(String fecha, String concepto, String categoria, double valor) {
        this.fecha = fecha; this.concepto = concepto; this.categoria = categoria; this.valor = valor;
    }
    public String getFecha() { return fecha; }
    public String getConcepto() { return concepto; }
    public String getCategoria() { return categoria; }
    public double getValor() { return valor; }
}

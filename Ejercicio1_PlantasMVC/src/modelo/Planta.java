package modelo;

public class Planta {
    private String codigo;
    private String nombre;
    private String tipo;
    private int diasRiego;

    public Planta(String codigo, String nombre, String tipo, int diasRiego) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.diasRiego = diasRiego;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
    public int getDiasRiego() { return diasRiego; }
}

package modelo;

public class Avenger {
    private int id;
    private String nombre;
    private String mision;
    private int peligrosidad;
    private double pagoMensual;

    public Avenger(int id, String nombre, String mision,
                   int peligrosidad, double pagoMensual) {

        this.id = id;
        this.nombre = nombre;
        this.mision = mision;
        this.peligrosidad = peligrosidad;
        this.pagoMensual = pagoMensual;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public int getPeligrosidad() {
        return peligrosidad;
    }

    public void setPeligrosidad(int peligrosidad) {
        this.peligrosidad = peligrosidad;
    }

    public double getPagoMensual() {
        return pagoMensual;
    }

    public void setPagoMensual(double pagoMensual) {
        this.pagoMensual = pagoMensual;
    }

    public double calcularFondoHeroes() {
        return pagoMensual * 0.08;
    }

    public double calcularImpuesto() {

        double pagoAnual = pagoMensual * 12;

        if (pagoAnual <= 50000) {
            return 0;
        } else if (pagoAnual <= 100000) {
            return (pagoAnual - 50000) * 0.10;
        } else if (pagoAnual <= 200000) {
            return (pagoAnual - 100000) * 0.20;
        } else {
            return (pagoAnual - 200000) * 0.30;
        }
    }

    public double calcularPagoNeto() {

        double impuestoMensual = calcularImpuesto() / 12;

        return pagoMensual
                - calcularFondoHeroes()
                - impuestoMensual;
    }

    @Override
    public String toString() {

        return "ID: " + id
                + "\nNombre: " + nombre
                + "\nMision: " + mision
                + "\nPeligrosidad: " + peligrosidad
                + "\nPago Mensual: $"
                + String.format("%.2f", pagoMensual);
    }
}

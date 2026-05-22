package controlador;
import modelo.Avenger;
import java.util.ArrayList;

public class GestionAvengers {
    private ArrayList<Avenger> listaAvengers;

    public GestionAvengers() {
        listaAvengers = new ArrayList<>();
    }

    public boolean agregarAvenger(Avenger avenger) {

        if (buscarPorId(avenger.getId()) == null) {

            listaAvengers.add(avenger);
            return true;
        }

        return false;
    }

    public ArrayList<Avenger> listarAvengers() {
        return listaAvengers;
    }

    public Avenger buscarPorId(int id) {

        for (Avenger a : listaAvengers) {

            if (a.getId() == id) {
                return a;
            }
        }

        return null;
    }

    public boolean modificarAvenger(int id, String nombre, String mision, int peligrosidad, double pagoMensual) {

        Avenger avenger = buscarPorId(id);

        if (avenger != null) {

            avenger.setNombre(nombre);
            avenger.setMision(mision);
            avenger.setPeligrosidad(peligrosidad);
            avenger.setPagoMensual(pagoMensual);

            return true;
        }

        return false;
    }
}

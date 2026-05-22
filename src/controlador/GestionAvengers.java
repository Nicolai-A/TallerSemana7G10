package controlador;
import modelo.Avenger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class GestionAvengers {

    private ArrayList<Avenger> listaAvengers;

    public GestionAvengers() {

        listaAvengers = new ArrayList<>();
    }


    public boolean agregarAvenger(Avenger avenger) {

        if (buscarPorId(avenger.getId()) == -1) {

            listaAvengers.add(avenger);

            ordenarPorId();

            return true;
        }

        return false;
    }


    private void ordenarPorId() {

        Collections.sort(listaAvengers, Comparator.comparingInt(Avenger::getId));
    }


    public ArrayList<Avenger> listarAvengers() {

        return listaAvengers;
    }


    public int buscarPorId(int id) {

        if (listaAvengers.isEmpty() || id < listaAvengers.get(0).getId() || id > listaAvengers.get(listaAvengers.size() - 1).getId()) {

            return -1;
        }

        return buscarBinarioRecursivo(id, 0, listaAvengers.size() - 1);
    }

    private int buscarBinarioRecursivo(
            int id,
            int inferior,
            int superior
    ) {

        if (inferior > superior) {

            return -1;
        }

        int centro = (inferior + superior) / 2;

        if (listaAvengers.get(centro).getId() == id) {

            return centro;

        } else if (listaAvengers.get(centro).getId() > id) {

            return buscarBinarioRecursivo(id, inferior, centro - 1);

        } else {

            return buscarBinarioRecursivo(id, centro + 1, superior);
        }
    }

    public Avenger getValor(int indice) throws Exception {

        if (indice < 0 || indice >= listaAvengers.size()) {

            throw new Exception("Indice fuera de rango");
        }

        return listaAvengers.get(indice);
    }

    public boolean modificarAvenger(int id, String nombre, String mision, int peligrosidad, double pagoMensual) {

        int indice = buscarPorId(id);

        if (indice != -1) {

            try {

                Avenger avenger = getValor(indice);

                avenger.setNombre(nombre);
                avenger.setMision(mision);
                avenger.setPeligrosidad(peligrosidad);
                avenger.setPagoMensual(pagoMensual);

                return true;

            } catch (Exception e) {

                return false;
            }
        }

        return false;
    }
}
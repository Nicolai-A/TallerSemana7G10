import controlador.GestionAvengers;
import modelo.Avenger;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ventana {
    private JPanel ventana;
    private JTabbedPane tabbedPane1;
    private JTextField txtNombreRegistro;
    private JTextField txtMisionRegistro;
    private JTextField txtPagoRegistro;
    private JButton btnRegistrar;
    private JTextField txtNombreBuscar;
    private JTextField txtMisionBuscar;
    private JTextField txtPagoBuscar;
    private JButton btnBuscar;
    private JButton btnModificar;
    private JSpinner spnPeligrosidadBuscar;
    private JSpinner SpnIdRegistro;
    private JSpinner spnPeligrosidadRegistro;
    private JSpinner spnBuscarId;
    private JSpinner spnReporteId;
    private JTextArea txtAreaReporte;
    private JButton btnReporteId;
    private JScrollBar scrollBar1;
    private GestionAvengers gestion;

    public Ventana() {

        gestion = new GestionAvengers();


        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int id = (int) SpnIdRegistro.getValue();

                    String nombre = txtNombreRegistro.getText();

                    String mision = txtMisionRegistro.getText();

                    int peligrosidad = (int) spnPeligrosidadRegistro.getValue();

                    double pago =
                            Double.parseDouble(txtPagoRegistro.getText());

                    if (nombre.isEmpty() || mision.isEmpty()) {

                        JOptionPane.showMessageDialog(null, "Complete todos los campos");

                        return;
                    }

                    Avenger avenger = new Avenger(
                            id,
                            nombre,
                            mision,
                            peligrosidad,
                            pago
                    );

                    boolean agregado = gestion.agregarAvenger(avenger);

                    if (agregado) {

                        JOptionPane.showMessageDialog(null, "Avenger registrado correctamente");

                        limpiarRegistro();

                    } else {
                        JOptionPane.showMessageDialog(null, "El ID ya existe");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en los datos");
                }
            }
        });

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int id = (int) spnBuscarId.getValue();

                    int indice = gestion.buscarPorId(id);

                    if (indice != -1) {

                        Avenger avenger = gestion.getValor(indice);

                        txtNombreBuscar.setText(avenger.getNombre());

                        txtMisionBuscar.setText(avenger.getMision());

                        spnPeligrosidadBuscar.setValue(avenger.getPeligrosidad());

                        txtPagoBuscar.setText(String.valueOf(avenger.getPagoMensual()));

                    } else {

                        JOptionPane.showMessageDialog(null, "Avenger no encontrado");
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int id = (int) spnBuscarId.getValue();

                    String nombre = txtNombreBuscar.getText();

                    String mision = txtMisionBuscar.getText();

                    int peligrosidad = (int) spnPeligrosidadBuscar.getValue();

                    double pago = Double.parseDouble(txtPagoBuscar.getText());

                    boolean modificado =
                            gestion.modificarAvenger(
                                    id,
                                    nombre,
                                    mision,
                                    peligrosidad,
                                    pago
                            );

                    if (modificado) {

                        JOptionPane.showMessageDialog(null, "Avenger modificado correctamente");

                    } else {

                        JOptionPane.showMessageDialog(null, "No se pudo modificar");
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, "Error al modificar");
                }
            }
        });

        btnReporteId.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {

                    int id = (int) spnReporteId.getValue();

                    int indice = gestion.buscarPorId(id);

                    if (indice != -1) {

                        Avenger avenger = gestion.getValor(indice);

                        txtAreaReporte.setText("");

                        txtAreaReporte.append(
                                avenger.toString()
                                        + "\n\n"
                                        + "Fondo de Heroes: $"
                                        + String.format("%.2f",
                                        avenger.calcularFondoHeroes())

                                        + "\n\n"
                                        + "Impuesto Gobierno: $"
                                        + String.format("%.2f",
                                        avenger.calcularImpuesto())

                                        + "\n\n"
                                        + "Pago Neto: $"
                                        + String.format("%.2f",
                                        avenger.calcularPagoNeto())
                        );

                    } else {

                        JOptionPane.showMessageDialog(null, "Avenger no encontrado");
                    }

                } catch (Exception ex) {

                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

    }
    private void limpiarRegistro() {

        txtNombreRegistro.setText("");
        txtMisionRegistro.setText("");
        txtPagoRegistro.setText("");

        SpnIdRegistro.setValue(1);
        spnPeligrosidadRegistro.setValue(1);
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sistema Avengers");

        frame.setContentPane(new Ventana().ventana);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }
    public JPanel getVentana() {

        return ventana;
    }
}

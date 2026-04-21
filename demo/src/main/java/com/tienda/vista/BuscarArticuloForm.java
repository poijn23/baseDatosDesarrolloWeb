package com.tienda.vista;

import javax.swing.*;
import java.awt.*;
import com.tienda.dto.ArticuloDTO;
import com.tienda.DAO.ArticuloDAO;

public class BuscarArticuloForm extends JInternalFrame {
    private JTextField txtId;
    private JTextArea txtInfo;

    public BuscarArticuloForm() {
        setTitle("Búsqueda de Artículos - Amazon Xalapa");
        setClosable(true);
        setSize(350, 250);
        setLayout(new BorderLayout());

        JPanel pnlBusqueda = new JPanel();
        pnlBusqueda.add(new JLabel("ID a buscar:"));
        txtId = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");
        pnlBusqueda.add(txtId);
        pnlBusqueda.add(btnBuscar);

        txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setBackground(new Color(240, 240, 240));

        add(pnlBusqueda, BorderLayout.NORTH);
        add(new JScrollPane(txtInfo), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> {
            ArticuloDAO dao = new ArticuloDAO();
            ArticuloDTO art = dao.buscar(Integer.parseInt(txtId.getText()));
            if (art != null) {
                txtInfo.setText("Nombre: " + art.getNombre() +
                        "\nPrecio: $" + art.getPrecio() +
                        "\nStock: " + art.getExistencias());
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el artículo.");
            }
        });
    }
}
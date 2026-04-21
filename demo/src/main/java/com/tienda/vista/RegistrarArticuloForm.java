package com.tienda.vista;

import javax.swing.*;
import java.awt.*;
import com.tienda.dto.ArticuloDTO;
import com.tienda.DAO.ArticuloDAO;

public class RegistrarArticuloForm extends JInternalFrame {

    private JTextField txtNombre, txtDesc, txtExistencia, txtPrecio;
    private JButton btnAgregar, btnSalir;

    public RegistrarArticuloForm() {
        setTitle("Registrar Nuevo Artículo");
        setClosable(true);
        setSize(400, 300);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel(" Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel(" Descripción:"));
        txtDesc = new JTextField();
        add(txtDesc);

        add(new JLabel(" Existencias:"));
        txtExistencia = new JTextField();
        add(txtExistencia);

        add(new JLabel(" Precio:"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        btnAgregar = new JButton("Agregar");
        btnSalir = new JButton("Salir");

        add(btnAgregar);
        add(btnSalir);

        btnAgregar.addActionListener(e -> accionAgregar());

        btnSalir.addActionListener(e -> dispose());
    }

    private void accionAgregar() {
        try {
            ArticuloDTO dto = new ArticuloDTO();
            ArticuloDAO dao = new ArticuloDAO();

            dto.setNombre(txtNombre.getText());
            dto.setDescripcion(txtDesc.getText());
            dto.setExistencias(Integer.parseInt(txtExistencia.getText()));
            dto.setPrecio(Double.parseDouble(txtPrecio.getText()));

            if (dao.agregar(dto)) {
                JOptionPane.showMessageDialog(this, "¡Artículo guardado en Amazon Xalapa!");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al guardar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, revisa los campos numéricos.", "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombre.setText("");
        txtDesc.setText("");
        txtExistencia.setText("");
        txtPrecio.setText("");
    }
}
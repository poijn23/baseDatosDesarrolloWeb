package com.tienda.vista;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import com.tienda.dto.ArticuloDTO;
import com.tienda.DAO.ArticuloDAO;

public class ListarArticulosForm extends JInternalFrame {
    public ListarArticulosForm() {
        setTitle("Inventario Completo");
        setClosable(true);
        setSize(500, 300);

        String[] columnas = { "ID", "Nombre", "Precio", "Stock" };
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);

        ArticuloDAO dao = new ArticuloDAO();
        List<ArticuloDTO> lista = dao.listarCompleto();

        for (ArticuloDTO a : lista) {
            modelo.addRow(new Object[] { a.getIdarticulo(), a.getNombre(), a.getPrecio(), a.getExistencias() });
        }

        add(new JScrollPane(tabla), BorderLayout.CENTER);
    }
}
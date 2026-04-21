package com.tienda.vista;

import javax.swing.*;
import java.awt.*;

public class FrmPrincipal extends JFrame {

    private JDesktopPane escritorio;

    public FrmPrincipal() {
        initComponents();
    }

    private void initComponents() {

        setTitle("Amazon Xalapa - Sistema de Gestión");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        escritorio = new JDesktopPane();
        escritorio.setBackground(new Color(35, 47, 62));
        add(escritorio, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();

        JMenu menuArticulo = new JMenu("Artículo");

        JMenuItem itemRegistrar = new JMenuItem("Registrar Artículo");
        JMenuItem itemModificar = new JMenuItem("Modificar Artículo");
        JMenuItem itemEliminar = new JMenuItem("Eliminar Artículo");

        JMenu subMenuConsultar = new JMenu("Consultar");
        JMenuItem itemBuscarID = new JMenuItem("Buscar por ID");
        JMenuItem itemBuscarTodos = new JMenuItem("Buscar todos");

        subMenuConsultar.add(itemBuscarID);
        subMenuConsultar.add(itemBuscarTodos);

        menuArticulo.add(itemRegistrar);
        menuArticulo.addSeparator();
        menuArticulo.add(subMenuConsultar);
        menuArticulo.addSeparator();
        menuArticulo.add(itemModificar);
        menuArticulo.add(itemEliminar);

        JMenu menuSalir = new JMenu("Opciones");
        JMenuItem itemCerrar = new JMenuItem("Salir del Sistema");
        menuSalir.add(itemCerrar);

        menuBar.add(menuArticulo);
        menuBar.add(menuSalir);
        setJMenuBar(menuBar);

        itemRegistrar.addActionListener(e -> {
            RegistrarArticuloForm registro = new RegistrarArticuloForm();
            escritorio.add(registro);
            registro.setVisible(true);
        });

        itemBuscarID.addActionListener(e -> {
        });

        itemBuscarTodos.addActionListener(e -> {
        });

        itemCerrar.addActionListener(e -> System.exit(0));

        itemBuscarID.addActionListener(e -> {
            BuscarArticuloForm busqueda = new BuscarArticuloForm();
            escritorio.add(busqueda);
            busqueda.setVisible(true);
        });

        itemBuscarTodos.addActionListener(e -> {
            ListarArticulosForm lista = new ListarArticulosForm();
            escritorio.add(lista);
            lista.setVisible(true);
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new FrmPrincipal().setVisible(true);
        });
    }
}
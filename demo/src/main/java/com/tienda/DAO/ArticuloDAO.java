package com.tienda.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.tienda.dto.ArticuloDTO;

public class ArticuloDAO {
    private final static String sqlInsert = "INSERT INTO articulo (nombre, descripcion, existencias, precio) VALUES (?, ?, ?, ?)";
    private final static String sqlUpdate = "UPDATE articulo SET nombre=?, descripcion=?, existencias=?, precio=? WHERE idarticulo=?";
    private final static String sqlDelete = "DELETE FROM articulo WHERE idarticulo = ?";
    private final static String sqlSelect = "SELECT * FROM articulo WHERE idarticulo = ?";
    private final static String sqlSelectAll = "SELECT * FROM articulo";

    public boolean agregar(ArticuloDTO articulo) {

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlInsert)) {
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getDescripcion());
            ps.setInt(3, articulo.getExistencias());
            ps.setDouble(4, articulo.getPrecio());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al agregar: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(ArticuloDTO articulo) {

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
            ps.setString(1, articulo.getNombre());
            ps.setString(2, articulo.getDescripcion());
            ps.setInt(3, articulo.getExistencias());
            ps.setDouble(4, articulo.getPrecio());
            ps.setInt(5, articulo.getIdarticulo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    }

    public boolean eliminar(int idarticulo) {

        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlDelete)) {
            ps.setInt(1, idarticulo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }

    public ArticuloDTO buscar(int idarticulo) {

        ArticuloDTO articulo = null;
        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelect)) {
            ps.setInt(1, idarticulo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                articulo = new ArticuloDTO();
                articulo.setIdarticulo(rs.getInt("idarticulo"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setExistencias(rs.getInt("existencias"));
                articulo.setPrecio(rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar: " + e.getMessage());
        }
        return articulo;
    }

    public List<ArticuloDTO> listarCompleto() {

        List<ArticuloDTO> lista = new ArrayList<>();
        try (Connection con = ConexionBD.getConnection();
                PreparedStatement ps = con.prepareStatement(sqlSelectAll);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ArticuloDTO articulo = new ArticuloDTO();
                articulo.setIdarticulo(rs.getInt("idarticulo"));
                articulo.setNombre(rs.getString("nombre"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setExistencias(rs.getInt("existencias"));
                articulo.setPrecio(rs.getDouble("precio"));
                lista.add(articulo);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar: " + e.getMessage());
        }
        return lista;
    }
}
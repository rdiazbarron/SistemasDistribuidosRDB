/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author diazb
 */
public class LibroDAO {
    public void añadirLibrero(Libro libro, String codigo) {
        String sql = "INSERT INTO Libro (autor,titulo,anio,librero_codigo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, libro.getAutor());
            statement.setString(2, libro.getTitulo());
            statement.setInt(3, libro.getAnio());
            
            statement.setString(4, codigo);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

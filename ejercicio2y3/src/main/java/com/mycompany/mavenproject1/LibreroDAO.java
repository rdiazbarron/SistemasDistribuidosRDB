/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author diazb
 */
public class LibreroDAO {
    public void añadirLibrero(Librero librero) {
        String sql = "INSERT INTO Librero (codigo, tipolibrero, biblioteca_id) VALUES (?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, librero.getCodigo());
            statement.setString(2, librero.getTipolibrero().toString());
            statement.setInt(3, 1);
            
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    

    // Nuevo método para leer todos los libreros
    public ArrayList<Librero> leerTodosLosLibreros() {
    ArrayList<Librero> libreros = new ArrayList<>();
    String sql = "SELECT codigo, tipolibrero FROM Librero";
    try (Connection conn = DBUtil.getConnection();
         PreparedStatement statement = conn.prepareStatement(sql);
         ResultSet resultSet = statement.executeQuery()) {
        
        while (resultSet.next()) {
            String codigo = resultSet.getString("codigo");
            String tipolibrero = resultSet.getString("tipolibrero");

            Librero librero = new Librero(codigo, TipoLibrero.valueOf(tipolibrero.toUpperCase()));
            libreros.add(librero);
        }
        
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return libreros;
}


}

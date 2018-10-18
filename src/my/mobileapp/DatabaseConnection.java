/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.mobileapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Ralph
 */
public class DatabaseConnection {

    public static Connection connect() {
        Connection con = null;
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/ca_abs", "ca_abs", "haji12345");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Cannot connect to database");
        }
        return con;
    }
}

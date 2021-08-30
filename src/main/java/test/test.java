package test;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class test {
    public static void main(String[] args) {
        //cadena de conexión
        var url = "jdbc:mysql://localhost:3306/test?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
        try {
            // Web Interface
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conexion = DriverManager.getConnection(url, "root", "admin");
            Statement intruccion = conexion.createStatement();
            var sql = "SELECT id_persona, nombre, apellido, email, telefono FROM prueba.persona";
            ResultSet resultado = intruccion.executeQuery(sql);// Ejecuta la consulta de instruccion
            // Para recorrer los elementos de la consulta
            while(resultado.next()){
                System.out.println("Id Persona:"+resultado.getString("id_persona"));
                System.out.println("Nombre:"+resultado.getString("nombre"));
                System.out.println("Apellidos:"+resultado.getString("apellido"));
                System.out.println("Email:"+resultado.getString("email"));
                System.out.println("telefono:"+resultado.getString("telefono"));
                System.out.println("\n");
            }
            // Cerrar sesión
            resultado.close();
            intruccion.close();
            conexion.close();
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            
        }
        
    }
}
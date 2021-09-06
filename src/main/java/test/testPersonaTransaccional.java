package test;

import datos.Conexion;
import datos.PersonaDAO;
import domain.Persona;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testPersonaTransaccional {

    public static void main(String[] args) {
        Connection conexion = null;
        
        try {
            conexion = Conexion.getConnection();
            if(conexion.getAutoCommit()){
                conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
            }
            PersonaDAO personaDAO = new PersonaDAO(conexion);
            
            Persona actualizarPersona = new Persona();
            actualizarPersona.setIdPersona(1);
            actualizarPersona.setNombre("Gerardo");
            actualizarPersona.setApellido("torres");
            actualizarPersona.setEmail("gtorres@gmail.com");
            actualizarPersona.setTelefono("4776018596");
            personaDAO.actualizar(actualizarPersona);
            
            
            Persona nuevaPersona = new Persona();
            nuevaPersona.setNombre("Shakira");
            nuevaPersona.setApellido("Piqué");
            nuevaPersona.setEmail("spique@gmail.com");
            nuevaPersona.setTelefono("4775896352");
            personaDAO.insertar(nuevaPersona);
            
            conexion.commit();
            System.out.println("Se realizaron correctamente las modificaciones en la BD");
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
            System.out.println("Entramos al rollback - no fue posible realizar cambios");
            try {
                conexion.rollback();
            } catch (SQLException ex1) {
                ex1.printStackTrace(System.out);
            }
        }
    }

}

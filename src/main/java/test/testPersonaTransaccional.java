package test;

import datos.Conexion;
import datos.PersonaDAO;
import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class testPersonaTransaccional {

    public static void main(String[] args) {
        Connection conexion = null;

        try {
            conexion = Conexion.getConnection();
            if (conexion.getAutoCommit()) {
                conexion.setAutoCommit(false);// Para no hacer automaticamente el commit
            }
            PersonaDAO personaDao = new PersonaDaoJDBC(conexion);
//            
//            PersonaDTO actualizarPersona = new PersonaDTO();
//            actualizarPersona.setIdPersona(1);
//            actualizarPersona.setNombre("Gerardo");
//            actualizarPersona.setApellido("torres");
//            actualizarPersona.setEmail("gtorres@gmail.com");
//            actualizarPersona.setTelefono("4776018596");
//            personaDAO.actualizar(actualizarPersona);
//            
//            
//            PersonaDTO nuevaPersona = new PersonaDTO();
//            nuevaPersona.setNombre("Shakira");
//            nuevaPersona.setApellido("Piqué");
//            nuevaPersona.setEmail("spique@gmail.com");
//            nuevaPersona.setTelefono("4775896352");
//            personaDAO.insertar(nuevaPersona);

            List<PersonaDTO> personas = personaDao.seleccionar();
            
            for(PersonaDTO persona:personas){
                System.out.println("PersonaDTO" + persona);
            }            

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

package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestManejoDAOPersona {

    public static void main(String[] args) {
        // Para acceder a los métodos del CRUD
        PersonaDAO personaDao = new PersonaDAO();
        
        // ´Para crear un modelo por medio de un objeto de tipo persona representado en la BD
        // Insertar
        /* Persona pesonaNueva = new Persona("Marvin", "Guzman", "mguzman@gmail.com", "4776058916");
        personaDao.insertar(pesonaNueva);*/
        
        //Actualizar
        /*Persona personaEditar = new Persona(1, "Luis Miguel", "Díaz", "luismi@gmail.com", "4778524696");
        personaDao.actualizar(personaEditar);*/
        
        // Elminar
        Persona personaEliminar = new Persona(4);
        personaDao.eliminar(personaEliminar);
        
        // Traer datos               
        List<Persona> personas = personaDao.seleccionar();
        personas.forEach(persona -> {
            System.out.println(persona);
        });
        
    }

}

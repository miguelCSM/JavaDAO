package test;

import datos.PersonaDaoJDBC;
import domain.PersonaDTO;
import java.util.List;

public class TestManejoDAOPersona {

    public static void main(String[] args) {
        // Para acceder a los métodos del CRUD
        PersonaDaoJDBC personaDao = new PersonaDaoJDBC();

        // ´Para crear un modelo por medio de un objeto de tipo persona representado en la BD
        // Insertar
        /* PersonaDTO pesonaNueva = new PersonaDTO("Marvin", "Guzman", "mguzman@gmail.com", "4776058916");
        personaDao.insertar(pesonaNueva);*/
        //Actualizar
        /*PersonaDTO personaEditar = new PersonaDTO(1, "Luis Miguel", "Díaz", "luismi@gmail.com", "4778524696");
        personaDao.actualizar(personaEditar);*/
        // Elminar
//        PersonaDTO personaEliminar = new PersonaDTO(4);
//        personaDao.eliminar(personaEliminar);
//        
        // Traer datos               
        List<PersonaDTO> personas = personaDao.seleccionar();
        personas.forEach(persona -> {
            System.out.println(persona);
        });

    }

}

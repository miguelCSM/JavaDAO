package test;

import datos.PersonaDAO;
import domain.Persona;
import java.util.List;

public class TestManejoDAOPersona {

    public static void main(String[] args) {
        PersonaDAO personaDao = new PersonaDAO();
        List<Persona> personas = personaDao.seleccionar();

        personas.forEach(persona -> {
            System.out.println(persona);
        });

//        for (Persona persona:personas){
//            System.out.println("persona"+persona);
//        }
    }

}

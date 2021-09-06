package datos;

import static datos.Conexion.*;
import domain.Persona;
import java.sql.*;
import java.util.*;

public class PersonaDAO {

    // Para el manejo de conexión - recibe un obj Conecction fuera de la clase
    private Connection conexionTransaccional;

    private static final String SQL_SELECT = "SELECT id_persona, nombre, apellido, email, telefono FROM test.persona";
    private static final String SQL_INSERT = "INSERT INTO persona(nombre, apellido, email, telefono) VALUES(?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE persona SET nombre = ?, apellido = ?, email = ?, telefono = ? WHERE id_persona = ?";
    private static final String SQL_DELETE = "DELETE FROM persona WHERE id_persona=?";

    // Para recibir la conexion
    public PersonaDAO() {

    }

    // Maneja la sesión de la conexión transaccional
    public PersonaDAO(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    // Método que regresa una lista de objetos de tipo Persona(clase Persona.java)
    public List<Persona> seleccionar() {
        //Inicializar parametros de conexión
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Persona persona = null;

        List<Persona> personas = new ArrayList<>();

        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {
                //Obtener los atributos de cada usuario en la BD
                int idPersona = rs.getInt("id_persona");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");

                persona = new Persona(idPersona, nombre, apellido, email, telefono);

                personas.add(persona);
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } // Cerrar todos los objetos
        finally {
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                // Si la conexión es externa entonces se cierra de forma externa sino se cierra dentro de DAO
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);

            }
        }
        return personas;
    }

    public int insertar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; // Saber cuantos registros se han actualizado
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            //debido a los campos que se insertan se trabaja con tipo String
            stmt.setString(1, persona.getNombre());//?
            stmt.setString(2, persona.getApellido());//?
            stmt.setString(3, persona.getEmail());//...
            stmt.setString(4, persona.getTelefono());

            // Ejecutar sentencia
            registros = stmt.executeUpdate(); // modifica el estado de la base de datos (insert, delete, update)
        } //        } catch (SQLException ex) {
        //            ex.printStackTrace(System.out);
        //        } 
        finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int actualizar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; // Saber cuantos registros se han actualizado
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            //debido a los campos que se insertan se trabaja con tipo String
            stmt.setString(1, persona.getNombre());//?
            stmt.setString(2, persona.getApellido());//?
            stmt.setString(3, persona.getEmail());//...
            stmt.setString(4, persona.getTelefono());
            stmt.setInt(5, persona.getIdPersona()); // Where id_persona = ?

            // Ejecutar sentencia
            registros = stmt.executeUpdate(); // modifica el estado de la base de datos (insert, delete, update)

        } //        catch (SQLException ex) {
        //            ex.printStackTrace(System.out);
        //        } 
        finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

    public int eliminar(Persona persona) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; // Saber cuantos registros se han actualizado
        try {
            conn = this.conexionTransaccional != null ? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, persona.getIdPersona());

            // Ejecutar sentencia
            registros = stmt.executeUpdate(); // modifica el estado de la base de datos (insert, delete, update)

        } //        catch (SQLException ex) {
        //            ex.printStackTrace(System.out);
        //        } 
        finally {
            try {
                close(stmt);
                if (this.conexionTransaccional == null) {
                    Conexion.close(conn);
                }
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
            }
        }
        return registros;
    }

}

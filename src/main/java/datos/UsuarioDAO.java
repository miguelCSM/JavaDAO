package datos;

import static datos.Conexion.getConnection;
import domain.Persona;
import domain.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    // SQL Statements
    private static final String SQL_SELECT = "SELECT id_usuario, username, password FROM test.usuario";
    private static final String SQL_INSERT = "INSERT INTO test.usuario(username, password) VALUES(?,?)";
    private static final String SQL_UPDATE = "UPDATE test.usuario SET username = ?, password = ? WHERE id_usuario = ?";
    private static final String SQL_DELETE = "DELETE FROM test.usuario WHERE id_usuario=?";
    
    public List<Usuario> seleccionar(){
        //Inicializar parametros de conexión
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario usuario = null;
        
        List<Usuario> usuarios = new ArrayList<>();
        
        try {
            conn = getConnection();
            stmt  = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                //Obtener los atributos de cada usuario en la BD
                int idUsuario = rs.getInt("id_usuario");
                String userName = rs.getString("username");
                String password = rs.getString("password");
                
                usuario = new Usuario(idUsuario, userName, password);
                
                usuarios.add(usuario);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        // Cerrar todos los objetos
        finally{
            try {
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
            } catch (SQLException ex) {
                ex.printStackTrace(System.out);
                
            }            
        }
        return usuarios;
    }
    

}

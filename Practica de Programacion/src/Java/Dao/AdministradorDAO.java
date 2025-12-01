package Java.Dao;

import Java.Model.Administrador;
import java.util.Optional;

public interface AdministradorDAO {
    boolean existeAdministrador() throws Exception;
    int crearAdministrador(Administrador admin) throws Exception;
    Optional<Administrador> buscarPorCorreo(String correo) throws Exception;
}

package dao;

import model.Departamento;
import java.util.List;

public interface DepartamentoDAO {
    boolean guardar(Departamento departamento);
    Departamento buscarPorNombre(String nombre);
    boolean actualizar(Departamento departamento);
    boolean eliminar(String nombre);
    List<Departamento> listarTodos();
    boolean existeNombre(String nombre);
}
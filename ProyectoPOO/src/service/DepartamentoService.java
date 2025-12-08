package service;

import dao.DepartamentoDAO;
import dao.impl.DepartamentoDAOImpl;
import model.Departamento;
import java.util.List;

public class DepartamentoService {
    private DepartamentoDAO departamentoDAO;

    public DepartamentoService() {
        this.departamentoDAO = new DepartamentoDAOImpl();
    }

    public boolean registrarDepartamento(Departamento departamento) {
        if (departamento == null || departamento.getNombre() == null || departamento.getNombre().trim().isEmpty()) {
            return false;
        }

        if (departamentoDAO.existeNombre(departamento.getNombre())) {
            System.out.println("Error: Ya existe un departamento con el nombre " + departamento.getNombre());
            return false;
        }

        return departamentoDAO.guardar(departamento);
    }

    public Departamento buscarDepartamentoPorNombre(String nombre) {
        return departamentoDAO.buscarPorNombre(nombre);
    }

    public boolean actualizarDepartamento(Departamento departamento) {
        return departamentoDAO.actualizar(departamento);
    }

    public boolean eliminarDepartamento(String nombre) {
        return departamentoDAO.eliminar(nombre);
    }

    public List<Departamento> listarDepartamentos() {
        return departamentoDAO.listarTodos();
    }

    public boolean existeDepartamento(String nombre) {
        return departamentoDAO.existeNombre(nombre);
    }
}
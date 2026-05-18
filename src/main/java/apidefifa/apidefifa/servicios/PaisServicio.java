package apidefifa.apidefifa.servicios;

/*
 * SERVICIO: PaisServicio
 * --------------------------------------------------------------
 * Implementa IPaisServicio con la lógica de negocio para Pais.
 * Spring inyecta el repositorio automáticamente en el constructor.
 * --------------------------------------------------------------
 */

import java.util.List;

import org.springframework.stereotype.Service;

import apidefifa.apidefifa.entidades.Pais;
import apidefifa.apidefifa.interfaces.IPaisServicio;
import apidefifa.apidefifa.repositorios.PaisRepositorio;

@Service
public class PaisServicio implements IPaisServicio {

    private final PaisRepositorio repositorio;

    public PaisServicio(PaisRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Pais> listar() {
        return repositorio.findAll();
    }

    @Override
    public Pais buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Pais guardar(Pais pais) {
        return repositorio.save(pais);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}

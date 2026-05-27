package apihp2.apihp2.servicios;
import java.util.List;

import org.springframework.stereotype.Service;

import apihp2.apihp2.entidades.Pais;
import apihp2.apihp2.interfaces.IPaisServicio;
import apihp2.apihp2.repositorios.PaisRepositorio;

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

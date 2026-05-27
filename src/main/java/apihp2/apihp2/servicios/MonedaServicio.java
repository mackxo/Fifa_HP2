package apihp2.apihp2.servicios;
import java.util.List;

import org.springframework.stereotype.Service;

import apihp2.apihp2.entidades.Moneda;
import apihp2.apihp2.interfaces.IMonedaServicio;
import apihp2.apihp2.repositorios.MonedaRepositorio;

@Service
public class MonedaServicio implements IMonedaServicio {

    private final MonedaRepositorio repositorio;

    // Inyección de dependencias por constructor (práctica recomendada en Spring)
    public MonedaServicio(MonedaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<Moneda> listar() {
        // findAll() viene gratis de JpaRepository
        return repositorio.findAll();
    }

    @Override
    public Moneda buscarPorId(long id) {
        // findById retorna Optional; si no existe devolvemos null
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Moneda guardar(Moneda moneda) {
        // save() inserta si el id es nuevo, o actualiza si ya existe
        return repositorio.save(moneda);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}

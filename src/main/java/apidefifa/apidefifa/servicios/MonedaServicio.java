package apidefifa.apidefifa.servicios;

/*
 * SERVICIO: MonedaServicio
 * --------------------------------------------------------------
 * Implementa IMonedaServicio con la lógica de negocio real.
 *
 * Spring crea automáticamente una instancia de esta clase
 * (@Service) e inyecta el repositorio en el constructor.
 * El controlador no crea este objeto manualmente; Spring se lo
 * entrega (Inversión de Control / Inyección de Dependencias).
 * --------------------------------------------------------------
 */

import java.util.List;

import org.springframework.stereotype.Service;

import apidefifa.apidefifa.entidades.Moneda;
import apidefifa.apidefifa.interfaces.IMonedaServicio;
import apidefifa.apidefifa.repositorios.MonedaRepositorio;

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

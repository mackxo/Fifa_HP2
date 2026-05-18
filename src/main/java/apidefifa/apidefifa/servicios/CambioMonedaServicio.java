package apidefifa.apidefifa.servicios;

/*
 * SERVICIO: CambioMonedaServicio
 * --------------------------------------------------------------
 * Implementa ICambioMonedaServicio con la lógica de negocio para
 * CambioMoneda. Incluye el método especial del taller que filtra
 * registros de cambio por rango de fechas y sigla de moneda.
 * --------------------------------------------------------------
 */

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import apidefifa.apidefifa.entidades.CambioMoneda;
import apidefifa.apidefifa.interfaces.ICambioMonedaServicio;
import apidefifa.apidefifa.repositorios.CambioMonedaRepositorio;

@Service
public class CambioMonedaServicio implements ICambioMonedaServicio {

    private final CambioMonedaRepositorio repositorio;

    public CambioMonedaServicio(CambioMonedaRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CambioMoneda> listar() {
        return repositorio.findAll();
    }

    @Override
    public CambioMoneda buscarPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public CambioMoneda guardar(CambioMoneda cambioMoneda) {
        return repositorio.save(cambioMoneda);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<CambioMoneda> listarPorPeriodoYSigla(LocalDateTime desde, LocalDateTime hasta, String sigla) {
        // Delega la consulta al repositorio que tiene la @Query JPQL definida
        return repositorio.listarPorPeriodoYSigla(desde, hasta, sigla);
    }
}

package apihp2.apihp2.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import apihp2.apihp2.entidades.CambioMoneda;
import apihp2.apihp2.interfaces.ICambioMonedaServicio;
import apihp2.apihp2.repositorios.CambioMonedaRepositorio;

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
    public List<CambioMoneda> listarPorPeriodoYMonedaId(LocalDateTime desde, LocalDateTime hasta, Long idMoneda) {
        return repositorio.listarPorPeriodoYMonedaId(desde, hasta, idMoneda);
    }
}

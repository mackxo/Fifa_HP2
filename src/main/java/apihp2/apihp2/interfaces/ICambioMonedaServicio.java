package apihp2.apihp2.interfaces;

import java.time.LocalDateTime;
import java.util.List;

import apihp2.apihp2.entidades.CambioMoneda;

public interface ICambioMonedaServicio {

    List<CambioMoneda> listar();

    CambioMoneda buscarPorId(long id);

    CambioMoneda guardar(CambioMoneda cambioMoneda);

    void eliminar(long id);

    List<CambioMoneda> listarPorPeriodoYMonedaId(LocalDateTime desde, LocalDateTime hasta, Long idMoneda);
}

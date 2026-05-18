package apidefifa.apidefifa.interfaces;
import java.time.LocalDateTime;
import java.util.List;

import apidefifa.apidefifa.entidades.CambioMoneda;

public interface ICambioMonedaServicio {

    List<CambioMoneda> listar();

    CambioMoneda buscarPorId(long id);

    CambioMoneda guardar(CambioMoneda cambioMoneda);   // crea o actualiza

    void eliminar(long id);

    /*
     * Método especial del taller:
     * Retorna los registros de cambio de una moneda (identificada por
     * su sigla) dentro de un rango de fechas dado.
     */
    List<CambioMoneda> listarPorPeriodoYSigla(LocalDateTime desde, LocalDateTime hasta, String sigla);
}

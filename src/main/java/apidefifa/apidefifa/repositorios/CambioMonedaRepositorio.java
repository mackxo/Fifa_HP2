package apidefifa.apidefifa.repositorios;

/*
 * REPOSITORIO: CambioMonedaRepositorio
 * --------------------------------------------------------------
 * Interfaz que gestiona el acceso a la tabla "cambiomoneda".
 *
 * Hereda de JpaRepository los métodos CRUD estándar:
 *   - findAll()           → lista todos los registros de cambio
 *   - findById(id)        → busca por clave primaria
 *   - save(cambioMoneda)  → inserta o actualiza un registro
 *   - deleteById(id)      → elimina por clave primaria
 *
 * Además define una consulta personalizada con @Query para el
 * requerimiento especial del taller: filtrar cambios por rango
 * de fechas y por la sigla de la moneda.
 * --------------------------------------------------------------
 */

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apidefifa.apidefifa.entidades.CambioMoneda;

@Repository
public interface CambioMonedaRepositorio extends JpaRepository<CambioMoneda, Long> {

    /*
     * Consulta JPQL personalizada (no SQL nativo, sino lenguaje de JPA).
     * Filtra los registros de cambio que estén dentro del rango de fechas
     * indicado Y cuya moneda tenga la sigla especificada.
     *
     * Equivalente SQL aproximado:
     *   SELECT * FROM cambiomoneda cm
     *   JOIN moneda m ON cm.IdMoneda = m.Id
     *   WHERE cm.Fecha BETWEEN :desde AND :hasta
     *     AND m.Sigla = :sigla
     *
     * Uso desde el servicio:
     *   repositorio.listarPorPeriodoYSigla(desde, hasta, "COP")
     */
    @Query("SELECT c FROM CambioMoneda c WHERE c.fecha BETWEEN :desde AND :hasta AND c.moneda.sigla = :sigla")
    List<CambioMoneda> listarPorPeriodoYSigla(
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta,
            @Param("sigla") String sigla);
}

package apihp2.apihp2.repositorios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import apihp2.apihp2.entidades.CambioMoneda;

@Repository
public interface CambioMonedaRepositorio extends JpaRepository<CambioMoneda, Long> {

    @Query("SELECT c FROM CambioMoneda c WHERE c.fecha BETWEEN :desde AND :hasta AND c.moneda.id = :idMoneda")
    List<CambioMoneda> listarPorPeriodoYMonedaId(
            @Param("desde") LocalDateTime desde,
            @Param("hasta") LocalDateTime hasta,
            @Param("idMoneda") Long idMoneda);
}

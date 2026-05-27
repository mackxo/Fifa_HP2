package apihp2.apihp2.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import apihp2.apihp2.entidades.CambioMoneda;
import apihp2.apihp2.interfaces.ICambioMonedaServicio;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CambioMonedaControlador {

    private final ICambioMonedaServicio servicio;

    public CambioMonedaControlador(ICambioMonedaServicio servicio) {
        this.servicio = servicio;
    }

    @GetMapping("/api/monedas/cambios/listar")
    public List<CambioMoneda> listar() {
        return servicio.listar();
    }

    @GetMapping("/api/monedas/cambios/buscar/{id}")
    public ResponseEntity<CambioMoneda> buscarPorId(@PathVariable long id) {
        CambioMoneda cambio = servicio.buscarPorId(id);
        if (cambio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cambio);
    }

    @PostMapping("/api/monedas/cambios/agregar")
    public ResponseEntity<CambioMoneda> agregar(@RequestBody CambioMoneda cambioMoneda) {
        return ResponseEntity.ok(servicio.guardar(cambioMoneda));
    }

    @PutMapping("/api/monedas/cambios/actualizar/{id}")
    public ResponseEntity<CambioMoneda> actualizar(@PathVariable long id, @RequestBody CambioMoneda cambioMoneda) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        cambioMoneda.setId(id);
        return ResponseEntity.ok(servicio.guardar(cambioMoneda));
    }

    @DeleteMapping("/api/monedas/cambios/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/api/monedas/listarporperiodo")
    public ResponseEntity<List<CambioMoneda>> listarPorPeriodo(@RequestBody FiltroPeriodoRequest filtro) {
        LocalDateTime desde = LocalDate.parse(filtro.getDesde()).atStartOfDay();
        LocalDateTime hasta = LocalDate.parse(filtro.getHasta()).atTime(23, 59, 59);
        List<CambioMoneda> resultado = servicio.listarPorPeriodoYMonedaId(desde, hasta, filtro.getIdMoneda());
        return ResponseEntity.ok(resultado);
    }
}

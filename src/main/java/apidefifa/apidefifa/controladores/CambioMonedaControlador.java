package apidefifa.apidefifa.controladores;

/*
 * CONTROLADOR: CambioMonedaControlador
 * --------------------------------------------------------------
 * Expone los endpoints REST para operar con CambioMoneda.
 * Ruta base: /api/monedas
 *
 * Endpoints disponibles:
 *   GET    /api/monedas/cambios/listar              → lista todos los registros
 *   GET    /api/monedas/cambios/buscar/{id}         → busca por id
 *   POST   /api/monedas/cambios/agregar             → crea un registro nuevo
 *   PUT    /api/monedas/cambios/actualizar/{id}     → actualiza un registro
 *   DELETE /api/monedas/cambios/eliminar/{id}       → elimina un registro
 *   GET    /api/monedas/listarporperiodo            → método especial del taller:
 *                                                     filtra por rango de fechas y sigla
 * --------------------------------------------------------------
 */

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import apidefifa.apidefifa.entidades.CambioMoneda;
import apidefifa.apidefifa.interfaces.ICambioMonedaServicio;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CambioMonedaControlador {

    private final ICambioMonedaServicio servicio;

    public CambioMonedaControlador(ICambioMonedaServicio servicio) {
        this.servicio = servicio;
    }

    /* ── GET /api/monedas/cambios/listar ── */
    @GetMapping("/api/monedas/cambios/listar")
    public List<CambioMoneda> listar() {
        return servicio.listar();
    }

    /* ── GET /api/monedas/cambios/buscar/{id} ── */
    @GetMapping("/api/monedas/cambios/buscar/{id}")
    public ResponseEntity<CambioMoneda> buscarPorId(@PathVariable long id) {
        CambioMoneda cambio = servicio.buscarPorId(id);
        if (cambio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cambio);
    }

    /* ── POST /api/monedas/cambios/agregar ── */
    @PostMapping("/api/monedas/cambios/agregar")
    public ResponseEntity<CambioMoneda> agregar(@RequestBody CambioMoneda cambioMoneda) {
        return ResponseEntity.ok(servicio.guardar(cambioMoneda));
    }

    /* ── PUT /api/monedas/cambios/actualizar/{id} ── */
    @PutMapping("/api/monedas/cambios/actualizar/{id}")
    public ResponseEntity<CambioMoneda> actualizar(@PathVariable long id, @RequestBody CambioMoneda cambioMoneda) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        cambioMoneda.setId(id);
        return ResponseEntity.ok(servicio.guardar(cambioMoneda));
    }

    /* ── DELETE /api/monedas/cambios/eliminar/{id} ── */
    @DeleteMapping("/api/monedas/cambios/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /*
     * ── GET /api/monedas/listarporperiodo ──
     * Método especial del taller.
     *
     * Recibe en el body un JSON con:
     *   { "desde": "2018-01-01", "hasta": "2018-06-01", "sigla": "COP" }
     *
     * Convierte las fechas String → LocalDateTime y delega al servicio.
     * Responde con la lista de cambios que coincidan con el filtro.
     */
    @GetMapping("/api/monedas/listarporperiodo")
    public ResponseEntity<List<CambioMoneda>> listarPorPeriodo(@RequestBody FiltroPeriodoRequest filtro) {
        // Convertimos las fechas: "yyyy-MM-dd" → LocalDateTime al inicio/fin del día
        LocalDateTime desde = LocalDate.parse(filtro.getDesde()).atStartOfDay();
        LocalDateTime hasta = LocalDate.parse(filtro.getHasta()).atTime(23, 59, 59);

        List<CambioMoneda> resultado = servicio.listarPorPeriodoYSigla(desde, hasta, filtro.getSigla());
        return ResponseEntity.ok(resultado);
    }
}

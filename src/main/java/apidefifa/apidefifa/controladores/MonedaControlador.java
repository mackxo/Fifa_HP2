package apidefifa.apidefifa.controladores;

/*
 * CONTROLADOR: MonedaControlador
 * --------------------------------------------------------------
 * Expone los endpoints REST para operar con la entidad Moneda.
 * Ruta base: /api/monedas
 *
 * Endpoints disponibles:
 *   GET    /api/monedas/listar          → lista todas las monedas
 *   GET    /api/monedas/buscar/{id}     → busca una moneda por su id
 *   POST   /api/monedas/agregar         → crea una nueva moneda
 *   PUT    /api/monedas/actualizar/{id} → actualiza una moneda existente
 *   DELETE /api/monedas/eliminar/{id}   → elimina una moneda por su id
 *
 * El controlador solo habla con la interfaz IMonedaServicio,
 * nunca directamente con el repositorio (separación de capas).
 * --------------------------------------------------------------
 */

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import apidefifa.apidefifa.entidades.Moneda;
import apidefifa.apidefifa.interfaces.IMonedaServicio;

@RestController
@RequestMapping("/api/monedas")
@CrossOrigin(origins = "http://localhost:4200")   // permite consumo desde Angular
public class MonedaControlador {

    private final IMonedaServicio servicio;

    public MonedaControlador(IMonedaServicio servicio) {
        this.servicio = servicio;
    }

    /* ── GET /api/monedas/listar ── */
    @GetMapping("/listar")
    public List<Moneda> listar() {
        return servicio.listar();
    }

    /* ── GET /api/monedas/buscar/{id} ── */
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Moneda> buscarPorId(@PathVariable long id) {
        Moneda moneda = servicio.buscarPorId(id);
        if (moneda == null) {
            // 404 si no existe el id
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(moneda);
    }

    /* ── POST /api/monedas/agregar ── */
    // El cuerpo del request debe ser un JSON con los datos de la moneda
    @PostMapping("/agregar")
    public ResponseEntity<Moneda> agregar(@RequestBody Moneda moneda) {
        Moneda guardada = servicio.guardar(moneda);
        return ResponseEntity.ok(guardada);
    }

    /* ── PUT /api/monedas/actualizar/{id} ── */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Moneda> actualizar(@PathVariable long id, @RequestBody Moneda moneda) {
        // Verificamos que el id exista antes de actualizar
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        moneda.setId(id);   // aseguramos que se actualice el registro correcto
        return ResponseEntity.ok(servicio.guardar(moneda));
    }

    /* ── DELETE /api/monedas/eliminar/{id} ── */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();   // 204 No Content
    }
}

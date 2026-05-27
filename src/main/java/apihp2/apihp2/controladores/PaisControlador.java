package apihp2.apihp2.controladores;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import apihp2.apihp2.entidades.Pais;
import apihp2.apihp2.interfaces.IPaisServicio;

@RestController
@RequestMapping("/api/paises")
@CrossOrigin(origins = "http://localhost:4200")
public class PaisControlador {

    private final IPaisServicio servicio;

    public PaisControlador(IPaisServicio servicio) {
        this.servicio = servicio;
    }

    /* ── GET /api/paises/listar ── */
    @GetMapping("/listar")
    public List<Pais> listar() {
        return servicio.listar();
    }

    /* ── GET /api/paises/buscar/{id} ── */
    @GetMapping("/buscar/{id}")
    public ResponseEntity<Pais> buscarPorId(@PathVariable long id) {
        Pais pais = servicio.buscarPorId(id);
        if (pais == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pais);
    }

    /* ── POST /api/paises/agregar ── */
    @PostMapping("/agregar")
    public ResponseEntity<Pais> agregar(@RequestBody Pais pais) {
        return ResponseEntity.ok(servicio.guardar(pais));
    }

    /* ── PUT /api/paises/actualizar/{id} ── */
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Pais> actualizar(@PathVariable long id, @RequestBody Pais pais) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        pais.setId(id);
        return ResponseEntity.ok(servicio.guardar(pais));
    }

    /* ── DELETE /api/paises/eliminar/{id} ── */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable long id) {
        if (servicio.buscarPorId(id) == null) {
            return ResponseEntity.notFound().build();
        }
        servicio.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

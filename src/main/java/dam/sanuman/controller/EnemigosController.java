package dam.sanuman.controller;

import dam.sanuman.models.Enemigo;
import dam.sanuman.service.EnemigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnemigosController {
    @Autowired
    private EnemigoService enemigoService;

    @GetMapping("/enemigo")
    public List<Enemigo> obtenerEnemigos() {
        return enemigoService.obtenerTodos();
    }

    @PostMapping("/enemigo")
    public Enemigo crearEnemigo(@RequestBody Enemigo enemigo) {
        return enemigoService.guardar(enemigo);
    }

    @PutMapping("/enemigo/{id}")
    public Enemigo actualizarEnemigo(@PathVariable Long id, @RequestBody Enemigo enemigo) {
        return enemigoService.editar(id, enemigo);
    }

    @DeleteMapping("/enemigo/{id}")
    public void borrarEnemigo(@PathVariable Long id) {
        enemigoService.eliminar(id);
    }

}

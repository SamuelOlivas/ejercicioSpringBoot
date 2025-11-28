package dam.sanuman.service;

import dam.sanuman.models.Enemigo;
import dam.sanuman.repository.EnemigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnemigoService {
    @Autowired
    private EnemigoRepository enemigoRepository;

    public List<Enemigo> obtenerTodos() {
        List<Enemigo> enemigos = enemigoRepository.findAll();
        if (enemigos.isEmpty()) {
            System.out.println("Acho que no hay ninguno");
        } else {
            System.out.println("Jefe esto va como una maquina");
            enemigos.forEach(enemigo -> {
                System.out.println("ID: " + enemigo.getId() + " Nombre: " + enemigo.getNombre() + " Pais: "
                        + enemigo.getPais() + " Afiliacion: " + enemigo.getAfiliacion_politica());
            });
        }
        return enemigos;
    } // FIN GET

    public Enemigo guardar(Enemigo enemigo) {
        return enemigoRepository.save(enemigo);
    } // FIN POST

    public Enemigo editar(Long id, Enemigo enemigo) {
        Optional<Enemigo> enemigoExistente = enemigoRepository.findById(id);

        if (enemigoExistente.isPresent()) {
            Enemigo enemigoActualizado = enemigoExistente.get();
            enemigoActualizado.setNombre(enemigo.getNombre());
            enemigoActualizado.setPais(enemigo.getPais());
            enemigoActualizado.setAfiliacion_politica(enemigo.getAfiliacion_politica());

            System.out.println("Enemigo actualizado: ID " + id);
            return enemigoRepository.save(enemigoActualizado);
        } else {
            System.out.println("Enemigo no encontrado con ID: " + id);
            return null;
        }
    } // FIN PUT

    public boolean eliminar(Long id) {
        Optional<Enemigo> enemigoExistente = enemigoRepository.findById(id);

        if (enemigoExistente.isPresent()) {
            enemigoRepository.deleteById(id);
            System.out.println("Enemigo eliminado: ID " + id);
            return true;
        } else {
            System.out.println("Enemigo no encontrado con ID: " + id);
            return false;
        }
    } // FIN DELETE

}

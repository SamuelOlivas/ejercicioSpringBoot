package dam.sanuman.repository;

/*
Esto va a para operaciones CRUD
 */

import dam.sanuman.models.Enemigo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnemigoRepository extends JpaRepository<Enemigo, Long> {
    List<Enemigo> findByNombre(String nombre);

}

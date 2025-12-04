package dam.sanuman.repository;

/*
Esto va a para operaciones CRUD
 */

import dam.sanuman.models.Enemigo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EnemigoRepository extends MongoRepository<Enemigo, String> {

}

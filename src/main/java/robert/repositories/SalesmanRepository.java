package robert.repositories;

import org.springframework.data.repository.CrudRepository;
import robert.entities.Salesman;

/**
 * Created by robert on 29.03.16.
 */
public interface SalesmanRepository extends CrudRepository<Salesman, Long> {
    Salesman findOneByNipNo(String nipNo);
}

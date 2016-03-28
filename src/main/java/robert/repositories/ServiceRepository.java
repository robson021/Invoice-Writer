package robert.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import robert.entities.TheService;

/**
 * Created by robert on 28.03.16.
 */
@Repository
public interface ServiceRepository extends CrudRepository<TheService, Long> {
    TheService findOneBySymbol(String symbol);
}

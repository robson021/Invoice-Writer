package robert.repositories;

import org.springframework.data.repository.CrudRepository;
import robert.entities.TheService;

/**
 * Created by robert on 28.03.16.
 */
public interface ServiceRepository extends CrudRepository<TheService, Long> {
    TheService findOneBySymbol(String symbol);
}

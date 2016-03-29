package robert.repositories;

import org.springframework.data.repository.CrudRepository;
import robert.entities.Contractor;

/**
 * Created by robert on 29.03.16.
 */
public interface ContractorRepository extends CrudRepository<Contractor, Long> {
    Contractor findOneByNipNo(String nipNo);
}

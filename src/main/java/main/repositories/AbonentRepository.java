package main.repositories;

import main.model.Abonent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbonentRepository extends CrudRepository<Abonent, Integer> {
}

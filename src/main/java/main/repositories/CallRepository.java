package main.repositories;

import main.model.Call;
import org.springframework.data.repository.CrudRepository;

public interface CallRepository extends CrudRepository<Call,Integer> {
}

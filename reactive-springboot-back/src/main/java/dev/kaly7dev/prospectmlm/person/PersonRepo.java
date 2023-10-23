package dev.kaly7dev.prospectmlm.person;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface PersonRepo extends
        ReactiveCrudRepository<Person, Long> {
}

package dev.kaly7dev.prospectmlm.person;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Flux<Person> getPersons();

    Mono<Person> getPersonById(Long id);

    Mono<Person> savePerson(Person person);

    Mono<Person> updatePerson(Long id, Person person);

    Mono<Void> deletePerson(Long id);
}

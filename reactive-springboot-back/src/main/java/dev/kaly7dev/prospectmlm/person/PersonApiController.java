package dev.kaly7dev.prospectmlm.person;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/person")
public class PersonApiController {
    private final PersonService personService;

    @GetMapping
    public Flux<Person> all() {
        return personService.getPersons();
    }

    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Person> getFlux(){
        return personService.getPersons()
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping("/find/{id}")
    public Mono<ResponseEntity<Person>> getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> createPerson(@RequestBody Mono<Person> personMono) {
        return personMono.flatMap(personService::savePerson);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Person>> updatePerson(@PathVariable Long id, @RequestBody Mono<Person> personMono) {
        return personMono.flatMap(person -> personService.updatePerson(id, person))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePerson(@PathVariable Long id) {
        return personService.deletePerson(id);
    }
}

package dev.kaly7dev.prospectmlm.prospects;

import dev.kaly7dev.prospectmlm.person.Person;
import dev.kaly7dev.prospectmlm.person.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/prospect")
public class ProspectApiController {
    private final ProspectService prospectService;

    @GetMapping
    public Flux<Prospect> all() {
        return prospectService.getProspects();
    }

    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Prospect> getFlux(){
        return prospectService.getProspects()
                .delayElements(Duration.ofSeconds(1)).log();
    }

   @GetMapping("/find/{id}")
    public Mono<ResponseEntity<Prospect>> getProspectById(@PathVariable Long id) {
        return prospectService.getProspectById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Prospect> createProspect(@RequestBody Mono<Prospect> prospectMono) {
        return prospectMono.flatMap(prospectService::saveProspect);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Prospect>> updateProspect(@PathVariable Long id, @RequestBody Mono<Prospect> prospectMono) {
        return prospectMono.flatMap(prospect -> prospectService.updateProspect(id, prospect))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProspect(@PathVariable Long id) {
        return prospectService.deleteProspect(id);
    }
}

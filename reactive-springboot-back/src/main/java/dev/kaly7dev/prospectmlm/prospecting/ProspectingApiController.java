package dev.kaly7dev.prospectmlm.prospecting;


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
@RequestMapping("/api/v1/prospecting")
public class ProspectingApiController {
    private final ProspectingService prospectingService;

    @GetMapping
    public Flux<Prospecting> all() {
        return prospectingService.getProspectings();
    }

    @GetMapping(path = "/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Prospecting> getFlux(){
        return prospectingService.getProspectings()
                .delayElements(Duration.ofSeconds(1)).log();
    }

   @GetMapping("/find/{id}")
    public Mono<ResponseEntity<Prospecting>> getProspectingById(@PathVariable Long id) {
        return prospectingService.getProspectingById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Prospecting> createProspecting(@RequestBody Mono<Prospecting> prospectingMono) {
        return prospectingMono.flatMap(prospectingService::saveProspecting);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Prospecting>> updateProspecting(@PathVariable Long id, @RequestBody Mono<Prospecting> prospectingMono) {
        return prospectingMono.flatMap(prospecting -> prospectingService.updateProspecting(id, prospecting))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProspecting(@PathVariable Long id) {
        return prospectingService.deleteProspecting(id);
    }

    //public Mono<Prospecting> validateProspecting();
}

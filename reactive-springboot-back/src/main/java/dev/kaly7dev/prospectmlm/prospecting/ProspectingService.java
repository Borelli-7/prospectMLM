package dev.kaly7dev.prospectmlm.prospecting;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProspectingService {

    Flux<Prospecting> getProspectings();

    Mono<Prospecting> getProspectingById(Long id);

    Mono<Prospecting> saveProspecting(Prospecting prospecting);

    Mono<Prospecting> updateProspecting(Long id, Prospecting prospecting);

    Mono<Void> deleteProspecting(Long id);
}

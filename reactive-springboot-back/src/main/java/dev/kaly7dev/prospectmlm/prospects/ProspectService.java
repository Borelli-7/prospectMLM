package dev.kaly7dev.prospectmlm.prospects;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProspectService {
    Flux<Prospect> getProspects();

    Mono<Prospect> getProspectById(Long id);

    Mono<Prospect> saveProspect(Prospect prospect);

    Mono<Prospect> updateProspect(Long id, Prospect prospect);

    Mono<Void> deleteProspect(Long id);
}

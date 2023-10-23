package dev.kaly7dev.prospectmlm.prospecting;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ProspectingRepo extends
        ReactiveCrudRepository<Prospecting, Long> {

    @Query("SELECT * FROM prospectings ps JOIN prospects p ON ps.prospect_id = p.prospect_id WHERE ps.prospect_id = $1")
    Flux<Prospecting> findAllByProspectId(Long prospectID);
}

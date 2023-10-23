package dev.kaly7dev.prospectmlm.following;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface FollowingRepo extends
        ReactiveCrudRepository<Following, Long> {
    @Query("SELECT * FROM followings f JOIN prospects p ON f.prospect_id = p.prospect_id WHERE f.prospect_id = $1")
    Flux<Following> findAllByProspectId(Long prospectID);

}

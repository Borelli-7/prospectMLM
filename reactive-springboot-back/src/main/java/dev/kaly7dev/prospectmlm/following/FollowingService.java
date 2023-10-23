package dev.kaly7dev.prospectmlm.following;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FollowingService {

    Flux<Following> getFollowings();

    Mono<Following> getFollowingById(Long id);

    Mono<Following> saveFollowing(Following following);

    Mono<Following> updateFollowing(Long id, Following following);

    Mono<Void> deleteFollowing(Long id);
}

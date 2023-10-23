package dev.kaly7dev.prospectmlm.following;

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
@RequestMapping("/api/v1/following")
public class FollowingApiController {
    private final FollowingService followingService;

    @GetMapping
    public Flux<Following> all() {
        return followingService.getFollowings();
    }

   @GetMapping(path = "/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Following> getFlux(){
        return followingService.getFollowings()
                .delayElements(Duration.ofSeconds(1)).log();
    }

    @GetMapping("/find/{id}")
    public Mono<ResponseEntity<Following>> getFollowingById(@PathVariable Long id) {
        return followingService.getFollowingById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Following> createFollowing(@RequestBody Mono<Following> followingMono) {
        return followingMono.flatMap(followingService::saveFollowing);
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<Following>> updateFollowing(@PathVariable Long id, @RequestBody Mono<Following> followingMono) {
        return followingMono.flatMap(following -> followingService.updateFollowing(id, following))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteFollowing(@PathVariable Long id) {
        return followingService.deleteFollowing(id);
    }

    //public Mono<Following> validateFollowing();*/
}

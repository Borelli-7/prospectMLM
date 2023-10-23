package dev.kaly7dev.prospectmlm.following;

import dev.kaly7dev.prospectmlm.exception.NotFoundException;
import dev.kaly7dev.prospectmlm.exception.NotPermitException;
import dev.kaly7dev.prospectmlm.prospects.ProspectRepo;
import dev.kaly7dev.prospectmlm.prospects.ProspectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Slf4j
@Service
@AllArgsConstructor
public class FollowingServiceImplV1 implements FollowingService {
    private final FollowingRepo followingRepo;
    private final ProspectRepo prospectRepo;
    private final ProspectService prospectService;

    private static final String FOLLOWING_NOT_FOUND=" Following ID not found in Database !" ;
    @Override
    public Flux<Following> getFollowings() {
        return followingRepo.findAll();
    }

    @Override
    public Mono<Following> getFollowingById(Long id) {
        return followingRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(FOLLOWING_NOT_FOUND)))
                .flatMap(following -> prospectRepo.findById(following.getProspectID()).map(prospect -> {
                    following.setProspect(prospect);
                    return following;
                }));
    }

    @Override
    public Mono<Following> saveFollowing(Following followingDTO) {
        return prospectRepo.findById(followingDTO.getProspectID())
                .switchIfEmpty(Mono.error(new NotFoundException("Prospect ID not found")))
                .flatMap(prospect -> {
                    followingDTO.setCreatedDate(LocalDateTime.now());
                    followingDTO.setProspect(prospect);
                    return followingRepo.save(followingDTO);
                });
    }

    @Override
    public Mono<Following> updateFollowing(Long id, Following followingDTO) {
        return followingRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(FOLLOWING_NOT_FOUND)))
                .flatMap(following -> {
                    if(Boolean.TRUE.equals(following.getValidated())) {
                        throw new NotPermitException("Following already validated, you Can't update it ! ");
                    } else {
                        updateFollowingFields(followingDTO, following);
                        prospectService.updateProspect(followingDTO.getProspectID(), followingDTO.getProspect());
                        return followingRepo.save(followingDTO);

                    }
                });
    }

    @Override
    public Mono<Void> deleteFollowing(Long id) {
        // delete Following with Prospect
        return followingRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(FOLLOWING_NOT_FOUND)))
                .flatMap(following -> {
                    if(Boolean.TRUE.equals(following.getValidated()))
                        throw new NotPermitException("Following already validated, you Can't delete it ! ");
                    return followingRepo.deleteById(id);
                });
    }

    private void updateFollowingFields(Following followingDTO, Following following) {
        followingDTO.setFollowID(following.getFollowID());
        followingDTO.setFollowNumber(following.getFollowNumber());
        followingDTO.setCreatedDate(following.getCreatedDate());
        followingDTO.setLastUpdatedDate(LocalDateTime.now());

    }
}

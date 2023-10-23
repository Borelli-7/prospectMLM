package dev.kaly7dev.prospectmlm.prospects;

import dev.kaly7dev.prospectmlm.exception.NotFoundException;
import dev.kaly7dev.prospectmlm.following.Following;
import dev.kaly7dev.prospectmlm.following.FollowingRepo;
import dev.kaly7dev.prospectmlm.person.PersonRepo;
import dev.kaly7dev.prospectmlm.person.PersonService;
import dev.kaly7dev.prospectmlm.prospecting.Prospecting;
import dev.kaly7dev.prospectmlm.prospecting.ProspectingRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@AllArgsConstructor
public class ProspectServiceImplV1 implements ProspectService {
    private final ProspectRepo prospectRepo;
    private final PersonRepo personRepo;
    private final ProspectingRepo prospectingRepo;
    private final FollowingRepo followingRepo;
    private final PersonService personService;

    @Override
    public Flux<Prospect> getProspects() {
        return prospectRepo.findAll();
    }

    @Override
    public Mono<Prospect> getProspectById(Long id) {
        return prospectRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Prospect not found")))
                .flatMap(prospect -> {
                    if (prospect.getPersonID() == null) {
                        return Mono.just(prospect);
                    }else {
                        getProspectingsProspect(prospect);
                        getFollowingsProspect(prospect);
                        return getPersonProspect(prospect);
                    }
                });
    }

    @Override
    public Mono<Prospect> saveProspect(Prospect prospectDTO) {
        return personRepo.findById(prospectDTO.getPersonID())
                .switchIfEmpty(Mono.error(new NotFoundException("Person ID not found")))
                .flatMap(person -> {
                    prospectDTO.setPerson(person);
                    return prospectRepo.save(prospectDTO);
                });

    }

    @Override
    public Mono<Prospect> updateProspect(Long id, Prospect prospectDTO) {
        return prospectRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Prospect ID not found")))
                .flatMap(prospect -> {
                    prospectDTO.setProspectID(prospect.getProspectID()); // if there is something else to update do it here.
                    personService.updatePerson(prospectDTO.getPersonID(), prospectDTO.getPerson());
                    return prospectRepo.save(prospectDTO);
                });
    }

    @Override
    public Mono<Void> deleteProspect(Long id) {
        // delete Prospect with Person
        return prospectRepo.findById(id).flatMap(prospect -> {
            if (prospect.getPersonID() == null) return prospectRepo.deleteById(id);
            return personService.deletePerson(prospect.getPersonID())
                    .then(prospectRepo.deleteById(id));
        });
    }

    private Mono<Prospect> getPersonProspect(Prospect prospect) {
        return personRepo.findById(prospect.getPersonID()).map(person -> {
            prospect.setPerson(person);
            return prospect;
        });
    }
    private void getProspectingsProspect(Prospect prospect) {
        Flux<Prospecting> allByProspectId = prospectingRepo.findAllByProspectId(prospect.getProspectID());
        prospect.setProspecting(allByProspectId);
    }
    private void getFollowingsProspect(Prospect prospect) {
        Flux<Following> allByProspectId = followingRepo.findAllByProspectId(prospect.getProspectID());
        prospect.setFollowing(allByProspectId);
    }
}

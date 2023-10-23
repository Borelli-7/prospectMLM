package dev.kaly7dev.prospectmlm.prospecting;

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
public class ProspectingServiceImplV1 implements ProspectingService {
    private ProspectingRepo prospectingRepo;
    private ProspectRepo prospectRepo;
    private ProspectService prospectService;

    private static final String PROSPECTING_NOT_FOUND=" Prospecting ID not found in Database !" ;
    @Override
    public Flux<Prospecting> getProspectings() {
        return prospectingRepo.findAll();
    }

    @Override
    public Mono<Prospecting> getProspectingById(Long id) {
        return prospectingRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(PROSPECTING_NOT_FOUND)))
                .flatMap(prospecting -> prospectRepo.findById(prospecting.getProspectID()).map(prospect -> {
                    prospecting.setProspect(prospect);
                    return prospecting;
                }));
    }

    @Override
    public Mono<Prospecting> saveProspecting(Prospecting prospectingDTO) {
        return prospectRepo.findById(prospectingDTO.getProspectID())
                .switchIfEmpty(Mono.error(new NotFoundException("Prospect ID not found")))
                .flatMap(prospect -> {
                    prospectingDTO.setCreatedDate(LocalDateTime.now());
                    prospectingDTO.setProspect(prospect);
                    return prospectingRepo.save(prospectingDTO);
                });
    }

    @Override
    public Mono<Prospecting> updateProspecting(Long id, Prospecting prospectingDTO) {
        return prospectingRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(PROSPECTING_NOT_FOUND)))
                .flatMap(prospecting -> {
                    if(Boolean.TRUE.equals(prospecting.getValidated())) {
                        throw new NotPermitException("Prospecting already validated, you Can't update it ! ");
                    } else {
                        updateProspectingFields(prospectingDTO, prospecting);
                        prospectService.updateProspect(prospectingDTO.getProspectID(), prospectingDTO.getProspect());
                        return prospectingRepo.save(prospectingDTO);

                    }
                });
    }

    @Override
    public Mono<Void> deleteProspecting(Long id) {
        // delete Prospecting with Prospect
        return prospectingRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(PROSPECTING_NOT_FOUND)))
                .flatMap(prospecting -> {
                    if(Boolean.TRUE.equals(prospecting.getValidated()))
                        throw new NotPermitException("Prospecting already validated, you Can't delete it ! ");
                    return prospectingRepo.deleteById(id);
                });
    }

    private static void updateProspectingFields(Prospecting prospectingDTO, Prospecting prospecting) {
        prospectingDTO.setProspectingID(prospecting.getProspectingID());
        prospectingDTO.setCreatedDate(prospecting.getCreatedDate());
        prospectingDTO.setUpdatedDate(LocalDateTime.now());
    }


}

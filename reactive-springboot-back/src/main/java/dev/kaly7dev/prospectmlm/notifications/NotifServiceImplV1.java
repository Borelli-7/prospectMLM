package dev.kaly7dev.prospectmlm.notifications;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NotifServiceImplV1 implements NotifService {
    private final NotifRepo notifRepo;
    @Override
    public Flux<Notif> getNotifs() {
        return notifRepo.findAll();
    }

    @Override
    public Mono<Notif> saveNotif(Notif notif) {
        return notifRepo.save(notif);
    }
}

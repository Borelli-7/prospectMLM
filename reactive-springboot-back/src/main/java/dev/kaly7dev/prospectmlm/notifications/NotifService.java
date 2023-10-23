package dev.kaly7dev.prospectmlm.notifications;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotifService {


    Flux<Notif> getNotifs();

    Mono<Notif> saveNotif(Notif notif);

}

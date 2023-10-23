package dev.kaly7dev.prospectmlm.notifications;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifRepo extends
        ReactiveCrudRepository<Notif, Long> {
}

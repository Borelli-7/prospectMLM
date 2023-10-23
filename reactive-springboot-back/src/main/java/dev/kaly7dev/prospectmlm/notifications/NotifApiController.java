package dev.kaly7dev.prospectmlm.notifications;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.management.monitor.MonitorNotification;
import java.time.Duration;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/notifs")
public class NotifApiController {

    private final NotifService notifService;

    @GetMapping
    public Flux<Notif> getNotifs() {
        return notifService.getNotifs();
    }

    @GetMapping(path="/flux", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Notif> getFlux(){
        return notifService.getNotifs()
                .delayElements(Duration.ofSeconds(1)).log();
    }

}

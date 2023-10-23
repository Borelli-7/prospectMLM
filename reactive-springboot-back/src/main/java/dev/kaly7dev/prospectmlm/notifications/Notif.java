package dev.kaly7dev.prospectmlm.notifications;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("notifications")
public class Notif {
    @Id
    private Long notifID;
    private LocalDateTime createdDate;
    private String title;
    private String message;
    private String userName;

    public Notif(LocalDateTime createdDate, String title, String message, String userName) {
        this.createdDate = createdDate;
        this.title = title;
        this.message = message;
        this.userName = userName;
    }
}

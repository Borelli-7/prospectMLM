package dev.kaly7dev.prospectmlm.prospects;

import dev.kaly7dev.prospectmlm.following.Following;
import dev.kaly7dev.prospectmlm.person.Person;
import dev.kaly7dev.prospectmlm.prospecting.Prospecting;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Flux;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("prospects")
public class Prospect {
    @Id
    private Long prospectID;
    private String influenceCircle;
    private Boolean converted;
    private String userName;

    @Column("person_id")
    private Long personID;
    @Transient
    private Person person;
    @Transient
    private Flux<Prospecting> prospecting;
    @Transient
    private Flux<Following> following;
}

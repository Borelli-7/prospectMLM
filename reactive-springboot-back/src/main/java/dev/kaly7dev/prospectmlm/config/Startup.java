package dev.kaly7dev.prospectmlm.config;

import dev.kaly7dev.prospectmlm.address.AddressRepo;
import dev.kaly7dev.prospectmlm.following.FollowingRepo;
import dev.kaly7dev.prospectmlm.notifications.NotifRepo;
import dev.kaly7dev.prospectmlm.person.PersonRepo;
import dev.kaly7dev.prospectmlm.prospecting.ProspectingRepo;
import dev.kaly7dev.prospectmlm.prospects.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class Startup {

/*    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("init.sql")));

        return initializer;
    }*/
    @Bean
    CommandLineRunner loadataBase(
                NotifRepo notifRepo,
                AddressRepo addressRepo,
                PersonRepo personRepo,
                ProspectRepo prospectRepo,
                ProspectingRepo prospectingRepo,
                FollowingRepo followingRepo
    ) {
        return args -> {
/*            Notif notif1 = new Notif();
            notif1.setTitle("greeting");
            notif1.setMessage("Hello World!");
            Notif notif2 = new Notif();
            notif2.setTitle("greeting");
            notif2.setMessage("Hello World!");

            log.info("Loading Data Base");
            // save a couple of data
            var notifs = Flux.just(notif1, notif2);
            notifs.flatMap(notifRepo::save)
                    .subscribe(notif -> log.info("Saved Notif: " + notif));*/

/*             Address address1 = new Address ();
            address1.setNationality("Cameroonian");
            address1.setResidenceCountry("Canada");
             Address address2 = new Address ();
            address2.setNationality("Egyptian");
            address2.setResidenceCountry("Maroc");

            log.info("Loading Data Base");
            // save a couple of data
            var addresses = Flux.just(address1, address2);
            addresses.flatMap(addressRepo::save)
                    .subscribe(address -> log.info("Saved Address : " + address)); */

/*            Person person1 = new Person ();
            person1.setFirstName("kaly1");
            person1.setPhoneNumber("673974873");
            person1.setPersonAddressID(1L);
             Person person2 = new Person ();
            person2.setFirstName("kaly2");
            person2.setPhoneNumber("673974873");
            person2.setPersonAddressID(3L);

            log.info("Loading Data Base");
            // save a couple of data
            var persons = Flux.just(person1, person2);
            persons.flatMap(personRepo::save)
                    .subscribe(person -> log.info("Saved Person : " + person));*/

/*            Prospect prospect1 = new Prospect ();
            prospect1.setInfluenceCircle("Family");
            prospect1.setPersonID(1L);

            Prospect prospect2 = new Prospect ();
            prospect2.setInfluenceCircle("Friends");
            prospect2.setPersonID(2L);

            log.info("Loading Data Base");
            // save a couple of data
            var prospects = Flux.just(prospect1, prospect2);
            prospects.flatMap(prospectRepo::save)
                    .subscribe(prospect -> log.info("Saved Prospect : " + prospect));*/

/*            Prospecting prospecting1 = new Prospecting ();
            prospecting1.setProspectingType("ONE_TO_ONE");
            prospecting1.setProspectID(1L);

            Prospecting prospecting2 = new Prospecting ();
            prospecting2.setProspectingType("HOME_MEETING");
            prospecting2.setProspectID(2L);

            log.info("Loading Data Base");
            // save a couple of data
            var prospectings = Flux.just(prospecting1, prospecting2);
            prospectings.flatMap(prospectingRepo::save)
                    .subscribe(prospecting -> log.info("Saved Prospecting : " + prospecting));*/

/*            Following following1 = new Following ();
            following1.setFollowNumber(2);
            following1.setProspectID(1L);

            Following following2 = new Following ();
            following2.setFollowNumber(1);
            following2.setProspectID(2L);

            log.info("Loading Data Base");
            // save a couple of data
            var followings = Flux.just(following1, following2);
            followings.flatMap(followingRepo::save)
                    .subscribe(following -> log.info("Saved Following : " + following));*/
        };
    }
}


package dev.kaly7dev.prospectmlm.person;

import dev.kaly7dev.prospectmlm.address.Address;
import dev.kaly7dev.prospectmlm.address.AddressRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import dev.kaly7dev.prospectmlm.exception.NotFoundException;

@Slf4j
@Service
@AllArgsConstructor
public class PersonServiceImplV1 implements PersonService {
    private final PersonRepo personRepo;
    private final AddressRepo addressRepo;
    @Override
    public Flux<Person> getPersons() {
        return personRepo.findAll();
    }

    @Override
    public Mono<Person> getPersonById(Long id) {
        return personRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Person not found")))
                .flatMap(person -> addressRepo.findById(person.getPersonAddressID()).map(address -> {
                    person.setAddress(address);
                    return person;
                }));
    }

    @Override
    public Mono<Person> savePerson(Person person) {
        return addressRepo.save(person.getAddress()).flatMap(address -> {
            person.setPersonAddressID(address.getAddressID());
            return personRepo.save(person);
        });
    }

    @Override
    public Mono<Person> updatePerson(Long id, Person personDTO) {
        return personRepo.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException("Person not found")))
                .flatMap(person -> {
                    personDTO.setPersonID(person.getPersonID()); // if there is something else to update do it here.
                    return updatePersonAddressField(personDTO);
                });
    }

    @Override
    public Mono<Void> deletePerson(Long id) {
        // delete person with address
        return personRepo.findById(id).flatMap(person -> addressRepo.deleteById(person.getPersonAddressID())
                .then(personRepo.deleteById(id)));
    }

    private Mono<? extends Person> updatePersonAddressField(Person personDTO) {
        return addressRepo.findById(personDTO.getPersonAddressID()).flatMap(address -> {
            updateAddressField(personDTO, address);
            return addressRepo.save(address).flatMap(address1 -> {
                personDTO.setPersonAddressID(address1.getAddressID());
                return personRepo.save(personDTO);
            });
        });
    }

    private static void updateAddressField(Person personDTO, Address address) {
        //address.setAddressID(personDTO.getPersonAddressID());
        address.setCity(personDTO.getAddress().getCity());
        address.setStreet(personDTO.getAddress().getStreet());
        address.setState(personDTO.getAddress().getState());
        address.setZipCode(personDTO.getAddress().getZipCode());
        address.setNationality(personDTO.getAddress().getNationality());
        address.setResidenceCountry(personDTO.getAddress().getResidenceCountry());
    }


}

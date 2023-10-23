package dev.kaly7dev.prospectmlm.address;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface AddressRepo extends
        ReactiveCrudRepository<Address, Long> {
}

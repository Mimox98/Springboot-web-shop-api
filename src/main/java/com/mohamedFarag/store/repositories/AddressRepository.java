package com.mohamedFarag.store.repositories;

import com.mohamedFarag.store.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
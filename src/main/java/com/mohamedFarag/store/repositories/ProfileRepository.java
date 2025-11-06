package com.mohamedFarag.store.repositories;

import com.mohamedFarag.store.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface ProfileRepository extends CrudRepository<Profile, Long> {
}
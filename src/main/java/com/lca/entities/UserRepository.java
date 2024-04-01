package com.lca.entities;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends CrudRepository<LCAUser, Long>{

	Optional<LCAUser> findByUserName(String userName);
	Optional<LCAUser> findByEmail(String email);
	
}

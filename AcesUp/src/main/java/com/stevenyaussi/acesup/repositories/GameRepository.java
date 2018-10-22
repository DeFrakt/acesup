package com.stevenyaussi.acesup.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stevenyaussi.acesup.models.Game;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
//    User findByEmail(String email);
}
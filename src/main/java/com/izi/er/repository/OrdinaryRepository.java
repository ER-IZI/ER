package com.izi.er.repository;

import com.izi.er.model.Ordinary;
import com.izi.er.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdinaryRepository extends CrudRepository<Ordinary, Long> {
}

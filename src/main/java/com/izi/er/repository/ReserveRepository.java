package com.izi.er.repository;

import com.izi.er.model.Reserve;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveRepository extends CrudRepository<Reserve, Long> {
}

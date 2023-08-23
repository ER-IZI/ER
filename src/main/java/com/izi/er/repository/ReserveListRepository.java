package com.izi.er.repository;

import com.izi.er.model.ReserveList;
import com.izi.er.model.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReserveListRepository extends CrudRepository<ReserveList, Long> {

}

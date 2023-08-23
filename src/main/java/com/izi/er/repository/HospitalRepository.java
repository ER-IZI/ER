package com.izi.er.repository;

import com.izi.er.model.Hospital;
import com.izi.er.model.User;
import org.springframework.data.domain.Slice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends CrudRepository<Hospital, Long> {

    Slice<Hospital> findByHid(long Hid);

    Slice<Hospital> findByHname(String Hname);

    boolean existsByHid(long Hid);


    void deleteByHid(long Hid);




}

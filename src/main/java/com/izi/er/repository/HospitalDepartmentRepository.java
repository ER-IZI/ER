package com.izi.er.repository;

import com.izi.er.model.HospitalDepartment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalDepartmentRepository extends CrudRepository<HospitalDepartment, Long> {
}

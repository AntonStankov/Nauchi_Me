package com.example.nauchiMe.service;

import com.example.nauchiMe.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UnitRepo extends CrudRepository<Unit, Long> {
    Unit findByTitle(String title);

}

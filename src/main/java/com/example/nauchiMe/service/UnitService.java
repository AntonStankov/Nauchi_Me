package com.example.nauchiMe.service;

import com.example.nauchiMe.dto.UnitDto;
import com.example.nauchiMe.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnitService {
    UnitDto save(Unit unit);
    List<Unit> getUnitByGrade(int grade);
    Unit getUnitByTitle(String title);
    List<Unit> getUnitsUnpermitted();
    List<Unit> getUnitsPermitted();
    Unit findById(Long id);
    Unit update(Unit unit);
}

package com.example.nauchiMe.service.impl;

import com.example.nauchiMe.dto.UnitDto;
import com.example.nauchiMe.entity.Unit;
import com.example.nauchiMe.service.UnitRepo;
import com.example.nauchiMe.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UnitServiceImpl implements UnitService {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UnitRepo unitRepo;

    @Override
    @Transactional
    public UnitDto save(Unit unit) {
        entityManager.persist(unit);
        entityManager.flush();
        return new UnitDto(unit.getTitle(), unit.getText(), unit.getVideo(), unit.getGrade(), unit.getPermitted());
    }

    @Override
    public List<Unit> getUnitByGrade(int grade) {
        Query query = entityManager.createNamedQuery("Unit.getByGrade");
        query.setParameter("grade", grade);
        List<Unit> dtos = query.getResultList();
        return dtos;
    }

    @Override
    public Unit getUnitByTitle(String title) {
        Query query = entityManager.createNamedQuery("Unit.findByTitle").setParameter("title", title);
        List<Unit> unit = query.getResultList();
        if(unit.isEmpty()){
            return null;
        }
        else{
        return unit.get(0);

        }
//        return unitRepo.findByTitle(title);
//        return unitRepo.findByTitle(title);
    }

    @Override
    public List<Unit> getUnitsUnpermitted() { // this can be used only if user is SUPERADMIN
        Query query = entityManager.createNamedQuery("Unit.getUnpermitted");
        List<Unit> units = query.getResultList();
        return units;
    }

    public List<Unit> getUnitsPermitted(){
        Query query = entityManager.createNamedQuery("Unit.getPermitted");
        List<Unit> units = query.getResultList();
        return units;
    }

    public Unit findById(Long id){
        Query query = entityManager.createNamedQuery("Unit.findById").setParameter("id", id);
        List<Unit> units = query.getResultList();
        if(units.isEmpty()){
            return null;
        }else{
            return units.get(0);
        }
    }

    @Override
    @Transactional
    public Unit update(Unit unit){
        Unit updated = entityManager.merge(unit);
//        updated.setVideo(unit.getVideo());
        entityManager.flush();
        return unit;
    }
}

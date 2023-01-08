package com.example.nauchiMe.controller;

import com.example.nauchiMe.dto.UnitDto;
import com.example.nauchiMe.entity.Unit;
import com.example.nauchiMe.entity.UserDomain;
import com.example.nauchiMe.service.UnitRepo;
import com.example.nauchiMe.service.UnitService;
import com.example.nauchiMe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/unit")
public class UnitController {

    @Autowired
    UnitRepo unitRepo;
    @Autowired
    UserService userService;
    @Autowired
    UnitService unitService;

    @Autowired
    EntityManager entityManager;

    @PostMapping("/saveUnit")
    public UnitDto saveUnit(@RequestBody UnitDto dto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getType().toString().equals("SUPERADMIN")){
            return unitService.save(new Unit(null, dto.getTitle(), dto.getText(), dto.getVideo(), dto.getGrade(), dto.getPermitted()));

        }
        else throw new RuntimeException("You don't have prmittion to add new units");
    }

    @PostMapping("/grade")
    public List<Unit> getUnitsByGrade(@RequestBody Grade grade){
        return unitService.getUnitByGrade(grade.getGrade());
    }


    @PostMapping("/getByTitle")
    public Unit getByTitle(@RequestBody String title){
        return unitService.getUnitByTitle(title);
    }

    @GetMapping("/deny")
    public List<Unit> getUnpermitted(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getType().toString().equals("SUPERADMIN")){
            return unitService.getUnitsUnpermitted();
        }
        else throw new RuntimeException("You cant see these units because you don't have permittions!");

    }

    @GetMapping("/allowed")
    public List<Unit> getPermitted(){
        return unitService.getUnitsPermitted();
    }

    @PostMapping("/changePermittion")
    public Unit changePermittion(@RequestBody UnitId id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        UserDomain user =  userService.findByUsername(principal.getUsername());

        if(user.getType().toString().equals("SUPERADMIN")){
            Unit unit = unitService.findById(id.getId());
            if(unit.getPermitted()){
                unit.setPermitted(false);
            }
            else{
                unit.setPermitted(true);
            }
            unitService.update(unit);
            return unit;
        }
        else throw new RuntimeException("You don't have permittions!(Should be SUERADMIN)");


    }

    @PostMapping("byId")
    public Unit findById(@RequestBody UnitId id){
        Unit unit = unitService.findById(id.getId());
        return unit;
    }

    @PostMapping("/addImage")
    public Unit addImage(@RequestParam MultipartFile image, @RequestParam("id") Long id) throws IOException {



        Unit unit = unitService.findById(id);
        unit.setVideo(image.getBytes());
        unitService.update(unit);
        return unit;


    }

    @PostMapping("/title")
    public Unit changeTitle(@RequestParam String title, @RequestParam Long id){
        Unit unit = unitService.findById(id);
        unit.setTitle(title);
        unitService.update(unit);
        return unit;
    }



    @GetMapping("/hello")
    public String hello(){
        return "Hello";
    }

    @GetMapping("/api")
    public String api(){
        return "new message";
    }
}


package com.example.nauchiMe.entity;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "units")
@NamedQueries({
        @NamedQuery(name = "Unit.findByTitle", query = "select a from Unit a where a.title = :title"),
        @NamedQuery(name = "Unit.getByGrade", query = "select a from Unit a where a.grade=:grade"),
        @NamedQuery(name = "Unit.getUnpermitted", query = "select a from Unit a where a.permitted=false"),
        @NamedQuery(name = "Unit.getPermitted", query = "select a from Unit a where a.permitted=true"),
        @NamedQuery(name = "Unit.findById", query = "select a from Unit a where a.id=:id")
})
public class Unit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(unique = true)
    private String title;

    @Column
    private String text;
    @Lob
    @Column
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] video;

    @Column
    private int grade;

    @Column
    private Boolean permitted;
}

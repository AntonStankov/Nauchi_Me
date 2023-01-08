package com.example.nauchiMe.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UnitDto {
    private String title;
    private String text;
    private byte[] video;
    private int grade;
    private Boolean permitted;
}

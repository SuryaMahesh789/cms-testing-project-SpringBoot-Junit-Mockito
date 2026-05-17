package com.example.cmstestingproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuRequestDTO
{

    @NotBlank(message = "Menu name is Required")
    private String name;

}


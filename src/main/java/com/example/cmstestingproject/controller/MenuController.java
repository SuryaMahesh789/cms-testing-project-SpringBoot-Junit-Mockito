package com.example.cmstestingproject.controller;

import com.example.cmstestingproject.dto.MenuRequestDTO;
import com.example.cmstestingproject.entity.Menu;
import com.example.cmstestingproject.service.MenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController
{

    private final MenuService menuService;

    @PostMapping
    public Menu createMenu(@RequestBody @Valid MenuRequestDTO requestDTO)
    {
        return menuService.createMenu(requestDTO);
    }

    @GetMapping
    public List<Menu> getMenus()
    {
        return menuService.getMenus();
    }

}

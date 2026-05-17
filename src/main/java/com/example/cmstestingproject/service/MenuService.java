package com.example.cmstestingproject.service;

import com.example.cmstestingproject.dto.MenuRequestDTO;
import com.example.cmstestingproject.entity.Menu;
import com.example.cmstestingproject.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class MenuService
{

    private final MenuRepository menuRepository;

    public Menu createMenu(MenuRequestDTO requestDTO)
    {
        Menu menu = Menu.builder()
                .name(requestDTO.getName())
                .build();

        return menuRepository.save(menu);

    }

    public List<Menu> getMenus()
    {
        return menuRepository.findAll();
    }

}

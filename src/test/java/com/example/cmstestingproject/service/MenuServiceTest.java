package com.example.cmstestingproject.service;

import com.example.cmstestingproject.dto.MenuRequestDTO;
import com.example.cmstestingproject.entity.Menu;
import com.example.cmstestingproject.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class MenuServiceTest
{

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuService menuService;

    @Test
    void shouldCreateMenuSucessfully()
    {
        MenuRequestDTO requestDTO = new MenuRequestDTO();
        requestDTO.setName("Today's Menu");

        Menu savedMenu = Menu.builder()
                .id(1L)
                .name("Test Menu")
                .build();


        when(menuRepository.save(any(Menu.class)))
                .thenReturn(savedMenu);

        Menu result = menuService.createMenu(requestDTO);

        assertNotNull(result);
        assertEquals(1L,result.getId());
        assertEquals("Test Menu",result.getName());

        verify(menuRepository,times(1)).save(any(Menu.class));

    }

    @Test
    void shouldReturnAllMenus()
    {

        List<Menu> menuList = List.of(
          Menu.builder()
                  .id(1L)
                  .name("Breakfast Menu")
                  .build(),

                Menu.builder()
                        .id(2L)
                        .name("Lunch Menu")
                        .build()

        );

        when(menuRepository.findAll()).thenReturn(menuList);

        List<Menu>result = menuService.getMenus();

        assertNotNull(result);
        assertEquals(2,result.size());

        assertEquals("Breakfast Menu",result.get(0).getName());

        verify(menuRepository,times(1)).findAll();


    }

    @Test
    void shouldReturnEmptyListWhenNoMenusExist()
    {
        when(menuRepository.findAll()).thenReturn(Collections.emptyList());

        List<Menu>result = menuService.getMenus();

        assertTrue(result.isEmpty());

        verify(menuRepository).findAll();

    }

    @Test
    void shouldThrowExceptionWhenSaveFails()
    {
        MenuRequestDTO requestDTO = new MenuRequestDTO();
        requestDTO.setName("Today's Menu");

        when(menuRepository.save(any(Menu.class))).thenThrow(new RuntimeException("Database Error"));

        RuntimeException exception = assertThrows(RuntimeException.class,()->menuService.createMenu(requestDTO));

        assertEquals("Database Error", exception.getMessage());

        verify(menuRepository).save(any(Menu.class));

    }

    @Test
    void shouldMapDtoEntityCorrectly()
    {
        MenuRequestDTO requestDTO = new MenuRequestDTO();
        requestDTO.setName("Dinner Menu");

        Menu savedMenu = Menu.builder()
                .id(1L)
                .name("Dinner Menu")
                .build();

        when(menuRepository.save(any(Menu.class))).thenReturn(savedMenu);

        Menu result = menuService.createMenu(requestDTO);

        assertEquals(result.getName(),requestDTO.getName());

    }

    @Test
    void shouldMapDtoEntityCorrectlyReal()
    {
        MenuRequestDTO requestDTO = new MenuRequestDTO();
        requestDTO.setName("Dinner Menu");

        Menu savedMenu = Menu.builder()
                .id(1L)
                .name("Dinner Menu")
                .build();

        when(menuRepository.save(any(Menu.class))).thenReturn(savedMenu);

        Menu result = menuService.createMenu(requestDTO);

        ArgumentCaptor<Menu>menuCaptor = ArgumentCaptor.forClass(Menu.class);

        verify(menuRepository)
                .save(menuCaptor.capture());

        Menu capturedMenu = menuCaptor.getValue();

        assertEquals("Dinner Menu",capturedMenu.getName());

    }

    @Test
    void shouldThrowExceptionWhenFetchingMenusFails()
    {
        when(menuRepository.findAll()).thenThrow(new RuntimeException("Database Fetch Error"));

        RuntimeException exception = assertThrows(RuntimeException.class,()->menuService.getMenus());

        assertEquals("Database Fetch Error",exception.getMessage());

        verify(menuRepository).findAll();

    }

}

package com.museums.world.museumsdemo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.museums.world.museumsdemo.model.Museum;
import com.museums.world.museumsdemo.service.MuseumService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MuseumController.class)
class MuseumControllerTest {

    private final Museum museum = Museum.builder()
            .name("Dummy museum")
            .address("Dummy address")
            .phone("Dummy phone")
            .founded("1984")
            .build();

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MuseumService museumService;

    @Captor
    private ArgumentCaptor<Integer> museumIdCaptor;

    @Captor
    private ArgumentCaptor<Museum> museumCaptor;

    @Test
    void findMuseum() throws Exception {
        when(museumService.findMuseumWithWorks(museumIdCaptor.capture())).thenReturn(museum);

        mockMvc.perform(get("/museum/{id}", 1)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(museum.getName())))
                .andExpect(jsonPath("$.address", is(museum.getAddress())))
                .andExpect(jsonPath("$.phone", is(museum.getPhone())))
                .andExpect(jsonPath("$.founded", is(museum.getFounded())));

        verify(museumService, times(1)).findMuseumWithWorks(any(Integer.class));
        assertEquals(1, museumIdCaptor.getValue());
    }

    @Test
    void findAllMuseums() throws Exception {
        when(museumService.findAllMuseumsWithWorks()).thenReturn(Collections.singletonList(museum));

        mockMvc.perform(get("/museum")
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(museum.getName())))
                .andExpect(jsonPath("$[0].address", is(museum.getAddress())))
                .andExpect(jsonPath("$[0].phone", is(museum.getPhone())))
                .andExpect(jsonPath("$[0].founded", is(museum.getFounded())));

        verify(museumService, times(1)).findAllMuseumsWithWorks();
    }

    @Test
    void saveMuseum() throws Exception {
        when(museumService.saveMuseum(museumCaptor.capture())).thenReturn(museum);

        mockMvc.perform(post("/museum")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(museum)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", is(museum.getName())))
                .andExpect(jsonPath("$.address", is(museum.getAddress())))
                .andExpect(jsonPath("$.phone", is(museum.getPhone())))
                .andExpect(jsonPath("$.founded", is(museum.getFounded())));

        verify(museumService, times(1)).saveMuseum(any(Museum.class));
        assertEquals(museum, museumCaptor.getValue());
    }

    @Test
    void updateMuseum() throws Exception {
        when(museumService.updateMuseum(museumIdCaptor.capture(), museumCaptor.capture())).thenReturn(museum);

        mockMvc.perform(put("/museum/{id}", 2)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(museum)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(museum.getName())))
                .andExpect(jsonPath("$.address", is(museum.getAddress())))
                .andExpect(jsonPath("$.phone", is(museum.getPhone())))
                .andExpect(jsonPath("$.founded", is(museum.getFounded())));

        verify(museumService, times(1)).updateMuseum(any(Integer.class), any(Museum.class));
        assertEquals(2, museumIdCaptor.getValue());
        assertEquals(museum, museumCaptor.getValue());
    }

    @Test
    void deleteMuseum() throws Exception {
        doNothing().when(museumService).deleteMuseum(museumIdCaptor.capture());

        mockMvc.perform(delete("/museum/{id}", 3)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(museumService, times(1)).deleteMuseum(any(Integer.class));
        assertEquals(3, museumIdCaptor.getValue());
    }
}
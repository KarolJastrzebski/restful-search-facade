package com.example.controller;

import com.example.repository.EventRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(EventController.class)
public class SearchFacadeRequestFilterTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EventRepository eventRepository;

    @Test
    public void filter_parameter_has_to_be_present() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/events/search"))
            .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    public void filter_parameter_has_to_have_valid_json() throws Exception {
        this.mockMvc
            .perform(
                MockMvcRequestBuilders.get("/events/search")
                    .requestAttr("filter", "a")
            )
            .andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

}

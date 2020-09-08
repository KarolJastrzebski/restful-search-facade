package com.example.controller;

import com.example.controller.request.SearchFacadeRequestFilter;
import com.example.model.Event;
import com.example.repository.EventRepository;
import com.example.service.QueryService;
import com.example.validation.ValidList;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import javax.validation.Valid;

@RestController
@RequestMapping("/events")
@Validated
public class EventController {

    private final EventRepository eventRepository;
    private final QueryService<Event> queryService;

    public EventController(EventRepository eventRepository, QueryService<Event> queryService) {
        this.eventRepository = eventRepository;
        this.queryService = queryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getResource(@PathVariable String id) {
        Event event = eventRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Event not found"));

        return ResponseEntity.ok(event);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Event>> search(@Valid @RequestParam("filter") ValidList<SearchFacadeRequestFilter> filter) {
        List<Event> events = queryService.query(filter);
        return ResponseEntity.ok(events);
    }
}
/*

invalid operators:
http://localhost:6868/events/search?filter=%7B%22attribute%22:%22foo%22,%22operator%22:%22bar%22%7D&filter=%7B%22attribute%22:%22a%22,%22operator%22:%22b%22,%22value%22:%22c%22%7D&filter=%7B%22attribute%22:%22z%22,%22operator%22:%22x%22,%22range%22:%7B%22from%22:%221%22,%22to%22:%22z%22%7D%7D

missing range.from:
http://localhost:6868/events/search?filter=%7B%22attribute%22:%22foo%22,%22operator%22:%22bar%22%7D&filter=%7B%22attribute%22:%22a%22,%22operator%22:%22b%22,%22value%22:%22c%22%7D&filter=%7B%22attribute%22:%22z%22,%22operator%22:%22x%22,%22range%22:%7B%22to%22:%22z%22%7D%7D
 */
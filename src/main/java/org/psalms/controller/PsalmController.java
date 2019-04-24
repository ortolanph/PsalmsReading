package org.psalms.controller;

import org.psalms.entities.Psalm;
import org.psalms.services.PsalmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/psalms")
public class PsalmController {

    @Autowired
    private PsalmsService service;

    @PostMapping
    public void createPsalmReading(@RequestBody Psalm psalm) {
        service.create(psalm);
    }

    @GetMapping
    public @ResponseBody
    List<Psalm> getAllPsalmReadings() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Psalm getPsalmById(@RequestParam long id) {
        return service.getById(id);
    }

    @GetMapping("/{id}/plan")
    public @ResponseBody
    int planReading(@RequestParam long id) {
        return service.planReading(id);
    }

    @GetMapping("/{id}/read")
    public @ResponseBody
    Psalm markAsRead(@RequestParam long id) {
        return service.markAsRead(id);
    }

    @GetMapping("/next")
    public @ResponseBody
    long nextUnreadPsalm() {
        return service.nextUnreadPsalm();
    }

    @GetMapping("/remaining")
    public @ResponseBody
    List<Psalm> remainingPsalmsInOrder() {
        return service.remainingPsalms();
    }
}

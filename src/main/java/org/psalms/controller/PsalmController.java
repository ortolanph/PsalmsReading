package org.psalms.controller;

import org.psalms.entities.Psalm;
import org.psalms.services.PsalmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @GetMapping("/{number}")
    public @ResponseBody
    Psalm getPsalmByNumber(@PathVariable("number") int number) {
        return service.getByNumber(number);
    }

    @GetMapping("/{number}/plan")
    public @ResponseBody
    int planReading(@PathVariable("number") int number) {
        return service.planReading(number);
    }

    @GetMapping("/{number}/read")
    public @ResponseBody
    Psalm markAsRead(@PathVariable("number") int number) {
        return service.markAsRead(number);
    }

    @GetMapping("/next")
    public @ResponseBody
    int nextUnreadPsalm() {
        return service.nextUnreadPsalm();
    }

    @GetMapping("/remaining")
    public @ResponseBody
    List<Psalm> remainingPsalmsInOrder() {
        return service.remainingPsalms();
    }

    @GetMapping("/unread")
    public @ResponseBody
    List<Psalm> unread() {
        return service.unread();
    }

    @GetMapping("/unplanned")
    public @ResponseBody
    List<Psalm> unplanned() {
        return service.unplanned();
    }

    @PostMapping("/upload")
    public @ResponseBody
    List<Psalm> uploadCSV(@RequestParam("file") MultipartFile file) throws Exception {
        return service.uploadFileContents(file.getBytes());
    }
}

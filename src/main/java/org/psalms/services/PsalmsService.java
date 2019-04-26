package org.psalms.services;

import org.psalms.entities.Psalm;
import org.psalms.repositories.PsalmRepository;
import org.psalms.utils.CSVPsalmFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PsalmsService {

    @Autowired
    private PsalmRepository repository;

    public void create(Psalm psalm) {
        repository.save(psalm);
    }

    public List<Psalm> getAll() {
        return repository.findAll();
    }

    public Psalm getByNumber(int number) {
        return repository.findByPsalmNumber(number);
    }

    public int planReading(int number) {
        Psalm psalm = repository.findByPsalmNumber(number);

        psalm.readingOrder = repository.cuurentReadingOrder() + 1;
        repository.save(psalm);

        return psalm.readingOrder;
    }

    public Psalm markAsRead(int number) {
        Psalm psalm = repository.findByPsalmNumber(number);

        psalm.read = true;

        return repository.save(psalm);
    }

    public int nextUnreadPsalm() {
        return remainingPsalms().get(0).psalmNumber;
    }

    public List<Psalm> remainingPsalms() {
        return repository.remainingPsalms();
    }

    public List<Psalm> unread() {
        return repository.unread();
    }

    public List<Psalm> unplanned() {
        return repository.unplanned();
    }

    public List<Psalm> uploadFileContents(byte[] bytes) {
        List<Psalm> psalms = new ArrayList<>();

        CSVPsalmFunction csvPsalmFunction = new CSVPsalmFunction();
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        BufferedReader reader = new BufferedReader(new InputStreamReader(bais));

        List<String> lines = reader.lines().collect(Collectors.toList());

        psalms = lines
                .stream()
                .map(p -> p.split(","))
                .map(csvPsalmFunction)
                .collect(Collectors.toList());

        repository.saveAll(psalms);
        return psalms;
    }
}

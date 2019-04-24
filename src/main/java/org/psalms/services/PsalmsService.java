package org.psalms.services;

import org.psalms.entities.Psalm;
import org.psalms.repositories.PsalmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
        try {
            List<String> lines = Files.readAllLines(Files.createTempFile("upload", "csv"));


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

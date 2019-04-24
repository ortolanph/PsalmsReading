package org.psalms.services;

import org.psalms.entities.Psalm;
import org.psalms.repositories.PsalmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PsalmsService {

    @Autowired
    private PsalmRepository repository;

    public void create(Psalm psalm) {
        repository.save(psalm);
    }

    public List<Psalm> getAll() {
        return repository.findAll();
    }

    public Psalm getById(long id) {
        return repository.findById(id).get();
    }

    public int planReading(long id) {
        Psalm psalm = repository.findById(id).get();

        psalm.readingOrder = repository.cuurentReadingOrder() + 1;
        repository.save(psalm);

        return psalm.readingOrder;
    }

    public Psalm markAsRead(long id) {
        Psalm psalm = repository.findById(id).get();

        psalm.read = true;

        return repository.save(psalm);
    }

    public long nextUnreadPsalm() {
        return remainingPsalms().get(0).id;
    }

    public List<Psalm> remainingPsalms() {
        return repository.remainingPsalms();
    }
}

package org.psalms.repositories;

import org.psalms.entities.Psalm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsalmRepository extends JpaRepository<Psalm, Long> {

    @Query("SELECT MAX(p.readingOrder) FROM Psalm p WHERE p.read = FALSE")
    int cuurentReadingOrder();

    @Query("SELECT p FROM Psalm p WHERE p.read = FALSE AND p.readingOrder > 0 ORDER BY p.readingOrder")
    List<Psalm> remainingPsalms();

    Psalm findByPsalmNumber(int number);

    @Query("SELECT p FROM Psalm p WHERE p.read = FALSE and p.readingOrder > 0")
    List<Psalm> unread();

    @Query("SELECT p FROM Psalm p WHERE p.read = FALSE AND p.readingOrder = 0")
    List<Psalm> unplanned();
}

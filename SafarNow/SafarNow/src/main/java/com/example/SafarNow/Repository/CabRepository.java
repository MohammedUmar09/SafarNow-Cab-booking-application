package com.example.SafarNow.Repository;

import com.example.SafarNow.Model.Cab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CabRepository extends JpaRepository<Cab, Integer> {

    @Query("select c from Cab c where c.available = true order by rand() limit 1")
    Cab getAvailableCab();
}

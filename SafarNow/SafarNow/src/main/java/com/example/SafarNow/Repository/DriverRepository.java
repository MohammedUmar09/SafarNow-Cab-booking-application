package com.example.SafarNow.Repository;

import com.example.SafarNow.Model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {


    @Query(value = "SELECT * FROM driver WHERE cab_id = :cabId ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Driver getDriverbyCabId(int cabId);
}

package com.example.SafarNow.Repository;

import com.example.SafarNow.Enum.Gender;
import com.example.SafarNow.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByGender(Gender gender);

    List<Customer> findByGenderAndAge(Gender gender, int age);

    @Query("select c from Customer c where c.gender = :gender and c.age > :age")//HQL-Hibernate Query Language
    List<Customer> getAgeGreaterThan(@Param("gender") Gender gender,
                                     @Param("age") int age);

//    @Query(value = "select * from customer where gender = :gender and age > :age",
//    nativeQuery = true)
//    List<Customer> getAgeGreaterThan(@Param("gender") String gender,
//                                     @Param("age") int age);
}

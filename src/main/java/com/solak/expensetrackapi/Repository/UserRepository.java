package com.solak.expensetrackapi.Repository;

import com.solak.expensetrackapi.Model.Et_Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<Et_Users, Long> {


    Optional<Et_Users> findByEmail(String email);

    Integer countByEmail(String email);

}

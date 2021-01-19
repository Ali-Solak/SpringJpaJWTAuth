package com.solak.expensetrackapi.Repository;

import com.solak.expensetrackapi.Model.Category;
import com.solak.expensetrackapi.Model.Et_Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {

    List<Category> findAll();
    Set<Et_Users> findByUsers();


}

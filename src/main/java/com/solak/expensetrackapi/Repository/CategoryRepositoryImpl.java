package com.solak.expensetrackapi.Repository;

import com.solak.expensetrackapi.Exception.EtBadRequestException;
import com.solak.expensetrackapi.Exception.EtResourceNotFoundException;
import com.solak.expensetrackapi.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryRepositoryImpl {

    @Autowired
    CategoryRepository categoryRepository;


    public Category create(Category category) throws EtBadRequestException {
        try {
            return categoryRepository.save(category);

        } catch (Exception e) {
            throw new EtBadRequestException("Invalid request");

        }
    }

//    public Category findById(Long userId, Long categoryId) throws EtResourceNotFoundException {
//        try {
//            categoryRepository.findByUsers(userId);
//
//        } catch (Exception e) {
//            throw new EtResourceNotFoundException("Category not found");
//        }
//    }

}

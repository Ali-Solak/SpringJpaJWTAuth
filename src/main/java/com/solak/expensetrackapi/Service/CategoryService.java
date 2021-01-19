package com.solak.expensetrackapi.Service;

import com.solak.expensetrackapi.Exception.EtBadRequestException;
import com.solak.expensetrackapi.Exception.EtResourceNotFoundException;
import com.solak.expensetrackapi.Model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> fetchAllCategories(Integer userId);
    Category fetchCategoryById(Long userId, Integer categoryId) throws EtResourceNotFoundException;
    Category addCategory(Long userId, String title, String description) throws EtBadRequestException;
    void updateCategory(Long userId, Long categoryId, Category category) throws EtBadRequestException;
    void removeCategoryWithAllTransactions(Long userId, Long categoryId) throws EtResourceNotFoundException;
}

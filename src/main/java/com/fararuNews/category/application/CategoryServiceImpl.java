package com.fararuNews.category.application;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.category.infrastructure.repository.CategoryRepository;
import com.fararuNews.error.CategoryException;
import com.fararuNews.error.ErrorMessages;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;


    @Override
    public List<RegisterWebSite> findByCategory(String category) {
        try {
            List<RegisterWebSite> registerWebSite = categoryRepository.findByCategory(category);
            if (registerWebSite.size() != 0) {
                return registerWebSite;
            } else {
                throw new CategoryException(ErrorMessages.NO_DATA_ERROR.getErrorMessage());
            }
        } catch (Exception e) {
            throw new CategoryException(e.getMessage());

        }
    }


}

package com.fararuNews.category.application;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<RegisterWebSite> findByCategory(String category);


}

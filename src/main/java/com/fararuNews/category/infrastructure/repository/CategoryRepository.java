package com.fararuNews.category.infrastructure.repository;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<RegisterWebSite, Long> {

    @Query("select w from RegisterWebSite w where w.category like %:category%")
    List<RegisterWebSite> findByCategory(@Param("category") String category);
}

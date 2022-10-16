package com.fararuNews.news.infrastructure.persistence;

import com.fararuNews.news.domain.News;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends CrudRepository<News, Long> {

    @Query("select n from News n where n.link like %:link%")
    Page<News> findByLink(@Param("link") String link, Pageable pageable);

    @Query("SELECT news from News news ,RegisterWebSite website where news.category.category=website.category and news.category.category like %:category%")
    Page<News> findByCategory(@Param("category") String category, Pageable pageable);

    Page<News> findAll(Pageable pageable);

}

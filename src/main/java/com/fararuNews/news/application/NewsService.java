package com.fararuNews.news.application;

import com.fararuNews.news.domain.News;
import com.fararuNews.news.dto.NewsAddDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface NewsService {

    @Transactional
    void registerFromUrl();

    @Transactional
    void registerByPerson(NewsAddDto newsAddDto);

    @Transactional
    void edit(NewsAddDto newsAddDto);

    @Transactional
    void deleteById(Long id);

    Page<News> findAll(Pageable pageable);

    News findById(Long id);

    Page<News> findByLink(String link, Pageable pageable);

    Page<News> findByCategory(String category, Pageable pageable);


}

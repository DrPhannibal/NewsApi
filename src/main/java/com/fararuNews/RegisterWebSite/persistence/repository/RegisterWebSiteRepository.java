package com.fararuNews.RegisterWebSite.persistence.repository;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterWebSiteRepository extends CrudRepository<RegisterWebSite, Long> {

    Page<RegisterWebSite> findAll(Pageable pageable);


    List<RegisterWebSite> findAll();

}

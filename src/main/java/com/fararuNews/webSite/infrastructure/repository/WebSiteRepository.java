package com.fararuNews.webSite.infrastructure.repository;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebSiteRepository extends CrudRepository<RegisterWebSite, Long> {

    @Query("select w from RegisterWebSite w where w.url like %:url%")
    List<RegisterWebSite> findByUrl(@Param("url") String url);
}

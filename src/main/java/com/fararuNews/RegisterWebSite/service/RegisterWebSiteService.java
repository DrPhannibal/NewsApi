package com.fararuNews.RegisterWebSite.service;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.RegisterWebSite.dto.RegisterWebSiteAddDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

public interface RegisterWebSiteService {

    @Transactional
    RegisterWebSite save(RegisterWebSiteAddDto registerWebSiteAddDto);

    @Transactional
    RegisterWebSite edit(RegisterWebSiteAddDto registerWebSiteAddDto);

    @Transactional
    void delete(Long id);

    Page<RegisterWebSite> findAll(Pageable pageable);

    RegisterWebSite findById(Long id);
}

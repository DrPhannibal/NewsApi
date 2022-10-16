package com.fararuNews.webSite.application;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;

import java.util.List;

public interface WebSiteService {

    List<RegisterWebSite> findByUrl(String url);


}

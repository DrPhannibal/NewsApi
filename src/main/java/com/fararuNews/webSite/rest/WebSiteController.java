package com.fararuNews.webSite.rest;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.webSite.application.WebSiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(name = "webSite", path = "webSite")
public class WebSiteController {

    @Autowired
    private WebSiteService webSiteService;

    @GetMapping(name = "findByUrl", path = "/find-by-url/{url}")
    public ResponseEntity findByUrl(@PathVariable(name = "url") String url) {
        List<RegisterWebSite> registerWebSite = webSiteService.findByUrl(url);
        return new ResponseEntity(registerWebSite, HttpStatus.OK);
    }
}

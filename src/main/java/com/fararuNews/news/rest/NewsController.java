package com.fararuNews.news.rest;

import com.fararuNews.error.ExceptionAdvice;
import com.fararuNews.news.application.NewsService;
import com.fararuNews.news.domain.News;
import com.fararuNews.news.dto.NewsAddDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(name = "News", path = "News")
public class NewsController {

    @Autowired
    ExceptionAdvice exceptionAdvice;
    @Autowired
    private NewsService newsService;

    @PostMapping(name = "registerFromUrl", path = "/register-from-url")
    public ResponseEntity RegisterFromUrl() {
        newsService.registerFromUrl();
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @PostMapping(name = "registerByPerson", path = "/register-by-person")
    public ResponseEntity RegisterByPerson(@Valid NewsAddDto newsAddDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        newsService.registerByPerson(newsAddDto);
        return new ResponseEntity(newsAddDto, HttpStatus.OK);
    }


    @GetMapping(name = "showNewsById", path = "/show/{id}")
    public ResponseEntity createShowForm(@PathVariable(name = "id") Long id) {
        News news = newsService.findById(id);
        return new ResponseEntity(news, HttpStatus.OK);
    }


    @PostMapping(name = "edit", path = "/edit")
    public ResponseEntity edit(@Valid NewsAddDto newsAddDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity(bindingResult.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        newsService.edit(newsAddDto);
        return new ResponseEntity(true, HttpStatus.OK);
    }


    @GetMapping(name = "delete", path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        newsService.deleteById(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }


    @GetMapping(name = "listOfAllNews", path = "/list/{page}")
    public ResponseEntity findAll(@PathVariable(name = "page") int page) {
        int size = 80;
        Pageable paging = new PageRequest(page, size);
        Page<News> newsList = newsService.findAll(paging);
        return new ResponseEntity(newsList, HttpStatus.OK);
    }


    @GetMapping(name = "findByCategory", path = "search/{category}/{page}")
    public ResponseEntity findByCategory(@PathVariable(name = "category") String category,
                                         @PathVariable(name = "page") int page) {
        int size = 80;
        Pageable paging = new PageRequest(page, size);
        Page<News> newsList = newsService.findByCategory(category, paging);
        return new ResponseEntity(newsList, HttpStatus.OK);

    }

    @GetMapping(name = "findByLink", value = "find-by-link/{link}/{page}")
    public ResponseEntity findByLink(@PathVariable(name = "link") String link,
                                     @PathVariable(name = "page") int page) {
        int size = 80;
        Pageable paging = new PageRequest(page, size);
        Page<News> newsList = newsService.findByLink(link, paging);
        return new ResponseEntity(newsList, HttpStatus.OK);
    }


}































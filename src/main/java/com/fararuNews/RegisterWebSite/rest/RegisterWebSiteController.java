package com.fararuNews.RegisterWebSite.rest;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.RegisterWebSite.dto.RegisterWebSiteAddDto;
import com.fararuNews.RegisterWebSite.service.RegisterWebSiteService;
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
@RequestMapping(name = "RegisterWebSite",path = "Register-WebSite")
public class RegisterWebSiteController {

    @Autowired
    private RegisterWebSiteService registerWebSiteService;

    @PostMapping(name = "save",path = "/save")
    public ResponseEntity saveWebSite(@Valid RegisterWebSiteAddDto registerWebSiteAddDto,BindingResult binding) {
        if (binding.hasErrors()){
                return new ResponseEntity(binding.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        RegisterWebSite registerWebSite = registerWebSiteService.save(registerWebSiteAddDto);
        return new ResponseEntity(registerWebSite, HttpStatus.OK);
    }

    @GetMapping(name = "showNewsById",path = "/show/{id}")
    public ResponseEntity show(@PathVariable(name = "id") Long id) {
        RegisterWebSite registerWebSite = registerWebSiteService.findById(id);
        return new ResponseEntity(registerWebSite, HttpStatus.OK);
    }

    @PostMapping(name = "edit",path = "/edit")
    public ResponseEntity editWebSite(@Valid RegisterWebSiteAddDto registerWebSiteAddDto,BindingResult binding) {
        if (binding.hasErrors()){
            return new ResponseEntity(binding.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        RegisterWebSite registerWebSite = registerWebSiteService.edit(registerWebSiteAddDto);
        return new ResponseEntity(registerWebSite, HttpStatus.OK);
    }

    @GetMapping(name = "delete",path = "/delete/{id}")
    public ResponseEntity delete(@PathVariable(name = "id") Long id) {
        registerWebSiteService.delete(id);
        return new ResponseEntity(true, HttpStatus.OK);
    }

    @GetMapping(name ="findAll",path = "/list/{page}")
    public ResponseEntity findAll(@PathVariable(name = "page") int page) {
        int size=80;
        Pageable paging = new PageRequest(page, size);
        Page<RegisterWebSite> foundedWebSite = registerWebSiteService.findAll(paging);
        return new ResponseEntity(foundedWebSite, HttpStatus.OK);
    }

}

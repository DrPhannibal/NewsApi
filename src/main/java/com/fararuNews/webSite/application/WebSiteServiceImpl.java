package com.fararuNews.webSite.application;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.error.CategoryException;
import com.fararuNews.error.ErrorMessages;
import com.fararuNews.error.WebSiteException;
import com.fararuNews.webSite.infrastructure.repository.WebSiteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class WebSiteServiceImpl implements WebSiteService {

    @Autowired
    private final WebSiteRepository webSiteRepository;


    @Override
    public List<RegisterWebSite> findByUrl(String url) {
        try {
            List<RegisterWebSite> registerWebSite = webSiteRepository.findByUrl(url);
            if (registerWebSite.size() != 0) {
                return registerWebSite;
            } else {
                throw new WebSiteException(ErrorMessages.NO_DATA_ERROR.getErrorMessage());
            }
        } catch (Exception e) {
            throw new CategoryException(e.getMessage());
        }
    }
}

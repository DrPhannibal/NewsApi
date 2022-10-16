package com.fararuNews.RegisterWebSite.service;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.RegisterWebSite.dto.RegisterWebSiteAddDto;
import com.fararuNews.RegisterWebSite.persistence.repository.RegisterWebSiteRepository;
import com.fararuNews.error.ErrorMessages;
import com.fararuNews.error.RegisterWebSiteException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class RegisterWebSiteServiceImpl implements RegisterWebSiteService {

    @Autowired
    private final RegisterWebSiteRepository registerWebSiteRepository;

    @Override
    public RegisterWebSite save(RegisterWebSiteAddDto registerWebSiteAddDto) {
        try {
            RegisterWebSite registerWebSite = new RegisterWebSite();
            BeanUtils.copyProperties(registerWebSiteAddDto, registerWebSite);
            return registerWebSiteRepository.save(registerWebSite);
        } catch (Exception e) {
            throw new RegisterWebSiteException(ErrorMessages.REGISTRATION_ERROR.getErrorMessage());
        }

    }

    @Override
    public RegisterWebSite edit(RegisterWebSiteAddDto registerWebSiteAddDto) {
        try {
            RegisterWebSite registerWebSite = findById(registerWebSiteAddDto.getId());
            BeanUtils.copyProperties(registerWebSiteAddDto, registerWebSite);
            return registerWebSiteRepository.save(registerWebSite);
        } catch (Exception e) {
            throw new RegisterWebSiteException(ErrorMessages.REGISTRATION_ERROR.getErrorMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try {
            registerWebSiteRepository.delete(id);
        } catch (Exception e) {
            throw new RegisterWebSiteException(ErrorMessages.DELETE_ERROR.getErrorMessage());
        }
    }

    @Override
    public Page<RegisterWebSite> findAll(Pageable pageable) {
        try {
            Page<RegisterWebSite> registerWebSiteList = registerWebSiteRepository.findAll(pageable);
            if (registerWebSiteList.getContent().size() != 0) {
                return registerWebSiteList;
            } else {
                throw new RegisterWebSiteException(ErrorMessages.NO_DATA_ERROR.getErrorMessage());
            }
        } catch (Exception e) {
            throw new RegisterWebSiteException(e.getMessage());
        }
    }

    @Override
    public RegisterWebSite findById(Long id) {
        try {
            RegisterWebSite registerWebSite = registerWebSiteRepository.findOne(id);
            if (registerWebSite != null) {
                return registerWebSite;
            } else {
                throw new RegisterWebSiteException(ErrorMessages.NO_DATA_ERROR.getErrorMessage());
            }
        } catch (Exception e) {
            throw new RegisterWebSiteException(e.getMessage());
        }
    }
}

package com.fararuNews.RegisterWebSite.dto;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class RegisterWebSiteAddDto implements Serializable {
    private static final long serialVersionUID = -6574608271483991682L;

    private Long id;

    @NotNull(message = "{category.notEmpty}")
    @Size(min = 3, max = 200, message = "{category.size}")
    private String category;

    @NotNull(message = "{url.notEmpty}")
    @URL(message = "{url.notValid}")
    private String url;
}

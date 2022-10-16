package com.fararuNews.news.dto;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class NewsAddDto implements Serializable {

    private static final long serialVersionUID = 5411898365185229579L;

    private Long id;

    @NotNull(message = "{title.notEmpty}")
    @Size(min = 3,max = 200,message = "{title.size}")
    private String title;

    @NotNull(message = "{url.notEmpty}")
    @Size(min = 3,max = 200,message = "{url.size}")
    private String link;

    private String description;

    private String author;

    private String enclosure;

    @NotNull(message = "{category.notEmpty}")
    private RegisterWebSite category;

    private LocalDateTime pubDate;

}

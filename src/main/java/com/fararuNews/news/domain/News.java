package com.fararuNews.news.domain;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TB_News")
public class News {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String title;

    @Column(name = "link")
    private String link;

    @Column(name = "description")
    private String description;

    @Column(name = "author")
    private String author;

    @Column(name = "enclosure")
    private String enclosure;

    @Column(name = "pubDate")
    private LocalDateTime pubDate;

    @JoinColumn(name = "fk_cat", referencedColumnName = "ID")
    @ManyToOne(optional = false)
    private RegisterWebSite category;


}

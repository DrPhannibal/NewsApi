package com.fararuNews.news.application;

import com.fararuNews.RegisterWebSite.domain.RegisterWebSite;
import com.fararuNews.RegisterWebSite.persistence.repository.RegisterWebSiteRepository;
import com.fararuNews.error.ErrorMessages;
import com.fararuNews.error.ExceptionAdvice;
import com.fararuNews.error.NewsException;
import com.fararuNews.news.domain.News;
import com.fararuNews.news.dto.NewsAddDto;
import com.fararuNews.news.infrastructure.persistence.NewsRepository;
import com.fararuNews.utility.Utils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class NewsServiceImpl implements NewsService {


    @Autowired
    private final NewsRepository newsRepository;

    @Autowired
    private final RegisterWebSiteRepository registerWebSiteRepository;

    @Autowired
    ExceptionAdvice exceptionAdvice;

    @Override
    public void registerFromUrl() {
        try {
            List<RegisterWebSite> siteList = registerWebSiteRepository.findAll();
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
            if (siteList.size() != 0) {
                for (int i = 0; i < siteList.size(); i++) {
                    NodeList nodeList = InitialPreparation(siteList, i);
                    getNewsElement(siteList, dateFormat, i, nodeList);
                }
            } else {
                throw new NewsException(ErrorMessages.URL_ERROR.getErrorMessage());
            }
        } catch (Exception e) {
            throw new NewsException(e.getMessage());
        }
    }

    @Override
    public void registerByPerson(NewsAddDto newsAddDto) {
        try {
            News news = new News();
            BeanUtils.copyProperties(newsAddDto, news);
            news.setPubDate(Utils.getUtc());
            newsRepository.save(news);
        } catch (Exception e) {
            throw new NewsException(ErrorMessages.REGISTRATION_ERROR.getErrorMessage());
        }
    }

    @Override
    public void edit(NewsAddDto newsAddDto) {
        try {
            News news = findById(newsAddDto.getId());
            BeanUtils.copyProperties(newsAddDto, news);
            news.setPubDate(Utils.getUtc());
            newsRepository.save(news);
        } catch (Exception e) {
            throw new NewsException(ErrorMessages.REGISTRATION_ERROR.getErrorMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        try {
            newsRepository.delete(id);
        } catch (Exception e) {
            throw new NewsException(ErrorMessages.DELETE_ERROR.getErrorMessage());
        }
    }

    @Override
    public Page<News> findAll(Pageable pageable) {
        try {
            Page<News> news = newsRepository.findAll(pageable);
            if (news.getContent().size()!=0) {
                return news;
            } else {
                throw new NewsException(ErrorMessages.NEWS_NOT_EXIST.getErrorMessage());
            }
        } catch (Exception e) {
            throw new NewsException(e.getMessage());
        }
    }

    @Override
    public News findById(Long id) {
        try {
            News news = newsRepository.findOne(id);
            if (news != null) {
                return news;
            } else {
                throw new NewsException(ErrorMessages.NEWS_NOT_EXIST.getErrorMessage());
            }
        } catch (Exception e) {
            throw new NewsException(e.getMessage());
        }
    }

    @Override
    public Page<News> findByLink(String link,Pageable pageable) {
        try {
            Page<News> newsList = newsRepository.findByLink(link,pageable);
            if (newsList.getContent().size() != 0) {
                return newsList;
            } else {
                throw new NewsException(ErrorMessages.NEWS_NOT_EXIST.getErrorMessage());
            }
        } catch (Exception e) {
            throw new NewsException(e.getMessage());

        }
    }

    @Override
    public Page<News> findByCategory(String category,Pageable pageable) {
        try {
            Page<News> newsList = newsRepository.findByCategory(category,pageable);
            if (newsList.getContent().size() != 0) {
                return newsList;
            } else {
                throw new NewsException(ErrorMessages.NEWS_NOT_EXIST.getErrorMessage());
            }
        } catch (Exception e) {
            throw new NewsException(e.getMessage());

        }
    }


    private NodeList InitialPreparation(List<RegisterWebSite> registerWebSites, int i) throws ParserConfigurationException, SAXException, IOException {
        String URL = registerWebSites.get(i).getUrl();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(URL);
        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName("item");
    }

    private void getNewsElement(List<RegisterWebSite> registerWebSites, DateTimeFormatter dateFormat, int i, NodeList nodeList) throws ParseException {
        for (int j = 0; j < nodeList.getLength(); j++) {
            Node node = nodeList.item(j);
            News news = new News();
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                initialization(registerWebSites, dateFormat, i, (Element) node, news);
                newsRepository.save(news);
            }
        }
    }

    private void initialization(List<RegisterWebSite> registerWebSites, DateTimeFormatter dateFormat, int i, Element node, News news) throws ParseException {
        Element eElement = node;
        if (eElement.getElementsByTagName("title").item(0) != null) {
            news.setTitle(eElement.getElementsByTagName("title").item(0).getTextContent());
        }
        if (eElement.getElementsByTagName("link").item(0) != null) {
            news.setLink(eElement.getElementsByTagName("link").item(0).getTextContent());
        }
        if (eElement.getElementsByTagName("description").item(0) != null) {
            news.setDescription(StringUtils.left(eElement.getElementsByTagName("description").item(0).getTextContent(), 255));
        }
        if (eElement.getElementsByTagName("author").item(0) != null) {
            news.setAuthor(eElement.getElementsByTagName("author").item(0).getTextContent());
        }
        if (eElement.getElementsByTagName("enclosure").item(0) != null) {
            news.setEnclosure(eElement.getElementsByTagName("enclosure").item(0).getAttributes().item(2).getTextContent());
        }
        if (eElement.getElementsByTagName("pubDate").item(0) != null) {
            String date = StringUtils.left(eElement.getElementsByTagName("pubDate").item(0).getTextContent(), 20);
            LocalDateTime formatDateTime = LocalDateTime.parse(date, dateFormat);
            news.setPubDate(formatDateTime);

        }
        news.setCategory(registerWebSites.get(i));
    }

}

package com.example.News.services;

import java.util.Optional;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.News.comparators.SortByDate;
import com.example.News.entities.News;
import com.example.News.repositories.NewsRepository;
import jakarta.transaction.Transactional;

@Service
public class NewsService {

  @Autowired
  NewsRepository repository;

  public List<News> findAll() {
    return repository.findAll().stream().filter((news) -> {return news.getIsActive();}).sorted(new SortByDate()).toList();
  }

  public News findById(String id) throws Exception {
    Optional<News> response = repository.findById(id);
    News news;
    if (response.isPresent()) {
      news = response.get();
      if (news.getIsActive()) {
        return news;
      }
    }
    throw new Exception("Couldn't find the news");
  }

  public News getOne(String id) {
    News news = repository.getOne(id);
    if (news.getIsActive()) {
      return news;
    }
    return null;
  }
  
  @Transactional
  public void createNews(String title, String body) throws Exception {
    validate(title, body);
    News news = new News(title, body);
    repository.save(news);
  }

  @Transactional
  public void modifyNews(String id, String title, String body) throws Exception {

    Optional<News> response = repository.findById(id);
    News news;

    if (response.isPresent()) {
      news = response.get();
      news.setTitle(title);
      news.setBody(body);
      news.setDate(new Date());

      repository.save(news);
    }
  }

  @Transactional
  public void deleteNews(String id) {
    Optional<News> response = repository.findById(id);
    News news;

    if (response.isPresent()) {
      news = response.get();
      news.setIsActive(false);
      news.setDate(new Date());

      repository.save(news);
    }
  }

  private void validate (String title, String body) throws Exception {
    if (title == null || title.isEmpty()) {
        throw new Exception("Title can't be null or empty");
    }
    if (body == null || body.isEmpty()) {
        throw new Exception("Body can't be null or empty");
    }
}
}

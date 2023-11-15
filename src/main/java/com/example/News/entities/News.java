package com.example.News.entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String body;
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    private Boolean isActive;

    public News(String title, String body) {
        this.title = title;
        this.body = body;
        this.date = new Date();
        this.isActive = true;
    }

    public News() {
    }

    public String getId() {
      return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setIsActive(Boolean isActive) {
      this.isActive = isActive;
    }

    public Boolean getIsActive() {
      return isActive;
    }

    @Override
    public String toString() {
        return "{ " +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", date='" + date + '\'' +
                ", is active='" + isActive + '\'' +
                " }";
    }
}

package com.example.News.comparators;

import java.util.Comparator;

import com.example.News.entities.News;

public class SortByDate implements Comparator<News> {
  public int compare(News a, News b) {
    return b.getDate().compareTo(a.getDate());
  }
}

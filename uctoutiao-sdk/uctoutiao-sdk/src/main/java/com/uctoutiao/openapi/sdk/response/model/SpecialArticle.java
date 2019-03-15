/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class SpecialArticle {

  private String recoid = "";

  private String id = "";

  private String title = "";

  @JsonProperty("grab_time")
  private long grabTime;

  private String url = "";

  @JsonProperty("item_type")
  private int itemType = 0;

  @JsonProperty("style_type")
  private int styleType = 0;

  private List<Article> articles = new ArrayList<Article>();

  private List<String> tags = new ArrayList<String>();

  @JsonProperty("enter_desc")
  private String enterDesc = "";

  @JsonProperty("bottom_left_mark")
  private Object bottomLeftMark = new HashMap<Object, Object>();

  public String getRecoid() {
    return recoid;
  }

  public void setRecoid(String recoid) {
    this.recoid = recoid;
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

  public long getGrabTime() {
    return grabTime;
  }

  public void setGrabTime(long grabTime) {
    this.grabTime = grabTime;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public int getItemType() {
    return itemType;
  }

  public void setItemType(int itemType) {
    this.itemType = itemType;
  }

  public int getStyleType() {
    return styleType;
  }

  public void setStyleType(int styleType) {
    this.styleType = styleType;
  }

  public List<Article> getArticles() {
    return articles;
  }

  public void setArticles(List<Article> articles) {
    this.articles = articles;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public String getEnterDesc() {
    return enterDesc;
  }

  public void setEnterDesc(String enterDesc) {
    this.enterDesc = enterDesc;
  }

  public Object getBottomLeftMark() {
    return bottomLeftMark;
  }

  public void setBottomLeftMark(Object bottomLeftMark) {
    this.bottomLeftMark = bottomLeftMark;
  }

  @Override
  public String toString() {
    return "SpecialArticle [recoid=" + recoid + ", id=" + id + ", title=" + title + ", grabTime="
        + grabTime + ", url=" + url + ", itemType=" + itemType + ", styleType=" + styleType
        + ", articles=" + articles + ", tags=" + tags + ", enterDesc=" + enterDesc
        + ", bottomLeftMark=" + bottomLeftMark + "]";
  }

}

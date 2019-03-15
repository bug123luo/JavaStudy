/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.uctoutiao.openapi.sdk.response.model.Article;
import com.uctoutiao.openapi.sdk.response.model.SpecialArticle;

public class ChannelResponse extends BaseResponse {

  private List<ChannelItem> items = new ArrayList<ChannelItem>();

  private List<ChannelItem> banners = new ArrayList<ChannelItem>();

  @JsonProperty("articles")
  private Map<String, Article> articlesMap = new LinkedHashMap<String, Article>();

  @JsonProperty("specials")
  private Map<String, SpecialArticle> specialsMap = new LinkedHashMap<String, SpecialArticle>();

  @JsonProperty("is_clean_cache")
  private Integer isCleanCache = 0;

  public List<ChannelItem> getItems() {
    return items;
  }

  public void setItems(List<ChannelItem> items) {
    this.items = items;
  }

  public List<ChannelItem> getBanners() {
    return banners;
  }

  public void setBanners(List<ChannelItem> banners) {
    this.banners = banners;
  }

  public Map<String, Article> getArticlesMap() {
    return articlesMap;
  }

  public void setArticlesMap(Map<String, Article> articlesMap) {
    this.articlesMap = articlesMap;
  }

  public Map<String, SpecialArticle> getSpecialsMap() {
    return specialsMap;
  }

  public void setSpecialsMap(Map<String, SpecialArticle> specialsMap) {
    this.specialsMap = specialsMap;
  }

  public Integer getIsCleanCache() {
    return isCleanCache;
  }

  public void setIsCleanCache(Integer isCleanCache) {
    this.isCleanCache = isCleanCache;
  }

  @Override
  public String toString() {
    return "ChannelResponse [items=" + items + ", articlesMap=" + articlesMap + ", specialsMap="
        + specialsMap + ", isCleanCache=" + isCleanCache + "]";
  }

  public static class ChannelItem {

    private String id;

    private String map;

    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getMap() {
      return map;
    }

    public void setMap(String map) {
      this.map = map;
    }

    @Override
    public String toString() {
      return "ChannelItem [id=" + id + ", map=" + map + "]";
    }

  }

}

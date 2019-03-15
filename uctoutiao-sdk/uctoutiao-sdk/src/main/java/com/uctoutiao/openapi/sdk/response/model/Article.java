/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

public class Article {

  private String recoid = "";

  private String id = "";

  private String title = "";

  @JsonProperty("item_type")
  private int itemType = 0;

  @JsonProperty("style_type")
  private int styleType = 0;

  @JsonProperty("grab_time")
  private long grabTime;

  @JsonProperty("publish_time")
  private long publishTime;

  private String url = "";

  @JsonProperty("source_name")
  private String sourceName = "";

  @JsonProperty("origin_src_name")
  private String originSourceName = "";

  private String summary = "";

  private List<String> tags = new ArrayList<String>();

  private List<String> category = new ArrayList<String>();

  private String content = "";

  @JsonProperty("content_length")
  private int contentLength = 0;

  private List<Thumbnail> thumbnails = new ArrayList<Thumbnail>();

  private List<Image> images = new ArrayList<Image>();

  @JsonProperty("cmt_cnt")
  private int commentCnt = 0;

  private List<Video> videos = new ArrayList<Video>();

  @JsonProperty("share_url")
  private String shareUrl = "";

  @JsonProperty("cmt_url")
  private String cmtUrl = "";

  @JsonProperty("show_impression_url")
  private String showImpressionUrl = "";

  @JsonProperty("like_cnt")
  private int likeCnt;

  @JsonProperty("support_cnt")
  private int supportCnt;

  @JsonProperty("oppose_cnt")
  private int opposeCnt;

  @JsonProperty("ad_content")
  private Map<String, String> adContent = new HashMap<String, String>();

  @JsonProperty("bottom_left_mark")
  private Object BottomLeftMark = new HashMap<Object, Object>();

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

  public long getGrabTime() {
    return grabTime;
  }

  public void setGrabTime(long grabTime) {
    this.grabTime = grabTime;
  }

  public long getPublishTime() {
    return publishTime;
  }

  public void setPublishTime(long publishTime) {
    this.publishTime = publishTime;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getSourceName() {
    return sourceName;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public String getOriginSourceName() {
    return originSourceName;
  }

  public void setOriginSourceName(String originSourceName) {
    this.originSourceName = originSourceName;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public List<String> getTags() {
    return tags;
  }

  public void setTags(List<String> tags) {
    this.tags = tags;
  }

  public List<String> getCategory() {
    return category;
  }

  public void setCategory(List<String> category) {
    this.category = category;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public int getContentLength() {
    return contentLength;
  }

  public void setContentLength(int contentLength) {
    this.contentLength = contentLength;
  }

  public List<Thumbnail> getThumbnails() {
    return thumbnails;
  }

  public void setThumbnails(List<Thumbnail> thumbnails) {
    this.thumbnails = thumbnails;
  }

  public List<Image> getImages() {
    return images;
  }

  public void setImages(List<Image> images) {
    this.images = images;
  }

  public int getCommentCnt() {
    return commentCnt;
  }

  public void setCommentCnt(int commentCnt) {
    this.commentCnt = commentCnt;
  }

  public List<Video> getVideos() {
    return videos;
  }

  public void setVideos(List<Video> videos) {
    this.videos = videos;
  }

  public String getShareUrl() {
    return shareUrl;
  }

  public void setShareUrl(String shareUrl) {
    this.shareUrl = shareUrl;
  }

  public String getCmtUrl() {
    return cmtUrl;
  }

  public void setCmtUrl(String cmtUrl) {
    this.cmtUrl = cmtUrl;
  }

  public String getShowImpressionUrl() {
    return showImpressionUrl;
  }

  public void setShowImpressionUrl(String showImpressionUrl) {
    this.showImpressionUrl = showImpressionUrl;
  }

  public int getLikeCnt() {
    return likeCnt;
  }

  public void setLikeCnt(int likeCnt) {
    this.likeCnt = likeCnt;
  }

  public int getSupportCnt() {
    return supportCnt;
  }

  public void setSupportCnt(int supportCnt) {
    this.supportCnt = supportCnt;
  }

  public int getOpposeCnt() {
    return opposeCnt;
  }

  public void setOpposeCnt(int opposeCnt) {
    this.opposeCnt = opposeCnt;
  }

  public Map<String, String> getAdContent() {
    return adContent;
  }

  public void setAdContent(Map<String, String> adContent) {
    this.adContent = adContent;
  }


  public Object getBottomLeftMark() {
    return BottomLeftMark;
  }

  public void setBottomLeftMark(Object bottomLeftMark) {
    BottomLeftMark = bottomLeftMark;
  }

  @Override
  public String toString() {
    return "Article [recoid=" + recoid + ", id=" + id + ", title=" + title + ", itemType=" + itemType + ", styleType="
        + styleType + ", grabTime=" + grabTime + ", publishTime=" + publishTime + ", url=" + url + ", sourceName="
        + sourceName + ", originSourceName=" + originSourceName + ", summary=" + summary + ", tags=" + tags
        + ", category=" + category + ", content=" + content + ", contentLength=" + contentLength + ", thumbnails="
        + thumbnails + ", images=" + images + ", commentCnt=" + commentCnt + ", videos=" + videos + ", shareUrl="
        + shareUrl + ", cmtUrl=" + cmtUrl + ", showImpressionUrl=" + showImpressionUrl + ", likeCnt=" + likeCnt
        + ", supportCnt=" + supportCnt + ", opposeCnt=" + opposeCnt + ", adContent=" + adContent + ", BottomLeftMark="
        + BottomLeftMark + "]";
  }

}

/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class Video {

  private String url = "";

  @JsonProperty("video_id")
  private String videoId = "";

  private long length;

  private Thumbnail poster = new Thumbnail();

  @JsonProperty("view_cnt")
  private int viewCnt;

  public String getUrl() {
    return url;
  }

  public Video setUrl(String url) {
    this.url = url;
    return this;
  }

  public String getVideoId() {
    return videoId;
  }

  public Video setVideoId(String videoId) {
    this.videoId = videoId;
    return this;
  }

  public long getLength() {
    return length;
  }

  public Video setLength(long length) {
    this.length = length;
    return this;
  }

  public Thumbnail getPoster() {
    return poster;
  }

  public Video setPoster(Thumbnail poster) {
    this.poster = poster;
    return this;
  }

  public int getViewCnt() {
    return viewCnt;
  }

  public Video setViewCnt(int viewCnt) {
    this.viewCnt = viewCnt;
    return this;
  }

  @Override
  public String toString() {
    return "Video [url=" + url + ", videoId=" + videoId + ", length=" + length + ", poster="
        + poster + ", viewCnt=" + viewCnt + "]";
  }


}

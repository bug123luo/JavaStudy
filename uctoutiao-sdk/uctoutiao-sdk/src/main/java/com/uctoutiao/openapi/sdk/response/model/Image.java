/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response.model;


public class Image {

  private int index;

  private String title = "";

  private String description = "";

  private int width = 0;

  private int height = 0;

  private String type;

  private String url = "";

  public int getIndex() {
    return index;
  }

  public Image setIndex(int index) {
    this.index = index;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Image setTitle(String title) {
    this.title = title;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public Image setDescription(String description) {
    this.description = description;
    return this;
  }

  public int getWidth() {
    return width;
  }

  public Image setWidth(int width) {
    this.width = width;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public Image setHeight(int height) {
    this.height = height;
    return this;
  }

  public String getType() {
    return type;
  }

  public Image setType(String type) {
    this.type = type;
    return this;
  }

  public String getUrl() {
    return url;
  }

  public Image setUrl(String url) {
    this.url = url;
    return this;
  }

  @Override
  public String toString() {
    return "Image [index=" + index + ", title=" + title + ", description=" + description
        + ", width=" + width + ", height=" + height + ", type=" + type + ", url=" + url + "]";
  }

}

/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response.model;

public class Thumbnail {

  private String url = "";

  private int width = 0;

  private int height = 0;

  private String type = "";

  public String getUrl() {
    return url;
  }

  public Thumbnail setUrl(String url) {
    this.url = url;
    return this;
  }

  public int getWidth() {
    return width;
  }

  public Thumbnail setWidth(int width) {
    this.width = width;
    return this;
  }

  public int getHeight() {
    return height;
  }

  public Thumbnail setHeight(int height) {
    this.height = height;
    return this;
  }

  public String getType() {
    return type;
  }

  public Thumbnail setType(String type) {
    this.type = type;
    return this;
  }
  

  @Override
  public String toString() {
    return "Thumbnail [url=" + url + ", width=" + width + ", height=" + height + ", type=" + type
        + "]";
  }
}

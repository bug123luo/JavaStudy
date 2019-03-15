/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response.model;

import org.codehaus.jackson.annotate.JsonProperty;

public class BottomLeftMark {

  private String mark = "";

  @JsonProperty("mark_color")
  private int markColor;

  @JsonProperty("mark_icon_url")
  private String markIconUrl = "";

  public BottomLeftMark(String mark, int markColor, String markIconUrl) {
    this.mark = mark;
    this.markColor = markColor;
    this.markIconUrl = markIconUrl;
  }

  public String getMark() {
    return mark;
  }

  public void setMark(String mark) {
    this.mark = mark;
  }

  public int getMarkColor() {
    return markColor;
  }

  public void setMarkColor(int markColor) {
    this.markColor = markColor;
  }

  public String getMarkIconUrl() {
    return markIconUrl;
  }

  public void setMarkIconUrl(String markIconUrl) {
    this.markIconUrl = markIconUrl;
  }

  @Override
  public String toString() {
    return "BottomLeftMark [mark=" + mark + ", markColor=" + markColor + ", markIconUrl="
        + markIconUrl + "]";
  }

}

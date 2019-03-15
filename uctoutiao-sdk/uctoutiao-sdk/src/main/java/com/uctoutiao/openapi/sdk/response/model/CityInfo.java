/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response.model;

public class CityInfo {
  
  private String code;

  private String name;

  private String letter;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLetter() {
    return letter;
  }

  public void setLetter(String letter) {
    this.letter = letter;
  }

  @Override
  public String toString() {
    return "CityInfo [code=" + code + ", name=" + name + ", letter=" + letter + "]";
  }

}

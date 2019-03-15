/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response;

import java.util.ArrayList;
import java.util.List;

import com.uctoutiao.openapi.sdk.response.model.CityInfo;

public class CitiesResponse extends BaseResponse {

  private List<CityInfo> cities = new ArrayList<CityInfo>();

  public List<CityInfo> getCities() {
    return cities;
  }

  public void setCities(List<CityInfo> cities) {
    this.cities = cities;
  }

  @Override
  public String toString() {
    return "CitiesResponse [cities=" + cities + "]";
  }

}

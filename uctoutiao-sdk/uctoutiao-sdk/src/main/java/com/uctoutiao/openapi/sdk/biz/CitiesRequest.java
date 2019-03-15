/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import java.util.Map;

import com.uctoutiao.openapi.sdk.BaseRequest;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.BaseResponse;
import com.uctoutiao.openapi.sdk.response.CitiesResponse;

public class CitiesRequest extends BaseRequest {

  public CitiesRequest(String app, String dn) {
    super(app, dn);
  }

  public CitiesResponse getChannels(String accessToken, Map<String, Object> params) throws OpenApiException {
    String url = String.format(URL_CITIES, server, version, accessToken, app, dn);
    url = this.appendParams(url, params);
    BaseResponse baseResponse = this.doGetRequet(url);
    String data = baseResponse.getData();
    CitiesResponse citiesResponse;
    try {
      citiesResponse = mapper.readValue(data, CitiesResponse.class);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.JSON_DATA_ERROR, e.getMessage());
    }
    return citiesResponse;
  }


}

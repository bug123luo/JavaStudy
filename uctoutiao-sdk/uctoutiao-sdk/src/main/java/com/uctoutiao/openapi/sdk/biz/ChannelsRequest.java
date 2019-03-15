/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import java.util.Map;

import com.uctoutiao.openapi.sdk.BaseRequest;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.BaseResponse;
import com.uctoutiao.openapi.sdk.response.ChannelsResponse;

public class ChannelsRequest extends BaseRequest {


  public ChannelsRequest(String app, String dn) {
    super(app, dn);
  }

  public ChannelsResponse getChannels(String accessToken, Map<String, Object> params) throws OpenApiException {
    String url = String.format(URL_CHANNELS, server, version, accessToken, app, dn);
    url = this.appendParams(url, params);
    BaseResponse baseResponse = this.doGetRequet(url);
    String data = baseResponse.getData();
    ChannelsResponse channelsResponse;
    try {
      channelsResponse = mapper.readValue(data, ChannelsResponse.class);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.JSON_DATA_ERROR, e.getMessage());
    }
    return channelsResponse;
  }


}

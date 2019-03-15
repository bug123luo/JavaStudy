/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import java.util.Map;

import com.uctoutiao.openapi.sdk.BaseRequest;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.BaseResponse;
import com.uctoutiao.openapi.sdk.response.ChannelResponse;

public class ChannelRequest extends BaseRequest {

  public ChannelRequest(String app, String dn) {
    super(app, dn);
  }

  public ChannelResponse getChannel(String accessToken, long cid, Map<String, Object> params) throws OpenApiException {
    String url = String.format(URL_CHANNEL, server, version, cid, accessToken, app, dn);
    url = this.appendParams(url, params);
    BaseResponse baseResponse = this.doGetRequet(url);
    String data = baseResponse.getData();
    ChannelResponse channelResponse;
    try {
      channelResponse = mapper.readValue(data, ChannelResponse.class);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.JSON_DATA_ERROR, e.getMessage());
    }
    return channelResponse;
  }

}

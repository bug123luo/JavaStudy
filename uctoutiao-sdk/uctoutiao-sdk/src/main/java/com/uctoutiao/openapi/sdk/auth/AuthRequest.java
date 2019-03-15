/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.auth;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSONObject;
import com.uctoutiao.openapi.sdk.BaseRequest;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.util.HttpClientUtil;

public class AuthRequest extends BaseRequest {

  public boolean getAccessToken(String appId, String appSecret) throws OpenApiException {

    List<NameValuePair> params = new ArrayList<NameValuePair>();
    params.add(new BasicNameValuePair("app_id", appId));
    params.add(new BasicNameValuePair("app_secret", appSecret));

    String url = String.format(URL_AUTH, this.server);

    String result;
    try {
      result = HttpClientUtil.post(url, params);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.HTTP_ERROR, e.getMessage());
    }
    if (StringUtils.isBlank(result)) {
      throw OpenApiException.BLANK_RESPONSE;
    }

    JSONObject json = (JSONObject) JSONObject.parse(result);
    System.out.println(json);
    Integer code = json.getInteger("code");
    if (code != 0) {
      throw new OpenApiException(code, json.getString("msg"));
    }
    return true;
  }


}

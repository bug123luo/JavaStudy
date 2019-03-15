/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uctoutiao.openapi.sdk.AppConfig;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.CitiesResponse;

public class ChannelsRequestTest {

  @Test
  public void testGetChannels() throws OpenApiException {
    CitiesRequest req = new CitiesRequest(AppConfig.APP_NAME, AppConfig.DN);
    CitiesResponse response = req.getChannels(AppConfig.ACCESS_TOKEN, null);
    Assert.assertEquals(response.getCities().size() > 0, true);
  }

}

/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uctoutiao.openapi.sdk.AppConfig;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.ChannelsResponse;

public class CitiesRequestTest {

  @Test
  public void testGetChannels() throws OpenApiException {
    ChannelsRequest req = new ChannelsRequest(AppConfig.APP_NAME, AppConfig.DN);
    ChannelsResponse response = req.getChannels(AppConfig.ACCESS_TOKEN, null);
    Assert.assertEquals(response.getChannels().size(), 0);
  }

}

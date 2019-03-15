/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uctoutiao.openapi.sdk.AppConfig;
import com.uctoutiao.openapi.sdk.biz.ClientEventRequest.ClientEvent;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;

public class ClientEventRequestTest {

  @Test
  public void testClientEvent() throws OpenApiException {
    ClientEvent a = new ClientEvent();
    a.setAc(ClientEvent.AC_READ_ARTICLE);
    a.setAid("123456789");
    a.setRecoid("987654321");
    a.setDuration(150000L);
    ClientEventRequest req = new ClientEventRequest(AppConfig.APP_NAME, AppConfig.DN);
    req.setVersion("v1");
    Assert.assertEquals(true, req.clientEvent(AppConfig.ACCESS_TOKEN, Arrays.asList(a), null));
  }

}

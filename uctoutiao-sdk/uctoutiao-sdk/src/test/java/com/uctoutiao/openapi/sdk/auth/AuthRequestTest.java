/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.auth;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.uctoutiao.openapi.sdk.AppConfig;
import com.uctoutiao.openapi.sdk.auth.AuthRequest;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;

public class AuthRequestTest {

  @Test
  public void testGetAccessToken() throws OpenApiException {
    AuthRequest req = new AuthRequest();
    boolean result = req.getAccessToken(AppConfig.APP_ID, AppConfig.SECRET);
    Assert.assertEquals(result, true);
  }

  
  public static void main(String[] args) throws Exception {
	    AuthRequest req = new AuthRequest();
	    boolean result = req.getAccessToken(AppConfig.APP_ID, AppConfig.SECRET);
	    System.out.println(result);
	   // Assert.assertEquals(result, true);
  }
}

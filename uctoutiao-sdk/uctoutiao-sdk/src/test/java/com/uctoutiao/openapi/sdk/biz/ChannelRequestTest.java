/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uctoutiao.openapi.sdk.AppConfig;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.ChannelResponse;
import com.uctoutiao.openapi.sdk.response.ChannelResponse.ChannelItem;
import com.uctoutiao.openapi.sdk.response.model.Article;
import com.uctoutiao.openapi.sdk.response.model.SpecialArticle;

public class ChannelRequestTest {

  @Test(enabled = false)
  public void testNewGetChannel() throws OpenApiException {
    ChannelRequest req = new ChannelRequest(AppConfig.APP_NAME, AppConfig.DN);
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("method", "new");
    ChannelResponse response = req.getChannel(AppConfig.ACCESS_TOKEN, 100L, params);
    List<ChannelItem> items = response.getItems();
    Assert.assertEquals(items.size() > 0, true);

    for (ChannelItem item : items) {
      if ("articles".equals(item.getMap())) {
        String itemId = item.getId();
        Article article = response.getArticlesMap().get(itemId);
        System.out.println(article);
        Assert.assertNotEquals(article, null);
      }
      if ("specials".equals(item.getMap())) {
        String itemId = item.getId();
        SpecialArticle article = response.getSpecialsMap().get(itemId);
        System.out.println(article);
        Assert.assertNotEquals(article, null);
      }
    }
  }

  @DataProvider(name = "hisParam")
  public Object[][] hisParam() {
    return new Object[][] {{"", 0L}};
  }


  @Test(dataProvider = "hisParam", enabled = false)
  public void testHisGetChannel(String recoid, long ftime) throws OpenApiException {
    ChannelRequest req = new ChannelRequest(AppConfig.APP_NAME, AppConfig.DN);
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("method", "his");
    params.put("recoid", recoid);
    params.put("ftime", ftime);
    ChannelResponse response = req.getChannel(AppConfig.ACCESS_TOKEN, 100L, params);
    List<ChannelItem> items = response.getItems();
    Assert.assertEquals(items.size() > 0, true);

    for (ChannelItem item : items) {
      if ("articles".equals(item.getMap())) {
        String itemId = item.getId();
        Article article = response.getArticlesMap().get(itemId);
        System.out.println(article);
        Assert.assertNotEquals(article, null);
      }
    }

  }


  @Test(enabled = false)
  public void testHttpsNewGetChannel() throws OpenApiException {
    ChannelRequest req = new ChannelRequest(AppConfig.APP_NAME, AppConfig.DN);
    req.setServer("https://open.uczzd.cn");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("method", "new");
    ChannelResponse response = req.getChannel(AppConfig.ACCESS_TOKEN, 100L, params);
    List<ChannelItem> items = response.getItems();
    Assert.assertEquals(items.size() > 0, true);

    for (ChannelItem item : items) {
      if ("articles".equals(item.getMap())) {
        String itemId = item.getId();
        Article article = response.getArticlesMap().get(itemId);
        System.out.println(article);
        Assert.assertNotEquals(article, null);
      }
    }
  }


  @Test(enabled = false)
  public void testHttpsV2NewGetChannel() throws OpenApiException {
    ChannelRequest req = new ChannelRequest(AppConfig.APP_NAME, AppConfig.DN);
    req.setServer("https://open.uczzd.cn");
    req.setVersion("v2");
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("method", "new");
    ChannelResponse response = req.getChannel(AppConfig.ACCESS_TOKEN, 100L, params);
    List<ChannelItem> items = response.getItems();
    Assert.assertEquals(items.size() > 0, true);

    for (ChannelItem item : items) {
      if ("articles".equals(item.getMap())) {
        String itemId = item.getId();
        Article article = response.getArticlesMap().get(itemId);
        System.out.println(article);
        Assert.assertNotEquals(article, null);
      }
    }
  }



}

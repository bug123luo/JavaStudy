/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;


import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.uctoutiao.openapi.sdk.AppConfig;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.model.Article;

public class ArticleRequestTest {


  @DataProvider(name = "articleParam")
  public Object[][] articleParam() {
    return new Object[][] {{"", 0L}};
  }

  @Test(enabled = false, dataProvider = "articleParam")
  public void testGetArticle(String aid, long cid) throws OpenApiException {
    ArticleRequest req = new ArticleRequest(AppConfig.APP_NAME, AppConfig.DN);
    Article article = req.getArticle(AppConfig.ACCESS_TOKEN, aid, cid, null);
    System.out.println(article);
    Assert.assertNotEquals(article, null);
  }

  @Test(enabled = false, dataProvider = "articleParam")
  public void testGetRelatedArticles(String aid, long cid) throws OpenApiException {
    ArticleRequest req = new ArticleRequest(AppConfig.APP_NAME, AppConfig.DN);
    List<Article> articles = req.getRelatedArticles(AppConfig.ACCESS_TOKEN, aid, cid, null);
    Assert.assertNotEquals(articles.size(), 0);
  }


}

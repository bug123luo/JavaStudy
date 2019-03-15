/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.uctoutiao.openapi.sdk.BaseRequest;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;
import com.uctoutiao.openapi.sdk.response.BaseResponse;
import com.uctoutiao.openapi.sdk.response.model.Article;

public class ArticleRequest extends BaseRequest {

  public ArticleRequest(String app, String dn) {
    super(app, dn);
  }

  public Article getArticle(String accessToken, String aid, Long cid, Map<String, Object> params)
      throws OpenApiException {
    String url = String.format(URL_ARTICLE, server, version, aid, accessToken, app, dn, cid);
    url = this.appendParams(url, params);
    BaseResponse baseResponse = this.doGetRequet(url);
    if (baseResponse.getStatus() != 0) {
      throw new OpenApiException(baseResponse.getStatus(), baseResponse.getMessage());
    }
    String data = baseResponse.getData();
    Article article;
    try {
      article = mapper.readValue(data, Article.class);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.JSON_DATA_ERROR, e.getMessage());
    }
    return article;
  }

  public List<Article> getRelatedArticles(String accessToken, String aid, Long cid, Map<String, Object> params)
      throws OpenApiException {
    String url = String.format(URL_ARTICLE_RELATED, server, version, aid, accessToken, app, dn, cid);
    url = this.appendParams(url, params);
    BaseResponse baseResponse = this.doGetRequet(url);
    String data = baseResponse.getData();
    RelatedArticlesResponse response;
    try {
      response = mapper.readValue(data, RelatedArticlesResponse.class);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.JSON_DATA_ERROR, e.getMessage());
    }
    return response.getArticles();
  }

  public static class RelatedArticlesResponse {

    private List<Article> articles = new ArrayList<Article>();

    public List<Article> getArticles() {
      return articles;
    }

    public void setArticles(List<Article> articles) {
      this.articles = articles;
    }

  }



}

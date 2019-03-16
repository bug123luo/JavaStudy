package com.lcclovehww.demo.service;

public class TokenStorageService {

  private static volatile String accessToken;

  {
    accessToken = init();
  }

  public String init() {
    // TODO: 从持久化 db、文件等获取Token
    return accessToken;
  }

  public void storeNewAccessToken(String newToken) {
    accessToken = newToken;
    // TODO accessToken的持久化到 db、文件等
  }

  public String getAccessToken() {
    return accessToken;
  }
}

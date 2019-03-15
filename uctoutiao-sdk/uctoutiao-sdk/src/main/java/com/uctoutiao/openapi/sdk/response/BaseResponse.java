/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response;

import com.alibaba.fastjson.JSONObject;

public class BaseResponse {

  private String data;

  private int status;

  private String message = "success";

  public String getData() {
    return data;
  }

  public void setData(String data) {
    this.data = data;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public static BaseResponse parseBaseResponse(String result) {
    JSONObject json = (JSONObject) JSONObject.parse(result);
    BaseResponse response = new BaseResponse();
    response.setData(json.getString("data"));
    response.setStatus(json.getInteger("status"));
    response.setMessage(json.getString("message"));
    return response;
  }

  @Override
  public String toString() {
    return "BaseResponse [data=" + data + ", status=" + status + ", message=" + message + "]";
  }
}

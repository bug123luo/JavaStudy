/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.exception;

public class OpenApiException extends Exception {

  private static final long serialVersionUID = 1L;
  
  public static final int HTTP_ERROR = -997;

  public static final int JSON_DATA_ERROR = -998;

  public static OpenApiException BLANK_RESPONSE = new OpenApiException(-999, "blank response");

  private int status;

  public OpenApiException(int status, String message) {
    super(message);
    this.status = status;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }


  @Override
  public String toString() {
    return "OpenApiException [status=" + status + ", message=" + super.getMessage() + "]";
  }



}

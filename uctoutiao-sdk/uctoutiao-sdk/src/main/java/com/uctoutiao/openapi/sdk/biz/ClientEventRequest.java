/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.biz;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonProperty;

import com.uctoutiao.openapi.sdk.BaseRequest;
import com.uctoutiao.openapi.sdk.exception.OpenApiException;

public class ClientEventRequest extends BaseRequest {

  public ClientEventRequest(String app, String dn) {
    super(app, dn);
  }

  public boolean clientEvent(String accessToken, List<ClientEvent> events, Map<String, Object> params)
      throws OpenApiException {
    String url = String.format(URL_CLIENT_EVENT, server, version, accessToken, app, dn);
    url = this.appendParams(url, params);
    ClientEvents request = new ClientEvents();
    request.setLogs(events);
    String jsonBody;
    try {
      jsonBody = mapper.writeValueAsString(request);
    } catch (Exception e) {
      throw new OpenApiException(OpenApiException.JSON_DATA_ERROR, e.getMessage());
    }
    this.doPostRequest(url, jsonBody);
    return true;
  }

  public static class ClientEvents {

    private List<ClientEvent> logs;

    public ClientEvents() {}

    public List<ClientEvent> getLogs() {
      return logs;
    }

    public void setLogs(List<ClientEvent> logs) {
      this.logs = logs;
    }
  }

  public static class ClientEvent {

    public static final int AC_READ_ARTICLE = 1;
    public static final int AC_SHOW_ARTICLE = 5;

    private int ac;

    private long tm;

    private String aid;

    @JsonProperty("sub_aid")
    private String subAid;

    private String recoid;

    private long duration;

    private String content = "";

    private long cid;

    public int getAc() {
      return ac;
    }

    public void setAc(int ac) {
      this.ac = ac;
    }

    public long getTm() {
      return tm;
    }

    public void setTm(long tm) {
      this.tm = tm;
    }

    public String getAid() {
      return aid;
    }

    public void setAid(String aid) {
      this.aid = aid;
    }

    public String getSubAid() {
      return subAid;
    }

    public void setSubAid(String subAid) {
      this.subAid = subAid;
    }

    public String getRecoid() {
      return recoid;
    }

    public void setRecoid(String recoid) {
      this.recoid = recoid;
    }

    public long getDuration() {
      return duration;
    }

    public void setDuration(long duration) {
      this.duration = duration;
    }

    public String getContent() {
      return content;
    }

    public void setContent(String content) {
      this.content = content;
    }

    public long getCid() {
      return cid;
    }

    public void setCid(long cid) {
      this.cid = cid;
    }

    @Override
    public String toString() {
      return "ClientEvent [ac=" + ac + ", tm=" + tm + ", aid=" + aid + ", subAid=" + subAid + ", recoid=" + recoid
          + ", duration=" + duration + ", content=" + content + ", cid=" + cid + "]";
    }

  }


}

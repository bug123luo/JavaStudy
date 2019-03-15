/**
 * Copyright (C) 2017-2101 Alibaba Group Holding Limited
 */
package com.uctoutiao.openapi.sdk.response;

import java.util.ArrayList;
import java.util.List;

import com.uctoutiao.openapi.sdk.response.model.ChannelInfo;

public class ChannelsResponse extends BaseResponse {

  private List<ChannelInfo> channels = new ArrayList<ChannelInfo>();

  public List<ChannelInfo> getChannels() {
    return channels;
  }

  public void setChannels(List<ChannelInfo> channels) {
    this.channels = channels;
  }

  @Override
  public String toString() {
    return "ChannelsResponse [channels=" + channels + "]";
  }

}

package com.tct.codec.impl;

import com.alibaba.fastjson.JSONObject;

public interface MessageBodyCodec {
	public Object decode(JSONObject json) throws Exception;
	public String encode(Object outMsg) throws Exception;
}

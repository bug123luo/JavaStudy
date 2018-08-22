/*
 * Copyright 2015 The Dudu Project
 *
 * The Dudu Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package dudu.service.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	
	private static final Logger LOG=LoggerFactory.getLogger(Utils.class);
	public static final int ThrowableInfoBufferSize = 1024;
	

	public static String getThrowableInfo(Throwable throwable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(Utils.ThrowableInfoBufferSize);
        PrintStream printStream = new PrintStream(outputStream);
        throwable.printStackTrace(printStream);
        return outputStream.toString();
    }
	
	public static void dumpMap(Map<String, Channel> map) {
		
		Set<String> loginKeySet = map.keySet();
		Iterator<String> keyIterator = loginKeySet.iterator();
		int i = 0;
		
		while (keyIterator.hasNext()) {
			LOG.debug("key[{}]: {}.", i, keyIterator.next());
			i++;
		}
		
	}
	
	public static void dumpBytes(byte[] data) {
		
		LOG.debug("========begin dump========");
		int offset = 0;
		StringBuilder builder = new StringBuilder();
		while (offset < data.length) {
			for (int i=0; i<16 && offset<data.length; i++) {
				builder.append(String.format("0x%02x", data[offset]));
				if (i < 15) {
					builder.append(' ');
				} else {
					builder.append("\r\n");
				}
				
				offset++;
			}
		}
		LOG.debug("\r\n"+builder.toString());
		LOG.debug("========end dump========");
	}
	
	public static void dumpBuffer(ByteBuf buffer) {
		
		StringBuilder builder = new StringBuilder();
		
		buffer.markReaderIndex();
		while (buffer.isReadable()) {
			for (int i=0; i<16 && buffer.isReadable(); i++) {
				builder.append(String.format("0x%02x", buffer.readByte()));
				if (i < 15) {
					builder.append(' ');
				} else {
					builder.append("\r\n");
				}
			}
		}
		
		buffer.resetReaderIndex();
		LOG.debug("begin dump channel buffer========\r\n" + 
		          "length={}\r\n{}\r\n" +
				  "end dump channel buffer========", 
				  buffer.readableBytes(), builder.toString());
	}
	
	public static void dumpBuffer2(ByteBuf buffer) {
		
		StringBuilder builder = new StringBuilder();
		
		buffer.markReaderIndex();
		while (buffer.isReadable()) {
			for (int i=0; i<16 && buffer.isReadable(); i++) {
				builder.append(String.format("%02x", buffer.readByte()));
				if (i < 15) {
					builder.append(' ');
				} else {
					builder.append("\r\n");
				}
			}
		}
		
		buffer.resetReaderIndex();
		LOG.debug("begin dump channel buffer========\r\n" + 
				"length={}\r\n{}\r\n" +
				"end dump channel buffer========", 
				buffer.readableBytes(), builder.toString());
	}
	
	
	public static void dumpChars(byte[] data) {
		
		LOG.debug("========begin dump========");
		int offset = 0;
		StringBuilder builder = new StringBuilder();
		while (offset < data.length) {
			for (int i=0; i<16 && offset<data.length; i++) {
				builder.append((char)data[offset]);
				if (i >= 15) {
					builder.append("\r\n");
				}
				
				offset++;
			}
		}
		LOG.debug("\r\n"+builder.toString());
		LOG.debug("========end dump========");
	}
	
	public static Date getUTCTime() {
		
		Calendar cal = Calendar.getInstance();
		Date localTime = cal.getTime();
		
		return new Date(localTime.getTime() - cal.get(Calendar.ZONE_OFFSET));
	}
	
	
	private static final String DigestSrcData = "1234567890" +
		"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXXYZ";
	public static final int RegistryDigestLength = 16;
	
	/*16 bytes digest for track channel session*/
	public static String channelSessionDigest() {
		
		return String.format("%016x", System.currentTimeMillis());
	}
	
	public static String registryDigest() {
		
		LOG.debug(".registryDigest()");
		
		StringBuilder builder = new StringBuilder();
		Random random = new Random(System.currentTimeMillis());
		for (int i=0; i<RegistryDigestLength; i++) {
			char c = DigestSrcData.charAt(random.nextInt(DigestSrcData.length()));
			builder.append(c);
		}
		String digestOutput = builder.toString();
		LOG.debug("output text: {}.", digestOutput);
		
		return digestOutput;
	}
	
}

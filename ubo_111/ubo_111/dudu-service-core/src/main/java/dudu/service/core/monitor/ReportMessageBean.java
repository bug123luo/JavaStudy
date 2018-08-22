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
package dudu.service.core.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;

import dudu.service.core.MessageBean;

public class ReportMessageBean extends MessageBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -978042279076836638L;
	private String moduleName;
	private Level level;
	private String content;
	private String timestamp;
	private String host;
	
	public enum Level {
		
		EXCEPTION("EXCEPTION", 1), WARNING("WARNING", 2);
		
		private String name;
		private int value;
		
		private Level(String name, int value) {
			this.name = name;
			this.value = value;
		}
		
		@Override
        public String toString() {
            return this.name + "@" + this.value;
        }
	}
	
	public ReportMessageBean() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.timestamp = sdf.format(new Date());
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setTimestamp(Date date) {
		 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		this.timestamp = sdf.format(date);
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}
	
}

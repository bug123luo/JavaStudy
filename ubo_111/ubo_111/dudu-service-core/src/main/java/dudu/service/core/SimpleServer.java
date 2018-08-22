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
package dudu.service.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;

import dudu.service.core.utils.Utils;

public abstract class SimpleServer implements Runnable {
	
	private static final Logger LOG=LoggerFactory.getLogger(SimpleServer.class);
	protected static SimpleServer server = null;
	protected AbstractApplicationContext appContext;
	protected String id;
    protected int port;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setAppContext(AbstractApplicationContext appContext) {
    	this.appContext = appContext;
    }
    
    public AbstractApplicationContext getAppContext() {
    	return this.appContext;
    }
    
    protected abstract void onStart();
    protected abstract void onStop();
    
    public void startup() {
    	//serverThread = new Thread(this);
    	//serverThread.start();
    	run();
    }
    
    public void run() {
    	onStart();
    	Thread.currentThread();
    	LOG.info("going to wait for terminating.");
    	//waitToStop();
    	//logger.info("going to terminate.");
    	//onStop();
    	//logger.info("after stopping.");
    }
    
    protected synchronized void waitToStop() {
 		
    	try {
			this.wait();
		} catch (InterruptedException e) {
			LOG.error(Utils.getThrowableInfo(e));
		}
    }
    
    protected synchronized void notifyToStop() {
    	
    	try {
    		this.notify();
		} catch (IllegalMonitorStateException e) {
			LOG.error(Utils.getThrowableInfo(e));
		}
    }
    
    public void terminate() { 
    	LOG.info("Stopping server...");
    	//notifyToStop();
    	LOG.info("going to terminate.");
    	onStop();
    	LOG.info("after stopping.");
    }
}

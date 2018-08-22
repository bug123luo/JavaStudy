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
package dudu.service.core.processor;

import java.net.InetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dudu.service.core.SimpleServer;
import dudu.service.core.utils.Utils;

public class Processor extends SimpleServer
{
	private static final Logger LOG=LoggerFactory.getLogger(Processor.class);
	
	private String peerAddress;
	
	
	private Processor() {
		
	}

	public String getPeerAddress() {
		return peerAddress;
	}

	public void setPeerAddress(String peerAddress) {
		this.peerAddress = peerAddress;
	}

	public static Processor getInstance() {
		
		if (Processor.server == null) {
			Processor.server = new Processor();
		}
		
		return (Processor)Processor.server;
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onStop() {
		
		if (this.appContext != null) {
			this.appContext.close();
			this.appContext = null;
		}
	}
	
    public static void main(String[] args)
    {
    	
    	if (args.length <= 0) {
    		System.out.println("Invalid parameter!");
    		System.out.println("Usage: Processor processor-id");
    		System.out.println("processor-id is used in context-beans-id.xml, the same as file name id.");
    		return;
    	}
    	LOG.info(args[0]);
    	
    	Processor processor = Processor.getInstance();
    	
    	try {
    		
	    	AbstractApplicationContext ctx =
	    		new ClassPathXmlApplicationContext(String.format("context-beans-%s.xml", args[0]));
	        
	    	Runtime.getRuntime().addShutdownHook(new Thread() {
				public void run() {
					Processor.getInstance().terminate();
				}
	        });
	        
	        processor.setId(args[0]);
	        processor.setAppContext(ctx);
	        
	        try {
	        	String peerAddress = (String)ctx.getBean("peerAddress");
	        	processor.setPeerAddress(peerAddress);
	        } catch(BeansException e) {
	        	LOG.warn(e.getMessage(), e.getCause());
	        	
	        	String localHostAddress = InetAddress.getLocalHost().getHostAddress();
	        	LOG.debug("Local host address: {}.", localHostAddress);
	    		processor.setPeerAddress(localHostAddress);
	        }
	        
	        processor.startup();
	        
    	} catch(Exception e) {
    		
    		processor.onStop();
    		
    		LOG.error(e.getMessage());
    		if (e.getCause() != null) {
    			LOG.error(Utils.getThrowableInfo(e.getCause()));
    		}
    	}
    }
}


package com.cnbdty.device.queue.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cnbdty.device.handler.Handler;
import com.cnbdty.device.handler.HandlerProxy;

public class QueueMessageListener   implements MessageListener{
    public  static final Logger logger = LoggerFactory.getLogger(QueueMessageListener.class); 

	public void onMessage(Message message) {
		 TextMessage tm = (TextMessage) message;
	        try {
	        	String msg=tm.getText();
	        	logger.info("read mq message: {}" , msg);
	        	
	        	Handler handler= HandlerProxy.getInstance().forward(msg);
	        	if(!StringUtils.isBlank(msg)){
	        		handler.handle(msg);
	        	}
	            
	        } catch (JMSException e) {
	        	logger.error("read mq messsage exception ",e);
	        }
	}
	
	

}

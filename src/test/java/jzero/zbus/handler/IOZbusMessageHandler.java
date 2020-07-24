package jzero.zbus.handler;

import java.io.IOException;

import io.zbus.mq.Message;
import io.zbus.mq.MqClient;
import jzero.base.io.zbus.IOZbusHandler;

/**
 * ZBUS 消息接收接口回调
 * @category 
 * @author 9龙
 */
public class IOZbusMessageHandler extends IOZbusHandler{

	@Override
	public void handle(Message msg, MqClient client) throws IOException {
		System.err.println("zbus 消费者收到了消息：："+msg.getBodyString());
	}

}

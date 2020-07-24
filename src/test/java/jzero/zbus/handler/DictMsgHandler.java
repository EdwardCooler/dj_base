package jzero.zbus.handler;

import org.zbus.net.http.Message;

import com.jfinal.log.Log;

import jzero.base.zbus.annotation.MqHandler;
import jzero.base.zbus.handler.TMsgHandler;

/**
 * Zbus 消息读取回调
 * @author 9龙
 */
@MqHandler("xc_cool_jzero")
public class DictMsgHandler extends TMsgHandler<Message>{
	private Log logger = Log.getLog("zbus_info");

	@Override
	public void handle(Message msg) {
		// TODO Auto-generated method stub
		logger.info(msg.getBodyString());
	}

}

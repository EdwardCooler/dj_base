package gf.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.sun.mail.iap.Response;
/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>4.0.3</version>
</dependency>
*/
public class SendSms {
	
	public static void main(String [] args) {
		System.out.println("发送短信");
		send("15182471312","1234");
	}
	
	/**
	 * 发送验证码
	 * @Description 
	 * @author hsongjiang
	 * @date 2019年8月7日 下午8:52:43 
	 * @param code
	 */
    public static void send(String telephone,String message) {
    	
        DefaultProfile profile = DefaultProfile.getProfile("gongqu", "LTAITtgoi2Rg2Xmb", "nd5uxV9655BRWayMwABWUOGN7w73ql");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "default");
        request.putQueryParameter("PhoneNumbers",telephone);
        request.putQueryParameter("SignName", "阿里云短信测试专用");
        request.putQueryParameter("TemplateCode", "SMS_101275210");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+message+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        
    }
}
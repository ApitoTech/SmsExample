package SmsExample.com.apito;

import java.util.HashMap;
import java.util.Map;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.MessageAttributeValue;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;

public class AwsSnsClient {

	public static final String  AWS_ACCESS_KEY_ID="aws.accessKeyId";
	public static final String  AWS_SECRET_KEY="aws.secretKey";
	
	static {
		System.setProperty(AWS_ACCESS_KEY_ID, "AKIASMCYHONL7NWBNOEE");
		System.setProperty(AWS_SECRET_KEY, "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");
	}
	
	public static void main(String[] args) {
		AwsSnsClient myClient=new AwsSnsClient();
		myClient.sendsingleSMS("This is brand new msg","+91-9663875523");
	} 
	public void sendsingleSMS(String msg, String phoneNo) {
		
		AmazonSNS snsclient= AmazonSNSClient.builder().withRegion(Regions.US_EAST_1).build();
		Map<String,MessageAttributeValue> smsAttributes= new HashMap<>();
		
		smsAttributes.put("AWS.SNS.SMS.SenderID", new MessageAttributeValue().withStringValue("Mywebsite").withDataType("String"));
		smsAttributes.put("AWS.SNS.SMS.SMSType", new MessageAttributeValue().withStringValue("Transactional").withDataType("String"));
		
		PublishResult result=snsclient.publish(new PublishRequest()
				.withMessage(msg)
				.withPhoneNumber(phoneNo)
				.withMessageAttributes(smsAttributes));
		
		System.out.println("Message sent successfully"+result.getMessageId());
	}

}

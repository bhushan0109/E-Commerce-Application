//package com.bhushan.payment;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.Arrays;
//import java.util.Base64;
//import java.util.Properties;
//import java.util.regex.Pattern;
//
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.ndml.rmq.consumer.dto.MessageConvertEmailContainDto;
//import com.ndml.rmq.consumer.dto.MessageConvertSmsContainDto;
//
//@Component
//public class ConsumerHelperUtility {
//	
//	private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerHelperUtility.class);
//
//	public static MessageConvertEmailContainDto convertEmailMsg(String from) {
//		String[] data = from.split("~");
//		return new MessageConvertEmailContainDto(data[0], data[1], data[2], data[3]);
//	}
//	
//	public static MessageConvertSmsContainDto convertSmsMsg(String from) {
//		String[] data = from.split("~");
//		return new MessageConvertSmsContainDto(data[0], data[1], data[2], data[3]);
//	}
//
//	// split a string
//	public static String[] splitContent(String content) {
//		try {
//			String REGEX = ",";
//			Pattern p = Pattern.compile(REGEX);
//			String[] items = p.split(content);
//			return items;
//		} catch (Exception e) {
//			LOGGER.info("Exception : Content is ------->" + content);
//			LOGGER.info("Exception occured while splitting contents :" + e.getMessage());
//			e.printStackTrace();
//		}
//		return new String[0];
//	}
//
//	public static String getEmailText(String emailTemplatePath, String templateID, String content, Properties props) {
//		CommonUtil.entryLog("getEmailText");
//		String fileName = emailTemplatePath + templateID + ".txt";
//		LOGGER.info("fileName ...." + fileName);
//		String label = props.getProperty("emailContent." + templateID);
//		LOGGER.info("label ...." + label);
//		String[] contentArray = ConsumerHelperUtility.splitContent(content);
//		String[] labelArray = ConsumerHelperUtility.splitContent(label); // splitting email content
//		LOGGER.info(
//				"############################################################################################################################################");
//		LOGGER.info("Lable Array " + labelArray.length + "...." + Arrays.toString(labelArray));
//		LOGGER.info("Content Array " + contentArray.length + "...." + Arrays.toString(contentArray));
//		LOGGER.info(
//				"############################################################################################################################################");
//		// this is done for decription of OTP
//		long indexOfOTPNumber = -1;
//		if (templateID.equalsIgnoreCase("T18") || templateID.equalsIgnoreCase("T27")
//				|| templateID.equalsIgnoreCase("T30")) {
//			for (int i = 0; i < labelArray.length; i++) {
//				if (labelArray[i].equalsIgnoreCase("<OTP>")) {
//					indexOfOTPNumber = i;
//					break;
//				}
//			}
//		}
//		LOGGER.info("Index of OTP number ...." + indexOfOTPNumber);
//
//		File f = new File(fileName);
//		if (contentArray.length == labelArray.length) {
//			FileInputStream fs = null;
//			InputStreamReader in = null;
//			BufferedReader br = null;
//			StringBuffer sb = new StringBuffer();
//			String textinLine;
//			try {
//				fs = new FileInputStream(f);
//				in = new InputStreamReader(fs);
//				br = new BufferedReader(in);
//				while (true) {
//					textinLine = br.readLine();
//					if (textinLine == null)
//						break;
//					sb.append(textinLine);
//				}
//				// new version
//				for (int i = 0; i < labelArray.length; i++) {
//					if (indexOfOTPNumber != -1 && indexOfOTPNumber == i && (templateID.equalsIgnoreCase("T18")
//							|| templateID.equalsIgnoreCase("T27") || templateID.equalsIgnoreCase("T30"))) {
//						LOGGER.info("Before decrypting email otp ....");
//						String decodedText = new String(Base64.getDecoder().decode(contentArray[i].getBytes("UTF-8")));
//						LOGGER.info("OTP decrypt done............");
//						String textToEdit = labelArray[i];
//						int cnt1 = sb.indexOf(textToEdit);
//						sb.replace(cnt1, cnt1 + textToEdit.length(), decodedText);
//						continue;
//					}
//					String textToEdit = labelArray[i];
//					int cnt1 = sb.indexOf(textToEdit);
//					sb.replace(cnt1, cnt1 + textToEdit.length(), contentArray[i]);
//				}
//				fs.close();
//				in.close();
//				br.close();
//			} catch (Exception e) {
//				LOGGER.info("Exception found : " + e.getMessage());
//
//			}
//			LOGGER.info("Returning with Email Message-->" + sb.toString());
//			CommonUtil.exitLog("getEmailText");
//			return sb.toString();
//		} else {
//			LOGGER.warn("INSTANT-EMAIL : message format not matching");
//		}
//		CommonUtil.exitLog("getEmailText");
//		return null;
//	}
//
//	public static String sendEmail(String toAddress, String templateID, String emailContent) throws IOException {
//		CommonUtil.entryLog("sendEmail");
//		LOGGER.info("Email Id------>" + toAddress);
//		LOGGER.info("Template Id--->" + templateID);
//		LOGGER.info("Content------->" + emailContent);
//		try {
//			// get email properties
//			Properties props = new Properties();
//			FileInputStream in = new FileInputStream("/app/nir/nir-properties/crip-email.properties");
//			props.load(in);
//			in.close();
//			String emailTemplatePath = props.getProperty("email.emailTemplatePath");
//			String emailSubject = props.getProperty("email.subject-" + templateID);
//			LOGGER.info("calling getEmailText ....");
//			String emailBody = ConsumerHelperUtility.getEmailText(emailTemplatePath, templateID, emailContent, props);
//
//			if (emailBody != null) {
//				String to = toAddress;
//				String from = props.getProperty("email.FromAddress");
//				String host = props.getProperty("email.Host");
//				Properties properties = System.getProperties();
//				properties.setProperty("mail.smtp.host", host);
//				Session session = Session.getDefaultInstance(properties);
//				MimeMessage message = new MimeMessage(session);
//				message.setFrom(new InternetAddress(from));
//				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//				message.setSubject(emailSubject);
//				message.setContent(emailBody, "text/html");
//				Transport.send(message); // temp comment
//				LOGGER.warn("<-----Instant Email Sent Successfully to ------>" + toAddress);
//				CommonUtil.exitLog("sendEmail");
//				return message.getMessageID();
//			} else {
//				LOGGER.info("Message body is null---->");
//				LOGGER.info("<---------- Email Not send to ------------>" + toAddress);
//				return null;
//			}
//
//		} catch (Exception e) {
//			LOGGER.info("Exception found IN sendEmail : " + e.getMessage());
//			CommonUtil.exitLog("sendEmail");
//			return null;
//		}
//	}
//
//	public static String readFile(String fileName) throws IOException {
//		BufferedReader br = new BufferedReader(new FileReader(fileName));
//		try {
//			StringBuilder sb = new StringBuilder();
//			String line = br.readLine();
//			int i = 0;
//			while (line != null) {
//				if (i != 0) {
//					sb.append(line);
//					sb.append("\n");
//				}
//				line = br.readLine();
//				i++;
//			}
//			return sb.toString();
//		} finally {
//			br.close();
//		}
//	}
//
//	public static String getSMSText(String smsTemplatePath, String templateID, String content, Properties props)
//			throws Exception {
//		try {
//			LOGGER.info("In getSMSText method with following data-->");
//			LOGGER.info("SMS template path-->" + smsTemplatePath);
//			LOGGER.info("SMS template ID---->" + templateID);
//			LOGGER.info("SMS content-------->" + content);
//			// get template from text path
//			String fileName = smsTemplatePath + templateID + ".txt";
//			// get labels from property file according to the template ID
//			String label = props.getProperty("smsContent." + templateID);
//			// split the csv content into array
//			String[] contentArray = splitContent(content);
//			LOGGER.info("Content array length-->" + contentArray.length);
//			// splitting content from database
//			String[] labelArray = splitContent(label);
//			// this is done for decription of OTP
//			long indexOfOTPNumber = -1;
//			if (templateID.equalsIgnoreCase("T18") || templateID.equalsIgnoreCase("T27")
//					|| templateID.equalsIgnoreCase("T28") || templateID.equalsIgnoreCase("T33")) {
//				for (int i = 0; i < labelArray.length; i++) {
//					if (labelArray[i].equalsIgnoreCase("<OTP>")) {
//						indexOfOTPNumber = i;
//						break;
//					}
//				}
//			}
//			LOGGER.info("Label array length-->" + labelArray.length);
//			// splitting sms content from property file
//			File f = new File(fileName);
//			if (contentArray.length == labelArray.length) {
//				LOGGER.info("Array length matched-->" + labelArray.length);
//				FileInputStream fs = null;
//				InputStreamReader in = null;
//				BufferedReader br = null;
//				StringBuffer sb = new StringBuffer();
//				String textinLine;
//				fs = new FileInputStream(f);
//				in = new InputStreamReader(fs);
//				br = new BufferedReader(in);
//				while (true) {
//					textinLine = br.readLine();
//					if (textinLine == null)
//						break;
//					sb.append(textinLine);
//				}
//				LOGGER.info("Template with Sms Message-->" + sb.toString());
//				// new version
//				for (int i = 0; i < labelArray.length; i++) {
//					if (indexOfOTPNumber != -1 && indexOfOTPNumber == i
//							&& (templateID.equalsIgnoreCase("T18") || templateID.equalsIgnoreCase("T27")
//									|| templateID.equalsIgnoreCase("T28") || templateID.equalsIgnoreCase("T33"))) {
//						String textToEdit = labelArray[i];
//						int cnt1 = sb.indexOf(textToEdit);
//						String decodedText = new String(Base64.getDecoder().decode(contentArray[i].getBytes("UTF-8"))); // decoding
//																														// of
//																														// OTP
//																														// number
//						sb.replace(cnt1, cnt1 + textToEdit.length(), decodedText.trim());
//						continue;
//					}
//					String textToEdit = labelArray[i];
//					int cnt1 = sb.indexOf(textToEdit);
//					sb.replace(cnt1, cnt1 + textToEdit.length(), contentArray[i]);
//				}
//				fs.close();
//				in.close();
//				br.close();
//				LOGGER.info("Returning with Sms Message-->" + sb.toString());
//				return sb.toString();
//			} else {
//				LOGGER.error("INSTANT-SMS : Message format not matching--->");
//			}
//			return null;
//		} catch (Exception e) {
//			LOGGER.error("Exception in getSMSText method-->" + e.getMessage());
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//
//}

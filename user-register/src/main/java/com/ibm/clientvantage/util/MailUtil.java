package com.ibm.clientvantage.util;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Component
public class MailUtil {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;


	@Value("${mail.fromMail.addr}")
	private String from;
	public String sendRegisterMail(String email, String code) {

		//SimpleMailMessage message = new SimpleMailMessage();
		MimeMessage message=mailSender.createMimeMessage();
		logger.info(code);
		//create email content
		Context context=new Context();
		context.setVariable("code", code);
		String emailContent = templateEngine.process("emailTemplate", context);

		try {
		MimeMessageHelper helper=new MimeMessageHelper(message,true);
		helper.setFrom(from);
		helper.setTo(email);
		helper.setSubject("从cas 发出的激活邮件！");
		helper.setText(emailContent,true);
        
            mailSender.send(message);
            logger.info("Success");
            return "1";//Success
        }
		catch (MailSendException  e) {
            logger.error("Exception:"+e);
            //logger.error("Invalid Addresse");
            if(e.toString().contains("Invalid Addresses"))
            {
            return "0";//Invalid Addresses
            }
            else {
            	return "-1";//Cloud not connect to SMTP host
            }
            
        }
		catch(MessagingException e) {
			logger.error("Exception:", e);
			//Cloud not connect to SMTP host
			return "-1";//Other exception
		}
	

	}

}

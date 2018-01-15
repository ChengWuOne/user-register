package com.ibm.clientvantage.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.clientvantage.dao.UserRepository;
import com.ibm.clientvantage.domain.User;
import com.ibm.clientvantage.util.MD5Util;
import com.ibm.clientvantage.util.MailUtil;
import com.ibm.clientvantage.util.UuidUtils;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	MailUtil mailUtil;

	/*处理注册用户服务
	 * */
	public String registerUser(User user) {
		if (userRepository.findByEmail(user.getEmail()).isEmpty()) {// verify email is not repeat in database.
			// Use MD5 to encrypt password
			user.setPassword(MD5Util.toMD5(user.getPassword()));
			user.setState(0);// 1,activated 0,unactivated
			String code = UuidUtils.getUuid() + UuidUtils.getUuid();
			user.setCode(code);

			// send a activated email to user
			String sendResult = mailUtil.sendRegisterMail(user.getEmail(), code);
			if ("0".equals(sendResult)) {// Invalid Addresses
				return sendResult;
			}
			userRepository.save(user);
			return sendResult;

		} else {
			return "2";//email repeat
		}

	}
	
	/*处理用户激活
	 * */
	public boolean registActivate(String code) {
	 User user =	userRepository.findByCode(code);
	 if(null == user) {
		 return false;
	 }
	 user.setState(1);
	 user.setCode("");
	 userRepository.save(user);
		return true;
	}
	
	
	
}

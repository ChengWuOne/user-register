package com.ibm.clientvantage.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;

import com.ibm.clientvantage.domain.User;
import com.ibm.clientvantage.service.UserService;

@Controller
@EnableAutoConfiguration
public class UserController {
	@Autowired
	private UserService userService;
	
	/*处理用户注册请求
	 * */
	 @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	  public  String registerUser(User user, Model model) {
		    

	        String result= userService.registerUser(user);
	        if("0".equals(result))//Invalid Addresses
	        	{
	        	model.addAttribute("emailMessage", "Invalid Addresses");
	        	return "register";
	        	}
	        else if("2".equals(result)) {
	        	model.addAttribute("emailMessage", "The email has been registered.");
	        	return "register";
	        }
	        
	        else {
	        	model.addAttribute("userEmail", user.getEmail());
	        return "activate";//重定向提示用户点击邮箱进行激活的页面。
	        }
	}
	 
	 /*
	  *跳转到注册页面 
	  * */
	   @RequestMapping("/register")
	    String register(Model model) {
	        model.addAttribute("user", new User());
	        return "register";
	}
	 /*
	  * 处理用户激活请求
	  * */
	   @GetMapping("/regist/activate")
	   @ResponseBody
	   public String registActivate(@RequestParam("code") String code,HttpServletResponse response) throws IOException {
		   boolean result = userService.registActivate(code);
		   
		   if(result) {
		   response.sendRedirect("https://passport.sso.com:8443/cas/login");
		   return "激活成功，正在重定向到cas的登录页面......";
		   }
		   else {
			   return "激活链接已失效......";
		   }
	   }
}

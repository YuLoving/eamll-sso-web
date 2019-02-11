package cn.e3mall.sso.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.CookieUtils;
import cn.e3mall.common.utils.E3Result;
import cn.e3mall.sso.service.LoginService;

/**  

* <p>Title: LoginController</p>  

* <p>Description: 登录处理</p>  

* @author 赵天宇

* @date 2019年1月22日  

*/
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@Value("${COOKIE_KEY}")
	private String COOKIE_KEY;
	
	//登录界面
	@RequestMapping("/page/login")
	public String showlogin(String redirect,Model model){
		model.addAttribute("redirect", redirect);
		return "login";
	}
	
	@RequestMapping(value="/user/login",method=RequestMethod.POST)
	@ResponseBody
	public E3Result login(String username,String password,
			HttpServletRequest request,HttpServletResponse response){
		E3Result result = loginService.userlogin(username, password);
		//判断是否登录成功
		if(result.getStatus()==200){
			String  token = result.getData().toString();
			//将token写入cookie
			CookieUtils.setCookie(request, response, COOKIE_KEY, token);
		}
		//返回结果
		return  result;
		
	}
	
}

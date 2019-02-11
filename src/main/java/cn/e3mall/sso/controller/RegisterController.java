package cn.e3mall.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.pojo.TbUser;
import cn.e3mall.sso.service.RegisterService;

/**  

* <p>Title: RegisterController</p>  

* <p>Description:注册功能 </p>  

* @author 赵天宇

* @date 2019年1月21日  

*/
@Controller
public class RegisterController {
	@Autowired
	private RegisterService registerService;

	//进去注册页面
	@RequestMapping("/page/register")
	public String showRegister(){
		return "register";
	}
	
	//注册的处理
	@RequestMapping("/user/check/{param}/{type}")
	@ResponseBody
	public E3Result checkData(@PathVariable String param,@PathVariable Integer type){
		E3Result e3Result = registerService.checkdata(param, type);
		return e3Result;
	}
	
	//注册信息没有问题后添加到数据库
	@RequestMapping(value="/user/register",method=RequestMethod.POST)
	@ResponseBody
	public E3Result register(TbUser user){
		E3Result e3Result = registerService.register(user);
		return e3Result;
	}
	
	
}

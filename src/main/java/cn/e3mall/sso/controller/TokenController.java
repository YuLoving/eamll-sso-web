package cn.e3mall.sso.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.e3mall.common.utils.E3Result;
import cn.e3mall.common.utils.JsonUtils;
import cn.e3mall.sso.service.TokenService;

/**  

* <p>Title: TokenController</p>  

* <p>Description:根据token来查询用户信息 </p>  

* @author 赵天宇

* @date 2019年1月23日  

*/
@Controller
public class TokenController {
	
	@Autowired
	private TokenService tokenService;
	
	            //produces="application/json;charset=utf-8"也可以换成如下写法
	@RequestMapping(value="/user/token/{token}",produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public String  getuserbytoken(@PathVariable String token ,String callback){
		E3Result result = tokenService.getuserbytoken(token);
		//响应结果之前，判断是否为jsonp请求
		if(StringUtils.isNotBlank(callback)){
			//把结果封装成js语句响应
			return "("+JsonUtils.objectToJson(result)+");";
		}
		return JsonUtils.objectToJson(result);
	}

	
	
}

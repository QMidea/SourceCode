package com.shq.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shq.pojo.Tb_user;
import com.shq.service.IUserService;

/** 
 * @author  作者：Shengke
 * @date 创建时间：2015年12月10日 上午9:55:17 
 * @version 1.0 
 */

@Controller  
public class UserController {

	@Resource  
    private IUserService userService;  
      
    @RequestMapping("/showUser")  
    public String toIndex(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        Tb_user user = this.userService.getUserById(userId);  
        model.addAttribute("user", user);  
        return "User";  
    }  
    
}

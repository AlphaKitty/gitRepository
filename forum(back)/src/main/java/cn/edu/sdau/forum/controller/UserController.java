package cn.edu.sdau.forum.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sdau.forum.config.MD5Utils;
import cn.edu.sdau.forum.config.MailUtils;

import cn.edu.sdau.forum.po.Post;
import cn.edu.sdau.forum.po.User;
import cn.edu.sdau.forum.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    private IUserService userService;
    
    //登录
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String Login(@RequestBody User user,HttpSession session) {
    	session.setAttribute("user", user);
//    	System.out.println("Version - "+org.hibernate.Version.getVersionString());
        if(null!=user){
            if(userService.queryLogin(user)){
                return "success";
            }
            return "fail";
        }
        return "fail";
    }
    
    //按id查询
    @RequestMapping(value = "/querybyid", method = RequestMethod.POST)
    @ResponseBody
    public User queryById(@RequestBody User user) {
        return this.userService.queryById(user);
    }
    
    //邮箱注册
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public String emailRegister(@RequestBody User user) {
        if (null!=user.getEmail() && !"".equals(user.getEmail())) {
            try {
//                user.setAddDate(new Date());
//                user.setState(0);
                // 这里可以将激活码设计的更复杂
                user.setCode(MD5Utils.encode2hex(user.getEmail()));
                StringBuffer sb = new StringBuffer(
                        "点击下面链接激活账号，48小时内有效，请尽快激活！</br>");
                sb.append("<a href=\"http://localhost:63342/forum/html/index.html?email=")
                        .append(user.getEmail())
                        .append("&code=")
                        .append(user.getCode())
                        .append("\">点我激活邮箱")
                        .append("</a>");
                MailUtils.send(user.getEmail(), sb.toString(),"帐号激活邮件");
                userService.saveOrUpdate(user);
            } catch (Exception e) {
                return "fail";
            }
            return "success";
        } else {
            return "fail";
        }
    }
    
//    //注册
//    @RequestMapping(value = "/register", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
//    @ResponseBody
//    public String register(@RequestBody User user) {
//        if(null!=user.getEmail()&&!"".equals(user.getEmail())&&null!=user.getPassword()&&""!=user.getPassword()){
//        	userService.saveOrUpdate(user);
//        	return "success";
//        }else{
//        	return "邮箱或密码不能为空!";
//        }
//    }
    
//    //根据邮箱查询
//    @RequestMapping(value = "/querybyemail", method = RequestMethod.POST)
//    @ResponseBody
//    public User queryByEmail(@RequestBody User user) {
//        if(null!=user.getEmail()&&!"".equals(user.getEmail())){
//        	return userService.queryByEmail(user);
//        }else{
//        	return null;
//        }
//    }
    
//    //查出相同id的所有帖子
//    @RequestMapping(value = "/querypostbyid", method = RequestMethod.POST)
//    @ResponseBody
//    public List<Post> queryPostById(@RequestBody User user) {
//    	user = userService.queryById(user);
//        return user.getPostsSet();
//    }
    
    //查询所有用户
    @RequestMapping(value = "/queryalluser", method = RequestMethod.POST)
    @ResponseBody
    public List<User> queryAllUser(@RequestBody User user) {
        return this.userService.queryAllUser(user);
    }
}

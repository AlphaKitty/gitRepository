package cn.edu.sdau.forum.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sdau.forum.config.JsonDateValueProcessor;

import cn.edu.sdau.forum.po.Post;
import cn.edu.sdau.forum.service.IPostService;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

@Controller
@RequestMapping("/post")
public class PostController {

	@Autowired
	private IPostService postService;
	public IPostService getPostService() {
		return postService;
	}
	public void setPostService(IPostService postService) {
		this.postService = postService;
	}
	
	//新增或修改
	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
    @ResponseBody
	public String saveOrUpdate(@RequestBody Post post){
		if(null!=post.getUser()){
			try {
				if (postService.saveOrUpdate(post)) {
					return "success";
				}else
					return "false";
			} catch (Exception e) {
				e.printStackTrace();
				return "fail";
			}
		}else
		return "fail";
	}
	
	//模糊查询
	@RequestMapping(value = "/querypostbycondition", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
    @ResponseBody
    public String queryPostByCondition(@RequestBody Post post,HttpSession session) {
		if (null!=post&&session.getAttribute("user")!=null) {
			System.out.println(session.getAttribute("user"));
			List<Post> listPost = postService.queryByCondition(post);
			JSONArray json = JSONArray.fromObject(listPost);
			String str = json.toString();
			return str;
		}
		return "false";
    }
	
	//按pid查询
	@RequestMapping(value = "/querypostbypid", method = RequestMethod.POST)
    @ResponseBody
    public Post queryPostByUid(@RequestBody Post post) {
		if (null!=post) {
			Post postResult = postService.queryByPid(post);
			return postResult;
		}
		return null;
    }

	//查询一个用户发过的所有帖子(按uid查询)
	@RequestMapping(value = "/querypostbyuid", method = RequestMethod.POST)
    @ResponseBody
    public List<Post> queryPostByPid(@RequestBody Post post) {
		if (null!=post.getUser()) {
			List<Post> listPost = postService.queryByUid(post);
			return listPost;
		}
		return null;
    }
	
	//删除帖子
	@RequestMapping(value = "/deletepost",method = RequestMethod.POST)
	@ResponseBody
	public boolean deletePostByPid(@RequestBody Post post) {
			return postService.deletePost(post);
    }
}

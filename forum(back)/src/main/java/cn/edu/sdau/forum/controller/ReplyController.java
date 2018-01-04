package cn.edu.sdau.forum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.sdau.forum.po.Reply;
import cn.edu.sdau.forum.service.IReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {

	@Autowired
	private IReplyService replyService;
	public IReplyService getReplyService() {
		return replyService;
	}
	public void setReplyService(IReplyService replyService) {
		this.replyService = replyService;
	}
	
	//新增或修改
	@RequestMapping(value = "/saveorupdate", method = RequestMethod.POST)
    @ResponseBody
	public String saveOrUpdate(@RequestBody Reply reply){
		if(null!=reply.getUser()&&null!=reply.getPost()){
			try {
				if (replyService.saveOrUpdate(reply)) {
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
	
	//按rid查询
	@RequestMapping(value = "/queryreplybyrid", method = RequestMethod.POST)
	@ResponseBody
	public Reply queryByRid(@RequestBody Reply reply){
		return replyService.queryByRid(reply);
	}
	
	//按pid查询
	@RequestMapping(value = "/queryreplybypid", method = RequestMethod.POST)
	@ResponseBody
	public List<Reply> queryByPid(@RequestBody Reply reply){
		return replyService.queryByPid(reply);
	}
}

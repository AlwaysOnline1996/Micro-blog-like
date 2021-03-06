package com.zengtong.Controller;

import com.zengtong.Service.FollowService;
import com.zengtong.Utils.JedisAdaptor;
import com.zengtong.Utils.Tool;
import com.zengtong.model.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowController {


    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private FollowService followService;


    @Autowired
    private JedisAdaptor jedisAdaptor;

    @RequestMapping(value = "/follow", method = {RequestMethod.POST, RequestMethod.GET},  produces = "application/json;charset=utf-8")
    @ResponseBody
    public String follow(@RequestParam("userId") int userId) {
        if (hostHolder.getUser() == null) {
            return Tool.getJSONString(999, "用户未登陆");
        }

        int myId = hostHolder.getUser().getId();
        followService.follow(myId, userId);
        return Tool.getJSONString(0, "关注成功");

    }

    @RequestMapping(value = "/unfollow", method = {RequestMethod.POST, RequestMethod.GET},  produces = "application/json;charset=utf-8")
    @ResponseBody
    public String unfollow(@RequestParam("userId") int userId) {
        if (hostHolder.getUser() == null) {
            return Tool.getJSONString(999, "用户未登陆");
        }
        int myId = hostHolder.getUser().getId();
        followService.unFollow(myId, userId);
        return Tool.getJSONString(0, "取消关注");
    }
}

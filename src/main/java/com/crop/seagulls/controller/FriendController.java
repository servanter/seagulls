package com.crop.seagulls.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Friend;
import com.crop.seagulls.entities.User;
import com.crop.seagulls.service.FriendService;
import com.crop.seagulls.service.UserService;
import com.crop.seagulls.util.SessionUtils;

@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/friend/friendIndex", method = RequestMethod.GET)
    public String friendIndex(HttpSession session, Model model) {
        User user = SessionUtils.getCurUser(session);
        List<User> users = userService.findFriendUsers(user.getId());
        model.addAttribute("list", users);
        return "friend/friend_index";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxAuth/friend/makeFriend", method = RequestMethod.GET)
    public Response makeFriend(@RequestParam("friendId") Long friendId, HttpSession session) {
        User user = SessionUtils.getCurUser(session);
        Friend friend = new Friend();
        friend.setUserId(user.getId());
        friend.setFriendId(friendId);
        return friendService.makeFriend(friend);
    }
    
}

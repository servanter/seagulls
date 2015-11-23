package com.crop.seagulls.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.Favourite;
import com.crop.seagulls.service.FavouriteService;
import com.crop.seagulls.util.SessionUtils;

@Controller
@RequestMapping("/favourite")
public class FavouriteController {

    @Autowired
    private FavouriteService favouriteService;

    @ResponseBody
    @RequestMapping("/favourite")
    public Response favourite(Favourite favourite, HttpSession session) {
        favourite.setUserId(SessionUtils.getCurUser(session).getId());
        return favouriteService.favourite(favourite);
    }
    
    @ResponseBody
    @RequestMapping("/unFavourite")
    public Response unFavourite(Favourite favourite, HttpSession session) {
        favourite.setUserId(SessionUtils.getCurUser(session).getId());
        return favouriteService.unFavourite(favourite);
    }
    
    @RequestMapping("/user/myFavourite_n{page:\\d+}")
    public String myFavourite(@PathVariable("page") Integer page) {
        Favourite favourite = new Favourite();
        favourite.setPage(page);
        favouriteService.findList(favourite);
        return "user/my_favourite";
    }
}

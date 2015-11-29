package com.crop.seagulls.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.dao.FriendDAO;
import com.crop.seagulls.entities.Friend;
import com.crop.seagulls.service.FriendService;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private FriendDAO friendDAO;

    @Override
    public Response makeFriend(Friend friend) {
        List<Friend> friends = friendDAO.getList(friend);
        if (CollectionUtils.isEmpty(friends)) {
            return friendDAO.save(friend) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        }
        return new Response(ReturnCode.SUCCESS);
    }
}

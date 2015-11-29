package com.crop.seagulls.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.Friend;

@Repository
public interface FriendDAO {

    public int save(Friend friend);

    public List<Friend> getList(Friend friend);

}

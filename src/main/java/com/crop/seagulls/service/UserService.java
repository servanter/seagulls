package com.crop.seagulls.service;

import java.util.List;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.entities.User;

/**
 * 用户业务层
 * 
 * @author zhy19890221@gmail.com
 * 
 */
public interface UserService {

    /**
     * 根据ID查找用户
     * 
     * @param id
     * @return
     */
    public User findUserById(Long id);

    /**
     * 获取一批用户
     * 
     * @param ids
     * @return
     */
    public List<User> findUsersByIds(Long[] ids);

    /**
     * 获取一批用户
     * 
     * @param ids
     * @return
     */
    public List<User> findUsersByIds(List<Long> ids);

    /**
     * 用户登录
     * 
     * @param user
     * @return
     */
    public Response login(User user);

    /**
     * 注册用户
     * 
     * @param user
     * @return
     */
    public Response register(User user);

    /**
     * 查看该用户名是否可用
     * 
     * @param userName
     * @return
     */
    public boolean isNameValid(String userName);

    /**
     * 删除用户(更新is_valid字段)
     * 
     * @param id
     * @return
     */
    public boolean logout(Long id);

    /**
     * 完善信息
     * 
     * @param user
     * @return
     */
    public boolean completeInfo(User user);

    /**
     * 根据用户昵称搜寻用户
     * 
     * @param userNick
     * @return
     */
    public List<User> seacherByUserName(String userNick);

    /**
     * 分页查找用户
     * 
     * @param user
     * @return
     */
    public List<User> findUsersByPaging(User user);

    /**
     * 获取所有用户昵称及ID
     * 
     * @return
     */
    public List<User> findUserNames();
}

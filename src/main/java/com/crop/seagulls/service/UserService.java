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
     * 根据用户名获取用户
     * 
     * @param userName
     * @return
     */
    public User findByUserName(String userName);

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
     * @param phone
     * @return
     */
    public Response checkPhone(String phone);

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

}

package com.crop.seagulls.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crop.seagulls.entities.User;

/**
 * 用户持久层
 * 
 * @author zhy19890221@gmail.com
 * 
 */
@Repository
public interface UserDAO {

    /**
     * 存储用户
     * 
     * @param user
     * @return
     */
    public int save(User user);

    /**
     * 根据用户名密码查找用户
     * 
     * @param user
     * @return
     * @throws DAOException
     */
    public User getUserByNameAndPass(User user);

    /**
     * 根据ID查找用户
     * 
     * @param id
     * @return
     */
    public User getUserById(Long id);

    /**
     * 验证用户是否存在
     * 
     * @param userName
     * @return
     */
    public int isExistUser(@Param("phone")
    String phone);

    /**
     * 更新用户信息
     * 
     * @param user
     * @return
     */
    public int update(User user);

    /**
     * 根据用户昵称查找用户
     * 
     * @param userNick
     * @return
     */
    public List<User> getUserByNick(String userNick);

    /**
     * 获取一批用户
     * 
     * @param ids
     * @return
     */
    public List<User> getUsersByIds(Long[] ids);

    /**
     * Get by username
     * 
     * @param userName
     * @return
     */
    public User getByUserName(String userName);

}

package com.crop.seagulls.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.CommonStatus;
import com.crop.seagulls.bean.Paging;
import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.AdminUserCache;
import com.crop.seagulls.dao.UserAuthDAO;
import com.crop.seagulls.entities.PersonRejection;
import com.crop.seagulls.entities.UserAuth;
import com.crop.seagulls.service.PersonRejectionService;
import com.crop.seagulls.service.UserAuthService;
import com.crop.seagulls.util.NumberUtils;
import com.crop.seagulls.util.SecurityUtils;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    private UserAuthDAO userAuthDAO;

    @Autowired
    private PersonRejectionService personRejectionService;

    @Autowired
    private AdminUserCache adminUserCache;

    @Override
    public Response add(UserAuth userAuth) {
        return userAuthDAO.save(userAuth) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Paging<UserAuth> findList(UserAuth userAuth) {
        List<UserAuth> result = userAuthDAO.getList(userAuth);
        int total = userAuthDAO.getListCount(userAuth);
        if (CollectionUtils.isNotEmpty(result)) {
            for (UserAuth model : result) {
                if (model.getAuditId() > 0 && ObjectUtils.notEqual(adminUserCache.getById(model.getAuditId()), null)) {
                    model.setAuditName(adminUserCache.getById(model.getAuditId()).getUsername());
                }
            }
        }
        return new Paging<UserAuth>(total, userAuth.getPage(), userAuth.getPageSize(), result);
    }

    @Override
    public Response modify(UserAuth userAuth) {
        return userAuthDAO.update(userAuth) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public UserAuth findByUserId(Long userId) {
        UserAuth result = null;
        UserAuth auth = new UserAuth();
        auth.setUserId(userId);
        Paging<UserAuth> auths = findList(auth);
        if (auths != null && CollectionUtils.isNotEmpty(auths.getResult())) {
            result = auths.getResult().get(0);
        }
        return result;
    }

    @Override
    public UserAuth findById(Long id) {
        return userAuthDAO.getById(id);
    }

    @Override
    public Response pass(Long id) {
        UserAuth userAuth = new UserAuth();
        userAuth.setId(id);
        userAuth.setStatus(CommonStatus.PASS.getCode());
        userAuth.setAuditId(SecurityUtils.getLoginedUserId());
        return userAuthDAO.update(userAuth) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response reject(Long id, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        UserAuth userAuth = new UserAuth();
        userAuth.setId(id);
        userAuth.setStatus(CommonStatus.REJECT.getCode());
        userAuth.setAuditId(SecurityUtils.getLoginedUserId());
        response = userAuthDAO.update(userAuth) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            PersonRejection personRejection = new PersonRejection();
            personRejection.setAuditId(SecurityUtils.getLoginedUserId());
            personRejection.setAuthId(id);
            personRejection.setOpinion(opinion);
            personRejection.setType(type);
            response = personRejectionService.add(personRejection);
        }
        return response;
    }

    @Override
    public Response passAll(String ids) {
        UserAuth userAuth = new UserAuth();
        userAuth.setStatus(CommonStatus.PASS.getCode());
        userAuth.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        return userAuthDAO.batchUpdate(userAuth, id)> 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
    }

    @Override
    public Response rejectAll(String ids, Integer type, String opinion) {
        Response response = new Response(ReturnCode.SUCCESS);
        UserAuth userAuth = new UserAuth();
        userAuth.setStatus(CommonStatus.REJECT.getCode());
        userAuth.setAuditId(SecurityUtils.getLoginedUserId());
        List<Long> id = NumberUtils.strSplit2List(ids, ",", Long.class);
        response = userAuthDAO.batchUpdate(userAuth, id) > 0 ? new Response(ReturnCode.SUCCESS) : new Response(ReturnCode.ERROR);
        if (ReturnCode.isSuccess(response.getReturnCode())) {
            List<PersonRejection> rejections = new ArrayList<PersonRejection>();
            for (Long everyId : id) {
                PersonRejection personRejection = new PersonRejection();
                personRejection.setAuditId(SecurityUtils.getLoginedUserId());
                personRejection.setAuthId(everyId);
                personRejection.setOpinion(opinion);
                personRejection.setType(type);
                rejections.add(personRejection);
            }
            response = personRejectionService.batchAdd(rejections);
        }
        return response;
    }

}

package com.crop.seagulls.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.crop.seagulls.bean.Response;
import com.crop.seagulls.bean.ReturnCode;
import com.crop.seagulls.cache.CategoryCache;
import com.crop.seagulls.cache.FrontCache;
import com.crop.seagulls.cache.VarietiesCache;
import com.crop.seagulls.service.TaskService;
import com.crop.seagulls.task.CategoryTask;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private ThreadPoolTaskExecutor executor;
    
    @Autowired
    private CategoryCache categoryCache;

    @Autowired
    private VarietiesCache varietiesCache;

    @Autowired
    private CategoryTask categoryTask;
    
    @Autowired
    private FrontCache frontCache;

    @Override
    public Response syncCategoryJS() {
        varietiesCache.refreshAll();
        categoryCache.refreshAll();
        executor.execute(categoryTask);
        return new Response(ReturnCode.SUCCESS);
    }

    @Override
    public Response syncFront() {
        executor.execute(frontCache);
        return new Response(ReturnCode.SUCCESS);
    }

}

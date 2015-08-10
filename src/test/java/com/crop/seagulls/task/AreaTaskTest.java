package com.crop.seagulls.task;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;

public class AreaTaskTest extends SuperTest {

    @Autowired
    private AreaTask areaTask;
    
    @Test
    public void testStart() {
        areaTask.start();
    }

}

package com.crop.seagulls.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.crop.seagulls.SuperTest;
import com.crop.seagulls.service.impl.SystemSerivceImpl;

public class SystemSerivceImplTest extends SuperTest{

    @Autowired
    private SystemSerivce systemSerivce;
    @Test
    public void testSend() {
        System.out.println(systemSerivce.send("15901074186").getResult());
    }

}

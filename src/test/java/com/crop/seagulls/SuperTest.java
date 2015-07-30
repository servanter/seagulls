package com.crop.seagulls;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "/applicationContext-dao.xml", "/applicationContext-service.xml",
        "/applicationContext-db.xml", "/applicationContext-index.xml", "/applicationContext-geography.xml",
        "/applicationContext-channel.xml","/applicationContext-spider.xml","/applicationContext-third.xml" })
public class SuperTest {

    @Autowired
    private SqlSession sqlSession;

    /**
     * 清除test数据
     * 
     * @param table
     * @param conditions
     * @return
     */
    protected int del(String table, String conditions) {
        Map<String, String> m = new HashMap<String, String>();
        m.put("table", table);
        m.put("conditions", conditions);
        return sqlSession.delete("clear", m);
    }

}

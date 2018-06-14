package com.crud.tasks.config;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdminConfig.class)
public class AdminConfigTest {
    @Autowired
    private AdminConfig adminConfig = new AdminConfig();

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("admin.mail", "pawel.stopa.92@gmail.com");
    }

    @Test
    public void testGetAdminMail() {
        assertEquals("pawel.stopa.92@gmail.com", adminConfig.getAdminMail());
    }
}
package com.crud.tasks.trello.config;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TrelloConfig.class)
public class TrelloConfigTest {
    @Autowired
    private TrelloConfig trelloConfig = new TrelloConfig();

    @BeforeClass
    public static void beforeClass() {
        System.setProperty("trello.api.endpoint.prod", "https://api.trello.com/1");
        System.setProperty("trello.api.username", "pstopa");
    }

    @Test
    public void getTrelloApiEndpoint() {
        assertEquals("https://api.trello.com/1", trelloConfig.getTrelloApiEndpoint());
    }

    @Test
    public void getTrelloUserName() {
        assertEquals("pstopa", trelloConfig.getTrelloUsername());
    }
}
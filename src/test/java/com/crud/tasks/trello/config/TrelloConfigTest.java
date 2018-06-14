package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTest {
    @Autowired
    private TrelloConfig trelloConfig = new TrelloConfig();

    @Test
    public void getTrelloApiEndpoint() {
        assertEquals("https://api.trello.com/1", trelloConfig.getTrelloApiEndpoint());
    }

    @Test
    public void getTrelloUserName() {
        assertEquals("pstopa", trelloConfig.getTrelloUsername());
    }
}
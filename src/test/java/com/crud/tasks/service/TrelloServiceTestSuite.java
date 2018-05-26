package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.domain.*;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private AdminConfig adminConfig;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

        @Test
        public void createCardTest() {
            //Given
            TrelloCardDto trelloCardDto = new TrelloCardDto("name", "desc", "top", "1");
            TrelloDto trelloDto = new TrelloDto(1,1);
            AttachmentByType attachmentByType = new AttachmentByType(trelloDto);
            BadgesDto badgesDto = new BadgesDto(1, attachmentByType);
            CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "name", "http://short.pl", badgesDto);

            when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDto);
            //When
            CreatedTrelloCardDto fetchCreatedTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
            //Then
            assertEquals("1", fetchCreatedTrelloCardDto.getId());
            assertEquals("name", fetchCreatedTrelloCardDto.getName());
            assertEquals("http://short.pl", fetchCreatedTrelloCardDto.getShortUlr());
            assertEquals(badgesDto.getVotes(), fetchCreatedTrelloCardDto.getBadgesDto().getVotes());
            assertEquals(badgesDto.getAttachmentByType().getTrelloDto().getBoard(), fetchCreatedTrelloCardDto.getBadgesDto().getAttachmentByType().getTrelloDto().getBoard());
            assertEquals(badgesDto.getAttachmentByType().getTrelloDto().getCard(), fetchCreatedTrelloCardDto.getBadgesDto().getAttachmentByType().getTrelloDto().getCard());
        }

}
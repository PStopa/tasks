package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.Test;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TrelloMapperTestSuite {

    @InjectMocks
    private TrelloMapper mapper;

    @Test
    public void testMapToBoard() {
        //Given
        List<TrelloListDto> trelloListDto = new ArrayList<>();
        List<TrelloBoardDto> trelloBoardDto = Arrays.asList(
                new TrelloBoardDto("1", "First", trelloListDto),
                new TrelloBoardDto("2", "Second", trelloListDto));

        //When
        List<TrelloBoard> boards = mapper.mapToBoard(trelloBoardDto);

        //Then
        assertEquals(2, boards.size());
        assertEquals("1", boards.get(0).getId());
        assertEquals("2", boards.get(1).getId());
        assertEquals("First", boards.get(0).getName());
        assertEquals("Second", boards.get(1).getName());
    }

    @Test
    public void testMapToBoardsDto() {
        //Given
        List<TrelloList> trelloList = new ArrayList<>();

        List<TrelloBoard> trelloBoards = Arrays.asList(
                new TrelloBoard("1", "First", trelloList),
                new TrelloBoard("2", "Second", trelloList));

        //When
        List<TrelloBoardDto> trelloBoardDtos = mapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals("1", trelloBoardDtos.get(0).getId());
        assertEquals("2", trelloBoardDtos.get(1).getId());
        assertEquals("First", trelloBoardDtos.get(0).getName());
        assertEquals("Second", trelloBoardDtos.get(1).getName());
    }

    @Test
    public void mapToList() {
        //Given
        List<TrelloListDto> trelloListDto = Arrays.asList(
                new TrelloListDto("1", "First", true),
                new TrelloListDto("2", "Second", true));

        //When
        List<TrelloList> trelloList = mapper.mapToList(trelloListDto);
        //Then
        assertEquals(2, trelloList.size());
        assertEquals("1", trelloList.get(0).getId());
        assertEquals("2", trelloList.get(1).getId());
        assertEquals("First", trelloList.get(0).getName());
        assertEquals("Second", trelloList.get(1).getName());
    }

    @Test
    public void mapToListDto() {
        //Given
        List<TrelloList> trelloList = Arrays.asList(
                new TrelloList("1", "First", true),
                new TrelloList("2", "Second", true));
        //When
        List<TrelloListDto> trelloListDto = mapper.mapToListDto(trelloList);
        //Then
        assertEquals(2, trelloListDto.size());
        assertEquals("1", trelloListDto.get(0).getId());
        assertEquals("2", trelloListDto.get(1).getId());
        assertEquals("First", trelloListDto.get(0).getName());
        assertEquals("Second", trelloListDto.get(1).getName());
    }

    @Test
    public void mapToCardDto() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Name", "Description", "top", "1");
        //When
        TrelloCard trelloCard = mapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("Name", trelloCard.getName());
        assertEquals("Description", trelloCard.getDescription());
        assertEquals("top", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void mapToCard() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Name", "Description", "top", "1");
        //When
        TrelloCardDto trelloCardDto = mapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("Name", trelloCardDto.getName());
        assertEquals("Description", trelloCardDto.getDescription());
        assertEquals("top", trelloCardDto.getPos());
        assertEquals("1", trelloCardDto.getListId());
    }
}
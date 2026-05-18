package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTestSuite {

    TrelloMapper mapper;
    @BeforeEach
    void setUp() {
         mapper = new TrelloMapper();
    }

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloCard card = new TrelloCard("card","nice card","pos","0731");
        //When
        TrelloCardDto cardDto = mapper.mapToCardDto(card);

        //Then
        assertEquals("card", cardDto.getName());
        assertEquals("nice card", cardDto.getDescription());
        assertEquals("pos", cardDto.getPos());
        assertEquals("0731", cardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card","nice card dto","pos","22721");
        //When
        TrelloCard trelloCard = mapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("card", trelloCard.getName());
        assertEquals("nice card dto", trelloCard.getDescription());
        assertEquals("pos", trelloCard.getPos());
        assertEquals("22721", trelloCard.getListId());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        TrelloList trelloList1 = new TrelloList("303","List of doom",true);
        TrelloList trelloList2 = new TrelloList("404","List of gloom",false);
        //When
        List<TrelloListDto> trelloListDto = mapper.mapToListDto(Arrays.asList(trelloList1,trelloList2));

        //Then
        assertEquals("303",trelloListDto.getFirst().getId());
        assertEquals("List of doom",trelloListDto.getFirst().getName());
        assertTrue(trelloListDto.getFirst().isClosed());

        assertEquals("404",trelloListDto.getLast().getId());
        assertEquals("List of gloom",trelloListDto.getLast().getName());
        assertFalse(trelloListDto.getLast().isClosed());
    }

    @Test
    void mapToListTest() {
        //Given
        TrelloListDto trelloList1 = new TrelloListDto("303","List of doom",true);
        TrelloListDto trelloList2 = new TrelloListDto("404","List of gloom",false);
        //When
        List<TrelloList> trelloLists = mapper.mapToList(Arrays.asList(trelloList1,trelloList2));

        //Then
        assertEquals("303",trelloLists.getFirst().getId());
        assertEquals("List of doom",trelloLists.getFirst().getName());
        assertTrue(trelloLists.getFirst().isClosed());

        assertEquals("404",trelloLists.getLast().getId());
        assertEquals("List of gloom",trelloLists.getLast().getName());
        assertFalse(trelloLists.getLast().isClosed());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("302","skateboard", new ArrayList<TrelloList>());
        TrelloList trelloList1 = new TrelloList("303","List of doom",true);
        TrelloBoard trelloBoard2 = new TrelloBoard("505","dashboard", new ArrayList<TrelloList>(Arrays.asList(trelloList1)));
        //When
        List<TrelloBoardDto> trelloboardsDto = mapper.mapToBoardsDto(Arrays.asList(trelloBoard1,trelloBoard2));

        //Then
        assertEquals("skateboard",trelloboardsDto.getFirst().getName());
        assertEquals("List of doom",trelloboardsDto.getLast().getLists().get(0).getName());
    }

    @Test
    void mapToBoardsTest() {
        //Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("302","skateboard", new ArrayList<TrelloListDto>());
        TrelloListDto trelloList1 = new TrelloListDto("303","List of doom",true);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("505","dashboard", new ArrayList<TrelloListDto>(Arrays.asList(trelloList1)));
        //When
        List<TrelloBoard> trelloboards = mapper.mapToBoards(Arrays.asList(trelloBoardDto1,trelloBoardDto2));

        //Then
        assertEquals("skateboard",trelloboards.getFirst().getName());
        assertEquals("List of doom",trelloboards.getLast().getLists().get(0).getName());
    }

    @Test
    void mapToListDtoEmptyListTest() {
        List<TrelloListDto> result = mapper.mapToListDto(Collections.emptyList());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void mapToListEmptyListTest() {
        List<TrelloList> result = mapper.mapToList(Collections.emptyList());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void mapToBoardsDtoEmptyListTest() {
        List<TrelloBoardDto> result = mapper.mapToBoardsDto(Collections.emptyList());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void mapToBoardsEmptyListTest() {
        List<TrelloBoard> result = mapper.mapToBoards(Collections.emptyList());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}
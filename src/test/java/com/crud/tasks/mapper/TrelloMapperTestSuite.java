package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrelloMapperTestSuite {

    @Test
    void mapToCardDtoTest() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloCard card = new TrelloCard("card","nice card","pos","0731");
        //When
        TrelloCardDto cardDto = mapper.mapToCardDto(card);

        //Then
        assertEquals("0731", cardDto.getListId());
    }

    @Test
    void mapToCardTest() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloCardDto trelloCardDto = new TrelloCardDto("card","nice card dto","pos","22721");
        //When
        TrelloCard trelloCard = mapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("22721", trelloCard.getListId());
    }

    @Test
    void mapToListDtoTest() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloList trelloList1 = new TrelloList("303","List of doom",true);
        TrelloList trelloList2 = new TrelloList("404","List of gloom",false);
        //When
        List<TrelloListDto> trelloListDto = mapper.mapToListDto(Arrays.asList(trelloList1,trelloList2));

        //Then
        assertEquals("List of doom",trelloListDto.getFirst().getName());
    }

    @Test
    void mapToListTest() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
        TrelloListDto trelloList1 = new TrelloListDto("303","List of doom",true);
        TrelloListDto trelloList2 = new TrelloListDto("404","List of gloom",false);
        //When
        List<TrelloList> trelloLists = mapper.mapToList(Arrays.asList(trelloList1,trelloList2));

        //Then
        assertEquals("List of gloom", trelloLists.getLast().getName());
    }

    @Test
    void mapToBoardsDtoTest() {
        //Given
        TrelloMapper mapper = new TrelloMapper();
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
        TrelloMapper mapper = new TrelloMapper();
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("302","skateboard", new ArrayList<TrelloListDto>());
        TrelloListDto trelloList1 = new TrelloListDto("303","List of doom",true);
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("505","dashboard", new ArrayList<TrelloListDto>(Arrays.asList(trelloList1)));
        //When
        List<TrelloBoard> trelloboards = mapper.mapToBoards(Arrays.asList(trelloBoardDto1,trelloBoardDto2));

        //Then
        assertEquals("skateboard",trelloboards.getFirst().getName());
        assertEquals("List of doom",trelloboards.getLast().getLists().get(0).getName());
    }

}
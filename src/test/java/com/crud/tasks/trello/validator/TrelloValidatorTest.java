package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TrelloValidatorTest {

    private final TrelloValidator trelloValidator = new TrelloValidator();

    @Test
    void testValidateTrelloBoards() {
        // Given
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(new TrelloBoard("1", "test", new ArrayList<>()));
        trelloBoards.add(new TrelloBoard("2", "Normal Board", new ArrayList<>()));

        // When
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);

        // Then
        assertNotNull(filteredBoards);
        assertEquals(1, filteredBoards.size());
        assertEquals("Normal Board", filteredBoards.get(0).getName());
    }

    @Test
    void testValidateCard() {
        // Given
        TrelloCard testCard = new TrelloCard("test card", "Description", "top", "1");
        TrelloCard normalCard = new TrelloCard("Normal Task", "Description", "top", "1");

        // When & Then
        trelloValidator.validateCard(testCard);
        trelloValidator.validateCard(normalCard);
    }
}
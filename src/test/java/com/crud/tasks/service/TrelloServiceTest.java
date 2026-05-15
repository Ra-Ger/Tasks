package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.Mail;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldFetchTrelloBoards() {
        // Given
        List<TrelloBoardDto> boards = List.of(new TrelloBoardDto("1", "Board", List.of()));
        when(trelloClient.getTrelloBoards()).thenReturn(boards);

        // When
        List<TrelloBoardDto> result = trelloService.fetchTrelloBoards();

        // Then
        assertEquals(1, result.size());
    }

    @Test
    void shouldCreateTrelloCardAndSendEmail() {
        // Given
        TrelloCardDto cardDto = new TrelloCardDto("Test Card", "Desc", "top", "1");
        CreatedTrelloCardDto createdCard = new CreatedTrelloCardDto("1", "Test Card", "http://test.com",null);

        when(trelloClient.createNewCard(cardDto)).thenReturn(createdCard);
        when(adminConfig.getAdminMail()).thenReturn("admin@test.com");

        // When
        CreatedTrelloCardDto result = trelloService.createTrelloCard(cardDto);

        // Then
        assertEquals("1", result.getId());
        assertEquals("Test Card", result.getName());

        ArgumentCaptor<Mail> mailCaptor = ArgumentCaptor.forClass(Mail.class);
        verify(emailService, times(1)).send(mailCaptor.capture());

        assertEquals("admin@test.com", mailCaptor.getValue().getMailTo());
        assertEquals(TrelloService.SUBJECT, mailCaptor.getValue().getSubject());
    }
}
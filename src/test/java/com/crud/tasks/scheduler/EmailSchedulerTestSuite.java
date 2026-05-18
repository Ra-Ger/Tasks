package com.crud.tasks.scheduler;

import com.crud.tasks.repository.TaskRepository;
import com.crud.tasks.service.SimpleEmailService;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTestSuite {

    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldSendInformationEmail() {
        // Given
        when(taskRepository.count()).thenReturn(5L);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(simpleEmailService, times(1)).send(argThat(mail ->
                mail.getMailTo().equals("test@test.com") &&
                        mail.getMessage().contains("Currently in database you got: 5 tasks")
        ));
    }

    @Test
    void shouldSendInformationEmailWithSingularTaskWord() {
        // Given
        when(taskRepository.count()).thenReturn(1L);
        when(adminConfig.getAdminMail()).thenReturn("test@test.com");

        // When
        emailScheduler.sendInformationEmail();

        // Then
        verify(simpleEmailService, times(1)).send(argThat(mail ->
                mail.getMailTo().equals("test@test.com") &&
                        mail.getMessage().contains("Currently in database you got: 1 task")
        ));
    }
}
package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigTestSuite {

    @Test
    void testTrelloConfig() {
        // Given
        TrelloConfig config = new TrelloConfig();
        ReflectionTestUtils.setField(config, "trelloApiEndpoint", "http://test.com");

        // When
        String endpoint = config.getTrelloApiEndpoint();

        // Then
        assertEquals("http://test.com", endpoint);
    }

    @Test
    void testAdminConfig() {
        // Given
        AdminConfig config = new AdminConfig();
        ReflectionTestUtils.setField(config, "adminMail", "admin@test.com");

        // When
        String mail = config.getAdminMail();

        // Then
        assertEquals("admin@test.com", mail);
    }
}
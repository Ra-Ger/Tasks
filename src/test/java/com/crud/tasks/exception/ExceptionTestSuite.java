package com.crud.tasks.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExceptionTestSuite {

    @Test
    void testTaskNotFoundException() {
        // Given & When
        TaskNotFoundException exception = assertThrows(TaskNotFoundException.class, () -> {
            throw new TaskNotFoundException();
        });

        // Then
        assertEquals("Task not found", exception.getMessage());
    }
}
package com.datauser.goalmanagement.controllers;

import com.datauser.goalmanagement.dto.OperationalMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ErrorControllerTest {

    @Test
    void errorMessage() {
        ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new OperationalMessage("You are not allowed to view this page"));
    }
}
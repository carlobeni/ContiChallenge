package com.company.bank.presentation.controller;

import com.company.bank.application.dto.TransferDto;
import com.company.bank.application.dto.TransferRequestDto;
import com.company.bank.application.usecase.PerformTransferUseCase;
import com.company.bank.infrastructure.security.jwt.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TransferController.class)
@AutoConfigureMockMvc(addFilters = false) // Deshabilitar filtros de seguridad para probar la capa web en aislamiento
class TransferControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PerformTransferUseCase performTransferUseCase;

    @MockBean
    private JwtService jwtService; // Se requiere MockBean porque la configuración de seguridad lo inyecta

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @WithMockUser
    void shouldPerformTransferSuccessfully() throws Exception {
        TransferRequestDto request = new TransferRequestDto(1L, 2L, new BigDecimal("100.00"));
        TransferDto response = new TransferDto(1L, 1L, 2L, new BigDecimal("100.00"), LocalDateTime.now());
        
        when(performTransferUseCase.performTransfer(any(TransferRequestDto.class))).thenReturn(response);

        mockMvc.perform(post("/api/v1/transfers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .with(csrf()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.amount").value(100.0));
    }
}

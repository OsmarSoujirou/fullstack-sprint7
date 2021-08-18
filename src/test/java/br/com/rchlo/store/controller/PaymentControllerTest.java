package br.com.rchlo.store.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc
@Sql("/schema.sql")
@ActiveProfiles("test")
class PaymentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
     void shouldReturnBadRequestDueToViolationsOfThePaymentInclusionBusinessRules() throws Exception {

        URI uri = new URI("/payments");

        String jsonValueZero = "{ " +
                "\"value\": 0," +
                "\"cardClientName\": \"Pedro de Alcântara\"," +
                " \"cardNumber\": \"1234567890120987\"," +
                "\"cardExpiration\": \"2022-04\"," +
                "\"cardVerificationCode\": \"121\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(jsonValueZero)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

        String jsonCardClientNameVeryLarge = "{ " +
                "\"value\": 5000," +
                "\"cardClientName\": \"Pedro de Alcântara Francisco António João Carlos Xavier de Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim\"," +
                " \"cardNumber\": \"1234567890120987\"," +
                "\"cardExpiration\": \"2022-04\"," +
                "\"cardVerificationCode\": \"121\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(jsonCardClientNameVeryLarge)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

        String jsonCardNumberMissingDigits = "{ " +
                "\"value\": 5000," +
                "\"cardClientName\": \"Pedro de Alcântara\"," +
                " \"cardNumber\": \"12345678901209\"," +
                "\"cardExpiration\": \"2022-04\"," +
                "\"cardVerificationCode\": \"121\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(jsonCardNumberMissingDigits)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

        String jsonCardNumberManyDigits = "{ " +
                "\"value\": 5000," +
                "\"cardClientName\": \"Pedro de Alcântara\"," +
                " \"cardNumber\": \"12345678901209876\"," +
                "\"cardExpiration\": \"2022-04\"," +
                "\"cardVerificationCode\": \"121\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(jsonCardNumberManyDigits)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

        String jsonCardExpirationOld = "{ " +
                "\"value\": 5000," +
                "\"cardClientName\": \"Pedro de Alcântara\"," +
                " \"cardNumber\": \"1234567890120987\"," +
                "\"cardExpiration\": \"2021-04\"," +
                "\"cardVerificationCode\": \"121\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(jsonCardExpirationOld)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

        String jsonCardVerificationCodeMissingDigits = "{ " +
                "\"value\": 5000," +
                "\"cardClientName\": \"Pedro de Alcântara\"," +
                " \"cardNumber\": \"1234567890120987\"," +
                "\"cardExpiration\": \"2022-04\"," +
                "\"cardVerificationCode\": \"12\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(jsonCardVerificationCodeMissingDigits)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

        String jsonCardVerificationCodeManyDigits = "{ " +
                "\"value\": 5000," +
                "\"cardClientName\": \"Pedro de Alcântara\"," +
                " \"cardNumber\": \"1234567890120987\"," +
                "\"cardExpiration\": \"2022-04\"," +
                "\"cardVerificationCode\": \"1234\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(jsonCardVerificationCodeManyDigits)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(400));

    }

    @Test
    void shouldReturnBadRequestDueToViolationsOfThePaymentStatusUpdateBusinessRules() throws Exception {

        URI uri = new URI("/payments");

        String jsonPaymentValid = "{ " +
                "\"value\": 3000," +
                "\"cardClientName\": \"Pedro de Alcântara\"," +
                " \"cardNumber\": \"1234567890120987\"," +
                "\"cardExpiration\": \"2022-04\"," +
                "\"cardVerificationCode\": \"121\"" +
                "}";

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonPaymentValid)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers
                .status()
                .is(201));

        mockMvc.perform(MockMvcRequestBuilders
                .post(uri)
                .content(jsonPaymentValid)
                .contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers
                .status()
                .is(201));

        uri = new URI("/payments/1");

        mockMvc.perform(MockMvcRequestBuilders.put(uri)).andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(400));
        mockMvc.perform(MockMvcRequestBuilders.put(uri)).andExpect(MockMvcResultMatchers.status().is(400));

        uri = new URI("/payments/2");

        mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(200));

        mockMvc.perform(MockMvcRequestBuilders.put(uri)).andExpect(MockMvcResultMatchers.status().is(400));
        mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andExpect(MockMvcResultMatchers.status().is(400));

    }

}

package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.CustomerDto;
import com.MRensen.transportApp.TransportAppApplication;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.repository.CustomerRepository;
import com.MRensen.transportApp.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@ContextConfiguration(classes={TransportAppApplication.class})
@EnableConfigurationProperties
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    CustomerService customerService;

    @Mock
    Customer customer;

    @Test
    public void getCustomersReturnsStatusOkAndCustomerList() throws Exception{
        customer.setName("testman");
        List<Customer> returnList = new ArrayList<>();
        returnList.add(customer);

        CustomerDto dto = new CustomerDto();
        dto.name ="testman";
        List<CustomerDto> dtoList = new ArrayList<>();
        dtoList.add(dto);

        Mockito
                .when(customerService.getAll())
                .thenReturn(returnList);

        mvc.perform(get("/customers"))
                .andExpect(status().isOk());
    }

}
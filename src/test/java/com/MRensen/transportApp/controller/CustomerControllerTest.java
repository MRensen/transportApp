package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.CustomerDto;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@SpringBootTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    CustomerService customerService;

    Customer customer;

    @BeforeEach
    public void setup() {
        customer = new Customer();
        User user = new User();
        user.setUsername("tester");
        customer.setUser(user);
        customer.setName("testman");
        customer.setId(1L);
    }

@Test
public void getCustomersReturnsStatusOk() throws Exception{
    mvc.perform(get("/customers"))
                    .andExpect(status().isOk());
}
    @Test
    public void postCustomerReturnsStatusCreated() throws Exception{


        Mockito
                .when(customerService.addOne(any(Customer.class)))
                .thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(CustomerDto.fromCustomer(customer)));
        mvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(CustomerDto.fromCustomer(customer))))
                .andExpect(status().isCreated());
    }

    @Test
    public void getCustomerWithIdReturnsStatusOk() throws Exception{
        Mockito
                .when(customerService.getOne(anyLong()))
                .thenReturn(customer);

        ObjectMapper objectMapper = new ObjectMapper();
        mvc.perform(get("/customers/1"))
                .andExpect(status().isOk());
    }

}
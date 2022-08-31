package com.MRensen.transportApp.controller;

import com.MRensen.transportApp.DTO.CustomerDto;
import com.MRensen.transportApp.config.security.JwtRequestFilter;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.service.CustomUserDetailsService;
import com.MRensen.transportApp.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.sql.DataSource;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc(addFilters = false)
//@SpringBootTest
@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private DataSource dataSource;
    @MockBean
    private JwtRequestFilter jwtRequestFilter;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @MockBean
    CustomerService customerService;

    //    @Autowired
//    private WebApplicationContext context;
//
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
        Mockito.when(customerService.getAll()).thenReturn(new ArrayList<>());
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
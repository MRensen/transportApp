package com.MRensen.transportApp.service;

import com.MRensen.transportApp.TransportAppApplication;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@ContextConfiguration(classes={TransportAppApplication.class})
@EnableConfigurationProperties
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Mock
    Customer customer;

    @Mock
    Customer completeCustomer;

    List<Order> orderList;

    @BeforeEach
    void setup(){
        customer = new Customer();
        customer.setName("testing");
        customer.setUser(new User());
        customer.setId(1L);
        orderList = new ArrayList<Order>();
        orderList.add(new Order());
        orderList.add(new Order());
        completeCustomer = new Customer("X", 2L, "completeCustomer", "x","x","x",orderList,"x",null,"x","x","x","x");
        completeCustomer.setName("completeCustomer");

    }

    @Test
    void getAllReturnsList(){
        var answer = new ArrayList<Customer>();
        answer.add(customer);
        Mockito
                .when(customerRepository.findAll())
                .thenReturn(answer);

        var found = customerService.getAll();

        assertEquals(customer.getName(), found.get(0).getName() );
    }

    @Test
    void getOneResturnsCustomer(){
        Optional<Customer> option = Optional.of(customer);
        Mockito
                .when(customerRepository.findById(anyLong()))
                .thenReturn(option);

        var actual = customerService.getOne(customer.getId());
        assertEquals(customer.getName(), actual.getName());
    }

    @Test
    void patchOneReturnsCustomer(){
        Mockito
                .when(customerRepository.existsById(anyLong()))
                .thenReturn(true);

        Mockito
                .when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(customer));


        Customer actual = customerService.patchOne(1L, completeCustomer);

        assertEquals("completeCustomer", actual.getName());
    }

    @Test
    void updateOneReturnsCustomer(){
        Mockito
                .when(customerRepository.existsById(anyLong()))
                .thenReturn(true);

        Mockito
                .when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(customer));


        Customer actual = customerService.updateOne(1L, completeCustomer);

        assertEquals("completeCustomer", actual.getName());
    }

    @Test
    void getOrdersReturnsOrderList(){
        Mockito
                .when(customerRepository.findById(anyLong()))
                .thenReturn(Optional.of(completeCustomer));

        var actual = customerService.getOrders(1l);
        assertEquals(orderList, actual);
    }

}
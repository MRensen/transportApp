package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.BadRequestException;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.User;
import com.MRensen.transportApp.repository.CustomerRepository;
import com.MRensen.transportApp.repository.OrderRepository;
import com.MRensen.transportApp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    CustomerRepository customerRepository;

    @Mock
    UserRepository userRepository;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    CustomerService customerService = new CustomerService(customerRepository, userRepository, orderRepository);

    Customer customer;

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
    void addOneReturnsCustomer(){
        Mockito
                .when(customerRepository.existsById(customer.getId()))
                .thenReturn(false);
        Mockito
                .when(userRepository.existsById(customer.getUser().getUsername()))
                .thenReturn(false);
        Mockito
                .when(customerRepository.save(any()))
                .thenAnswer(i -> i.getArguments()[0]);

        assertEquals(customer, customerService.addOne(customer));
    }
    @Test
    void addOneThrowsExceptionWhenUserNotExist(){
        Mockito
                .when(customerRepository.existsById(customer.getId()))
                .thenReturn(false);
        Mockito
                .when(userRepository.existsById(customer.getUser().getUsername()))
                .thenReturn(true);
        assertThrows(BadRequestException.class, ()->{customerService.addOne(customer);});

    }
    @Test
    void addOneThrowsExceptionWhenCustomerNotExist(){
        Mockito
                .when(customerRepository.existsById(customer.getId()))
                .thenReturn(true);
        assertThrows(BadRequestException.class, ()->{customerService.addOne(customer);});
    }

    @Test
    void deleteOneInvokesDeleteById(){
        customerService.deleteOne(customer.getId());
        Mockito.verify(customerRepository, Mockito.times(1)).deleteById(customer.getId());
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
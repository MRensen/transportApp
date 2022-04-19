package com.MRensen.transportApp.service;


import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.CustomerRepository;
import com.MRensen.transportApp.repository.OrderRepository;
import com.MRensen.transportApp.repository.PalletRepository;
import com.MRensen.transportApp.repository.RouteRepository;
import com.MRensen.transportApp.utils.OrderStatus;
import com.MRensen.transportApp.utils.Pallet.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_CLASS)
public class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @MockBean
    OrderRepository orderRepository;

    @MockBean
    CustomerRepository customerRepository;

    @MockBean
    PalletRepository palletRepository;

    @MockBean
    RouteRepository routeRepository;

    Order order;
    Customer customer;
    Route route;

    EuroPallet euroPallet;
    BlockPallet blockPallet;
    OtherPallet otherPallet;

    @BeforeEach
    void setup(){
        customer = new Customer();
        customer.setName("testcustomer");
        customer.setId(2L);
        route = new Route();
        route.setId(3L);
        euroPallet = new EuroPallet("test", 100);
        blockPallet = new BlockPallet("test",100);
        otherPallet = new OtherPallet("test", 100, 100, 100);
        List<Pallet> pallets = new ArrayList<>();
        pallets.add(euroPallet);
        EuroPallet euroPallet1 = euroPallet;
        pallets.add(euroPallet1);
        order = new Order(PalletType.EURO, OrderStatus.PROCESSING, false, "testing", customer, route, pallets, "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test", "test");
        order.setId(1L);

    }

    @Test
    void getAllOrdersReturnsOrderList() {
        Order order1 = order;
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order);

        Mockito
                .when(orderRepository.findAll())
                .thenReturn(orders);

        List<Order> actual = orderService.getAllOrders();

        assertEquals(orders, actual);
    }

    @Test
    void getOrderThrowsException(){
        Mockito
                .when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThrows(RecordNotFoundException.class, ()->{orderService.getOrder(1L);});
    }

    @Test
    void getOrderReturnsOrder(){
        Mockito
                .when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        Order actual = orderService.getOrder(1L);

        assertEquals(1L, actual.getId());
        assertEquals("testing", actual.getDescription());
    }

    @Test
    void getOrdersByRouteReturnsOrderList(){
        Order order1 = order;
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order);

        Mockito
                .when(routeRepository.getById(anyLong()))
                .thenReturn(route);
        Mockito
                .when(orderRepository.findAllByRoute(any(Route.class)))
                .thenReturn(orders);

        List<Order> actual = orderService.getOrdersByRoute(anyLong());

        assertEquals(orders, actual);
    }
    @Test
    void getOrdersByOrderStatusReturnsOrderList(){
        Order order1 = order;
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order);

        Mockito
                .when(orderRepository.findAllByOrderStatus(any(OrderStatus.class)))
                .thenReturn(orders);

        List<Order> actual = orderService.getOrdersByStatus(OrderStatus.PROCESSING);

        assertEquals(orders, actual);
    }

    @Test
    void addOrderReturnsOrder(){
        Mockito
                .when(palletRepository.save(any(Pallet.class)))
                .thenReturn(euroPallet);
        Mockito
                .when(customerRepository.getById(anyLong()))
                .thenReturn(customer);

        Mockito
                .when(orderRepository.save(any(Order.class)))
                .thenReturn(order);

        Order actual = orderService.addOrder(order);

        assertEquals(1L, actual.getId());
    }

    @Test
    void getTypeThrowsException(){
        Mockito
                .when(orderRepository.existsById(anyLong()))
                .thenReturn(false);
        assertThrows(RecordNotFoundException.class, ()->{orderService.getType(1L);});

    }

    @Test
    void getTypeReturnsPalletType(){
        Mockito
                .when(orderRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        PalletType actual = orderService.getType(1L);
        assertEquals(PalletType.EURO, actual);
    }

    @Test
    void getTypeReturnsPalletTypeNone(){
        order.setPallets(new ArrayList<>());
        Mockito
                .when(orderRepository.existsById(anyLong()))
                .thenReturn(true);
        Mockito
                .when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        PalletType actual = orderService.getType(1L);
        assertEquals(PalletType.NONE, actual);
    }

    @Test
    void getPalletsReturnsPalletList(){
        List<Pallet> pallets = new ArrayList<>();
        pallets.add(euroPallet);
        EuroPallet euroPallet1 = euroPallet;
        pallets.add(euroPallet1);

        Mockito
                .when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        List<Pallet> actual = orderService.getPallets(1L);

        assertEquals(pallets, actual);
    }

    @Test
    public void updateOrderTest(){
        Mockito
                .when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        Mockito
                .when(orderRepository.existsById(anyLong()))
                .thenReturn(true);

        Mockito
                .when(customerRepository.getById(anyLong()))
                .thenReturn(customer);

        Mockito
                .when(customerRepository.existsById(anyLong()))
                        .thenReturn(true);

        Mockito
                .when(orderRepository.save(any(Order.class)))
                .thenReturn(order);

        Order actual = orderService.updateOrder(10L, order);
        assertEquals(actual.getRoute(), order.getRoute());
        assertEquals(actual.getCreator(), order.getCreator());
        assertEquals(actual.getDescription(), order.getDescription());
        assertEquals(actual.getType(), order.getType());
        assertEquals(actual.isPickup(), order.isPickup());
        assertEquals(actual.getOrderStatus(), order.getOrderStatus());
        assertEquals(actual.getPallets(), order.getPallets());
        assertEquals(actual.getLoadingStreet(), order.getLoadingStreet());
        assertEquals(actual.getLoadingHouseNumber(), order.getLoadingHouseNumber());
        assertEquals(actual.getLoadingPostal(), order.getLoadingPostal());
        assertEquals(actual.getLoadingName(), order.getLoadingName());
        assertEquals(actual.getLoadingCity(), order.getLoadingCity());
        assertEquals(actual.getLoadingDate(), order.getLoadingDate());
        assertEquals(actual.getDeliveryStreet(), order.getDeliveryStreet());
        assertEquals(actual.getDeliveryHouseNumber(), order.getDeliveryHouseNumber());
        assertEquals(actual.getDeliveryPostal(), order.getDeliveryPostal());
        assertEquals(actual.getDeliveryDate(), order.getDeliveryDate());
        assertEquals(actual.getDeliveryName(), order.getDeliveryName());
        assertEquals(actual.getDeliveryCity(), order.getDeliveryCity());

    }

    @Test
    public void updateOrderTestThrowsExceptionForOrder(){

        Mockito
                .when(orderRepository.existsById(anyLong()))
                .thenReturn(false);

        assertThrows(RecordNotFoundException.class,
                ()->orderService.updateOrder(10L,order),
                "Order not found");

    }
    @Test
    public void updateOrderTestThrowsExceptionForCustomer(){

        Mockito
                .when(customerRepository.existsById(anyLong()))
                .thenReturn(false);

        assertThrows(RecordNotFoundException.class,
                ()->orderService.updateOrder(10L,order),
                "No (creator)customer found");

    }

    @Test
    public void patchOrderTest(){
        Mockito
                .when(orderRepository.findById(anyLong()))
                .thenReturn(Optional.of(order));

        Mockito
                .when(orderRepository.existsById(anyLong()))
                .thenReturn(true);

        Mockito
                .when(routeRepository.getById(anyLong()))
                .thenReturn(route);

        Mockito
                .when(orderRepository.save(any(Order.class)))
                .thenReturn(order);

        Order actual = orderService.patchOrder(10L, order);

        assertEquals(actual.getRoute(), order.getRoute());
        assertEquals(actual.getCreator(), order.getCreator());
        assertEquals(actual.getDescription(), order.getDescription());
        assertEquals(actual.getType(), order.getType());
        assertEquals(actual.isPickup(), order.isPickup());
        assertEquals(actual.getOrderStatus(), order.getOrderStatus());
        assertEquals(actual.getPallets(), order.getPallets());
        assertEquals(actual.getLoadingStreet(), order.getLoadingStreet());
        assertEquals(actual.getLoadingHouseNumber(), order.getLoadingHouseNumber());
        assertEquals(actual.getLoadingPostal(), order.getLoadingPostal());
        assertEquals(actual.getLoadingName(), order.getLoadingName());
        assertEquals(actual.getLoadingCity(), order.getLoadingCity());
        assertEquals(actual.getLoadingDate(), order.getLoadingDate());
        assertEquals(actual.getDeliveryStreet(), order.getDeliveryStreet());
        assertEquals(actual.getDeliveryHouseNumber(), order.getDeliveryHouseNumber());
        assertEquals(actual.getDeliveryPostal(), order.getDeliveryPostal());
        assertEquals(actual.getDeliveryDate(), order.getDeliveryDate());
        assertEquals(actual.getDeliveryName(), order.getDeliveryName());
        assertEquals(actual.getDeliveryCity(), order.getDeliveryCity());


    }

    @Test
    public void patchOrderThrowsException(){
        Mockito
                .when(orderRepository.existsById(anyLong()))
                .thenReturn(false);

        assertThrows(RecordNotFoundException.class,
                ()-> orderService.patchOrder(anyLong(), order),
                "Order not found");
    }
}
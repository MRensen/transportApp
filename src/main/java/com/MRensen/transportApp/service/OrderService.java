package com.MRensen.transportApp.service;

import com.MRensen.transportApp.exception.RecordNotFoundException;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.repository.CustomerRepository;
import com.MRensen.transportApp.repository.OrderRepository;
import com.MRensen.transportApp.repository.PalletRepository;
import com.MRensen.transportApp.repository.RouteRepository;
import com.MRensen.transportApp.utils.OrderStatus;
import com.MRensen.transportApp.utils.Pallet.Pallet;
import com.MRensen.transportApp.utils.Pallet.PalletType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private PalletRepository palletRepository;
    private RouteRepository routeRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository,
                        CustomerRepository customerRepository,
                        PalletRepository palletRepository,
                        RouteRepository routeRepository) {
        this.palletRepository = palletRepository;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.routeRepository = routeRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        Optional<Order> orderOption = orderRepository.findById(id);
        if (orderOption.isPresent()) {
            return orderOption.get();
        } else {
            throw new RecordNotFoundException("Order Id was not found");
        }
    }

    public List<Order> getOrdersByRoute(Long id){
        Route route = routeRepository.getById(id);
        return orderRepository.findAllByRoute(route);
    }

    public List<Order>  getOrdersByStatus(OrderStatus status){
        return orderRepository.findAllByOrderStatus(status);
    }

    public Order addOrder(Order order) {
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public void updateOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            throw new RecordNotFoundException("Order not found");
        }
        Order old = orderRepository.findById(id).orElse(null);
        if (customerRepository.existsById(order.getCreator().getId())) {
            old.setCreator(customerRepository.getById(order.getCreator().getId()));
        } else {
            throw new RecordNotFoundException("No (creator)customer found");
        }
        old.setPallets(order.getPallets());
        old.setDescription(order.getDescription());
        old.setLoadingStreet(order.getLoadingStreet());
        old.setOrderStatus(order.getOrderStatus());
        old.setType(order.getType());
        old.setPickup(order.isPickup());
        old.setLoadingHouseNumber(order.getLoadingHouseNumber());
        old.setLoadingPostal(order.getLoadingPostal());
        old.setLoadingName(order.getLoadingName());
        old.setLoadingCity(order.getLoadingCity());
        old.setDeliveryStreet(order.getDeliveryStreet());
        old.setDeliveryHouseNumber(order.getDeliveryHouseNumber());
        old.setDeliveryPostal(order.getDeliveryPostal());
        old.setDeliveryName(order.getDeliveryName());
        old.setDeliveryCity(order.getDeliveryCity());
        old.setDeliveryDate(order.getDeliveryDate());
        orderRepository.save(old);
    }

    public void patchOrder(Long id, Order order) {
        if (!orderRepository.existsById(id)) {
            throw new RecordNotFoundException("Order not found");
        }
        Order old = orderRepository.findById(id).orElse(null);
        if(order.getRoute() != null){
            old.setRoute(routeRepository.getById(order.getRoute().getId()));
        }
        if (order.getCreator() != null) {
            old.setCreator(order.getCreator());
        }
        if(order.getDescription() != null){
            old.setDescription(order.getDescription());
        }
        if (order.getType() != null) {
            old.setType(order.getType());
        }
        if (order.isPickup() != null) {
            old.setPickup(order.isPickup());
        }
        if (order.getOrderStatus() != null) {
            old.setOrderStatus(order.getOrderStatus());
        }
        if (order.getPallets() != null) {
            old.setPallets(order.getPallets());
        }
        if (order.getLoadingStreet() != null) {
            old.setLoadingStreet(order.getLoadingStreet());
        }
        if (order.getLoadingHouseNumber() != null) {
            old.setLoadingHouseNumber(order.getLoadingHouseNumber());
        }
        if (order.getLoadingPostal() != null) {
            old.setLoadingPostal(order.getLoadingPostal());
        }
        if (order.getLoadingName() != null) {
            old.setLoadingName(order.getLoadingName());
        }
        if (order.getLoadingCity() != null) {
            old.setLoadingCity(order.getLoadingCity());
        }
        if (order.getLoadingDate() != null) {
            old.setLoadingDate(order.getLoadingDate());
        }
        if (order.getDeliveryStreet() != null) {
            old.setDeliveryStreet(order.getDeliveryStreet());
        }
        if (order.getDeliveryHouseNumber() != null) {
            old.setDeliveryHouseNumber(order.getDeliveryHouseNumber());
        }
        if (order.getDeliveryPostal() != null) {
            old.setDeliveryPostal(order.getDeliveryPostal());
        }
        if (order.getDeliveryDate() != null) {
            old.setDeliveryDate(order.getDeliveryDate());
        }
        if (order.getDeliveryName() != null) {
            old.setDeliveryName(order.getDeliveryName());
        }
        if (order.getDeliveryCity() != null) {
            old.setDeliveryCity(order.getDeliveryCity());
        }
        orderRepository.save(old);
    }

    public PalletType getType(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RecordNotFoundException("Order not found");
        }
        Order order = orderRepository.findById(id).orElse(null);
        var pallets = order.getPallets();
        if (pallets.isEmpty()) {
            return PalletType.NONE;
        } else {
            return pallets.get(0).getType();
        }
    }

    public void addPallet(Long id, Pallet pallet) {
        palletRepository.save(pallet);
        Order newOrder = new Order();
        newOrder.addPallet(pallet);
        patchOrder(id, newOrder);
    }

    public List<Pallet> getPallets(Long id) {
        Order order = getOrder(id);
        return order.getPallets();
    }
}
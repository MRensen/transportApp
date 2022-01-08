package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.model.Customer;
import com.MRensen.transportApp.model.Order;
import com.MRensen.transportApp.model.Route;
import com.MRensen.transportApp.utils.OrderStatus;
import com.MRensen.transportApp.utils.Pallet.Pallet;
import com.MRensen.transportApp.utils.Pallet.PalletType;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    public Long id;
    public PalletType type;
    public OrderStatus orderStatus;
    public Boolean isPickup = false;
    @Size(max=30)
    public String description;
    @JsonIncludeProperties("id")
    public Customer creator;
    @JsonIncludeProperties("id")
    public Route route;
    public List<PalletDto> pallets;

    public String loadingStreet;
    public String loadingHouseNumber;
    public String loadingPostal;
    public String loadingName;
    public String loadingCity;
    public String loadingDate;

    public String deliveryStreet;
    public String deliveryHouseNumber;
    public String deliveryPostal;
    public String deliveryName;
    public String deliveryCity;
    public String deliveryDate;

    public static OrderDto fromOrder(Order o){
        OrderDto dto = new OrderDto();
        dto.id = o.getId();
        dto.orderStatus = o.getOrderStatus();
        dto.type = o.getType();
        dto.isPickup = o.isPickup();
        dto.description = o.getDescription();
        dto.creator = o.getCreator();
        dto.route = o.getRoute();
        dto.pallets = o.getPallets().stream().map(PalletDto::fromPallet).toList();
        dto.loadingStreet = o.getLoadingStreet();
        dto.loadingHouseNumber = o.getLoadingHouseNumber();
        dto.loadingPostal = o.getLoadingPostal();
        dto.loadingName = o.getLoadingName();
        dto.loadingCity = o.getLoadingCity();
        dto.loadingDate = o.getLoadingDate();
        dto.deliveryStreet = o.getDeliveryStreet();
        dto.deliveryHouseNumber = o.getDeliveryHouseNumber();
        dto.deliveryPostal = o.getDeliveryPostal();
        dto.deliveryName = o.getDeliveryName();
        dto.deliveryCity = o.getDeliveryCity();
        dto.deliveryDate = o.getDeliveryDate();
        return dto;
    }

    public Order toOrder(){
        Order o = new Order();
        o.setId(id);
        o.setOrderStatus(orderStatus);
        o.setPickup(isPickup);
        o.setType(type);
        o.setDescription(description);
        o.setCreator(creator);
        o.setRoute(route);
        o.setPallets(pallets.stream().map(PalletDto::toPallet).toList());
        o.setLoadingStreet(loadingStreet);
        o.setLoadingHouseNumber(loadingHouseNumber);
        o.setLoadingPostal(loadingPostal);
        o.setLoadingName(loadingName);
        o.setLoadingCity(loadingCity);
        o.setLoadingDate(loadingDate);
        o.setDeliveryStreet(deliveryStreet);
        o.setDeliveryHouseNumber(deliveryHouseNumber);
        o.setDeliveryPostal(deliveryPostal);
        o.setDeliveryName(deliveryName);
        o.setDeliveryCity(deliveryCity);
        o.setDeliveryDate(deliveryDate);
        return o;
    }

}
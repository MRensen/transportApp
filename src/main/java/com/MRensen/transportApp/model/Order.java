package com.MRensen.transportApp.model;

import com.MRensen.transportApp.utils.OrderStatus;
import com.MRensen.transportApp.utils.Pallet.Pallet;
import com.MRensen.transportApp.utils.Pallet.PalletType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue
    @Id
    long id;
    @Enumerated(EnumType.STRING)
    PalletType type = PalletType.EURO; //default value, because an Order has to have a type

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    Boolean isPickup;

    String description;

    @ManyToOne
    @JsonIgnore
    Customer creator;

    @ManyToOne
    Route route;

    @OneToMany
    List<Pallet> pallets;

    String loadingStreet;
    String loadingHouseNumber;
    String loadingPostal;
    String loadingName;
    String loadingCity;
    String loadingDate;


    String deliveryStreet;
    String deliveryHouseNumber;
    String deliveryPostal;
    String deliveryName;
    String deliveryCity;
    String deliveryDate;

    //CONSTRUCTORS

    public Order() {
        this.pallets = new ArrayList<>();
    }

    public Order(PalletType type, OrderStatus orderStatus, Boolean isPickup, String description, Customer creator, Route route, List<Pallet> pallets, String loadingStreet, String loadingHouseNumber, String loadingPostal, String loadingName, String loadingCity, String loadingDate, String deliveryStreet, String deliveryHouseNumber, String deliveryPostal, String deliveryName, String deliveryCity, String deliveryDate) {
        this.type = type;
        this.orderStatus = orderStatus;
        this.isPickup = isPickup;
        this.description = description;
        this.creator = creator;
        this.route = route;
        this.pallets = pallets;
        this.loadingStreet = loadingStreet;
        this.loadingHouseNumber = loadingHouseNumber;
        this.loadingPostal = loadingPostal;
        this.loadingName = loadingName;
        this.loadingCity = loadingCity;
        this.loadingDate = loadingDate;
        this.deliveryStreet = deliveryStreet;
        this.deliveryHouseNumber = deliveryHouseNumber;
        this.deliveryPostal = deliveryPostal;
        this.deliveryName = deliveryName;
        this.deliveryCity = deliveryCity;
        this.deliveryDate = deliveryDate;
    }

    //GETTERS AND SETTERS


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PalletType getType() {
        return type;
    }

    public void setType(PalletType type) {
        this.type = type;
    }


    public Boolean isPickup() {
        return isPickup;
    }

    public void setPickup(Boolean pickup) {
        isPickup = pickup;
    }

    public String getLoadingDate() {
        return loadingDate;
    }

    public void setLoadingDate(String loadingDate) {
        this.loadingDate = loadingDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLoadingStreet() {
        return loadingStreet;
    }

    public void setLoadingStreet(String loadingStreet) {
        this.loadingStreet = loadingStreet;
    }

    public String getLoadingHouseNumber() {
        return loadingHouseNumber;
    }

    public void setLoadingHouseNumber(String loadingHouseNumber) {
        this.loadingHouseNumber = loadingHouseNumber;
    }

    public String getLoadingPostal() {
        return loadingPostal;
    }

    public void setLoadingPostal(String loadingPostal) {
        this.loadingPostal = loadingPostal;
    }

    public String getLoadingName() {
        return loadingName;
    }

    public void setLoadingName(String loadingName) {
        this.loadingName = loadingName;
    }

    public String getLoadingCity() {
        return loadingCity;
    }

    public void setLoadingCity(String loadingCity) {
        this.loadingCity = loadingCity;
    }

    public String getDeliveryStreet() {
        return deliveryStreet;
    }

    public void setDeliveryStreet(String deliveryStreet) {
        this.deliveryStreet = deliveryStreet;
    }

    public String getDeliveryHouseNumber() {
        return deliveryHouseNumber;
    }

    public void setDeliveryHouseNumber(String deliveryHouseNumber) {
        this.deliveryHouseNumber = deliveryHouseNumber;
    }

    public String getDeliveryPostal() {
        return deliveryPostal;
    }

    public void setDeliveryPostal(String deliveryPostal) {
        this.deliveryPostal = deliveryPostal;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public String getDeliveryCity() {
        return deliveryCity;
    }

    public void setDeliveryCity(String deliveryCity) {
        this.deliveryCity = deliveryCity;
    }

    public Customer getCreator() {
        return creator;
    }

    public void setCreator(Customer creator) {
        this.creator = creator;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public List<Pallet> getPallets() {
        return pallets;
    }

    public void setPallets(List<Pallet> pallets) {
        this.pallets = pallets;
    }

    public void addPallet(Pallet pallet) {
        if (this.pallets == null) {
            this.pallets = new ArrayList<>();
        }
        this.pallets.add(pallet);
    }


}

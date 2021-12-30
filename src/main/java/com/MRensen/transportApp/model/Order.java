package com.MRensen.transportApp.model;

import com.MRensen.transportApp.utils.Pallet.Pallet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue
    @Id
    long id;

    //loading adress
    String loadingStreet;
    String loadingHousenumber;
    String loadingPostal;
    String loadingName;
    String loadingCity;


    //delivery adress
    String deliveryStreet;
    String deliveryHousenumber;
    String deliveryPostal;
    String deliveryName;
    String deliveryCity;

    @ManyToOne
    Customer creator;

    @ManyToOne
    Route route;

    @ElementCollection
    @CollectionTable(
            name = "pallets",
            joinColumns = @JoinColumn(name="id")
    )
    @Column(name="palletlist")
    List<Pallet> pallets = new ArrayList<>();


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

    public String getLoadingHousenumber() {
        return loadingHousenumber;
    }

    public void setLoadingHousenumber(String loadingHousenumber) {
        this.loadingHousenumber = loadingHousenumber;
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

    public String getDeliveryHousenumber() {
        return deliveryHousenumber;
    }

    public void setDeliveryHousenumber(String deliveryHousenumber) {
        this.deliveryHousenumber = deliveryHousenumber;
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


}

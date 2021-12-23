package com.MRensen.transportApp.Model;

import com.MRensen.transportApp.utils.Pallet;

import javax.persistence.*;
import java.util.List;

@Entity
public class Order {
    @GeneratedValue
    @Id
    long id;
    long creatorId;

    List<Pallet> pallets;


}

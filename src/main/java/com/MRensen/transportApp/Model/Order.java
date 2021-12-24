package com.MRensen.transportApp.Model;

import com.MRensen.transportApp.utils.Pallet.Pallet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_table")
public class Order {
    @GeneratedValue
    @Id
    long id;

    long creatorId;

    @ManyToOne
    Route route;


    @ElementCollection
    @CollectionTable(
            name = "pallets",
            joinColumns = @JoinColumn(name="id")
    )
    @Column(name="palletlist")
    List<Pallet> pallets = new ArrayList<>();


}

package com.MRensen.transportApp.model;

import com.MRensen.transportApp.utils.Pallet.BlockPallet;
import com.MRensen.transportApp.utils.Pallet.EuroPallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

public class OrderTest {
    private Order order;
    private EuroPallet euro;
    private BlockPallet block;

    @BeforeEach
    void setup(){
        this.block = new BlockPallet();
        this.euro = new EuroPallet();
        this.order = new Order();
    }

    @Test
    void getEmptyPalletsArrayList(){
        List actual = this.order.getPallets();
        List expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}
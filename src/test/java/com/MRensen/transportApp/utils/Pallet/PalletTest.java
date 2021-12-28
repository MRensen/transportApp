package com.MRensen.transportApp.utils.Pallet;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class PalletTest{

    @Test
    void NoPalletTest(){
        Pallet pallet = new BlockPallet();
        assertEquals(null, pallet.getLoad());
        pallet = new EuroPallet();
        assertEquals(null, pallet.getLoad());
        pallet = new OtherPallet();
        assertEquals(null, pallet.getLoad());

    }

    @Test
    void BlockPalletTest(){
        BlockPallet pallet = new BlockPallet("test", 100);
        assertEquals("test", pallet.getLoad());
        assertEquals(120, pallet.getWidth());
        assertEquals(100, pallet.getLength());
        assertEquals(100, pallet.getHeight());
        pallet.setHeight(10);
        pallet.setLoad("setTest");
        assertEquals("setTest", pallet.getLoad());
        assertEquals(10, pallet.getHeight());
    }

    @Test
    void EuroPalletTest(){
        EuroPallet pallet = new EuroPallet("test", 100);
        assertEquals("test", pallet.getLoad());
        assertEquals(120, pallet.getWidth());
        assertEquals(80, pallet.getLength());
        assertEquals(100, pallet.getHeight());
        pallet.setHeight(10);
        pallet.setLoad("setTest");
        assertEquals("setTest", pallet.getLoad());
        assertEquals(10, pallet.getHeight());
    }

    @Test
    void OtherPalletTest(){
        OtherPallet pallet = new OtherPallet("test", 100, 100, 100);
        assertEquals("test", pallet.getLoad());
        assertEquals(100, pallet.getWidth());
        assertEquals(100, pallet.getLength());
        assertEquals(100, pallet.getHeight());
        pallet.setHeight(10);
        pallet.setLoad("setTest");
        pallet.setLength(10);
        pallet.setWidth(10);
        assertEquals(10, pallet.getWidth());
        assertEquals(10, pallet.getLength());
        assertEquals("setTest", pallet.getLoad());
        assertEquals(10, pallet.getHeight());
        pallet = new OtherPallet("constructorTest", 50);
        assertEquals(50, pallet.getHeight());
        assertEquals("constructorTest", pallet.getLoad());
    }
}
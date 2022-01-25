package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.utils.Pallet.*;

import javax.validation.constraints.Size;

public class PalletDto {
    public int height;
    public int width;
    public int length;
    public int weight;
    @Size(max = 10)
    public String load;
    public String type;

    public static PalletDto fromPallet(Pallet p){
        PalletDto dto = new PalletDto();
        dto.height=p.getHeight();
        dto.weight = p.getWeight();
        dto.width = p.getWidth();
        dto.length = p.getLength();
        dto.load = p.getLoad();
        dto.type = p.getType().toString();
        return dto;
    }
    public Pallet toPallet(){
        Pallet p;
        switch (type) {
            case "EURO" -> p = new EuroPallet(load, height);
            case  "BLOCK" -> p = new BlockPallet(load, height);
            case "OTHER" -> p = new OtherPallet(load, height, width, length);
            default -> p = new OtherPallet();
        };
        if (p != null) {
            p.setWeight(weight);
        }
        return p;
    }
}


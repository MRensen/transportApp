package com.MRensen.transportApp.DTO;

import com.MRensen.transportApp.utils.Pallet.*;

public class PalletDto {
    public int height;
    public int width;
    public int length;
    public int weight;
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
        Pallet p = switch (type) {
            case "euro" -> new EuroPallet(load, height);
            case "block" -> new BlockPallet(load, height);
            case "other" -> new OtherPallet(load, height, width, length);
            default -> null;
        };
        return p;
    }
}


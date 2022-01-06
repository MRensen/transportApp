package com.MRensen.transportApp.DTO;

public class IdDto {
    public long id;

    public IdDto fromID (Long id){
        IdDto dto = new IdDto();
        dto.id = id;
        return dto;
    }

    public Long toId(){
        return id;
    }
}

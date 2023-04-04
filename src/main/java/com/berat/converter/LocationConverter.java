package com.berat.converter;

import com.berat.dto.request.LocationCreateRequestDto;
import com.berat.model.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationConverter {

    public static Location toLocation(LocationCreateRequestDto dto){
        return Location.builder().country(dto.getCountry()).city(dto.getCity())
                .createDate(System.currentTimeMillis())
                .updateDate(System.currentTimeMillis())
                .build();
    }
}

package com.fixedAssets.persistence.mapper;

import com.fixedAssets.domain.LocationDo;
import com.fixedAssets.persistence.entity.Location;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LocationMapper {

    @Mappings({
            @Mapping(source = "locationId", target = "locationIdD"),
            @Mapping(source = "locationName", target = "locationNameD"),
            @Mapping(source = "locationAddress", target = "locationAddressD"),
            @Mapping(source = "locationCity", target = "locationCityD"),
            @Mapping(source = "locationCountry", target = "locationCountryD"),

    })
    LocationDo toLocationDo (Location location);

    @InheritInverseConfiguration
    @Mapping(target = "fixedAssets", ignore = true)
    Location toLocation (LocationDo locationDo);
}

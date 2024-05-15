package com.fixedAssets.persistence.mapper;

import com.fixedAssets.domain.DepreciationDo;
import com.fixedAssets.persistence.entity.Depreciation;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", uses = {FixedAssetMapper.class})
public interface DepreciationMapper {

    @Mappings({
            @Mapping(source = "depreciationId", target = "depreciationIdD"),
            @Mapping(source = "assetId", target = "assetIdD"),
            @Mapping(source = "depreciationDate", target = "depreciationDateD"),
            @Mapping(source = "depreciationValue", target = "depreciationValueD"),
            @Mapping(source = "fixedAsset", target = "fixedAssetDo")
    })
    DepreciationDo toDepreciationDo (Depreciation depreciation);

    @InheritInverseConfiguration
    Depreciation toDepreciation (DepreciationDo depreciationDo);
}

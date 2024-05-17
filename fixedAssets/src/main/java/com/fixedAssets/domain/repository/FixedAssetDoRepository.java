package com.fixedAssets.domain.repository;

import com.fixedAssets.domain.FixedAssetDo;
import com.fixedAssets.persistence.entity.FixedAsset;

import java.util.List;
import java.util.Optional;

public interface FixedAssetDoRepository {

    List<FixedAssetDo> getAll();
    Optional<List<FixedAssetDo>> getByResponsiblePersonDo(String personIdD);
    Optional<List<FixedAssetDo>> getMinimumQuantityDo (int acquisitionValueD);
    FixedAssetDo save(FixedAssetDo fixedAssetDo);

}

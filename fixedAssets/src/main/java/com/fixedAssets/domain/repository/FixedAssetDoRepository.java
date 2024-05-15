package com.fixedAssets.domain.repository;

import com.fixedAssets.domain.FixedAssetDo;

import java.util.List;
import java.util.Optional;

public interface FixedAssetDoRepository {

    List<FixedAssetDo> getAll();
    List<FixedAssetDo> getByResponisblePersonDo(String personIdD);
    Optional<List<FixedAssetDo>> getMinimumQuantityDo (int acquisitionValueD);
    FixedAssetDo save(FixedAssetDo fixedAssetDo);

}

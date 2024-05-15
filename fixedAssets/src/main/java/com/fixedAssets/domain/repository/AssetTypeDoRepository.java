package com.fixedAssets.domain.repository;

import com.fixedAssets.domain.AssetTypeDo;

import java.util.List;
import java.util.Optional;

public interface AssetTypeDoRepository {

    List<AssetTypeDo> getAll();
    Optional<AssetTypeDo> getById(int typeIdD);





}

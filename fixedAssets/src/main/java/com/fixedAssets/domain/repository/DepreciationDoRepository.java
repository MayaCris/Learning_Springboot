package com.fixedAssets.domain.repository;

import com.fixedAssets.domain.DepreciationDo;

import java.util.List;
import java.util.Optional;

public interface DepreciationDoRepository {
    Optional<List<DepreciationDo>> getDepreciationByIdD(int assetIdD);

    void deleteDepreciationD (int depreciationIdD);

}

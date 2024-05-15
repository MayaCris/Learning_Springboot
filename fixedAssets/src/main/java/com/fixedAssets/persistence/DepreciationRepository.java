package com.fixedAssets.persistence;

import com.fixedAssets.persistence.crud.DepreciationCrudRepository;
import com.fixedAssets.persistence.entity.Depreciation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DepreciationRepository {

    private DepreciationCrudRepository depreciationCrudRepository;

    public Optional<List<Depreciation>> getDepreciationById (int assetId){
        return depreciationCrudRepository.findAllByAssetId(assetId);
    }

    public void deleteDepreciation(int depreciationId) {
        depreciationCrudRepository.deleteById(depreciationId);
    }

}

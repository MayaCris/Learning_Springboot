package com.fixedAssets.persistence;

import com.fixedAssets.persistence.crud.FixedAssetCrudRepository;
import com.fixedAssets.persistence.entity.FixedAsset;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FixedAssetRepository {

    private FixedAssetCrudRepository fixedAssetCrudRepository;

    public List<FixedAsset> getAll(){
        return (List<FixedAsset>) fixedAssetCrudRepository.findAll();
    }

    public List<FixedAsset> getByResponsiblePerson(String personId){
        return fixedAssetCrudRepository.findByPersonIdOrderByAssetNameAsc(personId);
    }

    public Optional<List<FixedAsset>> getMinimumQuantity (int acquisitionValue) {
        return fixedAssetCrudRepository.findByAcquisitionValueLessThan(acquisitionValue);
    }

    public FixedAsset save(FixedAsset fixedAsset){
        return fixedAssetCrudRepository.save(fixedAsset);
    }
}

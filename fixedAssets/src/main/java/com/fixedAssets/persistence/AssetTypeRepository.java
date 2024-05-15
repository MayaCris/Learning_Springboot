package com.fixedAssets.persistence;

import com.fixedAssets.domain.AssetTypeDo;
import com.fixedAssets.domain.repository.AssetTypeDoRepository;
import com.fixedAssets.persistence.crud.AssetTypeCrudRepository;
import com.fixedAssets.persistence.entity.AssetType;
import com.fixedAssets.persistence.mapper.AssetTypeMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AssetTypeRepository implements AssetTypeDoRepository {

    private AssetTypeCrudRepository assetTypeCrudRepository;

    private AssetTypeMapper mapper;

    @Override
    public List<AssetTypeDo> getAll(){
        List<AssetType> assetTypes = (List<AssetType>) assetTypeCrudRepository.findAll();
        return mapper.toAssetTypeDoList(assetTypes);
    }

    @Override
    public Optional<AssetTypeDo> getById(int typeIdD){
        Optional<AssetType> assetTypes = assetTypeCrudRepository.findById(typeIdD);
        return assetTypes.map(assetType -> mapper.toAssetTypeDo(assetType));

    }
}

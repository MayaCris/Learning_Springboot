package com.fixedAssets.domain.service;

import com.fixedAssets.domain.FixedAssetDo;
import com.fixedAssets.domain.repository.FixedAssetDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FixedAssetService {

    @Autowired
    private FixedAssetDoRepository fixedAssetDoRepository;

    public List<FixedAssetDo> getAll(){
        return fixedAssetDoRepository.getAll();
    }

    public Optional<List<FixedAssetDo>> getByResponsiblePersonDo(String personIdD){
        return fixedAssetDoRepository.getByResponsiblePersonDo(personIdD);
    }

    public Optional<List<FixedAssetDo>> getMinimumQuantityDo(int acquisitionValueD){
        return fixedAssetDoRepository.getMinimumQuantityDo(acquisitionValueD);
    }

    public FixedAssetDo save(FixedAssetDo fixedAssetDo){
        return fixedAssetDoRepository.save(fixedAssetDo);
    }
}

package com.fixedAssets.domain.service;

import com.fixedAssets.domain.DepreciationDo;
import com.fixedAssets.domain.repository.DepreciationDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;

@Service
public class DepreciationService {

    @Autowired
    private DepreciationDoRepository depreciationDoRepository;

    public List<DepreciationDo> getAll(){
        return depreciationDoRepository.getAll();
    }

    public Optional<List<DepreciationDo>> findByDepreciationIdD(int DepreciationIdD){
        return depreciationDoRepository.findByDepreciationIdD(DepreciationIdD);

    }

    public Optional<List<DepreciationDo>> findByAssetIdD (int assetIdD){
        return depreciationDoRepository.findByAssetIdD(assetIdD);
    }

    public DepreciationDo save(DepreciationDo depreciationDo){
        return depreciationDoRepository.save(depreciationDo);
    }

    public boolean delete(int depreciationIdD){
        return findByDepreciationIdD(depreciationIdD).map(DepreciationDo -> {
            depreciationDoRepository.delete(depreciationIdD);
            return true;
        }).orElse(false);

    }

}

package com.fixedAssets.persistence.crud;

import com.fixedAssets.persistence.entity.FixedAsset;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

import java.util.List;

public interface FixedAssetCrudRepository extends CrudRepository<FixedAsset, Integer> {


    //Query Method para Lista de activos que estén asignados a una persona en especifico
    List<FixedAsset> findByPersonIdOrderByAssetNameAsc(String personId); //Esto tambien se puede hacer con Query nativa: Pero no se por que no funciona 🤦‍♂️
    //@Query(value = "SELECT * FROM FIXED_ASSETS WHERE PERSON_ID = ?", nativeQuery = true);

    //Lista de activos con valor de adquisicion menor a 50 UVT $2.353.250
    Optional<List<FixedAsset>> findByAcquisitionValueLessThan(int acquisitionValue);


}

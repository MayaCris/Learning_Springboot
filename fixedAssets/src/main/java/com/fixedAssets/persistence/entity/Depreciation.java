package com.fixedAssets.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "DEPRECIATION")
public class Depreciation {

    @Id
    @Column(name = "DEPRECIATION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer depreciationId;

    @Column(name = "ASSET_ID")
    private Integer assetId;

    @Column(name = "DEPRECIATION_DATE")
    private LocalDateTime depreciationDate;

    @Column(name = "DEPRECIATION_VALUE")
    private Double depreciationValue;

    @ManyToOne
    @MapsId("assetId")
    @JoinColumn(name = "ASSET_ID",insertable = false, updatable = false)
    private FixedAsset fixedAsset;


    //Getters and Setters

    public Integer getDepreciationId() {
        return depreciationId;
    }

    public FixedAsset getFixedAsset() {
        return fixedAsset;
    }

    public void setFixedAsset(FixedAsset fixedAsset) {
        this.fixedAsset = fixedAsset;
    }

    public void setDepreciationId(Integer depreciationId) {
        this.depreciationId = depreciationId;
    }

    public Integer getAssetId() {
        return assetId;
    }

    public void setAssetId(Integer assetId) {
        this.assetId = assetId;
    }

    public LocalDateTime getDepreciationDate() {
        return depreciationDate;
    }

    public void setDepreciationDate(LocalDateTime depreciationDate) {
        this.depreciationDate = depreciationDate;
    }

    public Double getDepreciationValue() {
        return depreciationValue;
    }

    public void setDepreciationValue(Double depreciationValue) {
        this.depreciationValue = depreciationValue;
    }
}

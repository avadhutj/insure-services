package com.concretepage.service;

import java.util.List;
import com.concretepage.entity.Asset;

public interface IAssetService {
	
	void addAsset(Asset asset);	
	
	List<Asset> getAllAssets();
	
    Asset getAssetById(int id);   
    
    Asset updateAsset(Asset asset);
    
    void deleteAsset(int id);
}

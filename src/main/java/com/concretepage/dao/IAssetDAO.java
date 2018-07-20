package com.concretepage.dao;

import java.util.List;
import com.concretepage.entity.Asset;
public interface IAssetDAO {
	
	void addAsset(Asset asset);	
	
	List<Asset> getAllAssets();
	
    Asset getAssetById(int id);   
    
    Asset updateAsset(Asset asset);
    
    void deleteAsset(int id);
}
 
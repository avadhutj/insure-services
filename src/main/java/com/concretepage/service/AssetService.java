package com.concretepage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.concretepage.dao.IAssetDAO;
import com.concretepage.entity.Asset;

@Service
public class AssetService implements IAssetService {
	@Autowired
	private IAssetDAO assetDAO;

	@Override
	public void addAsset(Asset asset) {
		assetDAO.addAsset(asset);		
	}

	@Override
	public List<Asset> getAllAssets() {
		return assetDAO.getAllAssets();
	}

	@Override
	public Asset getAssetById(int id) {
		return assetDAO.getAssetById(id);
	}

	@Override
	public Asset updateAsset(Asset asset) {
		return assetDAO.updateAsset(asset);
	}

	@Override
	public void deleteAsset(int id) {		
		assetDAO.deleteAsset(id);
	}	
	
}

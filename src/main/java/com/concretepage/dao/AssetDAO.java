package com.concretepage.dao;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.concretepage.entity.Asset;
@Transactional
@Repository
public class AssetDAO implements IAssetDAO{
	
	@PersistenceContext	
	private EntityManager entityManager;
	
	@Override
	public void addAsset(Asset asset) {	
		asset.setLastUpdated(new Date());
		entityManager.persist(asset);		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Asset> getAllAssets() {
		String hql = "FROM Asset";
		return (List<Asset>) entityManager.createQuery(hql).getResultList();
	}
	@Override
	public Asset getAssetById(int id) {
		return entityManager.find(Asset.class, id);		
	}
	@Override
	public Asset updateAsset(Asset asset) {
		Asset existingAsset = getAssetById(asset.getId());
		existingAsset.setLastUpdated(new Date());
		entityManager.flush();
		return existingAsset;
	}
	@Override
	public void deleteAsset(int id) {
		entityManager.remove(getAssetById(id));			
	}
}

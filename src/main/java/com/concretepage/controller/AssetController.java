package com.concretepage.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.concretepage.entity.Asset;
import com.concretepage.service.AssetService;

@Controller
@RequestMapping("asset")
public class AssetController {
	@Autowired
	private AssetService assetService;

	private static Logger logger = LoggerFactory
			.getLogger(AssetController.class);

	@GetMapping("getAssetById/{id}")
	public ResponseEntity<Asset> getArticleById(@PathVariable("id") Integer id) {
		Asset asset = assetService.getAssetById(id);
		asset = bindImages(asset);
		return new ResponseEntity<Asset>(asset, HttpStatus.OK);
	}

	@GetMapping("assets")
	public ResponseEntity<List<Asset>> getAllArticles() {
		List<Asset> list = assetService.getAllAssets();
		return new ResponseEntity<List<Asset>>(list, HttpStatus.OK);
	}

	@PostMapping("captureAsset")
	public ResponseEntity<Asset> addArticle(@RequestBody Asset asset)
			throws Exception {
		assetService.addAsset(buildAssetToStore(asset));
		logger.debug("{} Captured Asset {} ", asset.getId(), Calendar
				.getInstance().getTime());
		return new ResponseEntity<Asset>(asset, HttpStatus.CREATED);
	}

	@PostMapping("captureAssets")
	public ResponseEntity<Boolean> addAssets(@RequestBody List<Asset> assetList)
			throws Exception {
		assetList.forEach(asset -> assetService
				.addAsset(buildAssetToStore(asset)));
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	@GetMapping(value = "getUserAssets/{uid}")
	public ResponseEntity<List<Asset>> getUserAssets(
			@PathVariable("uid") int uid) throws IOException {
		List<Asset> filteredAssets = assetService.getAllAssets().stream()
				.filter(asset -> asset.getUserId() == uid)
				.collect(Collectors.toList());
		filteredAssets.forEach(p -> bindImages(p));
		return new ResponseEntity<List<Asset>>(filteredAssets,
				HttpStatus.CREATED);
	}

	private Asset buildAssetToStore(Asset asset) {
		// Get User Home
		String userHome = System.getProperty("user.home");
		// Create User Directory
		String directoryName = userHome + "\\" + asset.getUserId();// +"\\"+asset.getCategoryId();
		File directoryUserId = new File(directoryName);
		if (!directoryUserId.exists()) {
			directoryUserId.mkdir();
			System.out.println(directoryUserId + " Created ");
		}
		// Create Category Directory within User Directory
		File directoryUserCategoryId = new File(directoryUserId + "\\"
				+ asset.getCategoryId());

		if (!directoryUserCategoryId.exists()) {
			directoryUserCategoryId.mkdir();
			System.out.println(directoryUserCategoryId + " Created ");
		}

		// Create Image Paths for all Images
		String image1AbsoulutePath = directoryUserCategoryId + "\\"
				+ "img1.jpg";
		String image2AbsoulutePath = directoryUserCategoryId + "\\"
				+ "img2.jpg";
		String image3AbsoulutePath = directoryUserCategoryId + "\\"
				+ "img3.jpg";
		String image4AbsoulutePath = directoryUserCategoryId + "\\"
				+ "imgInvoice.jpg";

		// Save Asset and Invoice Image to disk
		if (asset.getImage1() != null) {
			save(image1AbsoulutePath, asset.getImage1());
		}

		if (asset.getImage2() != null) {
			save(image2AbsoulutePath, asset.getImage2());
		}

		if (asset.getImage3() != null) {
			save(image3AbsoulutePath, asset.getImage3());
		}

		if (asset.getInvoiceImage() != null) {
			save(image4AbsoulutePath, asset.getInvoiceImage());
		}

		// Set Asset Image Locations
		asset.setImage1Path(image1AbsoulutePath);
		asset.setImage2Path(image2AbsoulutePath);
		asset.setImage3Path(image3AbsoulutePath);
		asset.setInvoicePath(image4AbsoulutePath);

		return asset;
	}

	private Asset bindImages(Asset asset) {

		try {
			if (asset.getImage1Path() != null) {
				Path path = Paths.get(asset.getImage1Path());
				byte[] image1 = Files.readAllBytes(path);
				asset.setImage1(image1);
			}

			if (asset.getImage2Path() != null) {
				Path path = Paths.get(asset.getImage2Path());
				byte[] image2 = Files.readAllBytes(path);
				asset.setImage2(image2);
			}

			if (asset.getImage3Path() != null) {
				Path path = Paths.get(asset.getImage3Path());
				byte[] image3 = Files.readAllBytes(path);
				asset.setImage3(image3);
			}

			if (asset.getInvoicePath() != null) {
				Path path = Paths.get(asset.getInvoicePath());
				byte[] invoiceImage = Files.readAllBytes(path);
				asset.setInvoiceImage(invoiceImage);
			}
		} catch (IOException e) {
			logger.error("Error while reading images from disk {} ", e);
		}

		return asset;
	}

	@PutMapping("updateAsset")
	public ResponseEntity<Asset> updateArticle(@RequestBody Asset asset) {
		assetService.updateAsset(asset);
		return new ResponseEntity<Asset>(asset, HttpStatus.OK);
	}

	@DeleteMapping("deleteAssetById/{id}")
	public ResponseEntity<Void> deleteArticle(@PathVariable("id") Integer id) {
		assetService.deleteAsset(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	private void save(String fileName, byte[] image) {
		try {
			Path path = Paths.get(fileName);
			Files.write(path, image);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * Converts a given Image into a BufferedImage
	 *
	 * @param img
	 *            The Image to be converted
	 * @return The converted BufferedImage
	 */
	public BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}

		// Create a buffered image with transparency
		BufferedImage bimage = new BufferedImage(img.getWidth(null),
				img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

		// Draw the image on to the buffered image
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();

		// Return the buffered image
		return bimage;
	}
}
package com.aj.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aj.ecommerce.model.Brand;
import com.aj.ecommerce.model.Category;
import com.aj.ecommerce.repository.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository brandRepository;
	
	public List<Brand> getAllBrands(){
		return brandRepository.findAll();
	}
	
	// Creating Brand
	public Brand createBrand(Brand brand) {
		brand.setCreatedDateTime(LocalDateTime.now());
		brand.setUpdatedDateTime(LocalDateTime.now());
		return brandRepository.save(brand);
	}
	
	//Update Brands 
	public Brand updateBrand(int id, Brand brandDetails) {
		Brand brand = brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found"));
		brand.setName(brandDetails.getName());
		brand.setUpdatedBy(brandDetails.getUpdatedBy());
		brand.setUpdatedDateTime(LocalDateTime.now());
		return brandRepository.save(brand);
	}
	
	//Delete Brands
	public void deleteBrand(int id) {
        brandRepository.deleteById(id);
    }
    
    public boolean existsById(int id) {
        return brandRepository.findById(id).isPresent();  
    }
    
    public Brand getBrandById(Integer brandId) {
        return brandRepository.findById(brandId).orElse(null);
    }
	
	
}

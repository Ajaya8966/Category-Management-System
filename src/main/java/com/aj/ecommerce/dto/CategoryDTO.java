package com.aj.ecommerce.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CategoryDTO {

	private Integer id;
	
	@NotBlank(message = "Category name is required")
	@Size(min = 2, max = 50, message = "Category name mut be between 2 and 50 characters")
	private String name;
	
    @NotBlank(message = "Created By is required")
	private String createdBy;
	
    @NotBlank(message = "Updated By is required")
	private String updatedBy;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	 // Constructor
    public CategoryDTO(String name, String updatedBy) {
        this.name = name;
        this.updatedBy = updatedBy;
    }

    // Default constructor (required for Spring)
    public CategoryDTO() {}

    
    
    
    
}

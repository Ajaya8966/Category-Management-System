package com.aj.ecommerce.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aj.ecommerce.dto.CategoryDTO;
import com.aj.ecommerce.model.Category;
import com.aj.ecommerce.service.CategoryService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/category") // Separate from /api/categories
public class CategoryViewController {

    @Autowired
    private CategoryService categoryService;

    // Display category list page
    @GetMapping
    public String showCategoryPage(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/category/category"; // Loads category.html inside /templates/admin/category/
    }

    // Show form to add a new category
    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new CategoryDTO()); // Use DTO for the form
        return "admin/category/add-category"; // Corrected path
    }
    
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute CategoryDTO categoryDTO, RedirectAttributes redirectAttributes) {
        if (categoryService.categoryExists(categoryDTO.getName())) {
            redirectAttributes.addFlashAttribute("error", "Category name already exists, please enter a unique category.");
            return "redirect:/admin/category/add"; // Redirect to the add-category page
        }

        categoryService.createCategory(categoryDTO);
        redirectAttributes.addFlashAttribute("success", "Category added successfully!");
        return "redirect:/admin/category";
    }
    
 // Show form to edit an existing category
    @GetMapping("/edit-category/{id}")
    public String showEditCategoryForm(@PathVariable("id") Integer id, Model model) {
        Category categoryDTO = categoryService.getCategoryById(id); // Fetch category to be edited
        model.addAttribute("category", categoryDTO);
        return "admin/category/edit-category"; // Edit category form
    }
    
    // Update an existing category
 // Show Edit Form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "edit-category"; // Thymeleaf template name
    }

    // Handle Form Submission
    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category, Model model) {
        // Create a DTO for the category with updated values
        CategoryDTO categoryDTO = new CategoryDTO(category.getName(), category.getUpdatedBy());

        // Check if the category name already exists (excluding the current category)
        if (categoryService.existsByNameAndIdNot(category.getName(), category.getId())) {
            // If the category name already exists, add an error message
            model.addAttribute("error", "Category name already exists. Please enter a unique category name.");
            model.addAttribute("category", category);  // Ensure category object is available in the model
            return "edit-category";  // Return to the edit page
        }

        // If no duplicates, update the category
        categoryService.updateCategory(category.getId(), categoryDTO);

        return "redirect:/admin/category";  // Redirect to the category list after updating
    }	

    

    // Delete a category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        categoryService.deleteCategory(id); // Delete category from database
        redirectAttributes.addFlashAttribute("success", "Category deleted successfully!");
        return "redirect:/admin/category"; // Redirect to category list page
    }


    
}


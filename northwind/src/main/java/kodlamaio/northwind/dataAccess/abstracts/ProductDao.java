package kodlamaio.northwind.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

public interface ProductDao extends JpaRepository<Product,Integer> {
	//getBy isimlendirme kurallarına uyduktan sonra kod yazmaya gerek yok hepsi Jpa Tarafından bize veriliyor.
	
	//İlk bulduğunu getirir
	Product getByProductName(String productName);
	
	Product getByProductNameAndCategory_CategoryId(String productName, int categoryId);
	//Hepsini getirir
	List<Product> getByProductNameOrCategory_CategoryId(String productName, int categoryId);
	
	List<Product> getByCategoryIn(List<Integer> categories);
	
	List<Product> getByProductNameContains(String productName);
	
	List<Product> getByProductNameStartsWith(String productName);
	
	@Query("From Product where productName=:productName and category.categoryId=:categoryId")
	List<Product> getByNameAndCategory(String productName, int categoryId);
	
	
	//Ana tablo önce yazılmalı  ,,,  OneToMany de ki One kısmız ilk yazılmalı
	@Query("Select new kodlamaio.northwind.entities.dtos.ProductWithCategoryDto"
			+ "(p.id, p.productName, c.categoryName) "
			+ "From Category c Inner Join c.products p")
	List<ProductWithCategoryDto> getProductWithCategoryDetails();
	
	
	
	
	
	

}

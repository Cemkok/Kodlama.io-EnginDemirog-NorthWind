package kodlamaio.northwind.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.ProductService;
import kodlamaio.northwind.core.utilities.results.DataResult;
import kodlamaio.northwind.core.utilities.results.Result;
import kodlamaio.northwind.core.utilities.results.SuccessDataResult;
import kodlamaio.northwind.entities.concretes.Product;
import kodlamaio.northwind.entities.dtos.ProductWithCategoryDto;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	private ProductService productService;
	@Autowired
	public ProductsController(ProductService productService) {
		super();
		this.productService = productService;
	}
	@GetMapping("/getall")
		//Dönüş tipi // Datanın tipi =(T data, hangi tipte olduğu belli değildi, şimdi belirliliyoruz)
	public DataResult<List<Product>> getAll(){
		return this.productService.getAll();
	}
	
	@PostMapping("/add")
	public Result add(@RequestBody Product Product) {
		return this.productService.add(Product);
		
	}
	
	@GetMapping("/getByProductName") 		//Kullanıcıdan aldığımız bilgi
	public DataResult<Product> getByProductName(@RequestParam String productName){
		return this.productService.getByProductName(productName);
	}
	
	@GetMapping("/getByProductNameAndCategoryId")
	public DataResult<Product> 
	getByProductNameAndCategory_CategoryId(@RequestParam("productName") String productName, @RequestParam("categoryId")int categoryId)
	{
		return this.productService.getByProductNameAndCategory_CategoryId(productName, categoryId);
	}
	
	@GetMapping("/getByProductNameContains")
	DataResult<List<Product>> getByProductNameContains(@RequestParam String productName){
		return this.productService.getByProductNameContains(productName);
		
		
	}
	@GetMapping("/getAllByPage")
	DataResult<List<Product>>  getAll(int PageNo, int pageSize){
		return this.productService.getAll(PageNo, pageSize);
		
	}
	
	@GetMapping("/getAllBySortDesc")
	public DataResult<List<Product>> getAllSorted() {
		return this.productService.getAllSorted();
	
	}

	@GetMapping("/getProductWithCategoryDetails")
	public DataResult<List<ProductWithCategoryDto>> getProductWithCategoryDetails() {
			return this.productService.getProductWithCategoryDetails();
		}
}
	



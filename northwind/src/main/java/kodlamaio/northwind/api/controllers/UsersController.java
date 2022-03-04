package kodlamaio.northwind.api.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.northwind.business.abstracts.UserService;
import kodlamaio.northwind.core.entities.User;
import kodlamaio.northwind.core.utilities.results.ErrorDataResult;
;

@RestController
@RequestMapping("api/users")
public class UsersController {
	
	private UserService userService;

	@Autowired
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	 
	@PostMapping(value="/add")
	// ? ne döneceğini belli değil, başarılı yada başarısız olabilir.
	
	public ResponseEntity<?>  add(@Valid @RequestBody User user) {
		return ResponseEntity.ok(this.userService.add(user));
		
	}
	//Döneceği nesnenin türü bilinmediğinden bütün class ların temeli olan(primitive tipler dahil) "Object" yazıyoruz 
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorDataResult<Object> handleValidionException
	(MethodArgumentNotValidException exceptions){

		//ilk yazan kontrol edilen yerin tipi
		//İkinci yazılan mesaj tipi
		
		Map<String,String> validationErrors=new HashMap<String,String>();
										//User nesnesi için valid alanlarında oluşan hataları getir
		for(FieldError fieldError: exceptions.getBindingResult().getFieldErrors()) {
			validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
			
		}
		
		ErrorDataResult<Object> errors =new ErrorDataResult<Object>(validationErrors,"Doğrulama hataları");
		
		return errors;
		
		 
	}
	

}

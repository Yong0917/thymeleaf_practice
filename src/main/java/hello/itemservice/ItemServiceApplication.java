package hello.itemservice;

import hello.itemservice.domain.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemServiceApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	// 설정하면 모든 컨트롤러에서 사용 가능
//	@Override
//	public Validator getValidator() {
//		return new ItemValidator();
//	}

}

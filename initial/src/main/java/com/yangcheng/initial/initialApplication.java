package com.yangcheng.initial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.yangcheng.initial")
public class InitialApplication {

	public static void main(String[] args) {
		SpringApplication.run(InitialApplication.class, args);

	}
//	@Autowired
//	private RequestMappingHandlerMapping handlerMapping;
//
//	@Bean
//	CommandLineRunner printMappings() {
//		return args -> {
//			handlerMapping.getHandlerMethods().forEach((key, value) -> {
//				System.out.println(key + " -> " + value);
//			});
//		};
//	}


}

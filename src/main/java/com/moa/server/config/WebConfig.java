package com.moa.server.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedOrigins("https://moa-7h4oyyc4v-mutajunes-projects.vercel.app")
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
				.allowedHeaders("*")
				.allowCredentials(true);
	}


	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/file/**")
		.addResourceLocations("file:///c:/test/");
	
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				// 1. API 요청 설정
				registry.addMapping("/api/**")
						.allowedOrigins("http://localhost:5173")
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "PUT", "DELETE");

				// 2. 이미지 파일 요청 설정
				registry.addMapping("/file/**")
						.allowedOrigins("http://localhost:5173") // 끝에 '/' 유무 확인 필요 (보통 없이 씀)
						.allowCredentials(true)
						.allowedMethods("GET", "POST", "PUT", "DELETE");
			}
		};
	}


	// 빈으로 등록해놓음
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//		return new WebMvcConfigurer() {
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/api/**")
//						.allowedOrigins("http://localhost:5173")
//						.allowCredentials(true)   // (반드시) 쿠키 허용
//						.allowedMethods("GET", "POST", "PUT", "DELETE");
//
//			}
//		};
//
//	}
	
	
}

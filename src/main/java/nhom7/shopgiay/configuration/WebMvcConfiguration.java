package nhom7.shopgiay.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "nhom7.shopgiay" })
public class WebMvcConfiguration implements WebMvcConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/", ".jsp");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String homeDir = System.getProperty("user.home").replace("\\", "/");
		registry.addResourceHandler("/public/**").addResourceLocations("/WEB-INF/public/");
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("/images/**").addResourceLocations("file:" + homeDir + "/shopgiay/images/");
		System.out.println("file:" + homeDir + "/shopgiay/images/");
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver mrs = new CommonsMultipartResolver();
		mrs.setMaxUploadSizePerFile(1024 * 1024 * 3);
		mrs.setMaxUploadSize(1024 * 1024 * 30);

		return mrs;
	}
}

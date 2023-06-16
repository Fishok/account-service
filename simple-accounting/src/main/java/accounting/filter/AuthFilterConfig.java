package accounting.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthFilterConfig {

	@Bean
	public FilterRegistrationBean<AuthFilter> authFIlter() {
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthFilter());
		registrationBean.addUrlPatterns("/api/*");
		return registrationBean;
	}
}

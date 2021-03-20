package work.huangxin.share.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import work.huangxin.share.handlerInterceptor.MyInterceptor;


/**
 * 拦截器配置
 */

@Configuration
public class FilterConfig implements WebMvcConfigurer {

    @Bean
    public MyInterceptor getMyInterceptor() {
        return new MyInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截器如果有注入其他则使用这种,都可以
        registry.addInterceptor(getMyInterceptor()).addPathPatterns("/wx/**").excludePathPatterns("/wx/Login").excludePathPatterns("/wx/api/**");


//        registry.addInterceptor(MyInterceptor()).addPathPatterns("/api/**").excludePathPatterns("/api/login");

    }


//    @Bean
//    public FilterRegistrationBean jwtFilter() {
//        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new JwtFilter());
//        //添加需要拦截的url
//        List<String> urlPatterns = Lists.newArrayList();
//        urlPatterns.add("/api/");
//
//        registrationBean.addUrlPatterns(urlPatterns.toArray(new String[urlPatterns.size()]));
//        return registrationBean;
//    }


}

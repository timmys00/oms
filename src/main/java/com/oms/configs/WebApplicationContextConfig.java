package com.oms.configs;

import java.util.ArrayList;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.ComponentScan; 
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer; 
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
//import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver; 
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MarshallingView;
import org.springframework.web.util.UrlPathHelper;

import com.oms.domain.Product;
import com.oms.interceptors.ProcessingTimeLogInterceptor;
import com.oms.interceptors.PromoCodeInterceptor; 
/*
 * TODO: Change WebMvcConfigurationSupport to WebMvcConfigurer
 * */
@Configuration 
@EnableWebMvc 
@ComponentScan("com.oms") 
public class WebApplicationContextConfig implements WebMvcConfigurer {
//extends WebMvcConfigurationSupport  { 

	@Bean 
    public MessageSource messageSource() {  
       ResourceBundleMessageSource resource = new ResourceBundleMessageSource(); 
       resource.setBasename("messages"); 
       return resource;     
    }
	
	@Override 
    public void addResourceHandlers(ResourceHandlerRegistry registry) { 
       registry.addResourceHandler("/img/**") 
              .addResourceLocations("/resources/images/");
//              ;.setCachePeriod(3600)
//              .resourceChain(true)
//              .addResolver(new GzipResourceResolver()); 
    } 

	@Bean 
    public CommonsMultipartResolver multipartResolver() { 
        CommonsMultipartResolver resolver = new CommonsMultipartResolver(); 
        resolver.setDefaultEncoding("utf-8"); 
        return resolver; 
    }
	
	@Bean 
    public MappingJackson2JsonView jsonView() { 
       MappingJackson2JsonView jsonView = new MappingJackson2JsonView(); 
       jsonView.setPrettyPrint(true); 
       return jsonView;  
    } 
	
	@Bean 
    public MarshallingView xmlView() { 
       Jaxb2Marshaller marshaller = new Jaxb2Marshaller(); 
       marshaller.setClassesToBeBound(Product.class); 
  
       MarshallingView xmlView = new MarshallingView(marshaller); 
       return xmlView; 
    }
	
	@Bean 
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) { 
       ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver(); 
       resolver.setContentNegotiationManager(manager); 
     
       ArrayList<View> views = new ArrayList<>(); 
       views.add(jsonView()); 
       views.add(xmlView()); 
     
       resolver.setDefaultViews(views); 
        
       return resolver; 
    } 


	@Override 
    public void configurePathMatch(PathMatchConfigurer configurer) { 
//		configurer
//        .setUseSuffixPatternMatch(true)
//        .setUseTrailingSlashMatch(false)
//        .setUseRegisteredSuffixPatternMatch(true)
//        .setPathMatcher(antPathMatcher())
//        .setUrlPathHelper(urlPathHelper());
//		@Bean
//	    public UrlPathHelper urlPathHelper() {
//	        //...
//	    }
//
//	    @Bean
//	    public PathMatcher antPathMatcher() {
//	        //...
//	    }
		
       UrlPathHelper urlPathHelper = new UrlPathHelper(); 
       urlPathHelper.setRemoveSemicolonContent(false); 

       configurer.setUrlPathHelper(urlPathHelper); 
    }
	
    @Override 
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    { 
       configurer.enable(); 
    } 

    @Bean 
    public InternalResourceViewResolver getInternalResourceViewResolver() { 
        InternalResourceViewResolver resolver = new InternalResourceViewResolver(); 
        resolver.setViewClass(JstlView.class); 
        resolver.setPrefix("/WEB-INF/views/"); 
        resolver.setSuffix(".jsp"); 
        System.out.println("*** View resolved");
        return resolver; 
    } 
    
    @Override 
    public void addInterceptors(InterceptorRegistry registry) {       
        registry.addInterceptor(new ProcessingTimeLogInterceptor());
        
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();	   
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
        
        registry.addInterceptor(promoCodeInterceptor())
        .addPathPatterns("/**/market/products/specialOffer"); 

    } 

    @Bean 
    public HandlerInterceptor promoCodeInterceptor() { 
       PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor(); 
       promoCodeInterceptor.setPromoCode("OFF3R"); 
       promoCodeInterceptor.setOfferRedirect("market/products"); 
       promoCodeInterceptor.setErrorRedirect("invalidPromoCode"); 
  
       return promoCodeInterceptor; 
    } 

    @Bean 
    public LocaleResolver localeResolver() { 
       SessionLocaleResolver resolver = new SessionLocaleResolver(); 
       resolver.setDefaultLocale(new Locale("en")); 
       return resolver; 
    } 

} 
package com.demo.oauth;
//Enble the CROSS error 
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.context.annotation.Bean;
// import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
public class WebConfig implements WebMvcConfigurer {

   @Bean
   public WebMvcConfigurer corsConfigurer(){
      return new WebMvcConfigurer() {
         @Override
         public void addCorsMappings(CorsRegistry registry) {
            registry.addMapping("/**") //apply ruless to all the urls in application
            .allowedOrigins("http://localhost:3000") //allowed origin
            .allowedMethods("GET","POST","PUT")
            .allowedHeaders("*")
            .allowCredentials(true);
      }
   };

}
}
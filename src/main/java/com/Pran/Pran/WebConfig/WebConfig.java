// package com.Pran.Pran.WebConfig;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class WebConfig {

//     @Bean
//     public WebMvcConfigurer corsConfigurer() {
//         return new WebMvcConfigurer() {
//             @Override
//             public void addCorsMappings(CorsRegistry registry) {
//                 registry.addMapping("/**") // Apply to all routes
//                         .allowedOrigins("https://pranayuv.com") // Allow your frontend origin
//                         .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
//                         .allowedHeaders("*")
//                         .allowCredentials(true); // If you're using cookies or sessions
//             }
//         };
//     }
// }

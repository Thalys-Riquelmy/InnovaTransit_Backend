//package innovaBackend.InnovaTransit.cors;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class CorsConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                		.allowedOrigins("http://localhost:8101", 
//                				"http://192.168.1.110:8101", 
//                				"https://innova-transit-frontend.vercel.app",
//                				"https://localhost:8100",
//                				"https://192.168.1.110:8100")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("*")
//                        .allowCredentials(true);
//            }
//        };
//    }
//}


package innovaBackend.InnovaTransit.cors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                .allowedOrigins("http://localhost:8101", 
        				"http://192.168.1.110:8101", 
        				"https://innova-transit-frontend.vercel.app",
        				"https://localhost:8100",
        				"https://192.168.1.110:8100")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}

//package innovaBackend.InnovaTransit.cors;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
////@Configuration
//public class CorsConfig {
//
//    //@Bean
//    public WebMvcConfigurer corsConfigurer() {
//        // CORS removido. Não há mais configuração de CORS aqui.
//        return new WebMvcConfigurer() {
//            @Override
//					public void addCorsMappings(CorsRegistry registry) {
//					    registry.addMapping("/**") // Permite CORS para todos os endpoints
//					            .allowedOrigins("*") // Permite todas as origens
//					            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permite esses métodos HTTP
//					            .allowedHeaders("*") // Permite todos os cabeçalhos
//					            .allowCredentials(true); // Permite envio de cookies (se necessário)
//					}
//				
//        };
//    }
//}


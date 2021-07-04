package io.github.heltonricardo.ingressoja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class IngressojaApiApplication implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE",
        "PATCH");
  }

  public static void main(String[] args) {
    SpringApplication.run(IngressojaApiApplication.class, args);
  }
}
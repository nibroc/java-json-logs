package com.corbinhughes.jsonlogs;

import org.apache.catalina.Context;
import org.springframework.boot.Banner.Mode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.corbinhughes.jsonlogs.controllers.LoggingController;

import ch.qos.logback.access.tomcat.LogbackValve;

@SpringBootApplication
@ComponentScan(basePackageClasses = LoggingController.class)
public class JsonLogsApplication {
  public static void main(String[] args) {
    final SpringApplication application = new SpringApplication(JsonLogsApplication.class);
    application.setBannerMode(Mode.OFF);
    application.run(args);
  }

  @Bean
  public TomcatContextCustomizer logbackValve() {
    return new TomcatContextCustomizer() {
      @Override
      public void customize(Context context) {
        context.getPipeline().addValve(new LogbackValve());
      }
    };
  }
}

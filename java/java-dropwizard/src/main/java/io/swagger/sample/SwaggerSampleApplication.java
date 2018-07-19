package io.swagger.sample;

import io.dropwizard.bundles.webjars.WebJarBundle;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.config.*;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.swagger.sample.resource.PetResource;

public class SwaggerSampleApplication extends Application <SwaggerSampleConfiguration> {
  public static void main(String[] args) throws Exception {
    new SwaggerSampleApplication().run(args);
  }

  @Override
  public void initialize(Bootstrap<SwaggerSampleConfiguration> bootstrap) {
    bootstrap.addBundle(new WebJarBundle());
  }

  @Override   
  public String getName() {
      return "swagger-sample";
  }

  @Override
  public void run(SwaggerSampleConfiguration configuration, Environment environment) {
    environment.jersey().register(new ApiListingResource());
    environment.jersey().register(new PetResource());
    environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);

    BeanConfig config = new BeanConfig();
    config.setTitle("Swagger sample app");
    config.setVersion("1.0.0");
    config.setResourcePackage("io.swagger.sample.resource");
//    config.setScan(true);
  }
}

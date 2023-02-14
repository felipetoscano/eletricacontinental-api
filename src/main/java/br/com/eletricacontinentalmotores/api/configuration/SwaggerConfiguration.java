package br.com.eletricacontinentalmotores.api.configuration;

import org.springframework.boot.actuate.autoconfigure.endpoint.web.CorsEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.endpoint.web.WebEndpointProperties;
import org.springframework.boot.actuate.autoconfigure.web.server.ManagementPortType;
import org.springframework.boot.actuate.endpoint.ExposableEndpoint;
import org.springframework.boot.actuate.endpoint.web.*;
import org.springframework.boot.actuate.endpoint.web.annotation.ControllerEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.annotation.ServletEndpointsSupplier;
import org.springframework.boot.actuate.endpoint.web.servlet.WebMvcEndpointHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        List<Response> searchGlobalResponses = new ArrayList<>();
        searchGlobalResponses.add(new ResponseBuilder().code("200").description("OK").build());
        searchGlobalResponses.add(new ResponseBuilder().code("500").description("Erro de servidor").build());

        List<Response> changeGlobalResponses = new ArrayList<>();
        changeGlobalResponses.add(new ResponseBuilder().code("200").description("OK").build());
        changeGlobalResponses.add(new ResponseBuilder().code("400").description("Erro de negócio").build());
        changeGlobalResponses.add(new ResponseBuilder().code("500").description("Erro de servidor").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.eletricacontinentalmotores.api.controllers"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalResponses(HttpMethod.GET, searchGlobalResponses)
                .globalResponses(HttpMethod.POST, changeGlobalResponses)
                .globalResponses(HttpMethod.PUT, changeGlobalResponses)
                .globalResponses(HttpMethod.DELETE, changeGlobalResponses);
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Elétrica Continental Motores API",
                "API responsável por gerenciar os dados da empresa",
                "v1",
                "",
                new Contact("Felipe Toscano", "", "felipetoscano02@gmail.com"),
                "", "", Collections.emptyList());
    }

    @Bean
    public WebMvcEndpointHandlerMapping webEndpointServletHandlerMapping(WebEndpointsSupplier webEndpointsSupplier, ServletEndpointsSupplier servletEndpointsSupplier, ControllerEndpointsSupplier controllerEndpointsSupplier, EndpointMediaTypes endpointMediaTypes, CorsEndpointProperties corsProperties, WebEndpointProperties webEndpointProperties, Environment environment) {
        List<ExposableEndpoint<?>> allEndpoints = new ArrayList<>();
        Collection<ExposableWebEndpoint> webEndpoints = webEndpointsSupplier.getEndpoints();
        allEndpoints.addAll(webEndpoints);
        allEndpoints.addAll(servletEndpointsSupplier.getEndpoints());
        allEndpoints.addAll(controllerEndpointsSupplier.getEndpoints());
        String basePath = webEndpointProperties.getBasePath();
        EndpointMapping endpointMapping = new EndpointMapping(basePath);
        boolean shouldRegisterLinksMapping = this.shouldRegisterLinksMapping(webEndpointProperties, environment, basePath);
        return new WebMvcEndpointHandlerMapping(endpointMapping, webEndpoints, endpointMediaTypes, corsProperties.toCorsConfiguration(), new EndpointLinksResolver(allEndpoints, basePath), shouldRegisterLinksMapping, null);
    }

    private boolean shouldRegisterLinksMapping(WebEndpointProperties webEndpointProperties, Environment environment, String basePath) {
        return webEndpointProperties.getDiscovery().isEnabled() && (StringUtils.hasText(basePath) || ManagementPortType.get(environment).equals(ManagementPortType.DIFFERENT));
    }
}

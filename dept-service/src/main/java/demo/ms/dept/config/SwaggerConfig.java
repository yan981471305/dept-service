package demo.ms.dept.config;


import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * 2019-5-8
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler( "swagger-ui.html" )
                .addResourceLocations( "classpath:/META-INF/resources/" );

        registry.addResourceHandler( "/webjars/**" )
                .addResourceLocations( "classpath:/META-INF/resources/webjars/" );
    }


    /**
     * 用来创建该Api的基本信息
     * （这些基本信息会展现在文档页面中）
     **/
    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title( "user restful apis" )
                .description( "api Swagger" )
                .termsOfServiceUrl( "" )
                .version( "1.0" )
                .build();
    }

    /**
     * 函数创建Docket的Bean
     * <p>
     * select()函数返回一个ApiSelectorBuilder实例用来控制哪些接口暴露给Swagger来展现，
     * 本例采用指定扫描的包路径来定义，
     * Swagger会扫描该包下所有Controller定义的API，
     * 并产生文档内容（除了被@ApiIgnore指定的请求）
     **/
    @Bean
    public Docket createRestApi() {


        return new Docket( DocumentationType.SWAGGER_2 )
                .apiInfo( apiInfo() )
                .select()
                //添加ApiOperiation注解的被扫描
                .apis( RequestHandlerSelectors.withMethodAnnotation( ApiOperation.class ) )
                .paths( PathSelectors.any() )
                .build();

    }

}



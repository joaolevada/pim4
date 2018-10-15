package br.unip.ads.pim4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String TAG_CHAMADO = "Chamados";
	public static final String TAG_CLIENTE = "Clientes";
	public static final String TAG_ATENDENTE = "Atendentes";

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.unip.ads.pim4.controller"))
				.build()
				.apiInfo(apiInfo())
				.tags(new Tag(TAG_CHAMADO, "Operações relacionadas ao domínio de Chamados")
					, new Tag(TAG_CLIENTE, "Operações relacionadas ao domínio de Clientes")
					, new Tag(TAG_ATENDENTE, "Operações relacionadas ao domínio de Atendentes"));
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Documentação API REST PIM (fantastic) Four")
				.version("0.0.1")
				.build();
	}

}
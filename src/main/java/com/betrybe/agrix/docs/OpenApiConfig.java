package com.betrybe.agrix.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * The type Open api config.
 */
@Configuration
public class OpenApiConfig implements OpenApiCustomizer {

  @Override
  public void customise(OpenAPI openApi) {
    Info info = new Info()
        .title("Agrix")
        .description("Este projeto apresenta uma API RESTful que facilita a gestão e o"
            + " monitoramento de fazendas, possibilitando às pessoas usuárias criar, visualizar"
            + ", atualizar e excluir fazendas, plantações e fertilizantes de forma intuitiva e "
            + "prática. A API oferece endpoints específicos para operações CRUD "
            + "(Create, Read, Update, Delete) em listas de plantações e fazendas, visando"
            + " proporcionar uma experiência consistente e confiável. Além de um autenticação "
            + "e autorização de rotas.")
        .version("1.0.0");

    openApi.info(info);
  }
}

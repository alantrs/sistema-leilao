package com.lp2;

import io.micronaut.runtime.Micronaut;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.info.*;

@OpenAPIDefinition(
    info = @Info(
            title = "leilao",
            version = "1.0"
    )
)

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}
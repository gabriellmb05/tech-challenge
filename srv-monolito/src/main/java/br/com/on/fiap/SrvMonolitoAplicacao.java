package br.com.on.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "br.com.on.fiap.adaptadores.integracao")
@SpringBootApplication
public class SrvMonolitoAplicacao {
    public static void main(String[] args) {
        SpringApplication.run(SrvMonolitoAplicacao.class, args);
    }
}

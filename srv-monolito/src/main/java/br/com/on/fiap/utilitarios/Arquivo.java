package br.com.on.fiap.utilitarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class Arquivo {

    private final ResourceLoader resourceLoader;

    public Arquivo(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String carregarArquivo(String caminhoArquivo) throws IOException {
        Resource resource = resourceLoader.getResource("classpath:" + caminhoArquivo);
        try (InputStream inputStream = resource.getInputStream();
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }
}

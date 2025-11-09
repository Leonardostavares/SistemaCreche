package main.frontend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import main.model.FormularioCompleto;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class ApiService {
    
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;
    private final String baseUrl = "http://localhost:8080/api";
    
    public ApiService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        // Configurar formato de data
        this.objectMapper.disable(com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        // Aguardar o backend estar disponível
        waitForBackend();
    }
    
    private void waitForBackend() {
        System.out.println("Aguardando backend estar disponível...");
        int maxAttempts = 30; // 30 tentativas (30 segundos)
        int attempt = 0;
        
        while (attempt < maxAttempts) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://localhost:8080/actuator/health"))
                        .timeout(java.time.Duration.ofSeconds(2))
                        .GET()
                        .build();
                
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                
                if (response.statusCode() == 200) {
                    System.out.println("Backend disponível!");
                    return;
                }
            } catch (Exception e) {
                // Backend ainda não está disponível
            }
            
            attempt++;
            try {
                Thread.sleep(1000); // Aguardar 1 segundo
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            
            if (attempt % 5 == 0) {
                System.out.println("Ainda aguardando backend... (" + attempt + "/" + maxAttempts + ")");
            }
        }
        
        System.out.println("Aviso: Backend pode não estar disponível. Prosseguindo mesmo assim...");
    }
    

    
    public FormularioCompleto salvarFormulario(FormularioCompleto formulario) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(formulario);
        
        // Se o formulário tem ID, é uma atualização (PUT), senão é criação (POST)
        boolean isUpdate = formulario.getId() != null;
        
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/formulario"))
                .header("Content-Type", "application/json");
                
        HttpRequest request = isUpdate 
            ? requestBuilder.PUT(HttpRequest.BodyPublishers.ofString(json)).build()
            : requestBuilder.POST(HttpRequest.BodyPublishers.ofString(json)).build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200 || response.statusCode() == 201) {
            return objectMapper.readValue(response.body(), FormularioCompleto.class);
        } else {
            throw new RuntimeException("Erro ao " + (isUpdate ? "atualizar" : "salvar") + " formulário: " + response.statusCode() + " - " + response.body());
        }
    }
    
    public FormularioCompleto buscarPorCpf(String cpf) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/formulario/buscar/cpf/" + cpf))
                .GET()
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), FormularioCompleto.class);
        } else if (response.statusCode() == 404) {
            return null; // Não encontrado
        } else {
            throw new RuntimeException("Erro ao buscar formulário: " + response.statusCode() + " - " + response.body());
        }
    }
    
    public List<FormularioCompleto> listarTodos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/formularios/completo"))
                .GET()
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, FormularioCompleto.class));
        } else {
            throw new RuntimeException("Erro ao listar formulários: " + response.statusCode() + " - " + response.body());
        }
    }
    
    public String editarFormulario(Long id, FormularioCompleto formulario) throws IOException, InterruptedException {
        String json = objectMapper.writeValueAsString(formulario);
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/formulario/editar/" + id))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(json))
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            return "Formulário editado com sucesso";
        } else {
            return "Erro: " + response.statusCode() + " - " + response.body();
        }
    }
    
    public List<FormularioCompleto> buscarPorStatus(String status) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/formularios/buscar/status/" + status))
                .GET()
                .build();
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        
        if (response.statusCode() == 200) {
            return objectMapper.readValue(response.body(), 
                objectMapper.getTypeFactory().constructCollectionType(List.class, FormularioCompleto.class));
        } else {
            throw new RuntimeException("Erro ao buscar formulários por status: " + response.statusCode() + " - " + response.body());
        }
    }
}
package lmstudiojava;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import extractjson.FindPattern;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LMStudio {
    public static String requestAnswer(String userAnswer, String model) {
        try {
            // URL do endpoint
            URL url = new URL("http://localhost:1234/v1/chat/completions");

            // Abrindo conexão
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            
            //Mensagem do usuario
            //String userAnswer = "Select in database all cats black are dead in clinic in March.";
            
            //Mensagem do sistema
            String msgSystem =  "Try to help create a SQL request. Just respond with SQL query. Use this schema to base your response: " +
                                "CREATE DATABASE IF NOT EXISTS alunos; " + 
                                "USE alunos; " + 
                                "CREATE TABLE IF NOT EXISTS aluno ( " + 
                                "    id INT AUTO_INCREMENT PRIMARY KEY, " + 
                                "    cpf VARCHAR(14) NOT NULL, " + 
                                "    nome VARCHAR(255) NOT NULL, " + 
                                "    data_nascimento DATE NOT NULL, " +
                                "    peso FLOAT NOT NULL, " +
                                "    altura FLOAT NOT NULL " +
                                ");";
            
            // Corpo da requisição
            String jsonInputString = "{ \"model\": \"" + model + "\", \"messages\": [ { \"role\": \"system\", \"content\": \"" + msgSystem + "\" }, { \"role\": \"user\", \"content\": \"" + userAnswer +  "\" } ], \"temperature\": 0.7, \"max_tokens\": -1, \"stream\": true }";
            
            //model-identifier
            //Always answer in rhymes.
            
            
            // Enviando dados
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Lendo a resposta
            StringBuilder response = new StringBuilder();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }

            // Imprimindo apenas o conteúdo da resposta
            System.out.println(response.toString());
            
            
            ArrayList<String> pacotes = new ArrayList<>();
            pacotes = FindPattern.extractList(response.toString(), "{", "}", 1);
            System.out.println(pacotes.size());
            
            // for(String pacote: pacotes){
            //     System.out.println(pacote);
            // }
            
            
            String mensagem = "";
            
            for(String pacote : pacotes){

                ObjectMapper objectMapper = new ObjectMapper();
                
                // Ler o arquivo JSON e mapeá-lo para um JsonNode
                JsonNode rootNode = objectMapper.readTree(pacote);
                
                if(rootNode.get("choices").get(0).get("delta").get("content") != null){
                    String bit = rootNode.get("choices").get(0).get("delta").get("content").asText();
                    mensagem = mensagem.concat(bit);
                }
                //System.out.println(mensagem);
                
            }
            
            // Fechando conexão
            conn.disconnect();
            
            return mensagem;
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}


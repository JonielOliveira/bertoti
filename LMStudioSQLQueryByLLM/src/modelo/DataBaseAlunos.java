
package modelo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;
import crud.Crud;
import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseAlunos {
    
    private Crud conexao;
    private String diretorio;
    private String arquivoImportacao;
    private String arquivoCriacao;
    
    public DataBaseAlunos(){
        this.diretorio = System.getProperty("user.dir");
        this.arquivoCriacao = this.diretorio.concat("\\db\\create_alunos.sql");
        this.arquivoImportacao = this.diretorio.concat("\\db\\alunos.txt");
    }
    
    
    public int criarDB(String nomeBanco) {
        
        try {
            
            Connection connection = new ConnectionFactory().getConnection("");
            Statement stmt = connection.createStatement();
            
            
            // Verificar se o banco de dados existe
            ResultSet resultSet = connection.getMetaData().getCatalogs();
            boolean bancoExiste = false;
            while (resultSet.next()) {
                String nomeBancoAtual = resultSet.getString(1);
                if (nomeBancoAtual.equals(nomeBanco)) {
                    bancoExiste = true;
                    break;
                }
            }
            resultSet.close();

            // Se o banco de dados não existir, criar
            if (!bancoExiste) {
                stmt.executeUpdate("CREATE DATABASE " + nomeBanco);
                System.out.println("Banco de dados criado com sucesso.");
                return 1;
            } else {
                System.out.println("Banco de dados já existe.");
                return 0;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return -1;
        } catch (Exception e) {
            System.err.println("Outro erro...");
            return -1;
        }    
        
    }
    
    public int verificarDados(String nomeTabela) {
        
        try {
             
            Connection connection = new ConnectionFactory().getConnection("alunos");
            Statement stmt = connection.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM " + nomeTabela);
            rs.next();
            int rowCount = rs.getInt(1);

            if (rowCount > 0) {
                System.out.println("Existem dados na tabela.");
                return 1;
            } else {
                System.out.println("Não existem dados na tabela.");
                return 0;
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return -1;
        } catch (Exception e) {
            System.err.println("Outro erro...");
            return -1;
        }  
        
    }
    
    public void criarTabela(){
        
        Crud conexao = new Crud("");
        
        try{
            BufferedReader reader = new BufferedReader(new FileReader(arquivoCriacao));
            
            StringBuilder sql = new StringBuilder();
            String linha;
            while ((linha = reader.readLine()) != null) {
                //sql.append(linha).append("\n");
                sql.append(linha);
            }
            
            String[] comandos = sql.toString().split(";");
            
            for (String comando : comandos) {
                // Executando o conteúdo SQL
                conexao.executa(comando);
                System.out.println(comando);
            }

        }
        catch(Exception e){
            System.err.println("Erro durante a execução do script SQL: " + e.getMessage());
        }
        
    }
    
    public boolean importarDados(){
        
        Crud conexao = new Crud("alunos");
        
        try {
            BufferedReader br = new BufferedReader(new FileReader(arquivoImportacao));
            String line;
  
            List<Aluno> listaDeAlunos = new ArrayList<>();
            
            int i = 0;
            while ((line = br.readLine()) != null) {
                
                String linhas[] = line.split(";");
                
                if(linhas.length == 5){
                    
                    String AlunoCpf = linhas[0];
                    String AlunoNome = linhas[1];
                    String AlunoData[] = linhas[2].split("/");
                    int AlunoDia = 1;
                    int AlunoMes = 1;
                    int AlunoAno = 1900;
                    if(AlunoData.length == 3){
                        AlunoDia = Integer.valueOf(AlunoData[0]);
                        AlunoMes = Integer.valueOf(AlunoData[1]);
                        AlunoAno = Integer.valueOf(AlunoData[2]);
                    }
                    LocalDate AlunoDate = LocalDate.of(AlunoAno, AlunoMes, AlunoDia);
                    float AlunoPeso = Float.valueOf(linhas[3]);
                    float AlunoAltura = Float.valueOf(linhas[4]);
                    
                    listaDeAlunos.add(new Aluno(i+1, AlunoNome, AlunoCpf, Date.valueOf(AlunoDate), AlunoPeso, AlunoAltura));  
                
                }
                 
                i++;
                
            }
            
            for(Aluno a : listaDeAlunos){
                System.out.println(a);
                conexao.adiciona(a);
            }
            
            br.close();
            
            return true;
            
        } catch (IOException e) {
            System.err.println("Erro ao importar dados do arquivo: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("Outro erro...");
            return false;
        }  
  
    }

}

package crud;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.Aluno;

public class Crud {
    
    private Connection connection;
    
    public Crud(String NomeDB){
        this.connection = new ConnectionFactory().getConnection(NomeDB);
    }
    
    public void adiciona (Aluno aluno){
        
        String sql = "INSERT INTO aluno(cpf, nome, data_nascimento, peso, altura) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setDate(3, aluno.getDataNasc());
            stmt.setFloat(4, aluno.getPeso());
            stmt.setFloat(5, aluno.getAltura());
            stmt.execute();
            stmt.close();
        }
        
        catch (SQLException u){
            throw new RuntimeException(u);
        }
        
    }
    
    public List<Aluno> consulta (String Sql){
        
        List<Aluno> listaDeAlunos = new ArrayList<>();
        
        try{
            
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(Sql);
            
            while (rs.next()) {
                
                int id = rs.getInt("id");
                String cpf = rs.getString("cpf");
                String nome = rs.getString("nome");
                Date dataNasc = rs.getDate("data_nascimento");
                java.sql.Date dataNascFormat = new java.sql.Date(dataNasc.getTime());
                float peso = rs.getFloat("peso");
                float altura = rs.getFloat("altura"); 
                
                listaDeAlunos.add(new Aluno(id, nome, cpf, dataNascFormat, peso, altura));
                
            }
            
            for(Aluno a : listaDeAlunos){
                // System.out.println(a);
            }
            
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
        finally{
            return listaDeAlunos;
        }
    }
    
    public void executa(String Sql){
        try{
            Statement stmt = connection.createStatement();
            stmt.execute(Sql);
            System.out.println("Script SQL executado com sucesso!");
        }
        catch(SQLException e){
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        catch (Exception e){
            System.err.println("Erro durante a execução do script SQL: " + e.getMessage());
        }
    }
    
    
    
    
}

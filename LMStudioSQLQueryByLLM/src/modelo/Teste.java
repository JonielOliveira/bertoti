
package modelo;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Teste {
    public static void main(String[] args){
        
        String nomeBanco = "rodrigues";
        
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
            } else {
                System.out.println("Banco de dados já existe.");
            }

        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Outro erro...");
        }
        
        
    }
}

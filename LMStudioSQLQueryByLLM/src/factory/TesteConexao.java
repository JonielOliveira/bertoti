
package factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.lang.Exception;
import javax.swing.JOptionPane;
import modelo.DataBaseAlunos;

public class TesteConexao {
    
    public static int testar(){
        
        boolean loop = true;
        int tentativas = 0;
        DataBaseAlunos banco = new DataBaseAlunos();
        
        while(loop & tentativas < 10){
            try {
                Connection connection = new ConnectionFactory().getConnection("");
                connection.close();
                loop = false;
                JOptionPane.showMessageDialog(null, "Conexão com o banco de dados estabelecida com sucesso!");
                
                int resultado = banco.criarDB("alunos");
                
                if (resultado == -1) {
                    JOptionPane.showMessageDialog(null, "Erro após estabelecer conexão!");
                    loop = true;
                }
                else if (resultado == 0 || resultado == 1) {
                    
                    if (resultado == 0) {
                        JOptionPane.showMessageDialog(null, "O banco de dados já existe!");
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "O banco de dados foi criado!");
                    }
                    
                    banco.criarTabela();
                    
                    int resultado2 = banco.verificarDados("aluno");
                        if (resultado2 == -1) {
                            JOptionPane.showMessageDialog(null, "Erro após estabelecer conexão!");
                            loop = true;
                        }
                        else if (resultado2 == 0) {
                            JOptionPane.showMessageDialog(null, "Não existem dados na tabela!");
                            if (banco.importarDados()) {
                                JOptionPane.showMessageDialog(null, "Dados importados com sucesso!");
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Erro ao importar dados para o banco de dados!");
                                loop = true;
                            }
                        }
                        else if (resultado2 == 1) {
                            JOptionPane.showMessageDialog(null, "Já existem dados na tabela!");
                        }
                }
                
            }
            catch (Exception excecao){
                loop = true;
                tentativas++;
                JOptionPane.showMessageDialog(null, "Conexão com o banco de dados falhou! Verifique a conexão!");
            }
        }
        return tentativas;
    }
    
}

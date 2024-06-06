
package factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    
    public Connection getConnection(String NomeDB){
        try{
            return DriverManager.getConnection("jdbc:mysql://localhost/" + NomeDB,"root","a1b2c3d4");
        }
        catch (SQLException excecao){
            throw new RuntimeException (excecao);
        }
    }
    
}

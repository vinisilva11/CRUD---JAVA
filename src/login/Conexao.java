package login;
 
import java.sql.Connection; // Conexão com o banco de dados
import java.sql.DriverManager; // Driver de conexão, em nosso MySQL
import java.sql.ResultSet; // Resultados das operações em banco de dados 
import java.sql.SQLException;
import java.sql.Statement; // Interpretação dos comandos SQL - CRUD
 
public class Conexao {
    // Atributos de comexão ligados as bibliotecas importadas
    public Connection con = null;
    public Statement stmt = null;
    public ResultSet resultset = null;
    
    // Atributos de conexão
    private final String servidor = "jdbc:mysql://127.0.0.1:3306/bd_login"; // Servidor de banco de daos
    private final String usuario = "root"; // Usuário de banco de dados
    private final String senha = ""; // Senha do banco de dados
    private final String driver = "com.mysql.cj.jdbc.Driver"; // Driver de conexão
    
    // Método de abertura da conexão com banco de dados
    public Connection abrirConexao() {
        try {
           Class.forName(driver);
           // Atributos de conexão
           con = DriverManager.getConnection(servidor, usuario, senha);
           stmt = con.createStatement();
           
           System.out.println("Conexão Aberta com sucesso");
           
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao acessar o Banco de Dados, verifique " + e.getMessage());
        }
        
        return con;
    }
    
    public void fecharConexao() {
        
        try {
            con.close();
            System.out.println("Conexão Finalizada com sucesso");
        } catch (SQLException e) {
            System.out.println("Erro ao encerrar a conexão");
        }
    }
}
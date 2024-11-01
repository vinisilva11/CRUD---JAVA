package login;
 
import java.sql.SQLException;
 
public class Usuario {

    private String usuario;
    private String nome;
    private String senha;

    // Atributo que armazenará o retorno do banco de dados
    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultAlteracao;
    private boolean resultExclusao;

    // Atributos Estáticos do sistema
    static String nomeUsuario;
    static String usuarioSistema;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // Método de verificação da autencidade do usuario
    public boolean verificaUsuario(String Usuario, String senha){

        // Fazer a instância da conexão com o banco de dados 
        Conexao banco = new Conexao();

        try{
            // Abrindo a conexão com o Banco de Dados
            banco.abrirConexao();

            // Criando parâmentro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a consulta no banco de dados
            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                    + "WHERE usuario = '" + usuario + "' "
                    + " AND senha = md5('" + senha + "')");

            // Verficando se existe retorno de dados no banco
            if (banco.resultset.next()) {
                // Caso tenha
                resultUsuario = true;

                // Setters em Nome e Usuario
                setUsuario(banco.resultset.getString(1));
                setNome(banco.resultset.getString(2));
                

                // Nos atributos estáticos, realizo as atribuições
                nomeUsuario = this.getNome();
                usuarioSistema = this.getUsuario();
            } else {
                // Caso não tenha
                resultUsuario = false;

            }

            banco.fecharConexao(); // Fechando a conexão com o banco de dados

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;

    }  

    // Método de verificação do usuário, estamos aqui fazendo uma sobrecarga de método
    // Sobrecarga de método
    public boolean verificaUsuario(String usuario){
        // Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();

        try {
            // Abro a conexão com o banco de dados
            banco.abrirConexao();

            // Criando parÂmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a consulta no banco de dados
            banco.resultset =
                    banco.stmt.executeQuery("SELECT * FROM usuario "
                                            + "WHERE usuario = '" + usuario + "'");

            // Verficando se existe retorno de dados no banco
            if (banco.resultset.next()){
                // Caso tenha
                resultUsuario = true;
            } else {
                // Caso não tenha
                resultUsuario = false;
            }

            banco.fecharConexao(); // Fechando a conexão com o banco de dados

        } catch (SQLException ec) {
            System.out.println("Erro ao consultar usuário " + ec.getMessage());
        }

        return resultUsuario;

    }

    // Método para cadastro de sário em nosso sistema
    public boolean cadastraUsuario(String nome, String usuario, String senha){
        // Fazer a instÂnciada conexão com o banco de dados
        Conexao banco = new Conexao();

        try{
            // Abro a conexão com o banco de dados
            banco.abrirConexao();

            // Criando parÂmetro de retorno
            banco.stmt = banco.con.createStatement();

            // Executando a inserção no banco de dados
                    banco.stmt.execute("INSERT INTO usuario (nome, usuario, senha)"
                            + " VALUES ('" + nome + "','" + usuario + "', md5('"
                            + senha + "'))");
                    
                    resultCadastro = true;

        } catch (SQLException ec) {
            System.out.println("Erro ao inserir usuário " + ec.getMessage());
            resultCadastro = false;
        }
        
        return resultCadastro;
     }

    // Método para alteração dos dados em nosso sistema
    public boolean alteraUsuario(String nome, String usuario, String senha) {
        // Fazer a instância da conexão com o banco de dados
        
        Conexao banco = new Conexao();

        try {
            // Abro a conexão com obanco de dados
            banco.abrirConexao();

            // Criando o parâmetro de retorno
            banco.stmt = banco.con.createStatement();

            //Executando a alteração no banco de dados
            banco.stmt.execute("UPDATE usuario SET nome = '" + nome +
                       "', senha = md5('" + senha + "') " + " WHERE usuario = '" + usuario + "'");

        }catch (SQLException ec) {
            System.out.println("Erro ao atualizar usuário " + ec.getMessage());
            resultAlteracao = false;
        }

        banco.fecharConexao();

        return resultAlteracao;
        
    }
    
    // Método para exclusão do Usuário do sistema
    public boolean excluiUsuario(String usuario) {
        // Fazer a instância da conexão com o banco de dados
        
        Conexao banco = new Conexao();
        
        try{
            // Abro a conexão com banco de dados
            banco.abrirConexao();
            
            // Criando o parâmetro de retorno
            banco.stmt = banco.con.createStatement();
            
            // Executando a alteração no banco de dados
            banco.stmt.execute("DELETE FROM usuario WHERE usuario = '" 
                               + usuario + "'");
            
            // Caso exclua
            resultExclusao = true;
            
        } catch (SQLException ec) {
            System.out.println("Erro ao excluir usuário " + ec.getMessage());
            resultExclusao = false;
        }
        
        banco.fecharConexao();
        
        return resultExclusao;
    }

}
 
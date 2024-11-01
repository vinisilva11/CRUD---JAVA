package login;
 
 
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
 
public class TelaCadastro extends JFrame{
    // Declaração dos atributos de tela
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;
    
    // Validações de usuário e cadastro corretos
    private boolean usuarioValido;
    private boolean cadastroValido;
    
    // String de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0;
 
    
    public TelaCadastro(){
        
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 230);
        
        tela = new JPanel();
        tela.setBackground(SystemColor.green);
        setContentPane(tela);
        tela.setLayout(null);
        
        // Adicionando elementos na tela
        JLabel lblIdentificacao = new JLabel ("Informar Campos para cadastro");
        lblIdentificacao.setBounds(60, 0, 500, 39);
        lblIdentificacao.setFont(new Font("Calibri", 3, 19));
        tela.add(lblIdentificacao); // Adiciono meu label ao Panel
        
        JLabel lblNome = new JLabel("Nome");
        lblNome.setBounds(24, 50, 70, 15);
        tela.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(160, 50, 219, 19);
        tela.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblUsuario = new JLabel ("Usuario");
        lblUsuario.setBounds(24, 75, 70, 15);
        tela.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(160, 75, 219, 19);
        tela.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24, 100, 70, 15);
        tela.add(lblSenha);
        
        passSenha = new JPasswordField();
        passSenha.setBounds(160, 100, 219, 19);
        tela.add(passSenha);
        
        JLabel lblconfsenha = new JLabel("Confirmar Senha");
        lblconfsenha.setBounds(24, 125, 100, 15);
        tela.add(lblconfsenha);
        
        passConfSenha = new JPasswordField();
        passConfSenha.setBounds(160, 125, 219, 19);
        tela.add(passConfSenha);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(200, 156, 117, 25);
        tela.add(btnCadastrar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50,156,117,25);
        tela.add(btnCancelar);
        
        // Ação do botão para cadastrar o usuário na base de dados
        btnCadastrar.addActionListener ((ActionEvent e) -> {
        try {
            // Instancio o objeto Usuario
            Usuario usu = new Usuario();
            
            // Realizando os setters dos dados de tela
            usu.setNome(txtNome.getText());
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(passSenha.getText());
            
            // validações de preenchimento dos dados
            if("".equals(usu.getNome())){
                mensagemJOption = "O Campo nome do usuário precisa ser informado!";
                mensagemTipo = 0;
            } else if ("".equals(usu.getUsuario())){
                mensagemJOption = "O Campo usuário precisa ser informado!";
            } else if("".equals(usu.getSenha())){
                mensagemJOption = "O Campo senha precisa ser informado";
                mensagemTipo = 0;
            } else if(!usu.getSenha(). equals(passConfSenha.getText())){
                mensagemJOption = "Os Campos senha e confirmação de senha não coincidem!";
                mensagemTipo = 0;
            } else {
                // Verifico se somente o usuário consta no banco,
                // Neste caso, faremosuma sobrecarga de método
                usuarioValido = usu.verificaUsuario(usu.getUsuario());
                if (usuarioValido == true){
                    // Caso exista, não pode ser colocado na base
                    mensagemJOption = "Usuário já existente na base de dados";
                    mensagemTipo = 0;
                } else {
                    cadastroValido = usu.cadastraUsuario(usu.getNome(),
                                                         usu.getUsuario(),
                                                         usu.getSenha());
                    if (cadastroValido == true){
                        // Usuario cadastrado na base de dados
                        mensagemJOption = "Usuário cadastrado com sucesso!";
                        mensagemTipo = 1;
                    } else {
                        // Algum erro aconteceu
                        mensagemJOption = "Problemas ao inserir o usuário";
                        mensagemTipo = 0;
                    }
                }
            }
            
            // Mostrar a mensagem referida
            JOptionPane.showMessageDialog(null,
                    mensagemJOption, "Atenção", mensagemTipo );
            if (mensagemTipo == 1){
                // Voltamos para a tela de login
                TelaLogin tLogin = new TelaLogin();
                tLogin.abreTela();
                
                // Fecho a tela de cadastro
                dispose();
            }
        } catch (HeadlessException ec) {
            System.out.println("Erro no cadastro do usuário "
            + ec.getMessage());
        }
    });
         
    btnCancelar.addActionListener((ActionEvent e) -> {
        TelaLogin tLogin = new TelaLogin();
        tLogin.abreTela();
        dispose();
    });
   }
    
    // Ação do botão cadastrar usário na base de dados  
    public void abreTela(){
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }
}

 
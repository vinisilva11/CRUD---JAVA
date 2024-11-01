package login;
 
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
 
public class TelaLogin extends JFrame {
    
    // Tela Objeto JPanel (tela em si)
    private final JPanel panelTela;
    
    //TxtUsuario Objeto JTextField (campo na tela)
    private final JTextField txtUsuario;
    
    //pswSenha Objeto PasswordField (campo na tela)
    private final JPasswordField pswSenha;
    
    // Método construtor
    public TelaLogin(){
        // Coloca o objeto na refrÊncia do centro da tela
        setLocationRelativeTo(null);
        
        // Não permite que o objeto seja expandido
        setResizable(false);
        
        // Título na caixa JFrame
        setTitle("Login - Fatec");
        
        // Quando clicado no X eu encerro todo o programa 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Define o posicionamento e tamanho
        //         x    y  width height
        setBounds(500, 200, 426, 212);
        
        // Crio um objeto JPanel e atribuo ele a variavel (tela)
        panelTela = new JPanel();
        
        // Vou utilizar o meu panel sem utilizar o padrão
        panelTela.setBackground(SystemColor.green);
        setContentPane(panelTela);
        
        // Vou utilizar o meu panel sem utilizar o padrão
        panelTela.setLayout(null);
        
        // Adicionando elementos na tela:
        // criando um objeto do tipo JLabel e atribuindo o valor ao atributo
        JLabel lblIdentificacao = new JLabel("Identificação");
        
        // Localização na tela
        lblIdentificacao.setBounds(144, 0, 160, 39);
        
        // Definindo a Fonte
        lblIdentificacao.setFont(new Font("Arial", 3, 19));
        
        // Adicionando o meu label ao meu Panel
        panelTela.add(lblIdentificacao);
        
        // Identificação e Posicionamento dos labels
        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(24, 65, 70, 15);
        panelTela.add(lblUsuario);
        
        JLabel lblSenha = new JLabel("Senha");
        lblSenha.setBounds(24, 92,70,15);
        panelTela.add(lblSenha);
        
        // Identificação e Posicionamento dos texts fields
        txtUsuario = new JTextField();
        txtUsuario.setBounds(112, 63, 219, 19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);

        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90, 219, 19);
        panelTela.add(pswSenha);
        
        // Identificação e Posicionamento dos botões
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(230, 136, 117, 25);
        panelTela.add(btnEntrar);

        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(90, 136, 117, 25);
        panelTela.add(btnCadastrar);
      
        
        btnEntrar.addActionListener ((ActionEvent e) -> {
           Usuario usu = new Usuario();
           usu.setUsuario(txtUsuario.getText());
           usu.setSenha(pswSenha.getText());
           if ("".equals(txtUsuario.getText())){
               JOptionPane.showMessageDialog(null,
                       "Campo usuário precisa ser informado!",
                       "Atenção",
                       JOptionPane.ERROR_MESSAGE);
               txtUsuario.grabFocus();
           } else if ("".equals(pswSenha.getText())) {
               JOptionPane.showMessageDialog(null, 
                       "Campo senha precisa ser informado!",
                       "Atenção",
                       JOptionPane.ERROR_MESSAGE);
               pswSenha.grabFocus();
           } else {
               boolean usuarioValido = usu.verificaUsuario(usu.getUsuario(),
                       usu.getSenha());
               if (usuarioValido == true){
                   JOptionPane.showMessageDialog(null,
                           "Usuário válido em nossa base de dados",
                           "Atenção",
                           JOptionPane.INFORMATION_MESSAGE);
                   
                   TelaInicio telaInicio = new TelaInicio();
                   telaInicio.abreTela();
                   dispose();
                            

               } else {
                   JOptionPane.showMessageDialog(null,
                           "Usuário inválido ou inexistente",
                           "Atenção",
                           JOptionPane.ERROR_MESSAGE);
                   limpaText();
                   txtUsuario.grabFocus();
               }
        }
   
    });
        
    // Ação no botão para cadastrar Usuário
    btnCadastrar.addActionListener((ActionEvent e) -> {
    // Intancio a classe TelaCadastro
       TelaCadastro tCadastro = new TelaCadastro();
       tCadastro.abreTela();
       dispose();
    });
                   
  }
        public void abreTela(){
            TelaLogin tela = new TelaLogin();
            tela.setVisible(true);
        }
        public void limpaText(){
            txtUsuario.setText("");
            pswSenha.setText("");
        }
    }

 
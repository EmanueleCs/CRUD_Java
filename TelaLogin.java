package LoginAtual;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent; // trabalhar com evento
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; //Trabalhar com mensagens
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class TelaLogin extends JFrame {
    
    private final JPanel panelTela;
    private final JTextField txtUsuario;
    private final JPasswordField pswSenha;
    private boolean usuarioValido;
    
    
    public TelaLogin(){
    
        setLocationRelativeTo(null);
        setResizable(false);
        
        setTitle("Login - Fatec");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);
        
        panelTela = new JPanel();
        panelTela.setBackground(SystemColor.PINK);
        setContentPane(panelTela);
        
        panelTela.setLayout(null);
        
        JLabel lblIdentificacao = new JLabel("IDENTIFICAÇÃO");
        lblIdentificacao.setBounds(144,0,160,39);
        lblIdentificacao.setFont(new Font("Verdana", 3, 15));
        panelTela.add(lblIdentificacao);
        
        JLabel lblUsuario = new JLabel ("Usuario");
        lblUsuario.setBounds(24,65,70,15);
        panelTela.add(lblUsuario);
      
        JLabel lblSenha = new JLabel ("Senha");
        lblSenha.setBounds(24,92,70,15);
        panelTela.add(lblSenha);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(112, 63, 219, 19);
        panelTela.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        pswSenha = new JPasswordField();
        pswSenha.setBounds(112, 90, 219, 19);
        panelTela.add(pswSenha);
        
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(200, 136, 117, 25);
        panelTela.add(btnEntrar);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(50, 136, 117, 25);
        panelTela.add(btnCadastrar);
        
        btnCadastrar.addActionListener((ActionEvent e) ->{
            TelaCadastro tCadastro = new TelaCadastro();
            tCadastro.setVisible(true);
            dispose();
        
        });
        
        //Ação no botão de entrar no sistema
        btnEntrar.addActionListener((ActionEvent e) ->{
            //Instancio a classe usuario
            Usuario usu = new Usuario();
            
            //Utilizo o setter de usuario e senha
            usu.setUsuario(txtUsuario.getText());
            usu.setSenha(pswSenha.getText());
            
            if ("".equals(txtUsuario.getText())){
                //vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null, 
                        "Campo usuário precisa ser informado!",
                                "Atenção", 
                                JOptionPane.ERROR_MESSAGE);
                //Voltar o cursor para o campo txtUsuario
                txtUsuario.grabFocus();
            }else if("".equals(pswSenha.getText())){
                
                //vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo senha precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                //Voltar o cursor ara o campo txtUsuario
                pswSenha.grabFocus();
            
            }else{
                //verifico se o usuario consta no banco de dados
                usuarioValido = usu.verificaUsuario(usu.getUsuario(),
                                        usu.getSenha());
                
                if(usuarioValido == true){
                    //Usuario e senha bateramno banco de estão corretos
                    JOptionPane.showMessageDialog(null, 
                            "Usuario valido em nossa base de dados",
                            "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    TelaInicio telaInicio = new TelaInicio();
                    telaInicio.abreTela();
                    
                    dispose();
                    
                }else{
                    JOptionPane.showMessageDialog(null, 
                            "Usuario invalido ou inexistente",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                    //Metodo para limpar os textos
                    limpaText();
                    //manda o foco para o campo usuario
                    txtUsuario.grabFocus();
                }
            }
        
        });
        
         //Ação no botao para cadastrar Usuario
        btnCadastrar.addActionListener((ActionEvent e) -> {
        //Instancio a classe TelaCadastro
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

package LoginAtual;

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
    //declaração dos atributos de tela
    private final JPanel tela;
    private final JTextField txtNome;
    private final JTextField txtUsuario;
    private final JPasswordField passSenha;
    private final JPasswordField passConfSenha;
    //Validacoa de usuario e cadastro corretos
    private boolean usuarioValido;
    private boolean cadastroValido;
    
    //Stirng de mensagem
    private String mensagemJOption;
    private int mensagemTipo = 0;
    
    //Metodo construtor da classe
    public TelaCadastro(){
    
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Cadastro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 230);
        setFont(new Font("Verdana", 3, 15));

        
        tela = new JPanel();
        tela.setBackground(SystemColor.ORANGE);
        setContentPane(tela);
        tela.setLayout(null);
        
        //Adicionando os elementos na tela
        JLabel lblIdentificacao = new JLabel("Informar campos para cadastro");
        lblIdentificacao.setBounds(60,0,500,39);
        lblIdentificacao.setFont(new Font("Verdana", 3, 15));
        tela.add(lblIdentificacao);
        
        JLabel lblNome = new JLabel ("Nome");
        lblNome.setBounds(24,50,70,15);
        tela.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(120, 50, 219, 19);
        tela.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblUsuario = new JLabel ("Usuario");
        lblUsuario.setBounds(24,75,70,15);
        tela.add(lblUsuario);
        
        txtUsuario = new JTextField();
        txtUsuario.setBounds(120, 75, 219, 19);
        tela.add(txtUsuario);
        txtUsuario.setColumns(10);
        
        JLabel lblSenha = new JLabel ("Senha");
        lblSenha.setBounds(24,100,70,15);
        tela.add(lblSenha);
        
        passSenha = new JPasswordField();
        passSenha.setBounds(120, 100, 219, 19);
        tela.add(passSenha);
        
        JLabel lblconfSenha = new JLabel ("Confirmar senha");
        lblconfSenha.setBounds(24,125,100,15);
        tela.add(lblconfSenha);
        
        passConfSenha = new JPasswordField();
        passConfSenha.setBounds(120, 125, 219, 19);
        tela.add(passConfSenha);
        
        JButton btnCadastrar = new JButton("Cadastrar");
        btnCadastrar.setBounds(200, 156, 117, 25);
        tela.add(btnCadastrar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 156, 117, 25);
        tela.add(btnCancelar);
        
        
        //Ação do botao cadastrar usuario na base de dados
        btnCadastrar.addActionListener((ActionEvent e) -> {
            
            try{
                //Instancio o objeto Usuario
                Usuario usu = new Usuario();

                //Realizando os setters dos dados de tela
                usu.setNome(txtNome.getText());
                usu.setUsuario(txtUsuario.getText());
                usu.setSenha(passSenha.getText());

                //validacoes de preenchimento dos dados
                if("".equals(usu.getNome())){
                    mensagemJOption = "Campo nome do usuario precisa ser informado";
                    mensagemTipo = 0;
                }else if ("".equals(usu.getUsuario())){
                    mensagemJOption = "campo usuario precisa ser informado!";
                    
                }else if ("".equals(usu.getSenha())){
                    mensagemJOption = "campo senha preicsa ser informado!";
                    
                }else if(!usu.getSenha().equals(passConfSenha.getText())){
                    mensagemJOption = "campo senha e confirmação de senha não coincidem!";
                    
                }else{
                    //Verifico se somente o usuario consta no banco,
                    //neste caso faremos uma sobrecarga de metodo
                    usuarioValido = usu.verificaUsuario(usu.getUsuario());

                    if(usuarioValido == true){
                        //Caso existe, nao pode ser colocado na base
                        mensagemJOption = "Usuario já existe na base de dados";
                        mensagemTipo = 0;

                    }else{
                        cadastroValido = usu.cadastraUsuario(usu.getNome(),
                                                            usu.getUsuario(),
                                                            usu.getSenha());
                        if (cadastroValido == true){
                            //usuario cadastrado na base de dados

                            mensagemJOption = "Usuario cadastrado corretamente";
                            mensagemTipo = 1;
                        }else{
                            //Algum erro aconteceu
                            mensagemJOption = "Problemas ao inserir o usuario";
                            
                        }
                    }
                }

                //Mostrar a mensagem referida
                JOptionPane.showMessageDialog(null,
                        mensagemJOption, "Atenção ", mensagemTipo);

                if(mensagemTipo == 1){
                    //Voltamos para a tela de login
                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();

                    //Fecho a tela de cadastro
                    dispose();
                }

            }catch(HeadlessException ec) {
                System.out.println("Erro no cadastro do usuario"
                        + ec.getMessage());

            }
        });
        
    
        //Botao cancelar
        btnCancelar.addActionListener ((ActionEvent e) -> {
            TelaLogin tLogin = new TelaLogin();
            tLogin.abreTela();
            dispose();

        });
    }
    
    public void abreTela(){
    
        TelaCadastro panelCadastro = new TelaCadastro();
        panelCadastro.setVisible(true);
    }
    
}

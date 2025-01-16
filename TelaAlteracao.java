package LoginAtual;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static LoginAtual.Usuario.usuarioSistema;
import java.awt.HeadlessException;

public class TelaAlteracao extends JFrame{
    //Criando meus atributos globais
    private final JPanel tela;
    private final JTextField txtNome;
    private final JPasswordField passAtual;
    private final JPasswordField passSenha;
    private final JPasswordField confPassSenha;
    
    private boolean atualizacaoValida;
    
    public TelaAlteracao(){
        
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Fatec - Alteração");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 200, 426, 212);
        setFont(new Font("Verdana", 3, 15));
        
        tela = new JPanel();
        tela.setBackground(SystemColor.ORANGE);
        setContentPane(tela);
        tela.setLayout(null);
        
        JLabel lblIdentificacao = new JLabel("Informar campos para alteração");
        lblIdentificacao.setBounds(60,0,500,39);
        lblIdentificacao.setFont(new Font("Verdana", 3, 15));
        tela.add(lblIdentificacao);
        
        JLabel lblNome = new JLabel ("Nome");
        lblNome.setBounds(24,35,100,15);
        tela.add(lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(120, 35, 218, 20);
        tela.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblSenhaAtual = new JLabel ("Senha Atual");
        lblSenhaAtual.setBounds(24,60,70,15);
        tela.add(lblSenhaAtual);
        
        passAtual = new JPasswordField();
        passAtual.setBounds(120, 60, 219, 19);
        tela.add(passAtual);
        
        JLabel lblNovaSenha = new JLabel ("Nova senha");
        lblNovaSenha.setBounds(24,85,70,15);
        tela.add(lblNovaSenha);
        
        passSenha = new JPasswordField();
        passSenha.setBounds(120, 85, 219, 19);
        tela.add(passSenha);
        
        JLabel lblConfSenha = new JLabel ("Nova senha");
        lblConfSenha.setBounds(24,110,100,15);
        tela.add(lblConfSenha);
        
        confPassSenha = new JPasswordField();
        confPassSenha.setBounds(120, 110, 219, 19);
        tela.add(confPassSenha);
        
        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(200, 136, 117, 25);
        tela.add(btnAlterar);
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(50, 136, 117, 25);
        tela.add(btnCancelar);
        
        //Botao cancelar
        btnCancelar.addActionListener ((ActionEvent e) -> {
            TelaInicio telaIni = new TelaInicio();
            telaIni.abreTela();
            dispose();

        });
        
        //Botao de alteracao
        btnAlterar.addActionListener((ActionEvent e) -> {
            try{
            //Instancio a classe usuario
            Usuario usu = new Usuario();
            
            //Validacao antes de efetivar a alteracao
            //setando a senha e usuario
            usu.setSenha(confPassSenha.getText());
            usu.setUsuario(usuarioSistema);
            
            //Nome vazio
            if ("".equals(usu.getNome())){
                //Vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo nome do usuario precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                //Voltar o cursor para o campo txtNome
                txtNome.grabFocus();
                
                //Senha vazia
            
            }else if("".equals(usu.getSenha())){
                //Vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null,
                        "Campo senha precisa ser informado!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
                //Voltar o cursor para o campo passSenha
                passSenha.grabFocus();
            
            }else if(usu.verificaUsuario(usu.getUsuario(),passAtual.getText()) == false){
                
                JOptionPane.showMessageDialog(null,
                        "Senha invalida, verifique!",
                        "Atenção",
                        JOptionPane.ERROR_MESSAGE);
            }else if(!passSenha.getText().equals(confPassSenha.getText())){
            
                //Vamos dar uma mensagem na tela
                JOptionPane.showMessageDialog(null, 
                                        "Campos de senha e confirmação não são iguais!",
                                        "Atenção",
                                        JOptionPane.ERROR_MESSAGE);
                //Voltar cursor par ao campo passSenha
                passSenha.grabFocus();
                
            }else{
                //Efetivo a alteracao do usuario
                atualizacaoValida = 
                        usu.alteraUsuario(txtNome.getText(),
                                usu.getUsuario(),
                                usu.getSenha());
                if(atualizacaoValida == true){
                    //Usuario cadastrado na base de dados
                    JOptionPane.showMessageDialog(null,
                            "Dado(s) do usuario alterado(s) retornarmemos"
                            + " a tela de login",
                            "Atenção",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    //Abrimos a tela de login novamente
                    TelaLogin tLogin = new TelaLogin();
                    tLogin.abreTela();
                    
                    //Fecho a tela de cadastro
                    dispose();
                
                }else{
                    //Usuario cadastrado na base de dados
                    JOptionPane.showMessageDialog(null,
                            "Problemas ao atualizar o usuario",
                            "Atenção",
                            JOptionPane.ERROR_MESSAGE);
                
                }
                
            }
            
        }catch(HeadlessException ec){
                System.out.println("Erro ao alterar usuario"
                                + ec.getMessage());
        }
           
        });
        
        //Atribuir o atributo global ao objeto
        txtNome.setText(Usuario.nomeUsuario);
    
    }
    
    public void abreTela(){
        TelaAlteracao telaAlteracao = new TelaAlteracao();
        telaAlteracao.setVisible(true);
    }
    
}

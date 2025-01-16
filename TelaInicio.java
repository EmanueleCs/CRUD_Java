package LoginAtual;

import java.awt.HeadlessException;
import java.awt.SystemColor;
import java.awt.event.ActionEvent; // trabalhar com evento
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane; //Trabalhar com mensagens
import javax.swing.JPanel;

public class TelaInicio extends JFrame{
    private final JPanel tela;
    
    //Atributo que afremoa para validar a exclusao
    private boolean exclusaoValida;
    
        public TelaInicio() {
            
            setLocationRelativeTo(null);
            setResizable(false);
            setTitle("Menu - Fatec");
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(500, 200, 426, 212);
            
            tela = new JPanel();
            tela.setBackground(SystemColor.darkGray);
            setContentPane(tela);
            tela.setLayout(null);
            
            //Concateno o label com o atributo global nome da classe usuario
            JLabel lblUsuario = new JLabel ("Bem vindo " + Usuario.nomeUsuario);
            lblUsuario.setBounds(24,65,200,15);
            tela.add(lblUsuario);
        
            JButton btnExcluir = new JButton("Excluir");
            btnExcluir.setBounds(2, 130, 117, 25);
            tela.add(btnExcluir);

            JButton btnAlterar = new JButton("Alterar dados");
            btnAlterar.setBounds(150, 130, 117, 25);
            tela.add(btnAlterar);
            
            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.setBounds(300, 130, 117, 25);
            tela.add(btnVoltar);
        
            
            btnVoltar.addActionListener((ActionEvent e) -> {
                try{
                    TelaLogin telaLogin = new TelaLogin();
                    telaLogin.setVisible(true);
                    dispose(); 
                
                }catch (Exception ec){
                    System.out.println("Erro ao acessar a tela de login"
                            + ec.getMessage());
                
                }
            
            });
            
            btnAlterar.addActionListener((ActionEvent e) -> {
                try{
                    TelaAlteracao telaAlterar = new TelaAlteracao();
                    telaAlterar.setVisible(true);
                    dispose(); 
                
                }catch (Exception ec){
                    System.out.println("Erro ao acessar a tela de alteracao"
                            + ec.getMessage());
                
                }
            
            });
            
            btnExcluir.addActionListener((ActionEvent e) -> {
                try{
                    //Array que recebe as opções
                    Object[] options = {"Sim", "Não"};
                    
                    int opcao = JOptionPane.showOptionDialog(null,
                            Usuario.nomeUsuario +
                                    " deseja se excluir?", "Atenção",
                                    JOptionPane.DEFAULT_OPTION,
                                    JOptionPane.QUESTION_MESSAGE, null,
                                    options, options[0]);
                    
                    if (opcao == 0){
                        //Excluir o usuario
                        //Instancio a classe usuario
                        Usuario usu = new Usuario();
                        
                        exclusaoValida = 
                                usu.excluiUsuario(Usuario.usuarioSistema);
                        
                        if (exclusaoValida == true){
                            //Usuario excluido na base de dados
                            JOptionPane.showMessageDialog(null, 
                                        "Usuario deletado corretamente, voltaremos"
                                                + " a tela de login.",
                                                "Atenção",
                                                JOptionPane.INFORMATION_MESSAGE);
                            
                            //Abrimos a tela de login novamente
                            TelaLogin tLogin = new TelaLogin();
                            tLogin.abreTela();
                            dispose();
                             
                        }else{
                            JOptionPane.showMessageDialog(null,
                                    "Problemas ao excluir o usuario",
                                    "Atenção",
                                    JOptionPane.ERROR_MESSAGE);
                        
                        }
                    
                    }
                
                }catch (HeadlessException ec){
                    System.out.println("Erro ao excluir usuario"
                            + ec.getMessage());
                
                }
            
            });
            
        }
            
        public void abreTela(){
            TelaInicio telaInicio = new TelaInicio();
            telaInicio.setVisible(true);
                
        }
    
}

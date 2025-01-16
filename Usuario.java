package LoginAtual;

import java.sql.SQLException; //Tratar as exceções no banco de dados

public class Usuario {
    private String usuario;
    private String nome;
    private String senha;
    
    //Atributos Estaticos do sistema
    static String nomeUsuario;
    static String usuarioSistema;
    
    //Atributo que armazenará o retorno do banco de dados 
    private boolean resultUsuario;
    private boolean resultCadastro;
    private boolean resultAlteracao;
    private boolean resultExclusao;
    
    //Criação dos getters e setters
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
    
    // Método de verificação da autenticidade do usuario
    public boolean verificaUsuario(String usuario, String senha){
    
        // Fazer a instância da conexão com o banco de dados
        Conexao banco = new Conexao();
        
        try{
            //Abro a conexão com o banco de dados
            banco.abrirConexao();
            
            //Criando parametro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executando a consulta no banco de dados
            banco.resultset = 
                    banco.stmt.executeQuery(
                            "select * from usuario "
                                    + "where usuario = '" + usuario + "'"
                                    + " and senha = md5('" + senha +"')");
            
            //verificando se existe retorno de dados no banco
            if(banco.resultset.next()){
                //caso tenha
                resultUsuario = true;
                
                //Setters em Nome e usuario
                setUsuario(banco.resultset.getString(1));
                setNome(banco.resultset.getString(2));
                
                //Nos atributos estaticos, realizo as atribuicoes
                nomeUsuario = getNome();
                usuarioSistema = getUsuario();
                
            }else{
            
                //caso não tenha
                resultUsuario = false;
            }
            
            banco.fecharConexao(); // fecha nossa conexão com o banco de dados
         
        }catch(SQLException ec){
            System.out.println("Erro ao consultar usuario" + ec.getMessage());
        }
        return resultUsuario;
        
    }
    
    //Metodo de verificacao da autenticidade do usuario
    public boolean verificaUsuario (String usuario){
        //Fazer a instancia da conexao com o banco de dados
        Conexao banco = new Conexao();
        
        try{
            //Abro a conexao com o banco de dados
            banco.abrirConexao();
            
            //Criando parametro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executando a consulta no banco de dados
            banco.resultset = banco.stmt.executeQuery("select * from usuario "
                                                + "where usuario = '" + usuario + "' and senha = md5('"+ senha+ "')");
            
            //verificando se existe retorno de dados no banco
            if (banco.resultset.next()){
                //caso tenha
                resultUsuario = true;
                
            }else{
                //caso não tenha
                resultUsuario = false;
            }
            
            banco.fecharConexao(); //fecha nossa conexao com o banco de dados
            
      
        }catch(SQLException ec){
            System.out.println("Erro ao consultar usuario" + ec.getMessage());
        
        }
        return resultUsuario;
    
    }
    
    //Metodo para cadastro de usuario em nosso sistema
    public boolean cadastraUsuario(String nome, String usuario, String senha){
        //Fazer instancia da conexao com o banco de dados
        Conexao banco = new Conexao();
        
        try{
            //Abro a conexao com o banco de dados
            banco.abrirConexao();
            
            //Criando parametro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executando a insercao dos dados
            banco.stmt.execute("insert into usuario (nome, usuario, senha)"
                                                + " values ('" + nome + "','" + usuario + "', md5('"
                                                + senha + "'))");
            
            resultCadastro = true;            
      
        }catch(SQLException ec){
            System.out.println("Erro ao inserir usuario" + ec.getMessage());
            resultCadastro = false;
        
        }
        
        return resultCadastro;
    
    
    }
    
    //Metodo para alteracao dos dados em nosso sistema
    public boolean alteraUsuario(String nome, String usuario, String senha){
        //fazer instancia da conexao com o banco de dados
        
        Conexao banco = new Conexao();
        
        try{
            //Abro a conexao com o banco de dados
            banco.abrirConexao();
            
            //Criando o parametro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executando a alteracao no banco de dados
            banco.stmt.execute("update usuario set nome = '" + nome +
                        "', senha = md5('" + senha + "') where usuario = '" +
                        usuario + "'");
           resultAlteracao = true;
        
        }catch(SQLException ec){
            System.out.println("Erro ao atualizar usuario " + ec.getMessage());
            resultAlteracao = false;
        }
        
        banco.fecharConexao();
        
        return resultAlteracao;
        
    }
    
    //Metodo para exclusao do usuario do sistema
    public boolean excluiUsuario(String usuario){
        //fazer a instancia da conexao com o banco de dados
        
        Conexao banco = new Conexao();
        
        
        try{
            //Abro a conexao com o banco de dados
            banco.abrirConexao();
            
            //Criando o parametro de retorno
            banco.stmt = banco.con.createStatement();
            
            //Executando a alteracao no banco de dados
            banco.stmt.execute("delete from usuario where usuario = '"
                            + usuario + "'");  
            
            //Caso exclua 
            resultExclusao = true;
        
        }catch(SQLException ec){
            System.out.println("Erro ao excluir usuario " + ec.getMessage());
            resultExclusao = false;
        }
        
        banco.fecharConexao();
        
        return resultExclusao;
    
    }
    
}

    
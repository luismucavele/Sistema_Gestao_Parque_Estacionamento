//package Controller;
//
//import Mode.ModelDAO.FuncionarioDAO;
//
//import Veiw.Login;
//import Model.Funcionario;
//
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.List;
//
//public class LoginController {
//    private LoginController telaLogin;
//    private FuncionarioDAO funcionarioDAO;
//
//    public LoginController(LoginController telaLogin, FuncionarioDAO funcionarioDAO) {
//        this.Login = telaLogin;
//        this.funcionarioDAO = funcionarioDAO;
//
//        this.Login.adicionarListenerLogin(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                realizarLogin();
//            }
//        });
//
//        this.telaLogin.adicionarListenerCancelar(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                telaLogin.dispose();
//            }
//        });
//
//        this.telaLogin.adicionarListenerRecuperar(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                // lógica para recuperar senha
//            }
//        });
//    }
//
//    private void realizarLogin() {
//        String usuario = Login.gettxtUser();
//        String senha = telaLogin.gettxtPasseword();
//
//        try {
//            List<Funcionario> funcionarios = funcionarioDAO.carregarFuncionarios();
//            for (Funcionario funcionario : funcionarios) {
//                if (funcionario.getUsuario().equals(usuario) && funcionario.getSenha().equals(senha)) {
//                    if (funcionario.getTipo().equals("Gestor")) {
//                        // Redirecionar para Menu do Gestor
//                    } else {
//                        // Redirecionar para Menu Simples
//                    }
//                    dispose();
//                    return;
//                }
//            }
//            telaLogin.exibirMensagemErro("Usuário ou senha incorretos!");
//        } catch (IOException | ClassNotFoundException ex) {
//            telaLogin.exibirMensagemErro("Erro ao ler o arquivo de funcionários: " + ex.getMessage());
//        }
//    
//    }
//}

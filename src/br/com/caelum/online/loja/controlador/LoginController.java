package br.com.caelum.online.loja.controlador;

import br.com.caelum.online.loja.dominio.UsuarioLogado;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;

@Resource
public class LoginController {

	private final Result result;
	private final UsuarioLogado usuarioLogado;

	public LoginController(Result result, UsuarioLogado usuarioLogado) {
		this.result = result;
		this.usuarioLogado = usuarioLogado;
	}

	public void formulario() {
	}

	public void loga(String login, String senha) {
		if (login.equals("caelum") && senha.equals("123")) {
			usuarioLogado.setLogin(login);
			result.redirectTo(ProdutoController.class).lista();
		} else {
			result.redirectTo(LoginController.class).formulario();
		}
	}

}

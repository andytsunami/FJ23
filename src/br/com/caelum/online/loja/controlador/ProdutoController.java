package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {

	private final ProdutoDao produtos;
	private final Result result;

	public ProdutoController(Result result, ProdutoDao produtos) {
		this.produtos = produtos;
		this.result = result;
	}

	public List<Produto> lista() {
		return produtos.pegaTodos();
	}

	public void formulario() {
	}

	@Post
	public void adiciona(Produto produto) {
		produtos.salva(produto);
		result.include("mensagem", "Novo produto adicionado com sucesso!");
		result.redirectTo(ProdutoController.class).lista();
	}

	@Path("/produto/{id}")
	public Produto exibe(Long id) {
		return produtos.pegaPorId(id);
	}

	@Path("/produto/{id}/xml")
	public void exibeComoXml(Long id) {
		result.use(Results.xml()).from(produtos.pegaPorId(id)).serialize();
	}

	@Path("/produto/{id}/json")
	public void exibeComoJson(Long id) {
		result.use(Results.json()).from(produtos.pegaPorId(id)).serialize();
	}

	@Path("/produto/lista/json")
	public void listaComoJson() {
		result.use(Results.json()).from(produtos.pegaTodos()).serialize();
	}

	@Path("/produto/lista/xml")
	public void listaComoXml() {
		result.use(Results.xml()).from(produtos.pegaTodos()).serialize();
	}

}

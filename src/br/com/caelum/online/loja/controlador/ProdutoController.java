package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Validations;
import br.com.caelum.vraptor.view.Results;

@Resource
public class ProdutoController {

	private final ProdutoDao produtos;
	private final Result result;
	private final Validator validator;

	public ProdutoController(Result result, ProdutoDao produtos,
			Validator validator) {
		this.produtos = produtos;
		this.result = result;
		this.validator = validator;
	}

	public List<Produto> lista() {
		return produtos.pegaTodos();
	}

	public void formulario() {
	}

	@Post
	public void adiciona(final Produto produto) {

		validator.checking(new Validations() {
			{
				that(produto.getPreco() > 0.1, "preco",
						"produto.preco.invalido");
				that(produto.getDescricao() != null
						&& produto.getDescricao().length() > 0, "descricao",
						"produto.descricao.invalido");
				that(produto.getNome() != null
						&& produto.getNome().length() > 0
						&& produto.getNome().length() <= 100, "nome",
						"produto.nome.invalido");
				that(produto.getCor() != null && produto.getCor().length() > 0,
						"cor", "produto.cor.invalido");
			}
		});

		validator.onErrorUsePageOf(ProdutoController.class).formulario();

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
		result.use(Results.json()).from(produtos.pegaTodos(), "produtos")
				.serialize();
	}

	@Path("/produto/lista/xml")
	public void listaComoXml() {
		result.use(Results.xml()).from(produtos.pegaTodos(), "produtos")
				.serialize();
	}

	@Get
	public void remove(Produto produto) {
		produtos.remove(produto);
		result.nothing();
	}

}

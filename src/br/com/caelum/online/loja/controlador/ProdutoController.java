package br.com.caelum.online.loja.controlador;

import java.util.List;

import br.com.caelum.online.loja.dao.ProdutoDao;
import br.com.caelum.online.loja.dominio.Produto;
import br.com.caelum.vraptor.Resource;

@Resource
public class ProdutoController {

	public List<Produto> lista() {
		return new ProdutoDao().pegaTodos();
	}
}

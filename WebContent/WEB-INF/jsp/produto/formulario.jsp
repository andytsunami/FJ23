<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Minha Loja</title>
</head>
<body>
	<form action="<c:url value="/produto/adiciona" />" method="post">
	Nome: <input type="text" name="produto.nome" /><br />
	Descrição: <input type="text" name="produto.descricao" /><br />
	Preço: <input type="text" name="produto.preco" /><br />
	Cor: <input type="text" name="produto.cor" /><br />
	
	<input type="submit" value="Adicionar">
	
	</form>
</body>
</html>
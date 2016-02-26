<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Começando com Web Service RESTFul</h2>
	
	<form method="post" action="<%=request.getContextPath()%>/hello">
		Nome:<input type="text" name="nome"/>
		<br /><br />
		Sobrenome:<input type="text" name="sobrenome"/>
		<br />
		<input type="submit" name="Enviar"/>
	</form>
</body>
</html>
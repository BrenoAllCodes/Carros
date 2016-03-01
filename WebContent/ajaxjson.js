
// Funçõa para instanciar o objeto XMLHttpRequest
function iniciarAjax() {
    var objetoAjax = false;

    if(window.XMLHttpRequest) {
        objetoAjax = new XMLHttpRequest();
    } else if(window.ActiveXObject) {
        try{
            objetoAjax = new ActiveXObject("Msxml2.XMLHTTP");
        } catch(exception) {
            try{
                objetoAjax = new ActiveXObject("Microsoft.XMLHTTP");
            } catch(exception) {
                objetoAjax = false;
            }
        }
    }
    return objetoAjax;
}

// Função para requisitar um arquivo
function requisitar(arquivo) {
    var requisicaoAjax = iniciarAjax();

    if(requisicaoAjax) {
        requisicaoAjax.onreadystatechange = function() {
            tratarResposta(requisicaoAjax);
        };
        requisicaoAjax.open('GET', arquivo, true);
        requisicaoAjax.send(null);
    }
}

function tratarResposta(requisicaoAjax) {

    if(requisicaoAjax.readyState == 4) {
        if(requisicaoAjax.status == 200 || requisicaoAjax == 304) {

        	var dados = eval("(" + requisicaoAjax.responseText + ")"); // Retorna o arquivo JSON requisitado do servidor

        	// Recupera os dados do JSON retornado
            var tituloDado = dados.livro.titulo;
            var autorDado = dados.livro.autor;
            var siteDado = dados.livro.site;

            var titulo = document.createElement("h2");
            var site = document.createElement("a");

            site.setAttribute("href", siteDado);
            var textoTitulo = document.createTextNode(tituloDado);
            site.appendChild(textoTitulo);

            titulo.appendChild(site);

            var autor = document.createElement("p");
            var textoAutor = document.createTextNode(autorDado);
            autor.appendChild(textoAutor);

            var insere = document.getElementById("insere-aqui");

            while(insere.hasChildNodes()){
                insere.remove(insere.lastChild());
            }

            insere.appendChild(titulo);
            insere.appendChild(autor);

        } else {
            alert("Problema na comunicação com o servidor");
        }
    }

}

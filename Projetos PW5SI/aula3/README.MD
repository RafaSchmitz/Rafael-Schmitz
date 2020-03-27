## 1. Introdução
Neste projeto será discutido **como utilizar Thymeleaf com Spring**  juntamento com um caso de uso na camada de visão (*view*) de uma aplicação Spring Boot [1], Spring Web MVC [2], Spring Data JPA [3] e Thymeleaf [4].

### 1.1 Dependências do projeto.
O projeto será do tipo **Maven Project**.
A linguagem será **Java**.
A versão do Spring Boot será a versão estável atual na data de criação do projeto (**2.2.5**).
Os metadados do projeto são:
- Group: **br.edu.utfpr.pb.pw25s**
- Artifact: **aula3**
- Options: 
	- Packaging: **Jar** 	
	- Java: **11**.

Em dependências devem ser selecionados os *frameworks*:
- Spring Web
- Spring Data JPA
- Spring Devtools
- PostgreSQL Driver (ou o driver do banco de sua preferência H2, MySQL, etc...)
- Lombok

### 1.2 Instruções para executar o projeto.
Como o projeto foi desenvolvido com Maven e Spring Boot o processo para executar o processo é bem simples. Inicialmente é necessário importar o projeto na IDE. O próximo passo será atualizar as dependências. No passo seguinte deve ser configurada a conexão com o banco de dados, por padrão foi criada uma conexão com o `PostgreSql`. O nome do banco de dados é `pw25s-aula3`, então basta criar o banco no Sistema Gerenciador de Banco de Dados (SGBD) e configurar o usuário e senha do banco de dados no arquivo `aplication.properties` que fica dentro de `src/main/resources`.

### 1.3 Thymeleaf
O [Thymeleaf](http://www.thymeleaf.org/)  é um motor de template (*template engine*) que atua no lado servidor (*server-side*) para Java para processamento e criação de HTML, XML, JavaScript, CSS, e text [4].
Essa biblioteca é extensível e sua capacidade de modelagem garante que os modelos possam ser prototipados sem um *back-end* o que torna o desenvolvimento muito rápido quando comparado com outros motores de template, como o Java Server Pages (JSP) [4].


## 2. Integrando Thymeleaf com Spring

Inicialmente é necessário configurar o projeto com as dependências necessárias no arquivo **pom.xml** do Maven.
``` xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
```
Como os projetos Spring Boot possuem as configurações básicas com o Thymeleaf já pré-configuradas, nenhuma outra ação é necessária para o funcionamento. Agora já é possível criar as páginas que serão apresentadas na camada de visão da aplicação.


## 3. Criando o primeiro Controller e View

Neste projeto está sendo utilizado o padrão *Model View Controller* (MVC). Na camada *model* será utilizado o *framework* Spring JPA, na camada *view* o Thymeleaf e na camada *controller* o *framework* Spring MVC. 

### 3.1 Controller
No primeiro passo será criado uma nova classe chamada 'IndexController', nessa classe serão implementados os métodos responsáveis por fazer a chamada da página inicial da aplicação. Na classe criada será adicionada a anotação `@Controller`, que indica que essa classe é um componente do Spring Web MVC e que é um *controller*. O próximo passo é definir o método que retornará o nome da *view* que será exibida ao usuário, nesse caso o método `index()` que retorna o texto `index`, que será o nome do arquivo que será renderizado para o usuário. 

Agora é necessário defirnir qual *Uniform Resource Locator* (URL) o usuário irá digitar para acessar a página inicial, para isso foi utilizada a anotação `@GetMapping`, como a anotação aparece sózinha sem nenhum parâmetro significa que esse método responderá requisições HTTP Get na raiz da aplicação, ou seja, no caso deste projeto será exibida a página inicial quando o usuário digitar `http://localhost:8025`. 

Nessa mesma classe também foi criado um segundo método chamado `teste()`, nesse método também é possível visualizal a anotação `@GetMapping`, porém agora temos o parâmetro `value` que recebe dois valores **teste** e **teste2**, isso siginifica que caso o usuário digite na barra de enredeços no navegador `http://localhost:8025/teste` ou `http://localhost:8025/teste2` ele irá acessar a mesma página do méto `index()`, pois o retorno também foi o nome do arquivo da camada *view* `index`.
``` Java
package br.edu.utfpr.pb.aula3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping
	public String index() {
		return "index";
	}
	
	@GetMapping(value = {"teste", "teste2"})
	public String teste() {
		return "index";
	}
	
}
```

### 3.2 View
Nesse passo será criado o arquivo da camada *view*. A configuração padrão do Spring Boot com o  Thymeleaf, define que os arquivos da camada *view* devem ficar em `src/main/resources/templates`, e o sufixo dos arquivos devem ser **.html**.

O arquivo `index.html` foi criado na raiz da pasta `templates`. O conteúdo do arquivo é exibido abaixo. Alguns trechos desse HTML devem ser destacados, na *tag* HTML `<html>` o atributo `xmlns:th` define que será possível utilizar as propriedades definidas pelo Thymeleaf no código da página HTML. Além disso, na *tag* `<head>` estão as declarações dos arquivos *Cascading Style Sheets* (CSS) utilizados para estilizar a página.

Ainda no arquivo `index.html` é possível visualizar alguns atalhos para as próximas *views* da aplicação que está sendo desenvolvida. A  *tag* `<a />`, que define um *hyperlink* nesse caso utiliza o atributo `th:href` do Thymeleaf. Utilizando esse atributo é possível utilizar a expressão `@{}`, que coloca o contexto da aplicação nos *hyperlinks* do projeto, ou seja, independente da URL da aplicação em que o *hyperlink* for inserido o endereço sempre vai partir do contexto da aplicação.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Index</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.4.1/css/all.css"
	integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz"
	crossorigin="anonymous">
<link rel="stylesheet" 
	href="https://unpkg.com/shards-ui@latest/dist/css/shards.min.css">
</head>
<body>
	<div class="container my-5">
		<h3>Menu</h3>
		<ul class="nav nav-pills nav-fill">
			<li class="nav-item">
				<a class="btn btn-primary" th:href="@{/genero}"> 
					<i class="fas fa-table"> Lista de Gêneros</i>
				</a>
			</li>
			<li class="nav-item">
				<a class="btn btn-primary" th:href="@{/produtora}">
					<i class="fas fa-table"> Lista de Produtoras</i>
				</a>
			</li>
			<li class="nav-item">
				<a class="btn btn-primary" th:href="@{/serie}">
					<i class="fas fa-table"> Lista de Séries</i>
				</a>
			</li>
		</ul>
	</div>
</body>
</html>
```
Com a página `index.html` criada a mesma já pode ser acessada após executar a aplicação. Executando a aplicação basta abrir o navegador e acessar o endereço `http://localhost:8026`.

## 4. Criando uma interação entre o Controller, Model e View

Nos próximos exemplos serão realizadas operações de *Create, Read, Update and Delete* (CRUD) no SGBD. O projeto contém três *models* mapeados com anotações da *Java Persistence Api* (JPA) que são Genero, Produtora e Serie. No primeiro exemplo será apresentada uma lista de todos os **gêneros** cadastrados no banco de dados, para isso serão criados dois arquivos, o `GeneroController` no pacote `controller` da aplicação e o arquivo `list.html` `src/main/resources/templates/genero`. Já no segundo exemplo será criado um formulário para criar e manter **gêneros** e, para isso serão realizadas modificações no arquivo `GeneroController` e será criado o arquivo `form.html` dentro da pasta `src/main/resources/templates/genero`.

### 4.1 Criando a lista de gêneros
Inicialmente será criado o arquivo `GeneroController`* e, assim como no *controller* do *index* será mapeado com a anotação `@Controller`.  

#### 4.1.1 GeneroController
Esse *controller* também possuí a anotação `@RequestMapping("genero")` que indica que todos os seus métodos irão receber requisições HTTP a partir de `/genero`. A dependência `GeneroService` é injetada com a anotação `@Autowired`, por é por meio desse *service* que serão realizadas as operações com o banco de dados.

No método `list()` será realizado o processo de consultar os dados na base e fazer a chamada para página da camada *view*. A anotação `@GetMapping` indica que esse método irá receber requisições HTTP Get de `/genero`. O método recebe um `Model` que é uma interface que será responsável por adicionar os atributos necessários para serem exibidos no HTML. O código `model.addAttribute("generos", generoService.findAll());` adiciona uma lista de gêneros no model chamada `generos`, essa lista será utilizada no arquivo `list.html` para apresentação dos dados. Por fim, o retorno do método é a página que será exibidos os dados, ou seja, o arquivo `list.html` dentro da pasta `genero`.

###### * Para melhorar a visualização do código fonte o pacote e as importações necessárias não serão apresentada em conjunto com o código fonte, mas podem ser visualizadas no arquivo original.

``` Java
@Controller
@RequestMapping("genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("generos", generoService.findAll());
		return "genero/list";
	}

}
```
#### 4.1.2 View `/genero/list`

O arquivo `list.html` inicia com o conteúdo semelhante ao `index.html` com o namespace do Thymeleaf e a adição das referências aos arquivos CSS. Depois é apresentado um *hyperlink* para retornar ao `index`. É utilizada então uma *tag* `<table />` no cabeçalho (`<thead />`) é exibido o texto estático que será apresentado na primeira linha da tabela.  Em seguida vem o corpo da tabela com a *tag* (`<tbody />`), nele serão exibidos os dados dos **gêneros**. Na *tag* `<tr  th:each="genero: ${generos}">` visualiza-se o atributo do Thymeleaf `th:each` que é utilizado para percorrer uma lista, nesse caso a lista `generos` que foi adicionado no model na classe `GeneroController`, na linha de código `model.addAttribute("generos", generoService.findAll());`, e `genero:` significa que cada **gênero** da lista será um objeto `genero` que poderá ser utilizado pelos atributos do Thymeleaf. 

Para exibir os dados na tela são utilizadas as colunas da tabela `<td />` que nesse caso são `<td th:text="${genero.id}">código</td>` e `<td th:text="${genero.nome}">nome</td>`. Note o uso do atributo `th:text` que apresentará no texto de cada coluna (`<td> AQUI </td>`) os valores dos atributos `id` e `nome`  do objeto `genero`, respectivamente.

Para testar, basta executar a aplicação e clicar no *hyperlink* para a tela de **gêneros** na página inicial, ou digitar no navegador `http://localhost:8025/genero`.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Lista de Gêneros</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
        <link rel="stylesheet" href="https://unpkg.com/shards-ui@latest/dist/css/shards.min.css">
    </head>
    <body>
        <div class="container my-5">
    		<h1>Lista de Gêneros</h1>
    		<a class="btn btn-primary" th:href="@{/}"><i class="fas fa-home"></i>
        	</a>
        	
    		<table class="table table-striped table-responsive-md">
    			<thead>
    				<tr>
    					<th>Código</th>
    					<th>Nome</th>
    					<th>Editar</th>
    					<th>Remover</th>
    				</tr>
    			</thead>
    			<tbody>
    				<tr th:each="genero: ${generos}">
    					<td th:text="${genero.id}">código</td>
    					<td th:text="${genero.nome}">nome</td>
						<!-- EDITAR -->
						<td> ---
						</td>
						<!-- REMOVER -->
						<td> ---
						</td>
    				</tr>
    			</tbody>
    		</table> <!-- FIM TABLE -->
        </div>
    </body>
</html>
```

### 4.2 Criando o form de gêneros e o método de remoção de gêneros

#### 4.2.1 GeneroController
Nessa etapa será utilizado o mesmo arquivo `GeneroController` criado na seção 4.1. O método `list()` foi mantido e foram adicionados três novos métodos: um método `save()` e dois métodos `form()` com assinaturas diferentes, um para ser utilizado na criação de novos registros e outro na edição.

O método `form` para novos registros recebe uma requisição HTTP Get em `/genero/new`, `/genero/novo` ou `/genero/form`. Adiciona um novo gênero no `Model` e retorna para página `form.html` dentro de `src/main/resources/templates/genero`.

O método `form` para edição de registros recebe uma requisição HTTP Get em `/genero/{id}`. Nesse caso `{id}` seré o código do gênero que será editado pelo usuário e será recuperado por meio do parâmetro do método `@PathVariable Long id`. O `Model` também é parâmetro do método, pois é por meio dele que será adicionado o gênero que será editado na *view* `form.html` (o arquivo para novo e editar é o mesmo, a diferença está no fato de que no primeito método form é passado um novo gênero para o *view* e, no segundo um gênero existente no banco de dados). 

O método `save()` que recebe uma requisição HTTP Post em `/genero` e têm como parâmetros um objeto da classe `Genero` que será passado pelo forumário presente no aquivo `form.html`. Um objeto da classe `BindingResult ` que é utilizado na validação do objeto. Um objeto da classe `Model` que é utilizado para retornar o `genero` para o formulário em caso de erro.  Por fim um objeto da classe `RedirectAttributes ` que é utilizado para adicionar atributos que serão exibidos na página `index.html` após salvar um gênero.

###### * Para melhorar a visualização do código fonte o pacote e as importações necessárias não serão apresentada em conjunto com o código fonte, mas podem ser visualizadas no arquivo original.
``` Java
@Controller
@RequestMapping("genero")
public class GeneroController {
	
	@Autowired
	private GeneroService generoService;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("generos", generoService.findAll());
		return "genero/list";
	}

	@GetMapping(value = {"new", "novo", "form"})
	public String form(Model model) {
		model.addAttribute("genero", new Genero());
		return "genero/form";
	}

	@PostMapping
	public String save(@Valid Genero genero, BindingResult result,
					   Model model, RedirectAttributes attributes) {
		if ( result.hasErrors() ) {
			model.addAttribute("genero", genero);
			return "genero/form";
		}	
		generoService.save(genero);
		attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");
		return "redirect:/genero";
	}

	@GetMapping("{id}") // /genero/25
 	public String form(@PathVariable Long id, Model model) {
		model.addAttribute("genero", generoService.findOne(id));
		return "genero/form";
	}

}
```
#### 4.2.2 View `/genero/form` e atualizações no `/genero/list` 

O arquivo `form.html` também inicia com o conteúdo semelhante ao `index.html` com o namespace do Thymeleaf e a adição das referências aos arquivos CSS. Nos próximos projetos serão criadas páginas padrão para evitar a repetição de código.

Esse arquivo apresenta uma *tag* de formulário `<form th:action="@{/genero}" method="post" th:object="${genero}">`. No atributo do Thymeleaf `th:action` é definido que será realizada uma requisição HTTP para URL `/genero` a partir do contexto da aplicação. No atributo `method` é definido que a requisição HTTP será do tipo POST. E o atributo `th:object` indica que esse formulario irá receber um objeto `genero`, que foi o objeto adicionado no *controller* em três ocasiões, no método `form` para novos registros e também na edição e no método `save()` quando ocorre um erro (`model.addAttribute("genero", genero);`).

Então, são adicionados duas *tags* `<input/>`, uma para o atributo `id` e outra para o `nome`. No caso do `id` no input é indicado no atributo `th:field` o valor `id` e `readonly`, pois o campo id do banco de dados é auto-incremento (`<input type="text" class="form-control" th:field="*{id}" readonly/>)`. O atributo `th:field` é responsável por adicionar os atributos **id, name** e **value** no HTML que será renderizado no navegador. 

O `<input />` utilizado para o `nome` segue o mesmo padrão que o `id`, porém sem o `readonly`, pois esse atributo poderá ser editável. Ainda no `input` do atributo nome existe a exibição dos erros: `<span th:if="${#fields.hasErrors('nome')}" th:errors="*{nome}" class="text-danger"></span>`, o objeto `fields` é retornado ao validar o objeto no servidor utilizando a anotação `@Valid` e a classe `BindingResult`.

Por fim, é utilizado um `submit` para fazer a requisição HTTP Post ao servidor. `<button class="btn btn-primary" type="submit">Salvar</button>`

Para testar, basta acessar `http://localhost:8025/genero/new`. A tela com a lista de gêneros (`list.html`) também precisa ser atualizada para exibir as novas mensagens.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Cadastro de Gêneros</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
        <link rel="stylesheet" href="https://unpkg.com/shards-ui@latest/dist/css/shards.min.css">
    </head>
    <body>
        <div class="container my-5">
        	<h2>Formulário de Cadastro - Gênero</h2>
        	
        	<div class="row">
        		<form th:action="@{/genero}" method="post" th:object="${genero}">
        			<div class="row">
        				<div class="form-group">
	        				<label for="id">Código</label>
	        				<input type="text" class="form-control" th:field="*{id}" readonly/>
        				</div>
        			</div>
        			<div class="row">
        				<div class="form-group">
	        				<label for="nome">Nome</label>
	        				<input type="text" class="form-control" th:field="*{nome}" />
        					<span th:if="${#fields.hasErrors('nome')}"
        						th:errors="*{nome}" class="text-danger"></span>
        				</div>
        			</div>
        			
        			<div class="row">
        				<button class="btn btn-primary" type="submit" >Salvar</button>
        			</div>
        		</form>
        	</div> <!-- FIM div row -->
        </div>
    </body>
</html>
```
O conteúdo da nova página `list.html` apresenta agora um `hyperlink` para a tela de novos gêneros. E uma div para exibir as mensagens de sucesso e erro `<div th:if="${!#strings.isEmpty(sucesso)}"  class="alert alert-success"> ... </div>` e `<div th:if="${!#strings.isEmpty(erro)}"`. As strings `sucesso e erro` foram adicionadas na classe `GeneroController` nos métodos `save()` e `delete()` (que será desenvolvido na próxima seção) com o código `attributes.addFlashAttribute("sucesso", "Registro salvo com sucesso!");`. As mensagens são exibidas na *tag* `<span th:text="${sucesso}">Mensagem de sucesso!</span>` dentro da `div` de alerta.

```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="x-ua-compatible" content="ie=edge">
        <title>Lista de Gêneros</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.1/css/all.css" integrity="sha384-5sAR7xN1Nv6T6+dT2mhtzEpVJvfS3NScPQTrOxhwjIuvcA67KV2R5Jz6kr4abQsz" crossorigin="anonymous">
        <link rel="stylesheet" href="https://unpkg.com/shards-ui@latest/dist/css/shards.min.css">
    </head>
    <body>
        <div class="container my-5">
    		<h1>Lista de Gêneros</h1>
    		<div class="row">
    			<div class="col-md-6">
    				<div th:if="${!#strings.isEmpty(sucesso)}" 
    					class="alert alert-success">
    					<i class="fa fa-check-circle"></i>
    					<span th:text="${sucesso}">Mensagem de sucesso!</span>	
   					</div>
   					
   					<div th:if="${!#strings.isEmpty(erro)}" 
    					class="alert alert-danger">
    					<i class="fa fa-trash"></i>
    					<span th:text="${erro}">Mensagem de erro!</span>	
   					</div>
    			</div>
    		</div>
    		<a class="btn btn-primary" th:href="@{/}">
        		<i class="fas fa-home"></i>
        	</a>
        	<a class="btn btn-primary" th:href="@{/genero/new}">
        		<i class="fas fa-plus"></i> Novo
        	</a>
    		
    		<table class="table table-striped table-responsive-md">
    			<thead>
    				<tr>
    					<th>Código</th>
    					<th>Nome</th>
    					<th>Editar</th>
    					<th>Remover</th>
    				</tr>
    			</thead>
    			<tbody>
    				<tr th:each="genero: ${generos}">
    					<td th:text="${genero.id}">código</td>
    					<td th:text="${genero.nome}">nome</td>
						<!-- EDITAR -->
						<td>
						<a th:href="@{/genero/{id}(id=${genero.id})}" class="btn btn-primary">
							<i class="fas fa-edit ml-2"></i>
						</a>
						</td>
						<!-- REMOVER -->
						<td>
						<form method="post" 
							th:action="@{/genero/{id}?_method=DELETE(id=${genero.id})}">
							<input type="hidden" name="_method" value="DELETE">
							<button type="submit" class="btn btn-danger">
								<i class="fas fa-trash ml-2"></i>
							</button>
						</form>
						</td>
    				</tr>
    			</tbody>
    		</table> <!-- FIM TABLE -->
    		
        </div>
    </body>
</html>
```

#### 4.2.3 Método delete 

Na listagem do último código da página `list.html` foi adicionado um código na última coluna da tabela para realizar a remoção de um registro da tabela de gêneros. Quando é adicionado um parâmetro na URL da requisição com o conteúdo `_method=DELETE` ou é adicionado uma *tag* `hidden` com o  conteúdo `<input type="hidden" name="_method" value="DELETE">` é realizada uma requisição do tipo HTTP Delete para URL da propriedade `th:action`.

No `GeneroController` foi adicionado o método `delete()` que está anotado com `@DeleteMapping("{id}")`, ou seja, esse método recebe requisições HTTP Delete em `/genero/{id}`, em que `{id}` é o código do gênero que será removido do banco de dados.

```html
<!-- REMOVER -->
<td>
	<form method="post" th:action="@{/genero/{id}?_method=DELETE(id=${genero.id})}">
		<input type="hidden" name="_method" value="DELETE">
		<button type="submit" class="btn btn-danger">
			<i class="fas fa-trash ml-2"></i>
		</button>
	</form>
</td>
```
```Java
@Controller
@RequestMapping("genero")
public class GeneroController {
	 // ... foram omitidos os métodos listados anteriormente
	@DeleteMapping("{id}") // /genero/25
	public String delete(@PathVariable Long id,RedirectAttributes attributes) {
		try {
			generoService.delete(id);
			attributes.addFlashAttribute("sucesso", "Registro removido com sucesso!");
		} catch (Exception e) {
			attributes.addFlashAttribute("erro", "Falha ao remover o registro!");
		}
		return "redirect:/genero";
	}
}
```

### 4.3 Criando o formulário de Série

 A tela de lista dos objetos **serie**, composta pelo arquivo `list.html` localizado no diretório `src/main/resources/templates/serie`, seque o mesmo padrão que a lista de **genero**. Na qual é realizada uma requisicão HTTP Get para `/serie` que é atendia pelo método `list()` localizado na classe `SerieController`, conforme o código abaixo, que está dentro do *controller* de série.
```Java
	... 
	@GetMapping
	public String list( Model model ) {
		model.addAttribute("series", serieService.findAll());
		return "serie/list";
	}
	...
```
 E, na camada *view* a exibicão da lista também segue o mesmo padrão. Com detalhes na formatação das colunas de data, para ficar no formato `dd/mm/yyyy`.
 
```html
...
<tbody>
	<tr th:each="serie : ${series}">
	    <td th:text="${serie.id}">código</td>
	    <td th:text="${serie.nome}">nome</td>
		<td th:text="${#temporals.format(serie.dataEstreia, 'dd/MM/yyyy')}"></td>
		<td th:text="${#temporals.format(serie.dataEncerramento, 'dd/MM/yyyy')}"></td>
    	<td th:text="${serie.genero.nome}"></td>
    	<td th:text="${serie.produtora.nome}"></td>
		<!-- EDITAR -->
		<td>
			<a th:href="@{/serie/{id}(id=${serie.id})}" class="btn btn-primary">
				<i class="fas fa-edit ml-2"></i>
			</a>
		</td>
		<!-- REMOVER -->
		<td>
			<form method="post" th:action="@{/serie/{id}?_method=DELETE(id=${serie.id})}">
				<input type="hidden" name="_method" value="DELETE">
				<button type="submit" class="btn btn-danger">
					<i class="fas fa-trash ml-2"></i>
				</button>
			</form>
		</td>
	</tr>
</tbody>
...
```
Mas o que realmente diferencia o CRUD de **serie** dos de **genero** e **produtora**, são os relacionamentos que **serie** possui com as outras entidades. No *model* de **serie** pode ser observado as anotações no mapeamento que fazem referência à esse relacionamento. Esses dados ao serem preenchidos no cadastro de uma Série, devem vir do banco de dados.

```Java
@Entity
public class Serie {
	...
	
	@ManyToOne
	@JoinColumn(name = "genero_id", referencedColumnName = "id")
	private Genero genero;
	
	@ManyToOne
	@JoinColumn(name = "produtora_id", referencedColumnName = "id")
	private Produtora produtora;
	
	...
```
Ao acessar o formuário de cadastro de **serie** é realizada uma requisição HTTP Get para url `/new`, que é atendida pelo método `form()` localizado na classe `SerieController`. No corpo do método é adicionado um atributo `serie`, com uma nova instância de **Serie** e então é chamado o método `carregarCombos()`. Nesse métodos são adicionadas as listas de **genero** e **serie**, que serão utilizadas na camada *view* para exibir os dados aos usuários.
```Java
...
	@GetMapping({"new", "novo"})
	public String form( Model model) {
		model.addAttribute("serie", new Serie());
		carregarCombos(model);
		return "serie/form";
	}
	
	private void carregarCombos(Model model) {
		model.addAttribute("generos", generoService.findAll() );
		model.addAttribute("produtoras", produtoraService.findAll() );
	}
...
```
No formulário da camada *view* que é o arquivo `form.html` dentro da pasta serie as duas listas adicionadas no método `carregarCombos()` serão percorridas para exibir em uma *tag* `<select />`as listas de **genero** e **produtora**. A *tag* de **genero** contem o `th:field="*{genero}"`, que é o nome do atributo na classe `Serie`. E na *tag* `<option />` da *tag* `<select />` é percorrida a lista de **generos** que foi adicionada no *controller*. A *tag* `<select />` de **produtora** seguiu o mesmo padrão.
```html
...
<div class="row">
	<div class="form-group">
		<label for="genero">Gênero</label>
		<select class="form-control" th:field="*{genero}">
			<option value=""> (selecione) </option>
			<option th:each="genero: ${generos}"
				th:value="${genero.id}"
				th:text="${genero.nome}"></option>
		</select>
		<span th:if="${#fields.hasErrors('genero')}"
			th:errors="*{genero}"
			class="text-danger"
		></span>
	</div>
</div> <!-- FIM GENERO -->
<div class="row">
	<div class="form-group">
		<label for="produtora">Produtora</label>
		<select class="form-control" th:field="*{produtora}">
			<option value=""> (selecione) </option>
			<option th:each="produtora: ${produtoras}"
				th:value="${produtora.id}"
				th:text="${produtora.nome}"></option>
		</select>
		<span th:if="${#fields.hasErrors('produtora')}"
			th:errors="*{produtora}"
			class="text-danger"
		></span>
	</div>
</div> <!-- FIM PRODUTORA -->
...
```

## Referências

[1] Spring Boot [https://docs.spring.io/spring-boot/docs/current/reference/html/](https://docs.spring.io/spring-boot/docs/current/reference/html/) *Acessado em: 19/03/2020*

[2] Spring Web MVC [https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc) *Acessado em: 19/03/2020*

[3] Spring Data JPA [https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#preface) *Acessado em: 19/03/2020*

[4] Thymeleaf [https://www.thymeleaf.org/](https://www.thymeleaf.org/) - *Acessado em: 19/03/2020*\

[5] W3Schools HTML [https://www.w3schools.com/html/](https://www.w3schools.com/html/) - *Acessado em: 19/03/2020*

# Atividade

## Partes 1 e 2
Criar um novo projeto utilizando o mapeamento ORM das classes criadas no projeto anterior
 - Cidade {id, nome}
 - Autor {id, nome}
 - Editora {id, nome}
 - Genero {id, descricao}
 - Livro {id, titulo, EDITORA, GENERO, AUTOR, ano, isbn, CIDADE, valor}

E criar as telas de listagem e formulário para as classes sem relacionamento (Cidade, Autor, Editora, Genero e Livro). Entrega dia 26/03/2020.
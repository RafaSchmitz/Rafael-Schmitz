INSERT INTO PAIS (NOME) VALUES ('Brasil');

INSERT INTO ESTADO (id ,nome, pais_id) VALUES (1, 'Acre', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (2, 'Alagoas', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (3, 'Amazonas', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (4, 'Amapa', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (5, 'Bahia', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (6, 'Ceara', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (7, 'Distrito Federal', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (8, 'Espirito Santo', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (9, 'Goias', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (10, 'Maranhao', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (11, 'Minas Gerais', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (12, 'Mato Grosso do Sul', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (13, 'Mato Grosso', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (14, 'Para', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (15, 'Paraiba', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (16, 'Pernambuco', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (17, 'Piaui', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (18, 'Parana', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (19, 'Rio de Janeiro', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (20, 'Rio Grande do Norte', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (21, 'Rondonia', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (22, 'Roraima', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (23, 'Rio Grande do Sul', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (24, 'Santa Catarina', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (25, 'Sergipe', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (26, 'Sao Paulo', 1);
INSERT INTO ESTADO (id ,nome, pais_id) VALUES (27, 'Tocantins', 1);

INSERT INTO CIDADE (id, nome, estado_id) VALUES (3042, 'Pato Branco', 18);
INSERT INTO CIDADE (id, nome, estado_id) VALUES (2907, 'Francisco Beltrao', 18);
INSERT INTO CIDADE (id, nome, estado_id) VALUES (2920, 'Guarapuava', 18);
INSERT INTO CIDADE (id, nome, estado_id) VALUES (3032, 'Palmas', 18);

INSERT INTO CATEGORIA(id, descricao) VALUES (1, 'Quentes');
INSERT INTO CATEGORIA(id, descricao) VALUES (2, 'Frios');
INSERT INTO CATEGORIA(id, descricao) VALUES (3, 'Limpeza');
INSERT INTO CATEGORIA(id, descricao) VALUES (5, 'Serviços de Quarto');
INSERT INTO CATEGORIA(id, descricao) VALUES (6, 'Bebidas');

INSERT INTO PRODUTO (descricao, nome, valor, categoria_id)  values('Coca cola - 250 ml', 'Coca cola 250ml', 5.50, 6);
INSERT INTO PRODUTO (descricao, nome, valor, categoria_id)  values('Sanduiche Natural', 'Sanduiche Natural', 7.00, 2);
INSERT INTO PRODUTO (descricao, nome, valor, categoria_id)  values('Sorvete de Flocos', 'Sorvete de Flocos', 11.00, 2);

INSERT INTO SERVICO (descricao, nome, valor, categoria_id)  values('Serviços de Limpeza', 'Limpeza', 70, 3);
INSERT INTO SERVICO (descricao, nome, valor, categoria_id)  values('Camareira', 'Camareira', 40.00, 5);


INSERT INTO CLIENTE (bairro, cep, cpf, email,  endereco, nome, numpassaporte, rg, telefone, cidade_id)values('Centro', '85508000','06354909994', 'rafasch@live.com', 'rua tamoio','Rafael Schmitz','00000000', '102788819', '46999838552', 3042);

INSERT INTO CLIENTE (bairro, cep, cpf, email,  endereco, nome, numpassaporte, rg, telefone, cidade_id)values('Centro', '85508000','06354909995', 'teste@live.com', 'rua tamoio','Beijamin Franklin','00000000', '102788819', '46999838552', 2907);



INSERT INTO QUARTO (numquarto, qtdcamas, qtdpessoas, tipoquarto)values(2, 1, 1, 'ECONOMICO');
					
INSERT INTO QUARTO (numquarto, qtdcamas, qtdpessoas, tipoquarto)values(3, 1, 2, 'LUXO');
	
INSERT INTO QUARTO (numquarto, qtdcamas, qtdpessoas, tipoquarto)values(4, 2, 4, 'SUPERIOR');


INSERT INTO USUARIO(ativo, cpf, datanascimento, email, foto, nome, senha)values('T', '06354809994', '1995-09-06','console@live.com',null ,'Console', '03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4');

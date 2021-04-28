INSERT INTO permissao (nome) values('ROLE_ADMIN');
INSERT INTO permissao (nome) values('ROLE_USER');

INSERT INTO usuario(nome, cpf, telefone, rua, numero, cidade, estado, cep, bairro, email,  username, password) VALUES ('Administrador', '06354809992','46999858352', 'Ibiporã',  '907', 'Pato Branco', 'PR', '00000000', 'Centro', 'rafasch@live.com', 'rafasch@live.com', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');


INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 1);



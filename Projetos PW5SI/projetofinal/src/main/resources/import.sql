INSERT INTO permissao (nome) values('ROLE_ADMIN');
INSERT INTO permissao (nome) values('ROLE_USER');

INSERT INTO usuario(nome, cpf, telefone, rua, numero, cidade, estado, cep, bairro, email,  username, password) VALUES ('Administrador', '06354809992','46999858352', 'Ibiporã',  '907', 'Pato Branco', 'PR', '00000000', 'Centro', 'rafasch@live.com', 'rafasch@live.com', '$2a$10$.PVIfB07x.SfMYTcToxL0.yxcLWU0GbS2NUO1W1QAvqMm/TsFhVem');


insert into categoria(nome) values('Leptops');
insert into categoria(nome) values('Tablets');
insert into categoria(nome) values('Hybrids');



insert into produto (nome, img, valor, categoria_id) values ('HP Chromebook 11', '../img/products/chrome-book-11.jpg', 199.0, 1);
insert into produto (nome, img, valor, categoria_id) values ('HP Chromebook 14', '../img/products/chrome-book-14.jpg', 209.99, 1);
insert into produto (nome, img, valor, categoria_id) values ('Asus Chromebook', '../img/products/chrome-book-asus.jpg', 299.99, 1);
insert into produto (nome, img, valor, categoria_id) values ('iPad Air', '../img/products/ipad-air.jpg', 449.99, 2);
insert into produto (nome, img, valor, categoria_id) values ('iPad Mini', '../img/products/ipad-mini.jpg', 399.99, 2);
insert into produto (nome, img, valor, categoria_id) values ('Mi Pad 2', '../img/products/mi-pad-2.jpg', 199.0, 2);
insert into produto (nome, img, valor, categoria_id) values ('Surface Pro', '../img/products/surface-pro.jpg', 199.0, 3);
insert into produto (nome, img, valor, categoria_id) values ('Lenovo Yoga', '../img/products/lenovo-yoga.jpg', 199.0, 3);
insert into produto (nome, img, valor, categoria_id) values ('ASUS Transformer', '../img/products/asus-transformer.jpg', 199.0, 3);



INSERT INTO usuario_permissoes(usuario_id, permissoes_id) VALUES (1, 1);



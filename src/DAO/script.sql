


---------- SCRIPTS-----------
            

            create table usuario(id serial,
                         login varchar(50) not null,
                         senha varchar(50) not null,
                         CONSTRAINT pkusuario primary key (id)
                         );

            CREATE TABLE cidade(id serial,
                         descricao varchar(100) not null,
                         ativo char(1) not null,
                         constraint pkcidade primary key(id)
                        );

            CREATE TABLE cliente (id serial ,
                         id_cidade int,
                         razao_social varchar(150) not null,
                         tipo_cadastro CHAR(1) not null,
                         cpf_cnpj varchar(18) unique,
                         endereco varchar(150),
                         telefone varchar(15),
                         ativo char(1) not null,
                         constraint pkcliente primary key (id),
                         constraint fkid_cidadepessoa foreign key (id_cidade)
                         REFERENCES cidade
                         );

            CREATE TABLE fornecedor (id serial ,
                         id_cidade int,
                         razao_social varchar(150) not null,
                         tipo_cadastro CHAR(1) not null,
                         cpf_cnpj varchar(18) unique,
                         endereco varchar(150),
                         telefone varchar(15),
                         ativo char(1) not null,
                         constraint pkfornecedor primary key (id),
                         constraint fkid_cidadepessoa foreign key (id_cidade)
                         REFERENCES cidade
                         );

            CREATE TABLE empresa (id serial ,
                          id_cidade int not null,
                          razao_social varchar(150) not null,
                          cnpj varchar(18) not null,
                          inscricao_estadual varchar(20),
                          endereco varchar(150),
                          telefone varchar(15),
                          ativo CHAR(1) not null,
                          constraint pkpessoa primary key (id),
                          constraint fkid_cidadepessoa foreign key (id_cidade)
                          REFERENCES cidade
                          );

            CREATE TABLE forma_pagamento( id serial not null,
                         descricao VARCHAR(100) not null,
                         forma_avista CHAR(1) NOT NULL,
                         ativo CHAR(1) not null,
                         constraint pkforma_pagamento primary key (id)
                         );
                    
                     
            CREATE TABLE mercadoria(id serial ,
                         referencia VARCHAR(45) not null unique,
                         descricao VARCHAR(100) not null,
                         estoque DOUBLE PRECISION not null,
                         preco_custo DOUBLE PRECISION not null,
                         preco_venda DOUBLE PRECISION not null,
                         ativo char(1) not null,
                         CONSTRAINT pkmercadoria primary key (id)                       
                         );              

            CREATE TABLE faturamento(id serial,
                         id_cliente int not null,                         
                         id_empresa int not null,
                         data_emissao timestamp not null,
                         fase char(1) not null,
                         desconto DOUBLE PRECISION,
                         valor_total DOUBLE PRECISION not null,		
                         valor_total_liquido double PRECISION not null,
                         constraint pkfaturamento primary key (id),
                         CONSTRAINT fkid_clientefaturamento foreign key(id_cliente)
                         references cliente,
                         constraint fkid_empresafaturamento foreign key(id_empresa)
                         references empresa
                         );
                                                  
            CREATE TABLE faturamento_item(id serial,
                         id_faturamento int not null,
                         id_mercadoria int not null,
                         quantidade DOUBLE PRECISION not null,
                         valor_unitario DOUBLE PRECISION not null,
                         desconto DOUBLE PRECISION,
                         valor_total DOUBLE PRECISION not null,
                         constraint pkfaturamento_item primary key (id),
                         constraint fkid_mercadoriafaturamento foreign key (id_mercadoria)
                         references mercadoria,
                         constraint fkid_faturamentofaturamento foreign key (id_faturamento)
                         references faturamento
                         );


            CREATE TABLE financeiro_receber(id serial,
                         id_faturamento int not null,
                         id_cliente int not null,
                         id_forma_pagamento int not null,
                         numero_titulo varchar(10) not null,
                         data_emissao timestamp not null,
                         data_vencimento date not null,
                         data_pagamento date,
                         --valor_provisorio DOUBLE PRECISION,
                         valor_titulo DOUBLE PRECISION,
                         valor_pago DOUBLE PRECISION,
                         quitado char(1),
                         --recebido char(1) not null,
                         ativo char(1) not null,
                         constraint pkfinanceiro primary key (id),
          		 constraint fkid_faturamentofaturamento foreign key(id_faturamento)
                         references faturamento,
                         constraint fkid_clientecliente foreign key(id_cliente)
                         references cliente,
                         constraint fkid_forma_pagamentoforma_pagamento foreign key(id_forma_pagamento)
                         references forma_pagamento
                         );


            CREATE TABLE financeiro_pagar(id serial,                         
                         id_fornecedor int not null,
                         id_forma_pagamento int not null,
                         numero_titulo varchar(10) not null,
                         data_emissao timestamp not null,
                         data_vencimento date not null,
                         data_pagamento date,
                         valor_provisorio DOUBLE PRECISION,
                         valor_titulo DOUBLE PRECISION,
                         valor_pago DOUBLE PRECISION,
                         quitado char(1),
                         --recebido char(1) not null,
                         ativo char(1) not null,
                         constraint pkfinanceiropagar primary key (id),          		 
                         constraint fkid_fornecedorfornecedor foreign key(id_fornecedor)
                         references fornecedor,
                         constraint fkid_forma_pagamentoforma_pagamento foreign key(id_forma_pagamento)
                         references forma_pagamento
                         );


---------------insert-------------------

insert into usuario values(default,'usuario','e10adc3949ba59abbe56e057f20f883e');
--senha 123456

INSERT INTO public.cidade(
	id, descricao, ativo)
	VALUES (default, 'Lajeado', 'T');

INSERT INTO public.cidade(
	id, descricao, ativo)
	VALUES (default, 'Porto Alegre', 'T');


INSERT INTO public.cliente(
	id, id_cidade, razao_social, tipo_cadastro, cpf_cnpj, endereco, telefone, ativo)
	VALUES (default, null, 'Cliente Balcão', 'F', '', '','', 'T');

INSERT INTO public.cliente(
	id, id_cidade, razao_social, tipo_cadastro, cpf_cnpj, endereco, telefone, ativo)
	VALUES (default, 2, 'Gabriel da Silva', 'F', '89682357080', 'Rua João Cardoso,23','5199899079', 'T');  

INSERT INTO public.cliente(
	id, id_cidade, razao_social, tipo_cadastro, cpf_cnpj, endereco, telefone, ativo)
	VALUES (default, 1, 'Jorge Alves', 'F', '27156648268', 'Av. Castelo Branco','5199876379', 'T');  



INSERT INTO public.empresa(
	id, id_cidade, razao_social, cnpj, inscricao_estadual, endereco, telefone, ativo)
	VALUES (1, 1, 'Atacado de Doces LTDA', '77157937000108', '2058042772', 'Rua Alberto Silva, 222', '5199898998', 'T');


INSERT INTO public.mercadoria(
            id, referencia, descricao, estoque, preco_custo, preco_venda,ativo)
    VALUES (default, '01', 'Mercadoria 01', 10, 5, 10, 'T');

INSERT INTO public.mercadoria(
            id, referencia, descricao, estoque, preco_custo, preco_venda,ativo)
    VALUES (default, '02', 'Mercadoria 02', 10, 10, 20, 'T');

INSERT INTO public.mercadoria(
            id, referencia, descricao, estoque, preco_custo, preco_venda,ativo)
    VALUES (default, '03', 'Mercadoria 03', 10, 15, 20, 'T');

INSERT INTO public.mercadoria(
            id, referencia, descricao, estoque, preco_custo, preco_venda,ativo)
    VALUES (default, '04', 'Mercadoria 04', 10, 50, 100, 'T');

INSERT INTO public.mercadoria(
            id, referencia, descricao, estoque, preco_custo, preco_venda,ativo)
    VALUES (default, '05', 'Mercadoria 05', 10, 55, 90, 'T');


INSERT INTO public.fornecedor(
            id, id_cidade, razao_social, tipo_cadastro, cpf_cnpj, endereco, telefone, ativo)
    VALUES (default, 1, 'Fornecedor 1', 'J', '43756326000156', 'Rua do cascalho', '5198679817', 'T');

INSERT INTO public.fornecedor(
            id, id_cidade, razao_social, tipo_cadastro, cpf_cnpj, endereco, telefone, ativo)
    VALUES (default, 1, 'Fornecedor 2', 'J', '76548845000188', 'Rua da pedra', '5198768796', 'T');

INSERT INTO public.fornecedor(
            id, id_cidade, razao_social, tipo_cadastro, cpf_cnpj, endereco, telefone, ativo)
    VALUES (default, 1, 'Fornecedor 3', 'J', '36838313000160', 'Rua XYZ', '5198761524', 'T');

INSERT INTO public.forma_pagamento(
            id, descricao, forma_avista, ativo)
    VALUES (default, 'Dinheiro', 'T', 'T');

INSERT INTO public.forma_pagamento(
            id, descricao, forma_avista, ativo)
    VALUES (default, 'Cartão', 'T', 'T');

INSERT INTO public.forma_pagamento(
            id, descricao, forma_avista, ativo)
    VALUES (default, 'Boleto', 'F', 'T');






--------procedure e triggers---------------



--CREATE OR REPLACE FUNCTION fatuaizaestoque()
--RETURNS trigger
--AS $$
 
--BEGIN

--	if (NEW.fase = 'e') then
--for 
 --   update mercadoria m set estoque = (estoque - f.quantidade  )
 --   from faturamento_item f, faturamento fa
   -- where m.id = f.id_mercadoria and f.id_faturamento = fa.id and fa.id = new.id;

    
 --end if;
  --  if (NEW.fase = 'c') then
  --update mercadoria m set estoque = (estoque + f.quantidade)
  --  from faturamento_item f, faturamento fa
    --where m.id = f.id_mercadoria and f.id_faturamento = fa.id and fa.id = new.id;
--end if;
--RETURN null;
--END;
--$$ LANGUAGE plpgsql;


------------------
--CREATE  TRIGGER tatuaizaestoque
--AFTER  UPDATE ON faturamento
--FOR EACH ROW
--EXECUTE PROCEDURE fatuaizaestoque()


----nova proc-----

CREATE OR REPLACE FUNCTION fatuaizaestoque()
RETURNS trigger
AS $$
 declare
idmerc integer;
qtde DOUBLE PRECISION;
BEGIN

    if (NEW.fase = 'e') then

        for idmerc,qtde in select m.id, sum(f.quantidade)  qtde 
        from faturamento_item f,mercadoria m 
        where f.id_Faturamento = (select max(fa.id) 
        from faturamento fa) 
        and f.id_mercadoria = m.id 
        group by f.id_mercadoria,m.id

	
   loop
    update mercadoria m set estoque = (estoque - qtde  )
    from faturamento_item f, faturamento fa
    where m.id = idmerc and f.id_faturamento = fa.id and fa.id = new.id;

   end loop;

 end if;
    if (NEW.fase = 'c') then
 for idmerc,qtde in select m.id, sum(f.quantidade)  qtde 
        from faturamento_item f,mercadoria m 
        where f.id_Faturamento = old.id
        -- (select max(fa.id) 
         -- from faturamento fa) 
        and f.id_mercadoria = m.id 
        group by f.id_mercadoria,m.id

   loop
    update mercadoria m set estoque = (estoque + qtde  )
    from faturamento_item f, faturamento fa
    where m.id = idmerc and f.id_faturamento = fa.id and fa.id = new.id;

   end loop;
end if;
RETURN null;
END;
$$ LANGUAGE plpgsql;


------------------
CREATE  TRIGGER tatuaizaestoque
AFTER  UPDATE ON faturamento
FOR EACH ROW
EXECUTE PROCEDURE fatuaizaestoque()
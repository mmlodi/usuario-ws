/*criar database das pelo dbeaver e abrir script sql para criar essas tabelas nele*/

CREATE TABLE cliente (
    id serial NOT NULL,
    cpf varchar(11),
    nome varchar(30) NULL,
    sobrenome varchar(50) NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

CREATE TABLE produto (
    id serial NOT NULL,
    descricao varchar(45) NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
);

CREATE TABLE pedido (
    id SERIAL NOT NULL,
    "data" date NULL,
    id_cliente int4 NULL,
    CONSTRAINT pedido_pkey PRIMARY KEY (id),
    CONSTRAINT pedido_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id)
);

CREATE TABLE item_do_pedido (
    id_pedido int4 NOT NULL,
    id_produto int4 NULL,
    quantidade int4 NULL,
    CONSTRAINT item_do_pedido_id_pedido_fkey FOREIGN KEY (id_pedido) REFERENCES public.pedido(id),
    CONSTRAINT item_do_pedido_id_produto_fkey FOREIGN KEY (id_produto) REFERENCES public.produto(id)
);

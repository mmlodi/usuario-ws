CREATE TABLE public.cliente (
    id int4 NOT NULL,
    nome varchar(30) NULL,
    sobrenome varchar(50) NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);


-- public.produto definition

-- Drop table

-- DROP TABLE public.produto;

CREATE TABLE public.produto (
    id int4 NOT NULL,
    descricao varchar(45) NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
);


-- public.pedido definition

-- Drop table

-- DROP TABLE public.pedido;

CREATE TABLE public.pedido (
    id SERIAL NOT NULL,
    "data" date NULL,
    id_cliente int4 NULL,
    CONSTRAINT pedido_pkey PRIMARY KEY (id),
    CONSTRAINT pedido_id_cliente_fkey FOREIGN KEY (id_cliente) REFERENCES public.cliente(id)
);


-- public.item_do_pedido definition

-- Drop table

-- DROP TABLE public.item_do_pedido;

CREATE TABLE public.item_do_pedido (
    id_pedido int4 NOT NULL,
    id_produto int4 NULL,
    quantidade int4 NULL,
    CONSTRAINT item_do_pedido_pkey PRIMARY KEY (id_pedido),
    CONSTRAINT item_do_pedido_id_pedido_fkey FOREIGN KEY (id_pedido) REFERENCES public.pedido(id),
    CONSTRAINT item_do_pedido_id_produto_fkey FOREIGN KEY (id_produto) REFERENCES public.produto(id)
);

alter table cliente add column cpf varchar(11);

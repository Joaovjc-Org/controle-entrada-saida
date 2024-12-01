CREATE TABLE CLIENTE
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CPF           VARCHAR(255)                            NOT NULL,
    NOME          VARCHAR(255)                            NOT NULL,
    EMAIL         VARCHAR(255)                            NOT NULL,
    TELEFONE      VARCHAR(255)                            NOT NULL,
    DATA_CADASTRO TIMESTAMP                               NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id)
);

CREATE TABLE COMANDA
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CODIGO_COMANDA VARCHAR(255)                            NOT NULL,
    DATA_CRIACAO   TIMESTAMP                               NOT NULL,
    DATA_PAGAMENTO TIMESTAMP                               NOT NULL,
    cliente_id     BIGINT,
    folha_id       BIGINT,
    CONSTRAINT pk_comanda PRIMARY KEY (id)
);

CREATE TABLE FOLHA
(
    id              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    DATA_ABERTURA   date                                    NOT NULL,
    DATA_FECHAMENTO date                                    NOT NULL,
    FECHADO         BOOLEAN,
    CONSTRAINT pk_folha PRIMARY KEY (id)
);

CREATE TABLE GASTO
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CONSTRAINT pk_gasto PRIMARY KEY (id)
);

CREATE TABLE GASTO_itemGasto
(
    Gasto_id     BIGINT NOT NULL,
    itemGasto_id BIGINT NOT NULL
);

CREATE TABLE ITEM
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    NOME  VARCHAR(255)                            NOT NULL,
    PRECO DECIMAL                                 NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (id)
);

ALTER TABLE CLIENTE
    ADD CONSTRAINT uc_cliente_cpf UNIQUE (CPF);

ALTER TABLE COMANDA
    ADD CONSTRAINT uc_comanda_codigo_comanda UNIQUE (CODIGO_COMANDA);

ALTER TABLE COMANDA
    ADD CONSTRAINT FK_COMANDA_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES CLIENTE (id);

ALTER TABLE COMANDA
    ADD CONSTRAINT FK_COMANDA_ON_FOLHA FOREIGN KEY (folha_id) REFERENCES FOLHA (id);

ALTER TABLE GASTO_itemGasto
    ADD CONSTRAINT fk_gasite_on_gasto FOREIGN KEY (Gasto_id) REFERENCES GASTO (id);

ALTER TABLE GASTO_itemGasto
    ADD CONSTRAINT fk_gasite_on_item FOREIGN KEY (itemGasto_id) REFERENCES ITEM (id);
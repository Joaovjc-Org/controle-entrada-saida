CREATE TABLE CLIENTE
(
    ID            BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CPF           VARCHAR(255)                            NOT NULL,
    NOME          VARCHAR(255)                            NOT NULL,
    EMAIL         VARCHAR(255)                            NOT NULL,
    TELEFONE      VARCHAR(255)                            NOT NULL,
    DATA_CADASTRO TIMESTAMP                               NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (ID)
);

ALTER TABLE CLIENTE
    ADD CONSTRAINT uc_cliente_cpf UNIQUE (CPF);

CREATE TABLE COMANDA
(
    ID             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CODIGO_COMANDA VARCHAR(255)                            NOT NULL,
    DATA_CRIACAO   TIMESTAMP                               NOT NULL,
    DATA_PAGAMENTO TIMESTAMP                               NOT NULL,
    CLIENTE_ID     BIGINT,
    FOLHA_ID       BIGINT,
    CONSTRAINT pk_comanda PRIMARY KEY (ID)
);

ALTER TABLE COMANDA
    ADD CONSTRAINT uc_comanda_codigo_comanda UNIQUE (CODIGO_COMANDA);

ALTER TABLE COMANDA
    ADD CONSTRAINT FK_COMANDA_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES CLIENTE (ID);

CREATE TABLE FOLHA
(
    ID              BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    DATA_ABERTURA   date                                    NOT NULL,
    DATA_FECHAMENTO date                                    NOT NULL,
    FECHADO         BOOLEAN,
    CONSTRAINT pk_folha PRIMARY KEY (ID)
);

ALTER TABLE COMANDA
    ADD CONSTRAINT FK_COMANDA_ON_FOLHA FOREIGN KEY (folha_id) REFERENCES FOLHA (ID);

CREATE TABLE GASTO
(
    id BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    CONSTRAINT pk_gasto PRIMARY KEY (ID)
);

CREATE TABLE ITEM
(
    ID    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    NOME  VARCHAR(255)                            NOT NULL,
    PRECO DECIMAL                                 NOT NULL,
    CONSTRAINT pk_item PRIMARY KEY (ID)
);

CREATE TABLE GASTO_ITEM
(
    GASTO_ID     BIGINT NOT NULL,
    ITEM_ID BIGINT NOT NULL
);

ALTER TABLE GASTO_ITEM
    ADD CONSTRAINT fk_gasite_on_gasto FOREIGN KEY (GASTO_ID) REFERENCES GASTO (ID);

ALTER TABLE GASTO_ITEM
    ADD CONSTRAINT fk_gasite_on_item FOREIGN KEY (ITEM_ID) REFERENCES ITEM (ID);
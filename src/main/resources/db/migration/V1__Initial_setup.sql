CREATE TABLE symbol
(
    id bigserial NOT NULL,
    symbol varchar(50) NOT NULL,
    last_update timestamp,
    PRIMARY KEY (id),
    UNIQUE (symbol)
);

CREATE INDEX symbol_symbol_idx ON symbol(symbol);

CREATE TABLE IF NOT EXISTS public.k_lines
(
    id bigserial NOT NULL,
    symbol_id bigint NOT NULL,
    open_time timestamp without time zone NOT NULL,
    open double precision NOT NULL,
    high double precision NOT NULL,
    low double precision NOT NULL,
    close double precision NOT NULL,
    volume double precision NOT NULL,
    close_time timestamp NOT NULL,
    quote_asset_volume double precision NOT NULL,
    number_of_trades bigint NOT NULL,
    taker_buy_base_asset_volume double precision NOT NULL,
    taker_buy_quote_asset_volume double precision NOT NULL,
    can_be_ignored boolean NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_k_lines_symbol_symbol_id FOREIGN KEY (symbol_id) REFERENCES symbol(id)
);

CREATE INDEX k_lines_symbol_id_idx ON k_lines(symbol_id);
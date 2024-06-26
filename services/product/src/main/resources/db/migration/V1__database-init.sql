CREATE TABLE IF NOT EXISTS category
(
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description varchar(255)
);
CREATE TABLE IF NOT EXISTS product
(
    id INTEGER NOT NULL PRIMARY KEY,
    name VARCHAR(255),
    description varchar(255),
    availible_quantity DOUBLE PRECISION NOT NULL,
    price NUMERIC(20,2),
    category_id INTEGER CONSTRAINT fk_product_category references category
);
CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;
CREATE TABLE t_inventory (
    id BIGINT NOT NULL AUTO_INCREMENT,
    sku_code VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_sku_code (sku_code)
);
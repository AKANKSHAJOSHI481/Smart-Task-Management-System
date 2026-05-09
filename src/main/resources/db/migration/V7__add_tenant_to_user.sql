ALTER TABLE tenant_1.users
    ADD COLUMN tenant_id VARCHAR(255);

ALTER TABLE tenant_2.users
    ADD COLUMN tenant_id VARCHAR(255);
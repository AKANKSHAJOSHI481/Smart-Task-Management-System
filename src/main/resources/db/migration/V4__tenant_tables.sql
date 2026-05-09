CREATE TABLE IF NOT EXISTS tenant_1.users (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS tenant_1.tasks (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    title VARCHAR(255),
    description TEXT,
    status VARCHAR(50),
    priority VARCHAR(50),
    due_date DATE,
    assigned_to VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tenant_2.users (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    name VARCHAR(255),
    email VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(50)
);

CREATE TABLE IF NOT EXISTS tenant_2.tasks (
    id VARCHAR(255) PRIMARY KEY,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,

    title VARCHAR(255),
    description TEXT,
    status VARCHAR(50),
    priority VARCHAR(50),
    due_date DATE,
    assigned_to VARCHAR(255)
);
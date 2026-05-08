CREATE TABLE IF NOT EXISTS tasks (
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
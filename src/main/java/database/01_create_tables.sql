CREATE TABLE IF NOT EXISTS transactions (
    transaction_id VARCHAR(50) PRIMARY KEY,
    account_number VARCHAR(50) NOT NULL,
    transaction_type VARCHAR(30) NOT NULL,
    amount NUMERIC(15, 2) NOT NULL,
    balance_after_transaction NUMERIC(15, 2) NOT NULL,
    transaction_date_time TIMESTAMP NOT NULL
);
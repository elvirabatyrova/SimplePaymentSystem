CREATE TABLE customer (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  second_name VARCHAR(255) NOT NULL,
  date_of_birth DATE NOT NULL,
  address VARCHAR(255) NOT NULL
);

CREATE TABLE biller (
  id SERIAL PRIMARY KEY,
  company_name VARCHAR(255) NOT NULL
);

CREATE TABLE payment (
  id SERIAL PRIMARY KEY,
  customer_id INTEGER NOT NULL,
  biller_id INTEGER NOT NULL,
  account BIGINT,
  amount DECIMAL(10,2) NOT NULL,
  payment_date DATE,

  FOREIGN KEY (customer_id) REFERENCES customer(id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (biller_id) REFERENCES biller(id) ON UPDATE CASCADE ON DELETE CASCADE
);
INSERT INTO customer(first_name, second_name, date_of_birth, address)
    VALUES ('Elvira', 'Batyrova', '1996-05-24', 'Kazan');
INSERT INTO customer(first_name, second_name, date_of_birth, address)
    VALUES ('Sergey', 'Ostapenko', '1994-12-20', 'Kazan');
INSERT INTO customer(first_name, second_name, date_of_birth, address)
    VALUES ('Alexey', 'Ivanov', '1986-12-10', 'Moscow');
INSERT INTO customer(first_name, second_name, date_of_birth, address)
    VALUES ('Ekaterina', 'Melnikova', '1996-04-11', 'Moscow');

INSERT INTO biller(company_name) VALUES ('Company 1');
INSERT INTO biller(company_name) VALUES ('Company 2');
INSERT INTO biller(company_name) VALUES ('Company 3');

INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (1, 1, 34561234198, 2000.00, '2016-07-26');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (2, 1, 34419561238, 2400.50, '2016-07-25');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (4, 1, 41345612398, 78000.00, '2016-07-24');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (2, 2, 61234134598, 123.88, '2016-07-26');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (3, 2, 34198345612, 200.00, '2016-07-21');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (4, 2, 31234198456, 20.00, '2016-06-30');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (1, 3, 23419834561, 3450.00, '2016-06-26');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (4, 3, 31234564198, 1999.99, '2016-07-01');
INSERT INTO payment(customer_id, biller_id, account, amount, payment_date)
    VALUES (2, 3, 61234193458, 12.98, '2016-07-09');
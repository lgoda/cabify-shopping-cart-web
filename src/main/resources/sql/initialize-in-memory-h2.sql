-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (1, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'user@mail.com', 'user', 'Name', 'Surname',
   1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES
  (2, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'johndoe@gmail.com', 'johndoe', 'John', 'Doe', 1);
-- password in plaintext: "password"
INSERT INTO USER (user_id, password, email, username, name, last_name, active)
VALUES (3, '$2a$06$OAPObzhRdRXBCbk7Hj/ot.jY3zPwR8n7/mfLtKIgTzdJa4.6TwsIm', 'name@gmail.com', 'namesurname', 'Name',
        'Surname', 1);

INSERT INTO ROLE (role_id, role)
VALUES (1, 'ROLE_ADMIN');
INSERT INTO ROLE (role_id, role)
VALUES (2, 'ROLE_USER');

INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 1);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (1, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (2, 2);
INSERT INTO USER_ROLE (user_id, role_id)
VALUES (3, 2);


INSERT INTO PRODUCT (name, description, quantity, price, price_policy, code)
VALUES ('Cabify Voucher', 'Cabify Voucher', 100, 5.00, 'TWO_FOR_ONE_PRICE_POLICY', 'VOUCHER');
INSERT INTO PRODUCT (name, description, quantity, price, price_policy, code, price_policy_data)
VALUES ('Cabify T-Shirt', 'Cabify T-Shirt', 500, 20.00, 'DEGRESSIVE_PRICE_POLICY', 'TSHIRT', '{"thresholdQuantity":"3", "reducedUnitPrice":"19"}');
INSERT INTO PRODUCT (name, description, quantity, price, price_policy, code )
VALUES ('Cafify Coffee Mug ', 'Cafify Coffee Mug ', 500, 7.50, 'REGULAR_PRICE_POLICY', 'MUG');


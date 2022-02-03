INSERT INTO users (username, first_name, last_name, street, house_number, postal_code, city,phone_number, password, enabled, role)
VALUES
('plannerusername', 'piet', 'pieterson', 'steenstraat', '33', '8909ie', 'arnhem', '0689493832','$2a$12$5usMMaD9hathHXMKNMjlseunXe.QEQbRBtFiBycc.V/teqa0c4v6K', true, 'planner'),
('customerusername', 'jan', 'jansen', 'kalverstraat', '22', '1001ab', 'Amsterdam', '010-894839', '$2a$12$5usMMaD9hathHXMKNMjlseunXe.QEQbRBtFiBycc.V/teqa0c4v6K', true, 'customer'),
('driverusername', 'Mark', 'Rensen', 'Doesburgseweg', '26', '7031jd', 'Wehl', '0612334566', '$2a$12$5usMMaD9hathHXMKNMjlseunXe.QEQbRBtFiBycc.V/teqa0c4v6K', true, 'driver'),
('driverusername2', 'jantje', 'jansen', 'straatweg', '26','1001hd', 'amsterdam','0612334566', '$2a$12$5usMMaD9hathHXMKNMjlseunXe.QEQbRBtFiBycc.V/teqa0c4v6K', true, 'driver');


INSERT INTO authorities (username, authority)
VALUES
('customerusername', 'ROLE_CUSTOMER'),
('plannerusername', 'ROLE_PLANNER'),
('driverusername', 'ROLE_DRIVER');
INSERT INTO customers (id, name, user_username)
VALUES
(1001, 'jansen', 'customerusername');

INSERT INTO drivers (user_username, id,  employee_number, driver_license_number,  regular_truck)
VALUES
('driverusername', 2001, 1000001, 'xxx111xxx',  '97bph8'),
('driverusername2', 2005,  1000000, 'xxx222xxx',  'brvt22');

INSERT INTO planners (id, user_username )
VALUES
(4001, 'plannerusername');

INSERT INTO routes (id, truck, planner_id)
VALUES
(5001, '97bph8', 4001),
(5003, 'brvt33', 4001),
(5002, '97bph8', 4001);

INSERT INTO orders (id, loading_street, loading_house_number, loading_postal, loading_name, loading_city, loading_date, delivery_street, delivery_house_number, delivery_postal, delivery_name, delivery_city, delivery_date, creator_id, route_id, type, order_status, is_pickup, description)
VALUES
(3001, 'edisonstraat', '39', '7002xs', 'Brutra', 'Doetinchem', '15-02-2022', 'zuivelweg', '55', '8004dv', 'jansen', 'almere', '16-02-2022', 1001, 5001, 'EURO', 'PROCESSING', true, 'super long intelligent description telling the user what the exact contents of this delivery are and telling the driver how to properly handle it even though the description can actually not be longer that 30 characters.'),
(3003, 'voorweg', '44', '8888hg',       'Pfizer', 'Hamburg', '07-08-2022', 'varsseveldseweg', '55', '5345hh','dinges', 'terborg','04-04-2023', 1001, 5001, 'OTHER', 'PROCESSING', false, 'corona vaccin'),
(3002, 'wasstraat', '5', '7062xs', 'fabriek', 'Didam','03-03-2022', 'achterweg', '65', '6006it', 'hendriksen', 'houten','06-03-2022', 1001, 5001, 'BLOCK', 'IN_TRANSPORT', false, 'stuff');


UPDATE routes SET driver_id=2001 WHERE id=5001;
UPDATE routes SET driver_id=2001 WHERE id=5002;

INSERT INTO pallets (dtype, id, height, length, load, weight, width, type )
VALUES
('EuroPallet', 6001, 100, 120, 'stuff', 1000, 80, 0),
('EuroPallet', 6003, 50, 120, 'paper', 300, 80, 0),
('OtherPallet', 6004, 40, 120, 'oil', 500, 80, 2),
('BlockPallet', 6002, 100, 120, 'beer', 1000, 100, 1);
--type 0 = euro, 1 = block, 2 = other, 3 = none

INSERT INTO orders_pallets (order_id, pallets_id)
VALUES
(3001, 6002),
(3001, 6003),
(3001, 6004),
(3001, 6001);

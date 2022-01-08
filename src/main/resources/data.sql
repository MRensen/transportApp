INSERT INTO authorities (username, authority)
VALUES
('customerusername', 'customer'),
('plannerusername', 'planner'),
('driverusername', 'driver');
INSERT INTO customers (id, username, name, street, house_number, postal_code, city, phone_number, password, enabled)
VALUES
(1001, 'customerusername', 'jansen', 'kalverstraat', '22', '1001ab', 'Amsterdam', '010-894839', 'password', true);

INSERT INTO drivers (id, username,  first_name, last_name, street, house_number, city, employee_number, driver_license_number, phone_number, regular_truck, password, enabled )
VALUES
(2005, 'driverusername2', 'jantje', 'jansen', 'straatweg', '26', 'amsterdam', 1000000, 'xxx111xxx', '0612334566', '97bph8', 'password', true);


INSERT INTO planners (id, username, first_name, password, enabled)
VALUES
(4001, 'plannerusername', 'piet', 'password', true);

INSERT INTO routes (id, truck, planner_username)
VALUES
(5001, '97bph8', 'plannerusername'),
(5002, '97bph8', 'plannerusername');

INSERT INTO orders (id, loading_street, loading_house_number, loading_postal, loading_name, loading_city, loading_date, delivery_street, delivery_house_number, delivery_postal, delivery_name, delivery_city, delivery_date, creator_username, route_id, type, order_status, is_pickup, description)
VALUES
(3001, 'edisonstraat', '39', '7002xs', 'Brutra', 'Doetinchem', '15-02-2022', 'zuivelweg', '55', '8004dv', 'jansen', 'almere', '16-02-2022', 'customerusername', 5001, 'EURO', 'PROCESSING', true, 'whatever'),
(3003, 'voorweg', '44', '8888hg',       'Pfizer', 'Hamburg', '07-08-2022', 'varsseveldseweg', '55', '5345hh','dinges', 'terborg','04-04-2023', 'customerusername', 5001, 'OTHER', 'DELIVERED', false, 'testing how far it goes'),
(3002, 'wasstraat', '5', '7062xs', 'fabriek', 'Didam','03-03-2022', 'achterweg', '65', '6006it', 'hendriksen', 'houten','06-03-2022', 'customerusername', 5001, 'BLOCK', 'PROCESSING', false, 'get your ass over here or else');


INSERT INTO drivers (id, username,  first_name, last_name, street, house_number, city, employee_number, driver_license_number, phone_number, regular_truck, password, enabled)
VALUES
(2001, 'driverusername', 'Mark', 'Rensen', 'Doesburgseweg', '26', 'Wehl', 1000000, 'xxx111xxx', '0612334566', '97bph8', 'password', true);

UPDATE routes SET driver_username='driverusername' WHERE id=5001;
UPDATE routes SET driver_username='driverusername' WHERE id=5002;

INSERT INTO pallets (dtype, id, height, length, load, weight, width, type )
VALUES
('EuroPallet', 6001, 100, 120, 'stuff', 1000, 80, 0),
('EuroPallet', 6003, 50, 120, 'paper', 300, 80, 0),
('OtherPallet', 6004, 40, 120, 'oil', 500, 80, 0),
('BlockPallet', 6002, 100, 120, 'beer', 1000, 100, 1);
--type 0 = euro, 1 = block, 2 = other, 3 = none
INSERT INTO orders_pallets (order_id, pallets_id)
VALUES
(3001, 6002),
(3001, 6003),
(3001, 6004),
(3001, 6001);

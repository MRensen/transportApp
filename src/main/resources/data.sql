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
(2001, 'driverusername', 'Mark', 'Rensen', 'Doesburgseweg', '26', 'Wehl', 1000000, 'xxx111xxx', '0612334566', '97bph8', 'password', true);

INSERT INTO orders (id, loading_street, loading_house_number, loading_postal, loading_name, loading_city, delivery_street, delivery_house_number, delivery_postal, delivery_name, delivery_city, creator_username)
VALUES
(3001, 'edisonstraat', '39', '7002xs', 'Brutra', 'Doetinchem', 'zuivelweg', '55', '8004dv', 'jansen', 'almere', 'customerusername');

INSERT INTO planners (id, username, first_name, password, enabled)
VALUES
(4001, 'plannerusername', 'piet', 'password', true);

INSERT INTO routes (id, truck, driver_username, planner_username)
VALUES
(5001, '97bph8', 'driverusername', 'plannerusername');
INSERT INTO customers (id, name, street, house_number, postal_code, city, phone_number) VALUES (1001, 'jansen', 'kalverstraat', '22', '1001ab', 'Amsterdam', '010-894839');

INSERT INTO drivers (id, first_name, last_name, street, house_number, city, employee_number, driver_license_number, phone_number, regular_truck ) VALUES (2001, 'Mark', 'Rensen', 'Doesburgseweg', '26', 'Wehl', 1000000, 'xxx111xxx', '0612334566', '97bph8');

INSERT INTO orders (id, loading_street, loading_house_number, loading_postal, loading_name, loading_city, delivery_street, delivery_house_number, delivery_postal, delivery_name, delivery_city, creator_id) VALUES (3001, 'edisonstraat', '39', '7002xs', 'Brutra', 'Doetinchem', 'zuivelweg', '55', '8004dv', 'jansen', 'almere', 1001);

INSERT INTO planners (id, name) VALUES (4001, 'piet');

INSERT INTO routes (id, truck, driver_id, planner_id) VALUES (5001, '97bph8', 2001, 4001);
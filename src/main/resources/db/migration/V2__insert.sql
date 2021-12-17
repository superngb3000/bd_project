INSERT INTO roles (name) VALUES
                             ('ROLE_ADMIN'),
                             ('ROLE_MANAGER'),
                             ('ROLE_CLIENT');

INSERT INTO persons (second_name, first_name, middle_name, email, phone_number) VALUES
                                                                                    ('Буланов','Владимир','Алексеевич','bul.vla@gmail.com','+78005353535'),
                                                                                    ('Буланов','Максим','Алексеевич','ako.pamaks@gmail.com','+78005353535'),
                                                                                    ('Буланов','Егор','Алексеевич','param.egor@gmail.com','+78005353535');

insert into weapons (cadastral_number, name, producer, technical_specifications, price) values
                                                                                            ('1.3.2/270','Ружье охотничье двуствольное гладкоствольное калибра 12/70 модели ЛТ5000','Россия, ООО \"Оружейная фирма \"Левша-Т\"','калибр - 12; длина стволов - 750 мм; масса с древесиной средней плотности - 3,3-3,4 кг; усилие на спусковом крючке - 1,75-2 кг', 50000.0),
                                                                                            ('1.3.2/291','Ружье охотничье гладкоствольное длинноствольное самозарядное \"ВПО-209\" калибра .366 ТКМ','ООО МОЛОТ-ОРУЖИЕ','Калибр - 366 ТКМ; длина оружия - 883 мм; длина ствола - 515 мм; ширина оружия -72 мм; высота оружия -195 мм; масса карабина - 4 кг; емкость магазина - 10 патронов', 20000.0),
                                                                                            ('2.2.1/393','Пистолет спортивный модели M57 калибра 7,62 x 25 Tokarev','Сербия, Zastava','калибр - 7,62 x 25 Tokarev; емкость магазина - 9 патронов; габаритные размеры - 200 х 130 х 29 мм; длина ствола - 116 мм; масса без патронов - 0,9 кг', 30000.0);

insert into users (person_id, username, password) values
                                                      (1, 'admin', '$2a$10$TxWw/jbXHMdOX6IrRnUKWeP.nnSA1YveA6WdXHhWu0OsLXBkSq94C'),
                                                      (2, 'manager', '$2a$10$tJhRNSFiZWlG7jgc4gsjZucWQdrvf8L7Q14SD5S9BYTLIGecZADyS'),
                                                      (3, 'client', '$2a$10$mxx7b/DUWi6Vz43pVRxHMu0Zd.PF4jiKsm7a5pCp8ltrH49f/MYlW');

insert into users_roles values
                            (1, 1),
                            (1, 2),
                            (1, 3),
                            (2, 2),
                            (2, 3),
                            (3, 3);

insert into managers (person_id, license, employment_contract) values
                                                                   (1, '11111111', '111111111'),
                                                                   (2, '22222222', '22222222');

insert into clients (person_id, license) values
                                             (1, '01111111'),
                                             (2, '02222222'),
                                             (3, '03333333');

insert into orders (client_id, manager_id, price, date) values
    (3, 2, 50000.0, now());

insert into orders_weapons values
                               (1, 2),
                               (1, 3);

insert into orders (client_id, manager_id, price, date) values
    (3, 1, 100000.0, now());

insert into orders_weapons values
                               (2, 1),
                               (2, 2),
                               (2, 3);

insert into orders (client_id, manager_id, price, date) values
    (2, 1, 150000.0, now());

insert into orders_weapons values
                               (3, 1),
                               (3, 1),
                               (3, 1);

insert into orders (client_id, manager_id, price, date) values
    (1, 1, 200000.0, now());

insert into orders_weapons values
                               (4, 1),
                               (4, 2),
                               (4, 3),
                               (4, 2),
                               (4, 2),
                               (4, 3),
                               (4, 3);
create table roles
(
    id int auto_increment,
    name varchar(20) not null,
    PRIMARY KEY (id)
);

create table weapons
(
    id int auto_increment,
    cadastral_number varchar(20) not null,
    name text not null,
    producer text not null,
    technical_specifications text not null,
    price float not null,
    PRIMARY KEY (id)
);

create table persons
(
    id int auto_increment,
    second_name varchar(20) not null,
    first_name varchar(20) not null,
    middle_name varchar(20) not null,
    email varchar(20) not null,
    phone_number varchar(20) not null,
    PRIMARY KEY (id)
);

create table clients
(
    id int auto_increment,
    person_id int not null,
    license varchar(20) not null,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES persons (id)
);

create table managers
(
    id int auto_increment,
    person_id int not null,
    license varchar(20) not null,
    employment_contract varchar(20) not null,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES persons (id)
);

create table users
(
    id int auto_increment,
    person_id int not null,
    username varchar(20) not null,
    password varchar(100) not null,
    PRIMARY KEY (id),
    FOREIGN KEY (person_id) REFERENCES persons (id)
);

create table orders
(
    id int auto_increment,
    client_id int not null,
    manager_id int not null,
    price float not null default 0.0,
    date date not null,
    PRIMARY KEY (id),
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (manager_id) REFERENCES managers (id)
);

create table orders_weapons
(
    order_id int not null,
    weapon_id int not null,
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (weapon_id) REFERENCES weapons (id)
);

create table users_roles
(
    user_id int not null,
    role_id int not null,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

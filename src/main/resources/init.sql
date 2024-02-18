CREATE TABLE customer(
    id BIGSERIAL PRIMARY KEY,
    login TEXT NOT NULL,
    password TEXT NOT NULL,
    fio TEXT NOT NULL,
    role TEXT,
    UNIQUE (login)
);
CREATE TABLE transporter
(
    id BIGSERIAL PRIMARY KEY,
    name TEXT NOT NULL,
    phone_number TEXT NOT NULL
);
CREATE TABLE route
(
    id BIGSERIAL PRIMARY KEY,
    starting_point TEXT NOT NULL,
    destination_point TEXT NOT NULL,
    duration INTEGER NOT NULL
);
CREATE TABLE route_transporter
(
    id BIGSERIAL PRIMARY KEY,
    transporter_id INTEGER NOT NULL ,
    route_id INTEGER NOT NULL ,
    FOREIGN KEY (route_id) REFERENCES route (id) ON DELETE CASCADE,
    FOREIGN KEY (transporter_id) REFERENCES transporter (id) ON DELETE CASCADE
);
CREATE TABLE ticket
(
    id BIGSERIAL PRIMARY KEY,
    seat_number INTEGER NOT NULL ,
    price INTEGER NOT NULL ,
    date DATE NOT NULL ,
    time TEXT NOT NULL ,
    customer_id INTEGER,
    route_id INTEGER NOT NULL ,
    FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE,
    FOREIGN KEY (route_id) REFERENCES route (id) ON DELETE CASCADE
);




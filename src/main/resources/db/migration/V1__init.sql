USE `train-ticket-booker-test`;

create table stations (
    id   bigint auto_increment primary key,
    name varchar(255) not null,
    constraint unique_station_name unique (name)
);

create table station_connections (
    id           bigint auto_increment primary key,
    price_weight int    not null,
    time_weight  int    not null,
    from_station bigint not null,
    to_station   bigint not null,
    constraint unique_station_connection unique (from_station, to_station),
    constraint fk_station_connections_to_station foreign key (to_station) references stations (id),
    constraint fk_station_connections_from_station foreign key (from_station) references stations (id)
);

create table users (
    id       bigint auto_increment primary key,
    email    varchar(255)   not null,
    password varbinary(255) not null,
    username varchar(255)   not null
);

create table tickets (
    ticket_id       varchar(36)    not null primary key,
    booking_time    datetime(6)    not null,
    carriage        smallint       not null,
    discount        tinyint        not null,
    price           decimal(38, 2) not null,
    seat            smallint       not null,
    travel_time     datetime(6)    not null,
    station_from_id bigint         not null,
    station_to_id   bigint         not null,
    user_id         bigint         not null,
    constraint fk_tickets_user_id foreign key (user_id) references users (id),
    constraint fk_tickets_station_from_id foreign key (station_from_id) references stations (id),
    constraint fk_tickets_station_to_id foreign key (station_to_id) references stations (id),
    check (`discount` between 0 and 3)
);




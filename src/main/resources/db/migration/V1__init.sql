CREATE DATABASE IF NOT EXISTS `train-ticket-booker-test`;

USE `train-ticket-booker-test`;

create table stations (
    id   bigint auto_increment primary key,
    name varchar(255) not null,
    constraint UK_f6787k9crm2wetdsqyqc8xwt5 unique (name)
);

create table station_connections (
    id           bigint auto_increment primary key,
    price_weight int    not null,
    time_weight  int    not null,
    from_station bigint not null,
    to_station   bigint not null,
    constraint UKrkmdb4mn3s0w5fcokrq9tvagh unique (from_station, to_station),
    constraint FKawo5p8qauacie3j0kuli882vu foreign key (to_station) references stations (id),
    constraint FKjwr5n6uw6l07bmgqvd0b4ap16 foreign key (from_station) references stations (id)
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
    constraint FKeqgdma1fmnup4sas30nmk5hjd foreign key (user_id) references users (id),
    constraint FKfrbmta1tjaomhuc1dx7x4djal foreign key (station_from_id) references stations (id),
    constraint FKhhy3i6txk6n1uuxn8v8cv4h6j foreign key (station_to_id) references stations (id),
    check (`discount` between 0 and 3)
);




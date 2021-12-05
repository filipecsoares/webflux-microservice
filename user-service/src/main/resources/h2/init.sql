create table users(
    id bigint auto_increment,
    name varchar(70),
    balance decimal,
    primary key (id)
);

create table user_transaction(
    id bigint auto_increment,
    user_id bigint not null,
    amount decimal,
    transaction_date timestamp,
    primary key (id),
    foreign key (user_id) references users (id) on delete cascade
);

insert into users(name, balance) values
('Filipe', 1000), ('Maria', 1500), ('Fernanda', 1600), ('Augusto', 3000);
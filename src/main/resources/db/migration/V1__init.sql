create table product (
                         id serial primary key ,
                         description varchar(128) not null,
                         price numeric(5, 2) check ( price > 0 ),
                         available_number smallint check ( available_number >= 0 ),
                         is_special_offer boolean default false
);

create table discount_card(
id serial primary key ,
card_name VARCHAR(128) UNIQUE ,
discount_percent SMALLINT CHECK ( discount_percent BETWEEN 1 AND 100 )
);



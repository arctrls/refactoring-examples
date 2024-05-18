create table if not exists coupon
(
    id       int primary key auto_increment,
    name     varchar(100) not null,
    start_dt datetime(6)  not null,
    end_dt   datetime(6)  not null
);
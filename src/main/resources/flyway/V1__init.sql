create table order_contract(
    id bigint auto_increment primary key not null ,
    sign_date_time datetime ,
    update_date_time datetime,
    signer varchar(100),
    create_user varchar(100),
    `from` varchar(100),
    `to` varchar(100),
    train_no varchar(100),
    time datetime
)
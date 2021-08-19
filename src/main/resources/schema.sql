drop table if exists payment;
drop table if exists product_image;
drop table if exists product_available_sizes;
drop table if exists product;
drop table if exists category;
drop table if exists profile_users;
drop table if exists users;
drop table if exists profile;

create table category (
    id bigint(20) not null auto_increment,
    name varchar(255) not null,
    slug varchar(255) not null,
    position integer not null,
    primary key (id)
) engine=InnoDB default charset=latin1;

create table product (
    code bigint not null,
    name varchar(100) not null,
    description varchar(500) not null,
    slug varchar(100) not null,
    brand varchar(50) not null,
    color varchar(10) not null,
    price decimal(19,2) not null,
    discount decimal(19,2),
    weight_in_grams integer not null,
    category_id bigint,
    primary key (code)
) engine=InnoDB default charset=latin1;

alter table product
    add constraint FK_product_category
        foreign key (category_id)
            references category (id);

create table product_image (
    id bigint not null auto_increment,
    product_code bigint not null,
    image_url varchar(255) not null,
    primary key (id)
) engine=InnoDB default charset=latin1;

alter table product_image
    add constraint FK_product_image_product
        foreign key (product_code)
            references product (code);

create table product_available_sizes (
    product_code bigint not null,
    available_sizes varchar(15) not null
) engine=InnoDB default charset=latin1;

alter table product_available_sizes
    add constraint FK_product_available_sizes_product
        foreign key (product_code)
            references product (code);

create table payment (
    id bigint(20) not null auto_increment,
    value decimal(19,2) not null,
    status varchar(10) not null,
    card_client_name varchar(100) not null,
    card_number varchar(50) not null,
    card_expiration varchar(10) not null,
    card_verification_code varchar(3) not null,
    primary key (id)
) engine=InnoDB default charset=latin1;

create table profile (
    id bigint(20) not null auto_increment,
    profile varchar(255) not null,
    primary key (id)
) engine=InnoDB default charset=latin1;

create table users (
    id bigint(20) not null auto_increment,
    user varchar(255) not null,
    pass varchar(255) not null,
    primary key (id)
) engine=InnoDB default charset=latin1;

create table profile_users (

    profile_id bigint(20) not null,
    user_id bigint(20) not null,
    constraint FK_profile_id foreign key (profile_id)
        references profile (id)
        --on update no action on delete no action
        ,
    constraint FK_user_id foreign key (user_id)
        references users (id)
        --on update no action on delete no action
) engine=InnoDB default charset=latin1;



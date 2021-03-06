create sequence hibernate_sequence;
create table post (
    id bigint not null primary key,
    title varchar(128),
    content varchar(128),
    username varchar(128),
    created_on DATETIME(6)
);

create table comment (
    id bigint not null primary key,
    post_id bigint not null references post (id),
    comment varchar(160),
    username varchar(128),
    created_on DATETIME(6)
);

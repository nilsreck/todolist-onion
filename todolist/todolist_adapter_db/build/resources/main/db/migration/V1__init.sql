drop table if exists ITEM;

create table ITEM
(
    id          int auto_increment primary key,
    description varchar(255) not null,
    due         datetime     not null,
    done        bool         not null,
    priority    int          not null
);

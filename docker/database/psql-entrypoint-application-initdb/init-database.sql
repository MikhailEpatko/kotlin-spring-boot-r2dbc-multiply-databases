select 'create database application_db'
where not exists(select from pg_database where datname = 'application_db')
\gexec


\c application_db;

create table if not exists application
(
    id          bigserial primary key,
    name        varchar(100) not null,
    description text not null,
    created_at  timestamptz default current_date,
    modified_at timestamptz default current_date
);


insert into application (id, name, description)
values (1, 'Application-1', 'Description-1'),
       (2, 'Application-2', 'Description-2'),
       (3, 'Application-3', 'Description-3')
on conflict do nothing;
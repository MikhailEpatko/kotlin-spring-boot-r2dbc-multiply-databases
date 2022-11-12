create table if not exists app_user
(
    id          bigserial primary key,
    name        varchar(100),
    avatar      varchar(100),
    position    varchar(100),
    created_at  timestamptz default current_date,
    modified_at timestamptz default current_date
    );


insert into app_user (id, name, avatar, position)
values (1, 'UserName-1', 'UserAvatar-1', 'UserPosition-1'),
       (2, 'UserName-2', 'UserAvatar-2', 'UserPosition-2'),
       (3, 'UserName-3', 'UserAvatar-3', 'UserPosition-3')
    on conflict do nothing;
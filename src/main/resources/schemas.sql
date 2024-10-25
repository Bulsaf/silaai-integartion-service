create table if not exists chat_messages
(
    id           uuid not null
        primary key,
    content      jsonb,
    created_at   timestamp(6) with time zone,
    recepient_id uuid not null,
    sender_id    uuid not null
);

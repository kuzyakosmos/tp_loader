drop table if exists stock_info;
create table stock_info
(
    id               serial primary key,
    avg_total_volume   varchar,
    calculation_price varchar,
    change           varchar,
    change_percent    varchar,
    close            varchar,
    close_source      varchar,
    close_time        varchar,
    company_name      varchar
)
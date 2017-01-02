create table tag(
	t_id int primary key ,
	t_name varchar2(100) not null,
	t_count int
);
create sequence seq_tag_id start with 1 increment by 1;
create table Favorite(
	f_id int primary key ,
	f_label varchar2(1000) not null,
	f_url varchar2(300) not null,
	f_tags  varchar2(1000),
	f_desc varchar2(2000)
);
create sequence seq_fa_id start with 1 increment by 1;

select * from tag for update

select * from Favorite
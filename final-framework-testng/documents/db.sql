create table changepermissiongroup(
membername varchar(50) not null,
newgroup varchar(50),
comments varchar(500));


insert into changepermissiongroup(membername,newgroup,comments)values
("manzoor1","Full brokers","full access to the member");
insert into changepermissiongroup(membername,newgroup,comments)values("sunil","Full brokers","full access to the member");
insert into changepermissiongroup(membername,newgroup,comments)values("mariya","Disabled brokers","broker is disabled");

select * from changepermissiongroup;
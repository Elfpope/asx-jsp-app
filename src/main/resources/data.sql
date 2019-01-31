/* create table: Trading_Calendar */
--create table Trading_Calendar (
--	Id int not null,
--	"Date" date not null,
--	Trading_Date char(10) not null,
--	constraint trading_calendar_pk primary key (Id)
--);

/* Populate table: Trading_Calendar*/
insert into Trading_Calendar (Id, Date, Trading_Date) values (1, '2018-12-25', 'N');
insert into Trading_Calendar (Id, Date, Trading_Date) values (2, '2018-12-26', 'N');
insert into Trading_Calendar (Id, Date, Trading_Date) values (3, '2019-01-01', 'N');


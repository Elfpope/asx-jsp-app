/* create table: Trading_Calendar */
create table Trading_Calendar (
	"Date" date,
	TradingDate char(10)
);

/* Populate table: Trading_Calendar*/
insert into Trading_Calendar ("Date", TradingDate) values ('2018-12-25', 'N');
insert into Trading_Calendar ("Date", TradingDate) values ('2018-12-26', 'N');
insert into Trading_Calendar ("Date", TradingDate) values ('2019-01-01', 'N');


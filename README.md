# MySickWell

MySickWell is my hobby project, trying to create a basic (dumber) version of SQL, but with Java while learning generics and string manipulation.

## Versions:
2020.07.27 - After the first week: Create table, Insert table, Select * from table. Tests has ~85% coverage
2020.07.28 - Select with column names! Tests has ~92% coverage


## Technologies
Java (11 runs under the hood at the moment) and Spring. 

## Usage

Currently it can create tables with the syntax of:  
```CREATE TABLE table_name (column ColumnType, anothercolumn ColumnType);```\
 (allowed ColumnTypes are INTEGER, VARCHAR, BOOLEAN).

Insert into table with the syntax of:  
```INSERT INTO table_name VALUES(value1, value2);```

Select from table with the syntax of:\
```SELECT * FROM table_name;``` for all columns or\
```SELECT column1,column2 FROM table_name;``` for specific columns


## Contributing
It is only me:\
Alex Alpek - [Github](https://github.com/alexalpek)

## License
Nope.

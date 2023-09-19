create table IF NOT EXISTS xmlFileStore(fileName varchar(100),content varchar(4000), description varchar(1000), STATUS varchar(100) );
INSERT INTO xmlFileStore (fileName, content, description) VALUES ('1.xml', '<test>', 'test');
CREATE VIEW PUBLIC.HELLO_VIEW AS select fileName from xmlFileStore;

create or replace TABLE FILMIX.FILMIX.ACTOR (
	ID NUMBER(38,0) NOT NULL autoincrement,
	FIRST_NAME VARCHAR(50) NOT NULL,
	LAST_NAME VARCHAR(50) NOT NULL,
	BIRTHDATE DATE NOT NULL,
	constraint PK_ACTOR primary key (ID)
);


create or replace TABLE FILMIX.FILMIX.CATEGORY (
	ID NUMBER(38,0) NOT NULL autoincrement,
	NAME VARCHAR(255),
	constraint PK_CATEGORY primary key (ID)
);

create or replace TABLE FILMIX.FILMIX.MOVIE (
	ID NUMBER(38,0) NOT NULL autoincrement,
	TITLE VARCHAR(50),
	RELEASE_DATE DATE,
	DESCRIPTION VARCHAR(255),
	DURATION NUMBER(38,0) NOT NULL,
	EPISODES NUMBER(38,0) NOT NULL,
	CATEGORY_ID NUMBER(38,0),
	constraint PK_MOVIE primary key (ID),
	constraint FK_MOVIE_ON_CATEGORY foreign key (CATEGORY_ID) references FILMIX.FILMIX.CATEGORY(ID)
);

create or replace TABLE FILMIX.FILMIX.MOVIE_CATEGORY (
	ID NUMBER(38,0) NOT NULL autoincrement,
	CATEGORY_ID NUMBER(38,0) NOT NULL,
	MOVIE_ID NUMBER(38,0) NOT NULL,
	constraint PK_MOVIE_CATEGORY primary key (CATEGORY_ID, MOVIE_ID),
	constraint FK_MOVCAT_ON_CATEGORY foreign key (CATEGORY_ID) references FILMIX.FILMIX.CATEGORY(ID),
	constraint FK_MOVCAT_ON_MOVIE foreign key (MOVIE_ID) references FILMIX.FILMIX.MOVIE(ID)
);

create or replace TABLE FILMIX.FILMIX.NEWS (
	ID NUMBER(38,0) NOT NULL autoincrement,
	MOVIE_ID NUMBER(38,0),
	CREATED_AT TIMESTAMP_NTZ(9),
	CONTENT VARCHAR(255),
	constraint PK_NEWS primary key (ID),
	constraint FK_NEWS_ON_MOVIE foreign key (MOVIE_ID) references FILMIX.FILMIX.MOVIE(ID)
);

create or replace TABLE FILMIX.FILMIX.OPINION (
	ID NUMBER(38,0) NOT NULL autoincrement,
	USER_ID NUMBER(38,0),
	MOVIE_ID NUMBER(38,0),
	CREATED_AT TIMESTAMP_NTZ(9),
	OPINION VARCHAR(255),
	constraint PK_OPINION primary key (ID)
);

create or replace TABLE FILMIX.FILMIX.RATE (
	ID NUMBER(38,0) NOT NULL autoincrement,
	USER_ID NUMBER(38,0),
	MOVIE_ID NUMBER(38,0),
	RATING NUMBER(38,0),
	constraint PK_RATE primary key (ID)
);

create or replace TABLE FILMIX.FILMIX.ROLE (
	ID NUMBER(38,0) NOT NULL autoincrement,
	NAME VARCHAR(50) NOT NULL,
	constraint PK_ROLE primary key (ID)
);

create or replace TABLE FILMIX.FILMIX.USERS (
	ID NUMBER(38,0) NOT NULL autoincrement,
	USERNAME VARCHAR(50) NOT NULL,
	PASSWORD VARCHAR(255) NOT NULL,
	ROLE_ID NUMBER(38,0),
	constraint PK_USERS primary key (ID),
	constraint FK_USERS_ON_ROLE foreign key (ROLE_ID) references FILMIX.FILMIX.ROLE(ID)
);

create or replace TABLE FILMIX.FILMIX.CATEGORY_MOVIE_LIST (
	ID NUMBER(38,0) NOT NULL autoincrement,
	CATEGORY_ID NUMBER(38,0) NOT NULL,
	MOVIE_LIST_ID NUMBER(38,0) NOT NULL,
	constraint PK_CATEGORY_MOVIELIST primary key (CATEGORY_ID, MOVIE_LIST_ID),
	constraint UC_CATEGORY_MOVIE_LIST_MOVIELIST unique (MOVIE_LIST_ID),
	constraint FK_CATMOVLIS_ON_CATEGORY foreign key (CATEGORY_ID) references FILMIX.FILMIX.CATEGORY(ID),
	constraint FK_CATMOVLIS_ON_MOVIE foreign key (MOVIE_LIST_ID) references FILMIX.FILMIX.MOVIE(ID)
);


create or replace TABLE FILMIX.FILMIX.ACTOR_MOVIE (
	ID NUMBER(38,0) NOT NULL autoincrement,
	ACTOR_ID NUMBER(38,0),
	MOVIE_ID NUMBER(38,0),
	ROLE_NAME VARCHAR(255),
	constraint PK_ACTOR_MOVIE primary key (ID),
	constraint FK_ACTOR_MOVIE_ON_ACTOR foreign key (ACTOR_ID) references FILMIX.FILMIX.ACTOR(ID),
	constraint FK_ACTOR_MOVIE_ON_MOVIE foreign key (MOVIE_ID) references FILMIX.FILMIX.MOVIE(ID)
);
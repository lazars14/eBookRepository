Create Database eBookRepository

Use eBookRepository

Create Table Book_Language
(
	language_id Integer Primary Key Not Null Auto_increment,
	language_name Varchar(30),
    language_deleted Bit
);

Create Table Category
(
	category_id Integer Primary Key Not Null Auto_increment,
	category_name Varchar(30),
    category_deleted Bit
);

Create Table Book_File
(
	file_id Integer Primary Key Not Null Auto_increment,
    file_name Varchar(200),
    file_mime Varchar(100)
);

Create Table App_User
(
	app_user_id Integer Primary Key Not Null Auto_increment,
	app_user_firstname Varchar(30),
	app_user_lastname Varchar(30),
	app_user_username Varchar(10) Unique Not Null,
	app_user_password Varchar(10),
	app_user_category_id Integer,
    app_user_type Varchar(30),
    app_user_deleted Bit,
    
    Foreign Key (app_user_category_id) References Category(category_id)
);

Create Table eBook
(
	eBook_id Integer Primary Key Not Null Auto_increment,
    eBook_title Varchar(80),
    eBook_author Varchar(120),
    eBook_keywords Varchar(120),
    eBook_publication_year Integer,
    eBook_language Integer,
    eBook_category Integer,
    eBook_file_id Integer,
    eBook_deleted Bit,
    
    Foreign Key (eBook_language) References Book_Language(language_id),
    Foreign Key (eBook_category) References Category(category_id),
    Foreign Key (eBook_file_id) References Book_File(file_id)
);
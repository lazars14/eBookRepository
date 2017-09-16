create database ebookrepository

Use ebookrepository

Insert Into book_language(language_name, language_deleted) Values ('American', 0);
Insert Into book_language(language_name, language_deleted) Values ('French', 0);
Insert Into book_language(language_name, language_deleted) Values ('Serbian', 0);

Insert Into category(category_name, category_deleted) Values ('Biography', 0);
Insert Into category(category_name, category_deleted) Values ('Romance', 0);
Insert Into category(category_name, category_deleted) Values ('Mistery', 0);
Insert Into category(category_name, category_deleted) Values ('Horror', 0);
Insert Into category(category_name, category_deleted) Values ('Business', 0);

# book files
# .pdf = application/pdf

# Biography
Insert Into book_file(file_name, file_mime) Values ('abrahamLincoln1', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('miracleBoy2', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('theAutobiographyOfCharlesDarwin3', 'application/pdf');

# Romance
Insert Into book_file(file_name, file_mime) Values ('attractions13', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('loveLetters14', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('pridePrejudice15', 'application/pdf');

# Mistery
Insert Into book_file(file_name, file_mime) Values ('adventuresSherlockHolmes10', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('darkRemnants11', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('handcuffsAndHighHeels12', 'application/pdf');

# Horror
Insert Into book_file(file_name, file_mime) Values ('bedtime7', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('dracula8', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('theDeadlyOnez9', 'application/pdf');

# Business
Insert Into book_file(file_name, file_mime) Values ('humanResourcesBestPracticesGuide4', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('recruit5', 'application/pdf');
Insert Into book_file(file_name, file_mime) Values ('topTenSellingTheLumberjackChronicles6', 'application/pdf');


Insert Into app_user(app_user_firstname, app_user_lastname, app_user_username, app_user_password, app_user_category_id, app_user_type, app_user_deleted) Values ('Milan', 'Antic', 'milan', 'antic', null, 'administrator', 0);
Insert Into app_user(app_user_firstname, app_user_lastname, app_user_username, app_user_password, app_user_category_id, app_user_type, app_user_deleted) Values ('Nikola', 'Pavlovic', 'nikola', 'pavlovic', 1, 'subscriber', 0);
Insert Into app_user(app_user_firstname, app_user_lastname, app_user_username, app_user_password, app_user_category_id, app_user_type, app_user_deleted) Values ('Stefan', 'Simic', 'stefan', 'simic', 2, 'subscriber', 0);
Insert Into app_user(app_user_firstname, app_user_lastname, app_user_username, app_user_password, app_user_category_id, app_user_type, app_user_deleted) Values ('Novak', 'Music', 'novak', 'music', 3, 'subscriber', 0);
Insert Into app_user(app_user_firstname, app_user_lastname, app_user_username, app_user_password, app_user_category_id, app_user_type, app_user_deleted) Values ('Miroslav', 'Tomic', 'miroslav', 'tomic', null, 'subscriber', 0);
Insert Into app_user(app_user_firstname, app_user_lastname, app_user_username, app_user_password, app_user_category_id, app_user_type, app_user_deleted) Values ('Jovan', 'Vojinovic', 'jovan', 'vojinovic', null, 'subscriber', 0);

# Biography
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('ABRAHAM LINCOLN', 'JAMES RUSSELL LOWELL', 'biography|president|justice|assasination', 1865, 1, 1, 1, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('The Miracle Boy', 'Olatunji Joan Olufolake|Olatunji Ayodele Oluwaseyi', 'biography|miracle|africa|nigeria|war', 2013, 1, 1, 2, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('The Autobiography of Charles Darwin', 'Charles Darwin', 'biography|science|biology', 1887, 1, 1, 3, 0);

# Romance
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Attractions', 'Joshua Edward Smith', 'romance|love|drama', 2016, 1, 2, 4, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Love Letters', 'Bryan Mooney', 'romance|love|woman|complicated|relationship', 2011, 1, 2, 5, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Pride and Prejudice', 'Jane Austen', 'romance|old|middle age|castle|letter|love', 1813, 1, 2, 6, 0);

# Mistery
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('The Adventures Of Sherlock Holmes', 'Arthur Conan Doyle', 'mistery|crime|england|detective', 2012, 1, 3, 7, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Dark Remnants - Street Games: Book One', 'L.K.Hill', 'mistery|crime|darkness|assasination|murder', 2014, 1, 3, 8, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Handcuffs & High Heels', 'J.M.Edwards', 'mistery|woman|crime|detective|murder', 2014, 1, 3, 9, 0);

# Horror
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Bedtime and other tales of terror', 'Michael Whitehouse', 'horror|bed|terror|darkness', 2013, 1, 4, 10, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Dracula', 'Bram Stoker|Heidi Klum', 'horror|romania|vampire|fangs|blood|castle', 1897, 1, 4, 11, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('The Deadly Onez', 'Ziad Antar', 'horror|walking dead|zombie|terror|cemetery', 2012, 1, 4, 12, 0);

# Business
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('HUMAN RESOURCES BEST PRACTICES GUIDE', 'Solomon Hill', 'business|money|stock|market|staff', 2010, 1, 5, 13, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('Recruit', 'DAVID SWEET', 'business|money|finance|recruitment|staff', 2007, 1, 5, 14, 0);
Insert Into ebook(eBook_title, eBook_author, eBook_keywords, eBook_publication_year, eBook_language, eBook_category, eBook_file_id, eBook_deleted) Values ('The Lumberjack Chronicles', 'DAN NORMAN', 'business|money|finance|chronicle', 2007, 1, 5, 15, 0);
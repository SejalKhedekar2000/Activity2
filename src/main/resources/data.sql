
INSERT INTO AUTHOR (id, first_name, last_name)
VALUES
    (0, 'Robert', 'Frost'),
    (1, 'Martin', 'Fowler'),
    (2, 'Kevin', 'Gary'),
    (3, 'Kent', 'Beck'),
    (4, 'James', 'Gosling'),
    (5, 'Bjarne', 'Stroustrup'),
    (6, 'Joshua', 'Bloch'),
    (7, 'Andrew', 'Hunt'),
    (8, 'David', 'Thomas'),
    (9, 'Eric', 'Evans');


INSERT INTO BOOK (isbn, title, author_id)
VALUES
    ('123456789', 'The Road Not Taken', 0),
    ('987654321', 'Refactoring', 1),
    ('456789123', 'Software Engineering Essentials', 2),

    ('111111111', 'Test-Driven Development', 3),
    ('222222222', 'Extreme Programming Explained', 3),

    ('333333333', 'The Java Programming Language', 4),
    ('444444444', 'The Java Language Specification', 4),

    ('555555555', 'The C++ Programming Language', 5),
    ('666666666', 'Programming: Principles and Practice', 5),

    ('777777777', 'Effective Java', 6),
    ('888888888', 'Java Puzzlers', 6),

    ('999999999', 'The Pragmatic Programmer', 7),
    ('101010101', 'Pragmatic Thinking & Learning', 7),

    ('202020202', 'The Pragmatic Programmer 20th Anniversary', 8),

    ('303030303', 'Domain-Driven Design', 9);

DELETE FROM users;
DELETE FROM tokens;
ALTER SEQUENCE userseq RESTART WITH 1;
ALTER SEQUENCE token_seq RESTART WITH 1;

INSERT INTO users (name, email, phone, locale, short_code, email_valid) VALUES
('Alex', 'email@gmail.com', '+78122524578', 'Россия', 'RU', true),
('Alex2', 'gmail@gmail.com', '+78122347391', 'Россия', 'RU', true),
('Alex3', 'gmail@gmail.com', '+78122347398', 'Россия', 'RU', true);


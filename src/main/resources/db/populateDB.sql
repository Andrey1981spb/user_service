DELETE FROM users;
DELETE FROM tokens;
DELETE FROM trackers;
ALTER SEQUENCE userseq RESTART WITH 1;
ALTER SEQUENCE token_seq RESTART WITH 1;
ALTER SEQUENCE tracker_seq RESTART WITH 1;

INSERT INTO users (name, email, phone, locale, city, short_code, email_valid) VALUES
('Alex', 'email@gmail.com', '+78122524578', 'Россия', 'Spb','RU', true),
('Alex2', 'gmail@gmail.com', '+78122347391', 'Россия', 'Spb','RU', true),
('Alex3', 'gmail@gmail.com', '+78122347398', 'Россия', 'Spb','RU', true);

INSERT INTO trackers (name, value, user_id) VALUES
('Yandex', 'dfvdfv6d79dfvdfv09d0vd', 1),
('Yandex', 'ervevvcsdfvcdscvsdcvsd', 1);
INSERT INTO roles (id, key, name) VALUES
('34737491-a5ae-41dc-a0fb-c1ac20d31b8b', 'admin', 'Admin'),
('bde2722b-c45e-4256-b81a-721982d75f5b', 'student', 'Schüler'),
('4b88a5c6-cedb-4f73-809d-e042be5b0779', 'supervisor', 'Betreuer');

INSERT INTO users (id, approved, fullname, email, password, phone, verified) VALUES
('28fc7d3a-afc7-49ca-8070-eea8671abab3', true, 'Admin', 'admin@example.com', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '1111111111', true);

INSERT INTO user_roles (id, user_id, role_id) VALUES
('747fad72-720c-4747-8884-9dd61dc67f3c', '28fc7d3a-afc7-49ca-8070-eea8671abab3', '34737491-a5ae-41dc-a0fb-c1ac20d31b8b');

INSERT INTO settings (id, chat_active) VALUES
('1705572c-ce00-4486-b06c-55ce9f479934', true);

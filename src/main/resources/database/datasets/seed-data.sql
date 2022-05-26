INSERT INTO roles (id, name) VALUES
('34737491-a5ae-41dc-a0fb-c1ac20d31b8b', 'admin'),
('bde2722b-c45e-4256-b81a-721982d75f5b', 'approved'),
('f5bfa44f-2d19-4ebd-b40b-9112c6b9a996', 'superviser'),
('cf5090d0-dfeb-4bf8-a242-33a2522fe765', 'verified');

INSERT INTO users (id, fullname, email, password, phone, evaluate_course) VALUES
('28fc7d3a-afc7-49ca-8070-eea8671abab3', 'Admin', 'admin@example.com', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '1111111111', false);

INSERT INTO user_roles (id, user_id, role_id) VALUES
('25fa68bb-6967-441d-9017-8120a5fea00f', '28fc7d3a-afc7-49ca-8070-eea8671abab3', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('0e60c6f0-9a00-4564-8e9a-39e8fbfe4ec6', '28fc7d3a-afc7-49ca-8070-eea8671abab3', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765'),
('747fad72-720c-4747-8884-9dd61dc67f3c', '28fc7d3a-afc7-49ca-8070-eea8671abab3', '34737491-a5ae-41dc-a0fb-c1ac20d31b8b');
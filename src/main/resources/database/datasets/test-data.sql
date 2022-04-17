INSERT INTO roles (id, name) VALUES
('34737491-a5ae-41dc-a0fb-c1ac20d31b8b', 'admin'),
('bde2722b-c45e-4256-b81a-721982d75f5b', 'approved'),
('f5bfa44f-2d19-4ebd-b40b-9112c6b9a996', 'superviser'),
('cf5090d0-dfeb-4bf8-a242-33a2522fe765', 'verified');

INSERT INTO media (id, mime_type, name) VALUES
('4a3717af-94b2-4a5d-b328-2d2754ea3cb5', 'image/jpg', 'profile picture'),
('34af774d-2774-427b-a005-a5f406b0f72b', 'image/jpg', 'profile picture'),
('89c7c32f-f53d-4730-bb48-ac5b02b2abd5', 'image/jpg', 'profile picture'),
('ab6a0bdc-c5bf-442a-a7ca-5f4b768d6513', 'image/jpg', 'profile picture'),
('ced2157e-5e4f-44bb-b9a7-a17cd3cdad98', 'image/jpg', 'profile picture');

INSERT INTO users (id, fullname, email, profile_picture_id, password) VALUES
('28fc7d3a-afc7-49ca-8070-eea8671abab3', 'Admin', 'admin@example.com', '4a3717af-94b2-4a5d-b328-2d2754ea3cb5', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW'),
('36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'Student', 'student@example.com', '34af774d-2774-427b-a005-a5f406b0f72b', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW'),
('528250c0-e0e3-4166-aa71-0f150eb2453f', 'Superviser', 'superviser@example.com', '89c7c32f-f53d-4730-bb48-ac5b02b2abd5', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW'),
('dd939b40-be89-41ca-b031-fef2dc82a536', 'Not Veried User', 'notverified@example.com', 'ab6a0bdc-c5bf-442a-a7ca-5f4b768d6513', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW'),
('71a857db-dd35-4d08-bb0f-20115a584ee5', 'Not Approved User', 'notapproved@example.com', 'ced2157e-5e4f-44bb-b9a7-a17cd3cdad98', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW');

INSERT INTO user_roles (id, user_id, role_id) VALUES
('747fad72-720c-4747-8884-9dd61dc67f3c', '28fc7d3a-afc7-49ca-8070-eea8671abab3', '34737491-a5ae-41dc-a0fb-c1ac20d31b8b'),
('25fa68bb-6967-441d-9017-8120a5fea00f', '28fc7d3a-afc7-49ca-8070-eea8671abab3', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('0e60c6f0-9a00-4564-8e9a-39e8fbfe4ec6', '28fc7d3a-afc7-49ca-8070-eea8671abab3', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765'),
('cfae19fc-ee80-4462-ac53-72631e5a52c1', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('c87fae61-beab-4785-b728-6483e1ce123c', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765'),
('7a8d49f7-d1db-40b2-a653-3997a0439218', '528250c0-e0e3-4166-aa71-0f150eb2453f', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('4f649fac-e63d-48ba-a7c8-0de62c0b0fac', '528250c0-e0e3-4166-aa71-0f150eb2453f', 'f5bfa44f-2d19-4ebd-b40b-9112c6b9a996'),
('a5a2a082-1472-4b00-b19d-45b0612295f6', '528250c0-e0e3-4166-aa71-0f150eb2453f', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765'),
('64d47bfa-f34d-49bb-8bae-9e3ff7b2e1d4', '71a857db-dd35-4d08-bb0f-20115a584ee5', 'cf5090d0-dfeb-4bf8-a242-33a2522fe765');
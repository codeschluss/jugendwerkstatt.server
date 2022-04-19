INSERT INTO media (id, mime_type, name) VALUES
('4a3717af-94b2-4a5d-b328-2d2754ea3cb5', 'image/jpg', 'profile picture'),
('34af774d-2774-427b-a005-a5f406b0f72b', 'image/jpg', 'profile picture'),
('89c7c32f-f53d-4730-bb48-ac5b02b2abd5', 'image/jpg', 'profile picture'),
('ab6a0bdc-c5bf-442a-a7ca-5f4b768d6513', 'image/jpg', 'profile picture'),
('ced2157e-5e4f-44bb-b9a7-a17cd3cdad98', 'image/jpg', 'profile picture'),
('9eaee436-86d7-467e-beb9-be884a9c291c', 'image/jpg', 'title image'),
('c96198d3-2bd6-48bf-9af4-d79fbf66a1a8', 'image/jpg', 'title image');

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

INSERT INTO organizers (id, name, mail, phone, website) VALUES
('2295ed87-3f46-428a-a179-e7b7093d2f05', 'IHK-Ulm', 'Gaille.Susanne@web.de', '0176-3138557', 'https://www.ihk-ausbildungsmesse.de/' ),
('ead07153-6e4b-45ae-abd4-562fc126e92f', 'Bowling-Center-Ulm', 'Rainer.Zufall@web.de', '0173-7717645', 'https://wp.neu-ulm-inside.de/new-bowling-center/');

INSERT INTO event_categories (id, name, icon) VALUES
('555f2324-87bf-4510-ac08-893076444dbb', 'Education and Training', 'goodIcon'),
('a464edde-2e2f-455e-a5ca-84fd28f8ad4a', 'Sport', 'niceIcon');

INSERT INTO addresses (id, house_number, latitude, longitude, place, postal_code, street) VALUES
('ff656406-89af-4835-a9bf-571fd978f78f', '27', '48.397340', '9.992980', 'Ulm', '89073', 'ihk-street' ),
('b1b84b9d-a30c-4921-8d20-af21058bec79', '22', '48.397340', '9.992980', 'Ulm', '89073', 'müller-street'),
('4ff89e9a-2751-4a15-845e-8283194e81fa', '28', '48.397340', '9.992980', 'Ulm', '89073', 'bowling-street'),
('5dc8809e-8890-4262-b334-a0dff5b8ffd8', '33', '48.397340', '9.992980', 'Ulm', '89073', 'claus-street');

INSERT INTO events (id, name, description, category_id, organizer_id, title_image_id, address_id) VALUES
('440a6eec-be4d-47e8-b38d-3a819bc4624e', 'Vocational-Training-Fair','First touch with companys','555f2324-87bf-4510-ac08-893076444dbb',
'2295ed87-3f46-428a-a179-e7b7093d2f05','9eaee436-86d7-467e-beb9-be884a9c291c','ff656406-89af-4835-a9bf-571fd978f78f' ),
('3e9b15fb-02ce-4d09-bfcd-40751eaee1b7', 'Team-Bowling', 'Social gathering and bowling','a464edde-2e2f-455e-a5ca-84fd28f8ad4a',
'ead07153-6e4b-45ae-abd4-562fc126e92f','c96198d3-2bd6-48bf-9af4-d79fbf66a1a8', '4ff89e9a-2751-4a15-845e-8283194e81fa');

INSERT INTO schedules (id, start_date, end_date, event_id) VALUES
('2f2f8064-43e3-4e11-a157-d88d4f357e4c', '2022-10-19 10:00:00+02', '2022-10-19 16:00:00+02', '440a6eec-be4d-47e8-b38d-3a819bc4624e'),
('4d43606f-1338-4e61-baec-e080d9e55400', '2022-05-05 18:00:00+02', '2022-05-05 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('58fa6c11-de2d-4c54-b85c-dab0d546473a', '2022-05-12 18:00:00+02', '2022-05-12 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('fc7965c6-ce88-4ecc-a455-045237370b9a', '2022-05-19 18:00:00+02', '2022-05-19 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7');

INSERT INTO jobtypes (id, name, color) VALUES
('430d33c4-f0f2-42b1-927f-5270d91c7f32', 'metal trade', '#0000FF'),
('1a773b24-de53-4ccc-9daa-e9261590f88b', 'carpentry', '#00FF00');

INSERT INTO companies (id, name, mail, phone, website, address_id) VALUES
('89aaa55a-168d-4165-85fc-abde073d6732', 'Carpentry Müller GmbH', 'alex@müllergmbh.de', '11830', 'müllergmbh.de', 'b1b84b9d-a30c-4921-8d20-af21058bec79'),
('f476ed5a-c5c6-429b-bcf0-85fcf8363be3', 'Metal Claus GmbH', 'claus@metal.de', '112', 'clausmetal.de', '5dc8809e-8890-4262-b334-a0dff5b8ffd8');

INSERT INTO job_ads(id, title, start_date, due_date, company_id, job_type_id) VALUES
('39cdb5c0-aa0b-4665-a732-e51cb0986e6d', 'metal worker', '2022-10-1 10:00:00+02', '2022-09-15 10:00:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', '430d33c4-f0f2-42b1-927f-5270d91c7f32'),
('e7c48d91-851c-4542-ac7b-e8efb66d0f8e', 'carpetry worker', '2022-04-01 10:00:00+02', '2022-01-31 00:00:00+02', 'f476ed5a-c5c6-429b-bcf0-85fcf8363be3', '1a773b24-de53-4ccc-9daa-e9261590f88b');


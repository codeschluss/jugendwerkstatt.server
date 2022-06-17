INSERT INTO media (id, mime_type, name) VALUES
('4a3717af-94b2-4a5d-b328-2d2754ea3cb5', 'image/jpg', 'profile picture'),
('34af774d-2774-427b-a005-a5f406b0f72b', 'image/jpg', 'profile picture'),
('89c7c32f-f53d-4730-bb48-ac5b02b2abd5', 'image/jpg', 'profile picture'),
('ab6a0bdc-c5bf-442a-a7ca-5f4b768d6513', 'image/jpg', 'profile picture'),
('ced2157e-5e4f-44bb-b9a7-a17cd3cdad98', 'image/jpg', 'profile picture'),
('9eaee436-86d7-467e-beb9-be884a9c291c', 'image/jpg', 'title image'),
('670d0b9d-ecfb-4dd2-8f07-ead633c7ef5b', 'image/jpg', 'title image'),
('6359d990-bcfc-400a-b685-fe3d7ff9d1c0', 'image/jpg', 'title image'),
('e302e041-fb51-47b6-99bc-47fea56de39e', 'image/jpg', 'title image'),
('af5c4d43-54f1-40d1-ba26-5895b91bb287', 'image/jpg', 'title image'),
('c96198d3-2bd6-48bf-9af4-d79fbf66a1a8', 'image/jpg', 'title image'),
('76460bc7-ed5c-4f70-a377-46e2cf49e86f', 'image/jpg', 'title image'),
('ceb59217-29f6-4a58-a8f1-776b698a9c5a', 'video/mp4', 'GetRolled'),
('9b63a393-6a72-4d7a-b396-714964467d82', 'image/jpg', 'event image'),
('c3b9e5a2-3064-4157-a6db-9624656c70f1', 'image/jpg', 'event image'),
('3e19758d-0abc-4435-84ea-30eb8dddf352', 'image/jpg', 'event image'),
('710cb609-3892-445f-a54d-3cc55de27fa4', 'image/jpg', 'event image');

INSERT INTO chats(id, name, admin) VALUES
('41bf7460-1a1a-41cb-8b2f-a89688671b33', 'Holz1Groupchat', false),
('b2cb2479-1c75-4bf6-9543-38c53a55a70f', 'Student', true),
('4e26f3a2-3877-438f-bf8a-cd2d4ac720f5', 'MetallGroupchat', false);

INSERT INTO groups(id, name, chat_id) VALUES
('f5983943-b1a3-4265-bf88-208ecbbd09a3', 'Metall', '4e26f3a2-3877-438f-bf8a-cd2d4ac720f5'),
('1f8c55b2-4210-4eea-bc14-6a6d55cc538e', 'Holz', '41bf7460-1a1a-41cb-8b2f-a89688671b33');

INSERT INTO courses(id, name, group_id, active, active_order) VALUES
('2163ee48-8605-46eb-bbd0-c62497007b18', 'Metall 1', 'f5983943-b1a3-4265-bf88-208ecbbd09a3', true, 1),
('ab36cc61-a884-4da8-be52-db8f19abcbd1', 'Metall 2', 'f5983943-b1a3-4265-bf88-208ecbbd09a3', false, 2),
('dce3746a-6109-45fd-8bfa-09bb99378d37', 'Holz1', '1f8c55b2-4210-4eea-bc14-6a6d55cc538e', true, 1);

INSERT INTO users (id, approved, fullname, email, group_id, profile_picture_id, password, phone, evaluate_course, verified) VALUES
('36808f63-4b6b-40e7-b2ee-a91f657e4e58', true, 'Student', 'student@example.com', 'f5983943-b1a3-4265-bf88-208ecbbd09a3', '34af774d-2774-427b-a005-a5f406b0f72b', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '22222222', false, true),
('e14e5d92-0b4a-47b3-991d-ac18dd6923b3', true, 'Student2', 'student2@example.com', '1f8c55b2-4210-4eea-bc14-6a6d55cc538e', '34af774d-2774-427b-a005-a5f406b0f72b', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '33333333', false, true),
('528250c0-e0e3-4166-aa71-0f150eb2453f', true, 'Superviser', 'superviser@example.com', null, '89c7c32f-f53d-4730-bb48-ac5b02b2abd5', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '444444', false, true),
('dd939b40-be89-41ca-b031-fef2dc82a536', true, 'Not Veried User', 'notverified@example.com', null, 'ab6a0bdc-c5bf-442a-a7ca-5f4b768d6513', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '55555555', false, false),
('71a857db-dd35-4d08-bb0f-20115a584ee5', false, 'Not Approved User', 'notapproved@example.com', null, 'ced2157e-5e4f-44bb-b9a7-a17cd3cdad98', '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '66666666', false, true),
('f9cfca3d-691e-490a-8969-1e016b1d5cff', true, 'Admin1', 'admin1@example.com', null, null, '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '12345568', false, true),
('c8e8b553-bef6-4aea-9ec0-f7e8145b26cd', true, 'Admin2', 'admin2@example.com', null, null, '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '2345667', false, true),
('e404285e-4278-44ce-a6de-a2463088ee64', true, 'Admin3', 'admin3@example.com', null, null, '$2a$10$FpJxzuxpXKUIDQMuLNARGOb5Ocz2YoiW4WwgWOoM4uaB7P6iNM.SW', '4567798', false, true);

INSERT INTO user_roles (id, user_id, role_id) VALUES
('cfae19fc-ee80-4462-ac53-72631e5a52c1', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('f5bfa44f-2d19-4ebd-b40b-9112c6b9a996', 'e14e5d92-0b4a-47b3-991d-ac18dd6923b3', 'bde2722b-c45e-4256-b81a-721982d75f5b'),
('7a8d49f7-d1db-40b2-a653-3997a0439218', '528250c0-e0e3-4166-aa71-0f150eb2453f', '4b88a5c6-cedb-4f73-809d-e042be5b0779'),
('9d18f5ee-906c-47bb-a7e0-c74f44cbbed5', 'f9cfca3d-691e-490a-8969-1e016b1d5cff', '34737491-a5ae-41dc-a0fb-c1ac20d31b8b'),
('c8f16ed2-901c-4c2d-91e4-eec4ddf71326', 'c8e8b553-bef6-4aea-9ec0-f7e8145b26cd', '34737491-a5ae-41dc-a0fb-c1ac20d31b8b'),
('6e961c3c-bb9c-488a-b51e-861a15e8b80e', 'e404285e-4278-44ce-a6de-a2463088ee64', '34737491-a5ae-41dc-a0fb-c1ac20d31b8b');

INSERT INTO organizers (id, name, mail, phone, website) VALUES
('2295ed87-3f46-428a-a179-e7b7093d2f05', 'IHK-Ulm', 'Musterfrau.Mandy@example.com', '0176-3138557', 'https://www.ihk-ausbildungsmesse.de/'),
('ead07153-6e4b-45ae-abd4-562fc126e92f', 'Bowling-Center-Ulm', 'Mustermann.Max@example.com', '0173-7717645', 'https://wp.neu-ulm-inside.de/new-bowling-center/'),
('d2f041f1-4817-47be-a49a-b6e494a4a8a6', 'Jugendwerkstatt alpha e.V.', 'Susanne@example.com', '0176-5646541', 'https://www.alphaev.de');

INSERT INTO event_categories (id, name) VALUES
('555f2324-87bf-4510-ac08-893076444dbb', 'Education and Training'),
('a464edde-2e2f-455e-a5ca-84fd28f8ad4a', 'Sports'),
('320e2686-a4bc-4990-9b95-8d6f3ddf56ba', 'Information'),
('00db3dbf-b23d-4fb1-a1ff-5e7b321d8301', 'Social'),
('c777f9a8-2061-4fc4-9cae-63130641e4d5', 'Other');

INSERT INTO addresses (id, house_number, latitude, longitude, place, postal_code, street) VALUES
('ff656406-89af-4835-a9bf-571fd978f78f', '27', '48.397340', '9.992980', 'Ulm', '89073', 'ihk-street'),
('b1b84b9d-a30c-4921-8d20-af21058bec79', '22', '48.397340', '9.992980', 'Ulm', '89073', 'müller-street'),
('4ff89e9a-2751-4a15-845e-8283194e81fa', '28', '48.397340', '9.992980', 'Ulm', '89073', 'bowling-street'),
('5dc8809e-8890-4262-b334-a0dff5b8ffd8', '33', '48.397340', '9.992980', 'Ulm', '89073', 'claus-street'),
('4b2589d5-fc11-4cfd-be8a-c2471277b73d', '100', '48.333333', '9.888888', 'Wuppertal', '42285', 'Siegesstrasse'),
('4966fe7a-6770-4b32-aa73-046c64fc6b51', '10', '48.444444', '9.777777', 'Ulm', '89077', 'Blaubeurerstrasse'),
('0fbf3399-9265-40b4-8050-1cba034c2fe8', '21', '42.999999', '8.442342', 'Stuttgart', '70565', 'Ruppmannstrasse');

INSERT INTO events (id, name, description, category_id, organizer_id, title_image_id, address_id) VALUES
('440a6eec-be4d-47e8-b38d-3a819bc4624e', 'Vocational-Training-Fair','First touch with companys','555f2324-87bf-4510-ac08-893076444dbb',
'2295ed87-3f46-428a-a179-e7b7093d2f05','9eaee436-86d7-467e-beb9-be884a9c291c','ff656406-89af-4835-a9bf-571fd978f78f'),
('faf4cf31-f61f-420a-ba24-6253580f02f7', 'Educational lesson','How to apply to jobs','555f2324-87bf-4510-ac08-893076444dbb',
'2295ed87-3f46-428a-a179-e7b7093d2f05','9eaee436-86d7-467e-beb9-be884a9c291c','ff656406-89af-4835-a9bf-571fd978f78f'),
('852826f4-2a85-4e19-b818-e0b182707c00', 'Educational lesson2','How to fill in forms','555f2324-87bf-4510-ac08-893076444dbb',
'2295ed87-3f46-428a-a179-e7b7093d2f05','9eaee436-86d7-467e-beb9-be884a9c291c','ff656406-89af-4835-a9bf-571fd978f78f'),
('d582d740-ae21-42b5-bdb4-91d763ba82ef', 'Education lesson3','Does it look good?','555f2324-87bf-4510-ac08-893076444dbb',
'2295ed87-3f46-428a-a179-e7b7093d2f05','9eaee436-86d7-467e-beb9-be884a9c291c','ff656406-89af-4835-a9bf-571fd978f78f'),
('3e9b15fb-02ce-4d09-bfcd-40751eaee1b7', 'Team-Bowling', 'Team-Bowling in Ulm','a464edde-2e2f-455e-a5ca-84fd28f8ad4a',
'ead07153-6e4b-45ae-abd4-562fc126e92f','c96198d3-2bd6-48bf-9af4-d79fbf66a1a8', '4ff89e9a-2751-4a15-845e-8283194e81fa'),
('92367f6c-e6f0-4b3d-abde-d76f61a4d575', 'Football', 'Football in Ulm','a464edde-2e2f-455e-a5ca-84fd28f8ad4a',
'ead07153-6e4b-45ae-abd4-562fc126e92f','c96198d3-2bd6-48bf-9af4-d79fbf66a1a8', '4ff89e9a-2751-4a15-845e-8283194e81fa'),
('4d2a2a1f-1c36-471b-8483-ae18338a5fd6', 'Opening Event', 'Information and guidance', '320e2686-a4bc-4990-9b95-8d6f3ddf56ba',
'd2f041f1-4817-47be-a49a-b6e494a4a8a6', '670d0b9d-ecfb-4dd2-8f07-ead633c7ef5b', '4b2589d5-fc11-4cfd-be8a-c2471277b73d'),
('8b66b8d7-dd54-460b-b1f1-4204cc660872', 'Game-Night', 'Monopoly, Chess, Uno and more', '00db3dbf-b23d-4fb1-a1ff-5e7b321d8301',
'd2f041f1-4817-47be-a49a-b6e494a4a8a6', 'af5c4d43-54f1-40d1-ba26-5895b91bb287', '4b2589d5-fc11-4cfd-be8a-c2471277b73d'),
('fa30c3b2-a14d-4627-a11b-d2704ee496eb', 'Christmas-Party', 'Celebration of the year 2022', 'c777f9a8-2061-4fc4-9cae-63130641e4d5',
'd2f041f1-4817-47be-a49a-b6e494a4a8a6', '6359d990-bcfc-400a-b685-fe3d7ff9d1c0', '4b2589d5-fc11-4cfd-be8a-c2471277b73d');

INSERT INTO event_media (id, event_id, media_id) VALUES
('78d50278-a724-4d52-bc35-73916062b104', '440a6eec-be4d-47e8-b38d-3a819bc4624e', '9b63a393-6a72-4d7a-b396-714964467d82'),
('2eb441af-7f17-4292-82a2-3bd3acfa8f5a', '440a6eec-be4d-47e8-b38d-3a819bc4624e', 'c3b9e5a2-3064-4157-a6db-9624656c70f1'),
('82d2ac45-8a25-4e4f-9c96-a75f3d8fbc74', '440a6eec-be4d-47e8-b38d-3a819bc4624e', '3e19758d-0abc-4435-84ea-30eb8dddf352'),
('ab6cbbbd-52f0-4f70-a619-f8bb8f5dfdcc', '440a6eec-be4d-47e8-b38d-3a819bc4624e', '710cb609-3892-445f-a54d-3cc55de27fa4');

INSERT INTO schedules (id, start_date, end_date, event_id) VALUES
('2f2f8064-43e3-4e11-a157-d88d4f357e4c', '2022-10-19 10:00:00+02', '2022-10-19 16:00:00+02', '440a6eec-be4d-47e8-b38d-3a819bc4624e'),
('4d43606f-1338-4e61-baec-e080d9e55400', '2022-06-19 18:00:00+02', '2022-05-05 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('58fa6c11-de2d-4c54-b85c-dab0d546473a', '2022-06-18 18:00:00+02', '2022-05-12 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('fc7965c6-ce88-4ecc-a455-045237370b9a', '2022-06-20 18:00:00+02', '2022-05-19 20:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('fdfbfd8e-a940-433e-bdd9-fe91194131a7', '2022-09-15 10:00:00+02', '2022-09-15 14:00:00+02', '4d2a2a1f-1c36-471b-8483-ae18338a5fd6'),
('6cd32c9d-ac17-4e87-8a13-f8634bf8c069', '2022-07-10 19:00:00+02', '2022-07-10 22:00:00+02', '8b66b8d7-dd54-460b-b1f1-4204cc660872'),
('f4db1b24-8f48-464f-9565-8d2057bf5bc2', '2022-05-23 19:00:00+02', '2022-05-23 22:00:00+02', 'fa30c3b2-a14d-4627-a11b-d2704ee496eb'),
('672889bc-4496-41b2-ac3e-6cf9f0679431', '2022-06-16 19:00:00+02', '2022-06-16 22:00:00+02', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7');

INSERT INTO job_types (id, name, color) VALUES
('430d33c4-f0f2-42b1-927f-5270d91c7f32', 'metal trade', '#0000FF'),
('1a773b24-de53-4ccc-9daa-e9261590f88b', 'carpentry', '#00FF00'),
('b840ae1f-f9b8-4223-b50a-1643fb82a674', 'craft', '#FF0000'),
('be24e3e7-b08b-4792-be4e-df34cf8e9851', 'property', '#FFFF00');

INSERT INTO companies (id, name, mail, phone, website, address_id) VALUES
('89aaa55a-168d-4165-85fc-abde073d6732', 'Carpentry Müller GmbH', 'müller@example.com', '11830', 'müllergmbh.de', 'b1b84b9d-a30c-4921-8d20-af21058bec79'),
('f476ed5a-c5c6-429b-bcf0-85fcf8363be3', 'Metal Claus GmbH', 'claus@example.com', '112', 'clausmetal.de', '5dc8809e-8890-4262-b334-a0dff5b8ffd8'),
('f36d8f40-e387-4282-8dca-30e29d644600', 'Ikea Deutschland', 'Fynn@example.com', '0877-252525', 'ikea.com', '4966fe7a-6770-4b32-aa73-046c64fc6b51'),
('bd2c9144-1517-42e5-a35c-503793ccb51d', 'Regierungspräsidium Tübingen', 'mareike@example.com', '0588-545', 'rp.baden-wuerttemberg.de', '0fbf3399-9265-40b4-8050-1cba034c2fe8');

INSERT INTO job_ads(id, content, title, start_date, due_date, company_id, type_id) VALUES
('39cdb5c0-aa0b-4665-a732-e51cb0986e6d', 'content', 'metal worker', '2022-10-1 10:00:00+02', '2022-09-15 10:00:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', '430d33c4-f0f2-42b1-927f-5270d91c7f32'),
('259618dc-3144-427d-9b3a-e0eb087c34a4', 'content', 'metal worker2', '2022-10-1 10:00:00+02', '2022-09-15 10:00:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', '430d33c4-f0f2-42b1-927f-5270d91c7f32'),
('08eb6224-c681-4c59-9a62-62cae1ed2fba', 'content', 'metal worker3', '2022-10-1 10:00:00+02', '2022-09-15 10:00:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', '430d33c4-f0f2-42b1-927f-5270d91c7f32'),
('37ca51d8-ca5d-434f-862f-934b8b4b1289', 'content', 'metal worker4', '2022-10-1 10:00:00+02', '2022-09-15 10:00:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', '430d33c4-f0f2-42b1-927f-5270d91c7f32'),
('e5ce09af-3541-436b-b52f-d148761ca58d', 'content', 'metal worker5', '2022-10-1 10:00:00+02', '2022-09-15 10:00:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', '430d33c4-f0f2-42b1-927f-5270d91c7f32'),
('e7c48d91-851c-4542-ac7b-e8efb66d0f8e', 'content', 'carpetry worker', '2022-04-01 10:00:00+02', '2022-01-31 00:00:00+02', 'f476ed5a-c5c6-429b-bcf0-85fcf8363be3', '1a773b24-de53-4ccc-9daa-e9261590f88b'),
('56f6d14c-13cb-4fcc-a989-9a82eb3fd0e9', 'content', 'carpetry worker2', '2022-04-01 10:00:00+02', '2022-01-31 00:00:00+02', 'f476ed5a-c5c6-429b-bcf0-85fcf8363be3', '1a773b24-de53-4ccc-9daa-e9261590f88b'),
('10bee224-3c01-40cf-8598-1cce46269206', 'content', 'craftsman', '2022-06-01 10:00:00+02', '2022-05-20 09:08:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', 'b840ae1f-f9b8-4223-b50a-1643fb82a674'),
('3c4ff73a-d7a0-4d1c-b0b5-0a5bdd5d01a8', 'content', 'craftsman2', '2022-06-01 10:00:00+02', '2022-05-20 09:08:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', 'b840ae1f-f9b8-4223-b50a-1643fb82a674'),
('bcb51a9e-7da9-4c74-88fa-5110231d4c04', 'content', 'craftsman3', '2022-06-01 10:00:00+02', '2022-05-20 09:08:00+02', '89aaa55a-168d-4165-85fc-abde073d6732', 'b840ae1f-f9b8-4223-b50a-1643fb82a674'),
('9ff39911-533a-4420-9149-f261de7d569c', 'content', 'interior designer', '2022-09-01 10:00:00+02', '2022-05-31 10:00:00+02','f36d8f40-e387-4282-8dca-30e29d644600', 'be24e3e7-b08b-4792-be4e-df34cf8e9851'),
('0ae19f9c-94d6-4fdc-92f4-81595f3d6916', 'content', 'housekeeper', '2022-11-01 10:00:00+02', '2022-09-30 10:00:00+02', 'bd2c9144-1517-42e5-a35c-503793ccb51d', 'be24e3e7-b08b-4792-be4e-df34cf8e9851');

INSERT INTO assignment_states (id, name) VALUES
('aad3dd2b-f29d-4a87-8056-f72b0aae3dc0', 'ASSIGNED'),
('577d7e21-f994-433f-8f7b-c7ac3887d692', 'DONE');

INSERT INTO questionnaires(id, name) VALUES
('0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f', 'Metal-Group-Evaluation'),
('0e1a2d66-5629-4988-b45f-d207a3b9d4f4', 'Carpentry-Group-Evaluation');

INSERT INTO questions(id, item, questionnaire_id, sequence_order) VALUES
('7c6d47ab-f4fe-46c6-869c-0538c1d2c2c4', 'question1', '0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f', 1),
('4100e7c3-e648-4b68-b937-89d9fec60880', 'question2', '0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f', 2),
('90f4880d-dae8-4290-922c-216c48ce249d', 'question1', '0e1a2d66-5629-4988-b45f-d207a3b9d4f4', 1),
('90a8772d-f551-4111-9601-069e453ef770', 'question2', '0e1a2d66-5629-4988-b45f-d207a3b9d4f4', 2);

INSERT INTO assignments(id, assignment_state_id, questionnaire_id, user_id) VALUES
('5eb2d309-b5f7-45d5-9ed9-aa731cc6c4fd', 'aad3dd2b-f29d-4a87-8056-f72b0aae3dc0', '0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('89cece0b-8068-49d3-ad74-e882386c5c8b', '577d7e21-f994-433f-8f7b-c7ac3887d692', '0e1a2d66-5629-4988-b45f-d207a3b9d4f4', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('5e59bbab-2be3-4a21-95fe-f424f2914905', 'aad3dd2b-f29d-4a87-8056-f72b0aae3dc0', '0e0fc8f9-7271-48a1-95dd-74d1fa8bab3f', '28fc7d3a-afc7-49ca-8070-eea8671abab3'),
('fa4c794a-27e8-48ac-a511-437802941ae1', 'aad3dd2b-f29d-4a87-8056-f72b0aae3dc0', '0e1a2d66-5629-4988-b45f-d207a3b9d4f4', '36808f63-4b6b-40e7-b2ee-a91f657e4e58');

INSERT INTO answers(id, rating, question_id, assignment_id) VALUES
('892d91b3-e73b-4bd1-9874-99f810d30d56', '5', '7c6d47ab-f4fe-46c6-869c-0538c1d2c2c4', '5eb2d309-b5f7-45d5-9ed9-aa731cc6c4fd'),
('7c8e86c7-4049-42ad-89b6-6c3da2bd8a38', '3', '4100e7c3-e648-4b68-b937-89d9fec60880', '5eb2d309-b5f7-45d5-9ed9-aa731cc6c4fd'),
('9963bfd5-877c-4e50-8cca-3d3f3a094857', '2', '90f4880d-dae8-4290-922c-216c48ce249d', '89cece0b-8068-49d3-ad74-e882386c5c8b'),
('ad0f00a3-ef45-4b14-9ecf-5b5d78930a81', '2', '90a8772d-f551-4111-9601-069e453ef770', '89cece0b-8068-49d3-ad74-e882386c5c8b'),
('a1c26925-4a9a-4ed8-8176-9608d9e3a4da', '1', '4100e7c3-e648-4b68-b937-89d9fec60880', '5e59bbab-2be3-4a21-95fe-f424f2914905');

INSERT INTO link_categories(id, name) VALUES
('ff6340c7-e973-45c2-b1a0-1ff9cd70a861', 'Was will ich? Was kann ich?'),
('a5cb2e7f-baa2-4d28-a4a8-9106b2024049', 'Welche Ausbildungen gibt es?');

INSERT INTO links(id, url, title, category_id) VALUES
('077d22e5-1e37-463b-bd91-2bf84873ad46', 'https://planet-beruf.de/schuelerinnen/video/video-how-to-bewerbungsvideos-drehen', 'How to .. Bewerbungsvideo drehen', 'ff6340c7-e973-45c2-b1a0-1ff9cd70a861'),
('61a37d3e-e72a-4504-8766-2eb2176c17ed', 'https://planet-beruf.de/schuelerinnen/welche-ausbildungen-gibt-es/welche-berufe-gibt-es', 'Welche Berufe gibt es?', 'a5cb2e7f-baa2-4d28-a4a8-9106b2024049');

INSERT INTO template_types(id, name) VALUES
('e99c1aec-a2ce-4c10-8532-43d518551330', 'Deckblatt'),
('5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a', 'Anschreiben'),
('31d957ee-7b0b-4118-b470-ed08719df47f', 'Lebenslauf');

INSERT INTO templates(id, name, content, template_type_id) VALUES
('2f51d668-eab1-478b-8b0d-b2b16d38d64a', 'Vorlage1', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a'),
('4f27f7cc-f849-4438-83fe-1b8fffccb2c5', 'Vorlage2', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a');

INSERT INTO user_templates(id, name, content, template_type_id, user_id) VALUES
('0c132c71-d17c-4d1e-b473-f6f0fa085c6a', 'Firma1', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('d5ef1f29-56f8-4d89-bc77-e963c6a7bb3e', 'Firma2', 'document', '5852aa11-4e5c-4d8d-bc41-9fa44cb6ca1a', '36808f63-4b6b-40e7-b2ee-a91f657e4e58');

INSERT INTO feedbacks(id, rating, course_id, user_id) VALUES
('566458a2-fe16-4850-8e68-44c1cd5fd8cd', '3', '2163ee48-8605-46eb-bbd0-c62497007b18', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('50bb5dd5-e180-4cf4-9f30-f16bea9acfa8', '5', 'ab36cc61-a884-4da8-be52-db8f19abcbd1', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('71fb7dde-53f7-4df1-b285-6c4d3e68c4f6', '2', 'ab36cc61-a884-4da8-be52-db8f19abcbd1', 'e14e5d92-0b4a-47b3-991d-ac18dd6923b3');

INSERT INTO subscription_types(id, name, description) VALUES
('a3d3c3ce-6908-42e6-97f7-ddaddae8deb3', 'Events', 'Bowling'),
('e7e34f9d-31fb-472e-aba7-15b14dcef865', 'JobAds', 'metal worker');

INSERT INTO subscriptions(id, device_token, user_id) VALUES
('1038e9e5-a82f-4d00-83fe-6ea0910d2c8d', 'secretkey', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('92557db8-b5fd-4bee-920c-ba2fe348713e', 'secretkey2', '36808f63-4b6b-40e7-b2ee-a91f657e4e58');

INSERT INTO user_events(id, user_id, event_id) VALUES
('18c923a3-8160-4beb-bcc6-738b1fe5ad00', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', '440a6eec-be4d-47e8-b38d-3a819bc4624e'),
('ef115c0f-acc5-4700-b1e8-bb3ccfe2b194', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7'),
('7a8da824-9da6-4523-aa27-9571a315697d', '28fc7d3a-afc7-49ca-8070-eea8671abab3', '440a6eec-be4d-47e8-b38d-3a819bc4624e'),
('c5c89a43-f720-4dd6-ad9e-05e968519b5b', '28fc7d3a-afc7-49ca-8070-eea8671abab3', '3e9b15fb-02ce-4d09-bfcd-40751eaee1b7');

INSERT INTO user_job_ads(id, user_id, job_ad_id) VALUES
('c1f7570c-96ff-4e83-95e2-b86a7691f3a2', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', '39cdb5c0-aa0b-4665-a732-e51cb0986e6d'),
('3afe8863-71e7-4488-8925-7d453eb2dc2a', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'e7c48d91-851c-4542-ac7b-e8efb66d0f8e'),
('580f6081-0edb-431a-a864-cbf18d270fcf', '28fc7d3a-afc7-49ca-8070-eea8671abab3', '39cdb5c0-aa0b-4665-a732-e51cb0986e6d'),
('511b9380-ae00-4cde-8108-e010ef5fb21e', '28fc7d3a-afc7-49ca-8070-eea8671abab3', 'e7c48d91-851c-4542-ac7b-e8efb66d0f8e');

INSERT INTO pages(id, slug, content, name, title_image_id, video_id) VALUES
('8e65e9a7-f980-4be2-b412-9ea0c085df04', 'https://jugendwerkstatt/titleimage/slug', 'image', 'image', '76460bc7-ed5c-4f70-a377-46e2cf49e86f', null),
('3c918357-244d-4a6c-8b87-223da7364cdf', 'https://jugendwerkstatt/openingvideo/slug', 'video', 'video', null, 'ceb59217-29f6-4a58-a8f1-776b698a9c5a');

INSERT INTO participants(id, chat_id, user_id) VALUES
('87c048f9-26fb-4b3f-9d37-f03556c08edd', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('75cd4f05-816a-45d0-b242-e12b00499e7f', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('824d97ce-a60c-477d-9dc8-d48e8a5ba2d3', '41bf7460-1a1a-41cb-8b2f-a89688671b33', 'e14e5d92-0b4a-47b3-991d-ac18dd6923b3'),
('36ea3a0f-31e7-4db0-b2f5-d9b42b5139c3', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '528250c0-e0e3-4166-aa71-0f150eb2453f'),
('30932b23-9b0a-472b-b16a-a6ae1c80740d', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '528250c0-e0e3-4166-aa71-0f150eb2453f');

INSERT INTO messages(id, content, chat_id, user_id, media_id, parent_id) VALUES
('456588b4-f512-4d78-953c-53dee35b18c0', 'Hello fellow woodworkers!', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '528250c0-e0e3-4166-aa71-0f150eb2453f', null, null),
('125b98f8-0310-4455-ba31-7a43a6cdc7b0', 'Hello all!', '41bf7460-1a1a-41cb-8b2f-a89688671b33', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', null, null),
('c25cca5d-3f2c-4297-bf41-50a9f98a59fa', 'Hello Supervisor, here is my form!', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '36808f63-4b6b-40e7-b2ee-a91f657e4e58', 'ceb59217-29f6-4a58-a8f1-776b698a9c5a', null),
('569f2578-177e-4e08-a206-9f2626b8c258', 'This is not a form!', 'b2cb2479-1c75-4bf6-9543-38c53a55a70f', '528250c0-e0e3-4166-aa71-0f150eb2453f', null, 'c25cca5d-3f2c-4297-bf41-50a9f98a59fa');

INSERT INTO read_receipts(id, message_id, participant_id) VALUES
('2ce49f62-f74f-480a-b43e-f27232b8dc29', '456588b4-f512-4d78-953c-53dee35b18c0', '87c048f9-26fb-4b3f-9d37-f03556c08edd'),
('4c524fd6-f7ae-481c-89c8-2b16ddd1ca48', 'c25cca5d-3f2c-4297-bf41-50a9f98a59fa', '75cd4f05-816a-45d0-b242-e12b00499e7f'),
('af00cf23-a10e-45b0-8c5f-2ae481764d1d', 'c25cca5d-3f2c-4297-bf41-50a9f98a59fa', '30932b23-9b0a-472b-b16a-a6ae1c80740d');

INSERT INTO notifications(id, title, content, read, user_id) VALUES
('af3c1da6-585d-47fd-ab18-d0c987be8872', 'ThisIsTheTitle', 'ThisIsTheContent', true, '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('794560a6-76da-41b3-a5f3-f868f764caad', 'SecondTitle', 'SecondContent', false, '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('c80f0c5e-50ef-49df-9dba-9fab914c0de5', 'Notification', 'ThisIsANotification', false, '36808f63-4b6b-40e7-b2ee-a91f657e4e58'),
('750a6cc3-ba6c-429c-9753-2c43b744d614', 'AnotherNotification', 'ThisIsAnotherNotification', false, '36808f63-4b6b-40e7-b2ee-a91f657e4e58');


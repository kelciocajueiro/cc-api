CREATE TABLE public.jobs
(
    id              bigserial     NOT NULL,
    job_description varchar(1000) NOT NULL,
    job_name        varchar(255)  NOT NULL,
    CONSTRAINT jobs_pkey PRIMARY KEY (id)
);

CREATE TABLE public.volunteers
(
    id         bigserial    NOT NULL,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NULL,
    CONSTRAINT volunteers_pkey PRIMARY KEY (id)
);

CREATE TABLE public.jobs_volunteers
(
    volunteer_id int8 NOT NULL,
    job_id       int8 NOT NULL,
    CONSTRAINT jobs_volunteers_pkey PRIMARY KEY (volunteer_id, job_id)
);

-- public.jobs_volunteers foreign keys

ALTER TABLE public.jobs_volunteers
    ADD CONSTRAINT fkahhrjkbbuh8c7d8qxy9q57y6x FOREIGN KEY (job_id) REFERENCES public.jobs (id);
ALTER TABLE public.jobs_volunteers
    ADD CONSTRAINT fkggvkqc597lsd2r31d1erlee8h FOREIGN KEY (volunteer_id) REFERENCES public.volunteers (id);


INSERT INTO public.jobs (job_description, job_name)
VALUES ('Manage the front desk , keep track of signed in guest and print entry badges.

At http://contributetocommunity.org we organize events which would benefit the community. These range from organizing a yoga workshop, career fairs to food festivals always rely upon the willingness and generosity of our volunteers who want to give something back to the community. Please check the events calendar to view the job postings and do apply for one or more openings as per your availability!',
        'Front Desk'),
       ('Organize and arrange the props on the perofrmance stage during the event.

At http://contributetocommunity.org we organize events which would benefit the community. These range from organizing a yoga workshop, career fairs to food festivals always rely upon the willingness and generosity of our volunteers who want to give something back to the community. Please check the events calendar to view the job postings and do apply for one or more openings as per your availability!',
        'Stage Organizer'),
       ('Provide information to the guests about events, locations & details

At http://contributetocommunity.org we organize events which would benefit the community. These range from organizing a yoga workshop, career fairs to food festivals always rely upon the willingness and generosity of our volunteers who want to give something back to the community. Please check the events calendar to view the job postings and do apply for one or more openings as per your availability!',
        'Guest Coordinator'),
       ('Handle food logistics, preperation & serving

At http://contributetocommunity.org we organize events which would benefit the community. These range from organizing a yoga workshop, career fairs to food festivals always rely upon the willingness and generosity of our volunteers who want to give something back to the community. Please check the events calendar to view the job postings and do apply for one or more openings as per your availability!',
        'Food Services'),
       ('Manage stationary supplies.

At http://contributetocommunity.org we organize events which would benefit the community. These range from organizing a yoga workshop, career fairs to food festivals always rely upon the willingness and generosity of our volunteers who want to give something back to the community. Please check the events calendar to view the job postings and do apply for one or more openings as per your availability!',
        'Stationary Services'),
       ('Manage security outside of each event space by controlling entry exits.

At http://contributetocommunity.org we organize events which would benefit the community. These range from organizing a yoga workshop, career fairs to food festivals always rely upon the willingness and generosity of our volunteers who want to give something back to the community. Please check the events calendar to view the job postings and do apply for one or more openings as per your availability!',
        'Security Personnel'),
       ('Act as a supervisor to the team of volunteers like. We prefer to have past history working for us in order to qualify as a supervisor.

At http://contributetocommunity.org we organize events which would benefit the community. These range from organizing a yoga workshop, career fairs to food festivals always rely upon the willingness and generosity of our volunteers who want to give something back to the community. Please check the events calendar to view the job postings and do apply for one or more openings as per your availability!',
        'General Supervisor');

INSERT INTO public.volunteers (first_name, last_name)
VALUES ('Barry', 'Towne'),
       ('Ethyl', 'Donnelly'),
       ('Felipe', 'Breitenberg'),
       ('Fred', 'Hettinger'),
       ('Freeda', 'Koelpin'),
       ('Guy', 'Considine'),
       ('Gillian', 'Gerhold'),
       ('Gloria', 'Williamson'),
       ('Gordon', 'Kirlin'),
       ('Isabell', 'Prosacco');

INSERT INTO public.volunteers (first_name, last_name)
VALUES ('Jacky', 'Renner'),
       ('Kaitlyn', 'Tillman'),
       ('Laverna', 'D''Amore'),
       ('Lonnie', 'Kreiger'),
       ('Maeve', 'Hahn'),
       ('Marcia', 'Corkery'),
       ('Melyssa', 'Boehm'),
       ('Muriel', 'Metz'),
       ('Nat', 'Conn'),
       ('Orin', 'Bahringer');

INSERT INTO public.volunteers (first_name, last_name)
VALUES ('Pascale', 'Hayes'),
       ('Phoebe', 'Collier'),
       ('Rowena', 'Walsh'),
       ('Sabryna', 'Schowalter'),
       ('Sahil', 'Pacocha'),
       ('Sanford', 'Toy'),
       ('Viola', 'Wisozk');

INSERT INTO public.jobs_volunteers (volunteer_id, job_id)
VALUES (6, 1),
       (10, 1),
       (12, 1),
       (13, 1),
       (16, 1),
       (19, 1),
       (20, 1),
       (23, 1),
       (24, 1),
       (1, 2);

INSERT INTO public.jobs_volunteers (volunteer_id, job_id)
VALUES (7, 2),
       (15, 2),
       (17, 2),
       (18, 2),
       (22, 2),
       (25, 2),
       (2, 3),
       (5, 3),
       (8, 3),
       (9, 3);

INSERT INTO public.jobs_volunteers (volunteer_id, job_id)
VALUES (10, 3),
       (11, 3),
       (13, 3),
       (14, 3),
       (18, 3),
       (19, 3),
       (20, 3),
       (21, 3),
       (22, 3),
       (24, 3);

INSERT INTO public.jobs_volunteers (volunteer_id, job_id)
VALUES (27, 3),
       (2, 4),
       (3, 4),
       (4, 4),
       (6, 4),
       (11, 4),
       (16, 4),
       (20, 4),
       (21, 4),
       (26, 4);

INSERT INTO public.jobs_volunteers (volunteer_id, job_id)
VALUES (3, 5),
       (5, 5),
       (14, 5),
       (24, 5),
       (5, 6),
       (9, 6),
       (11, 6),
       (17, 6),
       (21, 6),
       (22, 6);

INSERT INTO public.jobs_volunteers (volunteer_id, job_id)
VALUES (25, 6),
       (1, 7),
       (3, 7),
       (4, 7),
       (7, 7),
       (9, 7),
       (10, 7),
       (12, 7),
       (16, 7),
       (17, 7);

INSERT INTO public.jobs_volunteers (volunteer_id, job_id)
VALUES (23, 7),
       (25, 7),
       (26, 7),
       (27, 7);

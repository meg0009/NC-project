--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: members; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.members (
    id integer NOT NULL,
    user_name character varying(255) NOT NULL,
    fio character varying(255) NOT NULL
);


ALTER TABLE public.members OWNER TO postgres;

--
-- Name: members_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.members ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.members_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: person; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.person (
    id integer NOT NULL,
    first_name character varying(255),
    last_name character varying(255),
    patronymic character varying(255)
);


ALTER TABLE public.person OWNER TO postgres;

--
-- Name: person_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.person ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.person_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: record; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.record (
    id integer NOT NULL,
    member integer NOT NULL,
    tournament integer NOT NULL
);


ALTER TABLE public.record OWNER TO postgres;

--
-- Name: record_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.record ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.record_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: tournament; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.tournament (
    id integer NOT NULL,
    date timestamp with time zone,
    rating_range integer,
    address character varying(255),
    phone character varying(20),
    organizer_name character varying(255),
    cost numeric,
    max integer,
    name character varying(255),
    division character varying(255)
);


ALTER TABLE public.tournament OWNER TO postgres;

--
-- Name: tournament_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.tournament ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.tournament_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    user_name character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    role character varying(255) DEFAULT 'ROLE_USER'::character varying NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Data for Name: members; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.members (id, user_name, fio) FROM stdin;
1	admin@admin.admin	admin
2	alex@alex.ru	Алексович Александр Александрович
3	alexey@alexey.com	Тарасов Алексей Дмитриевич
4	vasilii@vasilii.com	Тарасов Василий Дмитриевич
5	dmitry@dmitry.com	Захаров Дмитрий Сергеевич
6	anna@mail.ru	Харитонова Анна Сергеевна
7	fedora@gmail.com	Тарасов Федор Сергеевич
8	kate@mail.ru	Бахарева Екатерина Сергеевна
9	min_kate@mail.ru	Минеева Екатерина Александровна
10	bykov_dima@gmail.com	Быков Дмитрий Сергеевич
11	nastya@ya.ru	Смирнова Анастасия Степановна
12	ksu@yandex.ru	Костюнина Ксения Владимировна
13	tanya@mail.ru	Учуватова Татьяна Васильевна
14	newton@gmail.com	Ньютон Исаак
15	turing@ya.ru	Тьюринг Алан
16	cauchy@gmail.com	Коши Огюстен Луи
17	taylor@yahoo.com	Тейлор Брук
18	fourier@yahoo.com	Фурье Жан-Батист Жозеф
19	fermat@gmail.com	Ферма Пьер
20	tesla@yahoo.com	Тесла Никола
21	pascal@ya.ru	Паскаль Блез
22	heine@gmail.com	Гейне Эдуард
23	riemann@yahoo.com	Риман Бернхард
24	lobachevsky@yandex.ru	Лобачевский Николай Иванович
25	demidovich@mail.ru	Демидович Борис Павлович
26	egor@mail.ru	Кузьмичев Егор Алексеевич
\.


--
-- Data for Name: person; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.person (id, first_name, last_name, patronymic) FROM stdin;
1	Василий	Тарасов	Дмитриевич
2	Mike	Wazowski	\N
3	Дмитрий	Тарасов	Владимирович
4	Алексей	Тарасов	Дмитриевич
5	Татьяна	Тарасова	Дмитриевна
6	Дмитрий	Захаров	Сергеевич
7	Анна	Харитонова	Сергеевна
8	Алина	Штокман	Анатольевна
9	Лев	Толстой	Николаевич
10	Александер	Пушкин	Сергеевич
11	Невил	Долгопупс	
12	Гарри	Поттер	
13	Северус	Снейп	
14	Марина	Черноперова	Генадьевна
15	Анна	Шапошникова	Сергеевна
16	Иван	Иванов	Иванович
17	Тарас	Тарасов	Тарасович
18	Анатолий	Харько	Анатольевич
19	Анатолий	Харько	Анатольевич
20	Иван	Харитонова	Иванович
21	Никита	Антипов	Николаевич
22	Алексей	Лиходеев	Дмитриевич
23	Анастасия	Куликова	Викторовна
24	Генадий	Букин	Петрович
25	аааа	аааа	ааа
26	nnnnnn	nnnnnnn	nnnnnnn
\.


--
-- Data for Name: record; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.record (id, member, tournament) FROM stdin;
1	2	2
2	3	2
3	4	2
4	5	2
5	6	2
6	7	2
7	8	2
8	9	2
9	10	2
10	11	2
11	12	2
12	13	2
13	14	2
14	15	2
15	16	2
16	17	2
17	18	2
18	19	2
19	20	2
20	21	2
21	22	2
22	23	2
23	4	3
24	5	3
25	6	3
26	7	3
27	8	3
28	9	3
29	10	3
30	17	3
31	18	3
32	19	3
33	20	3
34	21	3
35	22	3
36	23	3
38	5	1
39	6	1
40	7	1
41	10	1
42	11	1
43	12	1
44	13	1
45	14	1
46	15	1
47	16	1
48	17	1
49	18	1
50	25	1
52	12	4
53	13	4
54	14	4
55	15	4
56	16	4
57	17	4
63	4	7
\.


--
-- Data for Name: tournament; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.tournament (id, date, rating_range, address, phone, organizer_name, cost, max, name, division) FROM stdin;
1	2022-04-05 18:30:00+00	0	Григорьевский съезд, 5 (TTCLUB)	+79999999999	Александр	250	20	TTCLUB Свободный	Свободный
2	2022-04-02 10:00:00+00	220	Григорьевский съезд, 5 (TTCLUB)	+79999999999	Антон	250	20	TTCLUB 3Д	Третий
3	2022-04-03 14:00:00+00	430	Григорьевский съезд, 5 (TTCLUB)	+79999999999	Ольга	250	20	TTCLUB Высший	Высший
4	2022-04-03 15:00:00+00	400	ул. Родионова, 28 (СДЮШОР №13)	+79999999999	Александр	150	50	СДЮШОР №13 Первый	Первый
6	2022-04-09 10:00:00+00	220	Григорьевский съезд, 5 (TTCLUB)	+79999999999	Антон	250	20	TTCLUB 3Д	Третий
7	2022-04-10 14:00:00+00	250	Григорьевский съезд, 5 (TTCLUB)	+79101351111	Анастасия	250	20	TTCLUB Высший	Высший
10	2022-04-10 18:00:00+00	400	ул. Родионова, 28 (СДЮШОР №13)	+79101351111	Мария	150	50	СДЮШОР №13 Первый	Первый
11	2022-04-09 14:00:00+00	0	Григорьевский съезд, 5 (TTCLUB)	+79999999999	Мария	250	20	TTCLUB Свободный	Свободный
12	2022-09-14 13:00:00+00	150	Григорьевский съезд, 5 (TTCLUB)	+79999999999	Василий	250	20	TTCLUB Высший	Высший
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (user_name, password, role) FROM stdin;
admin@admin.admin	$2y$10$Gc3PmqWnjBM.DEUGPQD7Qul5cZDILI7aMa6azsf6TZ23HtaYtH3n6	ROLE_ADMIN
alex@alex.ru	$2a$10$N3VznB32wd8xlSaUuFgH2eXuFr/DW3m5ZkKBNtCfgojkYmB/npXiC	ROLE_USER
alexey@alexey.com	$2a$10$MgAINZdEbKrHVVEbcvj6keL59dDyZPS4kMZIcltvbfsDsSCfNOhWi	ROLE_USER
vasilii@vasilii.com	$2a$10$TZWN5giRE3XgBk1j94hHNOkdtg6GyUmXNo2N030zQHDpTUepX7jPa	ROLE_USER
dmitry@dmitry.com	$2a$10$yIqmFNYWpd6l.03D668LC.ONMtk4iwMJqe0l3UuoDTvVfrtl/1fIK	ROLE_USER
anna@mail.ru	$2a$10$U1rKK/Ctm91lb83ojv2tDeefE3cHyO2Bcf4EYfGb.FBi8pnLzYOhi	ROLE_USER
fedora@gmail.com	$2a$10$7V0mfIV0zkf0URrJyrvp.ecNjdsZr.dbnytR3l8GsUitTX9vzDXAW	ROLE_USER
kate@mail.ru	$2a$10$cQnvhbD6NQ4UfW1kRTetJuwR.qd8Gr0LR88HqE90kFoJQePefmaDW	ROLE_USER
min_kate@mail.ru	$2a$10$ODia0/tLrqnwXWZhzifWO.Py.6dhu.JFr4l628JdN1aRe8wQ8h2Ji	ROLE_USER
bykov_dima@gmail.com	$2a$10$5xPI8N86laVaCaUW4EgRlO/tyfzfYKo1SM23vc2FAWHRCdZJ4mvOW	ROLE_USER
nastya@ya.ru	$2a$10$MF2zctCgHAT5teLybGi3uOzmkV8ROe/wY1IZ3VEW8V57QZDNuNCa6	ROLE_USER
ksu@yandex.ru	$2a$10$ZcCjyYfHZs4GN5qfylr9xetKSOBAuFQONW1r8kV0fBGxy3tYLLu0a	ROLE_USER
tanya@mail.ru	$2a$10$CNrd65HdX8dIagByMmjmveb.7eJypZHw8sO2BrVvJgbM8HkaAkAm2	ROLE_USER
newton@gmail.com	$2a$10$j2enprQtdwIZJioBGAP12eOD2XDDTONypd8mWXQm8pRvnQfuG6U7e	ROLE_USER
turing@ya.ru	$2a$10$LZ/zqnTT9qDpSGkxoRKrDu/z8wuMjVLN81HNvfIl57cJxFx8dt40C	ROLE_USER
cauchy@gmail.com	$2a$10$qyhtKaru0azg5MaCI3n0ne7DjSPv5DkOXkKlGdWwl7uvzJOmm/gwu	ROLE_USER
taylor@yahoo.com	$2a$10$3FTAuYLQWe897cOP1xeHhe8SVb1NXXEUcSuLMZxje3YfHF6Hj0Afa	ROLE_USER
fourier@yahoo.com	$2a$10$x9k4HWhxbprdSE9DviookelTrXp6XDerXsCxYusuWJGz5RauPIaCu	ROLE_USER
fermat@gmail.com	$2a$10$28cmoabf2W2BFkPSHJDucOtc2Xrta89fGB3.atvepOtFdfnf0PulW	ROLE_USER
tesla@yahoo.com	$2a$10$L2jCKJGARqRdKVwX86oot..AAoOTEBwLf4OTPQIHja6ozPoLvSh9u	ROLE_USER
pascal@ya.ru	$2a$10$NHSUaJ.z6W62IyI1hwlJuuQlRDJNkiPkd3HFxmjziu1L122MX4BG6	ROLE_USER
heine@gmail.com	$2a$10$aFdYS0M1B9qSENPDVqUqT.f2qs4HmyU/RT2xtlc1eGuh4DfxjLLLm	ROLE_USER
riemann@yahoo.com	$2a$10$Y0E/.Sa0SzO6oZO3wnUNrOuqAeiVdbdK0DM9pORgBE0feLw675kua	ROLE_USER
lobachevsky@yandex.ru	$2a$10$dR5FXoFHEVscJ98Q3FYCDe5XnFq0iY.h5mXLSpnPLJAGkI.D5WsFi	ROLE_USER
demidovich@mail.ru	$2a$10$ztOEVDR5teVFcXI156FwSePaWgmthVu5ADWlARJS6dVJpfJOCXnF6	ROLE_USER
egor@mail.ru	$2a$10$6pOnCvcuDPPQfpjeE8yS6.PY1Mn3.RekPbchc1Gbg3EI3/UDFNzMy	ROLE_USER
\.


--
-- Name: members_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.members_id_seq', 26, true);


--
-- Name: person_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.person_id_seq', 26, true);


--
-- Name: record_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.record_id_seq', 63, true);


--
-- Name: tournament_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.tournament_id_seq', 12, true);


--
-- Name: members members_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.members
    ADD CONSTRAINT members_pkey PRIMARY KEY (id);


--
-- Name: person person_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.person
    ADD CONSTRAINT person_pkey PRIMARY KEY (id);


--
-- Name: record record_member_tournament_key; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT record_member_tournament_key UNIQUE (member, tournament);


--
-- Name: record record_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT record_pkey PRIMARY KEY (id);


--
-- Name: tournament tournament_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.tournament
    ADD CONSTRAINT tournament_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_name);


--
-- Name: record member_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT member_id FOREIGN KEY (member) REFERENCES public.members(id) ON DELETE CASCADE;


--
-- Name: record tournament_id; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.record
    ADD CONSTRAINT tournament_id FOREIGN KEY (tournament) REFERENCES public.tournament(id) ON DELETE CASCADE NOT VALID;


--
-- Name: members user_name; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.members
    ADD CONSTRAINT user_name FOREIGN KEY (user_name) REFERENCES public.users(user_name) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- PostgreSQL database dump complete
--


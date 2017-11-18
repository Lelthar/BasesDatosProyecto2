CREATE TABLE sede (
    nombre_sede CHAR(40) NOT NULL,
    nombre_estadio CHAR(40) NOT NULL,
    capacidad_estadio INTEGER CHECK (25000 <= capacidad_estadio AND capacidad_estadio <= 150000),
    link_estadio VARCHAR(200) NOT NULL,
    PRIMARY KEY (nombre_sede, nombre_estadio)
)

INSERT INTO sede VALUES('San Petesburgo', 'Estadio Krestovski', 68134, 'https://www.google.co.cr/maps/place/St.+Petersburg+Stadium/@59.9730497,30.2180579,17z/data=!3m1!4b1!4m5!3m4!1s0x469636ecc0b23029:0x385942b59e488b88!8m2!3d59.973047!4d30.2202466');
INSERT INTO sede VALUES('Moscú', 'Estadio Olímpico Luzhnikí', 81500, 'https://www.google.co.cr/maps/place/Estadio+de+Loujniki/@55.715765,37.551527,17z/data=!3m1!4b1!4m5!3m4!1s0x46b54b8719336f0f:0x65b83e6741430782!8m2!3d55.715762!4d37.5537157');
INSERT INTO sede VALUES('Túshino, Moscú', 'Otkrytie Arena', 45360, 'https://www.google.co.cr/maps/place/Otkrytie+Arena/@55.8177978,37.4385126,17z/data=!3m1!4b1!4m5!3m4!1s0x46b54879fb49c793:0xfc3b0d63bfbcc532!8m2!3d55.8177948!4d37.4407013');
INSERT INTO sede VALUES('Kaliningrado', 'Arena Baltika', 45015, 'https://www.google.co.cr/maps/place/Kaliningrad+Stadium/@54.6981599,20.5316701,17z/data=!3m1!4b1!4m5!3m4!1s0x46e31668f0417d05:0xba56dc7ac1d5fd13!8m2!3d54.6981568!4d20.5338588');
INSERT INTO sede VALUES('Kazán', 'Kazán Arena', 45379, 'https://www.google.co.cr/maps/place/Kazan+Arena/@55.8209656,49.1584423,17z/data=!3m1!4b1!4m5!3m4!1s0x415eb2a3797cea25:0x3bbd6eea66b8a8b4!8m2!3d55.8209626!4d49.160631');
INSERT INTO sede VALUES('Ekaterimburgo', 'Estadio Central', 35000, 'https://www.google.com/maps/place/Estadio+Central/@56.8324898,60.5735848,15z/data=!4m5!3m4!1s0x0:0x85758898fe2ae44!8m2!3d56.8324898!4d60.5735848');
INSERT INTO sede VALUES('Samara', 'Kosmos Arena', 63000, 'https://www.google.co.cr/maps/place/%22Kosmos+Arena%22/@53.2779426,50.2353967,17z/data=!3m1!4b1!4m5!3m4!1s0x4166184dbd004203:0xed8086b3ea6f9942!8m2!3d53.2779394!4d50.2375854');
INSERT INTO sede VALUES('Saransk', 'Mordovia Arena', 45100, 'https://www.google.co.cr/maps/place/Mordovia+Arena/@54.1818701,45.2015223,17z/data=!3m1!4b1!4m5!3m4!1s0x414408f11a95ed59:0x324308f8be8215c2!8m2!3d54.181867!4d45.203711');
INSERT INTO sede VALUES('Rostov del Don', 'Rostov Arena', 43702, 'https://www.google.co.cr/maps/place/Rostov+Arena/@47.2099452,39.7353401,17z/data=!3m1!4b1!4m5!3m4!1s0x40e3b97ccdbbea2f:0x6701b2a5e6851845!8m2!3d47.2099416!4d39.7375288');
INSERT INTO sede VALUES('Sochi', 'Estadio Olímpico Fisht', 48000, 'https://www.google.co.cr/maps/place/Estadio+Ol%C3%ADmpico+de+Sochi/@43.4020153,39.9535202,17z/data=!3m1!4b1!4m5!3m4!1s0x40f5950ba45b45d7:0x9ec94ebaaff808cc!8m2!3d43.4020114!4d39.9557089');

commit;




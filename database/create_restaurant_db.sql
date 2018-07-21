DROP SCHEMA IF EXISTS RESTAURANT ;
CREATE SCHEMA RESTAURANT;

USE RESTAURANT;

CREATE TABLE DISHES
	(
		DISH_CODE VARCHAR(3) PRIMARY KEY,
        DISH_NAME VARCHAR(60) NOT NULL,
        DISH_TYPE VARCHAR (20) NOT NULL,				-- ANTIPASTO, PRIMO, SECONDO, DOLCE, BEVANDA
		DISH_PRICE DOUBLE NOT NULL,
        VEGAN BOOLEAN DEFAULT FALSE,					-- TRUE SE IL PIATTO E' PER VEGANI
        VEGETARIAN BOOLEAN DEFAULT FALSE,				-- TRUE SE IL PIATTO E' PER VEGETARIANI
        CELIAC BOOLEAN DEFAULT FALSE					-- TRUE SE IL PIATTO E' PER CELIACI
     );

-- INSERIMENTO ANTIPASTI
INSERT INTO DISHES VALUES('A01', 'TAGLIERE SALUMI MISTI', 'STARTER', 5, FALSE, FALSE, TRUE);
INSERT INTO DISHES VALUES('A02', 'TARTARE DI SALMONE', 'STARTER', 15, FALSE, FALSE, TRUE);
INSERT INTO DISHES VALUES('A03', 'GNOCCO FRITTO', 'STARTER', 5, FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES('A04', 'VERDURE GRIGLIATE', 'STARTER', 4, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES('A05', 'BRUSCHETTA CON POMODORINI', 'STARTER', 2, TRUE, TRUE, FALSE);
INSERT INTO DISHES VALUES('A06', 'TORTA SALATA', 'STARTER', 3, FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES('A07', 'TAGLIERE FORMAGGI MISTI', 'STARTER', 5, FALSE, TRUE, TRUE);
INSERT INTO DISHES VALUES('A08', 'MOZZARELLA IN CARROZZA', 'STARTER', 3, FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES('A09', 'COZZE GRATINATE', 'STARTER', 7.5, FALSE, FALSE, FALSE);
INSERT INTO DISHES VALUES('A10', 'TOFU CON SALSA DI SOIA', 'STARTER', 3.5, TRUE, TRUE, TRUE);

-- INSERIMENTO PRIMI
-- MODIFICA : PAELLA, TOFU CON SALSA DI SOIA, POLLO AL CURRY E GRIGLIATA MISTA SENZA GLUTINE
INSERT INTO DISHES VALUES('P01', 'PAELLA','FIRST_COURSE', 12.5, FALSE, FALSE, TRUE);
INSERT INTO DISHES VALUES('P02', 'SPAGHETTI ALLO SCOGLIO','FIRST_COURSE', 10, FALSE, FALSE, FALSE);
INSERT INTO DISHES VALUES('P03', 'CARBONARA','FIRST_COURSE', 8, FALSE, FALSE, FALSE);
INSERT INTO DISHES VALUES('P04', 'LASAGNA','FIRST_COURSE', 7.5, FALSE, FALSE, FALSE);
INSERT INTO DISHES VALUES('P05', 'RISOTTO AI FUNGHI','FIRST_COURSE', 7, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES('P06', 'PASTA E CECI','FIRST_COURSE', 5 , TRUE, TRUE, FALSE);
INSERT INTO DISHES VALUES('P07', 'RAVIOLI BURRO E SALVIA','FIRST_COURSE', 5.5 , FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES('P08', 'SPAGHETTI DI SOIA CON GAMBERI','FIRST_COURSE', 7.5 , FALSE, FALSE, TRUE);
INSERT INTO DISHES VALUES('P09', 'GNOCCHI DI PATATE AL SUGO','FIRST_COURSE', 6 , TRUE, TRUE, FALSE);
INSERT INTO DISHES VALUES('P10', 'FUSILLI SENZA GLUTINE CON PESTO DI CAVOLFIORE','FIRST_COURSE', 7.5 , TRUE, TRUE, TRUE);

-- INSERIMENTO SECONDI
INSERT INTO DISHES VALUES ('S01','ARAGOSTA ALLA CATALANA','MAIN_COURSE', 18.5, FALSE, FALSE, TRUE);
INSERT INTO DISHES VALUES ('S02','COTOLETTA ALLA MILANESE CON PATATE','MAIN_COURSE', 10, FALSE, FALSE, FALSE);
INSERT INTO DISHES VALUES ('S03','INSALATONA MISTA','MAIN_COURSE', 5, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('S04','SCALOPPINE AL LIMONE','MAIN_COURSE', 8.5, FALSE, FALSE, FALSE);
INSERT INTO DISHES VALUES ('S05','SEPPIE CON PISELLI','MAIN_COURSE', 10.5, FALSE, FALSE, TRUE);
INSERT INTO DISHES VALUES ('S06','SEITAN ALLA PIZZAIOLA','MAIN_COURSE', 9.5, TRUE, TRUE, FALSE);
INSERT INTO DISHES VALUES ('S07','POLLO AL CURRY','MAIN_COURSE', 8, FALSE, FALSE, TRUE);
INSERT INTO DISHES VALUES ('S08','FRITTO MISTO DI PESCE','MAIN_COURSE', 15, FALSE, FALSE, FALSE);
INSERT INTO DISHES VALUES ('S09','OMELETTE DI VERDURE','MAIN_COURSE', 6.5, FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES ('S10','GRIGLIATA MISTA','MAIN_COURSE', 13, FALSE, FALSE, TRUE);


-- INSERIMENTO DESSERT
INSERT INTO DISHES VALUES ('D01', 'CHEESECAKE','DESSERT', 4, FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES ('D02', 'TIRAMISU','DESSERT', 5, FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES ('D03', 'SORBETTO AL LIMONE','DESSERT', 3.5, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('D04', 'CROSTATA DI FRAGOLE','DESSERT', 4.5, FALSE, TRUE, FALSE);
INSERT INTO DISHES VALUES ('D05', 'TORTA CON FARINA SARACENA','DESSERT', 5, FALSE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('D06', 'GELATO AL CIOCCOLATO','DESSERT', 3.5, TRUE, TRUE, TRUE);

-- INSERIMENTO BEVANDE
INSERT INTO DISHES VALUES ('B01', 'ACQUA MINERALE NATURALE 1L','DRINK', 2, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('B02', 'ACQUA MINERALE FRIZZANTE 1L','DRINK', 2, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('B03', 'COCA COLA IN LATTINA','DRINK', 2.5, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('B04', 'ARANCIATA IN LATTINA','DRINK', 2.5, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('B05', 'GASSOSA IN LATTINA','DRINK', 2.5, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('B06', 'BIRRA MEDIA CHIARA','DRINK', 4.5, TRUE, TRUE, FALSE);
INSERT INTO DISHES VALUES ('B07', 'BIRRA MEDIA SCURA','DRINK', 4.5, TRUE, TRUE, FALSE);
INSERT INTO DISHES VALUES ('B08', 'VINO ROSSO DELLA CASA MEZZO LITRO','DRINK', 7, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('B09', 'VINO BIANCO DELLA CASA MEZZO LITRO','DRINK', 7, TRUE, TRUE, TRUE);
INSERT INTO DISHES VALUES ('B10', 'PROSECCO','DRINK', 8, TRUE, TRUE, TRUE);



CREATE TABLE ALLERGENS
	(
		ALLERGEN_CODE CHAR PRIMARY KEY,
        ALLERGEN_DESCR VARCHAR(50) NOT NULL

    );

-- INSERIMENTO ALLERGENI
INSERT INTO ALLERGENS VALUES ('A', 'GLUTINE');
INSERT INTO ALLERGENS VALUES ('B', 'CROSTACEI E DERIVATI');
INSERT INTO ALLERGENS VALUES ('C', 'UOVA E DERIVATI');
INSERT INTO ALLERGENS VALUES ('D', 'PESCE E DERIVATI');
INSERT INTO ALLERGENS VALUES ('E', 'ARACHIDI E DERIVATI');
INSERT INTO ALLERGENS VALUES ('F', 'SOIA E DERIVATI');
INSERT INTO ALLERGENS VALUES ('G', 'LATTE E DERIVATI');
INSERT INTO ALLERGENS VALUES ('H', 'FRUTTA A GUSCIO');
INSERT INTO ALLERGENS VALUES ('I', 'SEDANO E DERIVATI');
INSERT INTO ALLERGENS VALUES ('L', 'SENAPE E DERIVATI');
INSERT INTO ALLERGENS VALUES ('M', 'SEMI DI SESAMO E DERIVATI');
-- CORREZIONE NOME
INSERT INTO ALLERGENS VALUES ('N', 'ANIDRIDE SOLFOROSA E SOLFITI');
INSERT INTO ALLERGENS VALUES ('O', 'LUPINO E DERIVATI');
INSERT INTO ALLERGENS VALUES ('P', 'MOLLUSCHI E DERIVATI');


CREATE TABLE INGREDIENTS
	(
		INGREDIENT_CODE VARCHAR(4) PRIMARY KEY,
        INGREDIENT_NAME VARCHAR(50) NOT NULL

    );

-- INSERIMENTO INGREDIENTI

INSERT INTO INGREDIENTS VALUES ('I01','ERBE E SPEZIE');
INSERT INTO INGREDIENTS VALUES ('I02','CARNE');
INSERT INTO INGREDIENTS VALUES ('I03','PESCE E FRUTTI DI MARE');
INSERT INTO INGREDIENTS VALUES ('I04','LATTICINI');
INSERT INTO INGREDIENTS VALUES ('I05','CEREALI E FARINE');
INSERT INTO INGREDIENTS VALUES ('I06','PASTA');
INSERT INTO INGREDIENTS VALUES ('I07','POMODORO');
INSERT INTO INGREDIENTS VALUES ('I08','LEGUMI');
INSERT INTO INGREDIENTS VALUES ('I09','PEPERONCINO');
INSERT INTO INGREDIENTS VALUES ('I10','UOVA');
INSERT INTO INGREDIENTS VALUES ('I11','CIOCCOLATO');
INSERT INTO INGREDIENTS VALUES ('I12','FRUTTA');
INSERT INTO INGREDIENTS VALUES ('I13','ALCOLICI');
INSERT INTO INGREDIENTS VALUES ('I14','TOFU');
INSERT INTO INGREDIENTS VALUES ('I15','FRUTTA SECCA');
INSERT INTO INGREDIENTS VALUES ('I16','ZUCCHERO');
INSERT INTO INGREDIENTS VALUES ('I17','OLIVE');
INSERT INTO INGREDIENTS VALUES ('I18','SOTTACETI');
INSERT INTO INGREDIENTS VALUES ('I19','SALSE');
INSERT INTO INGREDIENTS VALUES ('I20','LIMONE');
INSERT INTO INGREDIENTS VALUES ('I21','CIPOLLA');
INSERT INTO INGREDIENTS VALUES ('I22','FUNGHI');
INSERT INTO INGREDIENTS VALUES ('I23','OLIO GENERICO');
INSERT INTO INGREDIENTS VALUES ('I24','VERDURE');

-- AGGIUNTE

INSERT INTO INGREDIENTS VALUES ('I25','RISO');
INSERT INTO INGREDIENTS VALUES ('I26','SENAPE');
INSERT INTO INGREDIENTS VALUES ('I27','SOIA');
INSERT INTO INGREDIENTS VALUES ('I28','SEITAN');
INSERT INTO INGREDIENTS VALUES ('I29','CAFFE');
INSERT INTO INGREDIENTS VALUES ('I30','SAVOIARDI');



CREATE TABLE ALLERGENS_IN_DISHES
	(
		ALLERGEN_CODE CHAR,
        DISH_CODE VARCHAR(3),
		PRIMARY KEY(ALLERGEN_CODE, DISH_CODE),
        FOREIGN KEY (DISH_CODE) REFERENCES DISHES(DISH_CODE),
		FOREIGN KEY (ALLERGEN_CODE) REFERENCES ALLERGENS(ALLERGEN_CODE)

    );

-- STARTERS ALLERGENS

INSERT INTO ALLERGENS_IN_DISHES VALUES ('D','A02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('F','A02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','A03');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','A03');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','A05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','A05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('N','A05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','A06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','A06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','A06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','A07');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','A08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','A08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','A08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','A09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('N','A09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('P','A09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('F','A10');

-- FIRST COURSES ALLERGENS

INSERT INTO ALLERGENS_IN_DISHES VALUES ('B','P01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('D','P01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('P','P01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','P02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('B','P02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('D','P02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('P','P02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','P03');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','P03');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P03');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','P04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','P04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('F','P04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('I','P04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('N','P05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','P06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('E','P06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('M','P06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','P07');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P07');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('B','P08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('F','P08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('M','P08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','P09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('O','P09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','P10');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('M','P10');

-- MAIN COURSES ALLERGENES

INSERT INTO ALLERGENS_IN_DISHES VALUES ('B','S01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','S02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','S02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','S04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('D','S05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('M','S05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','S06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('F','S06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('L','S06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('L','S07');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','S08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('B','S08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('D','S08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('M','S08');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','S09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','S09');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','S09');


-- DESSERT AND DRINKS ALLERGENS


INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','D01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','D01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','D01');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','D02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','D02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','D02');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','D04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','D04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','D04');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','D05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','D05');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('C','D06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('G','D06');

INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','B06');
INSERT INTO ALLERGENS_IN_DISHES VALUES ('A','B07');


CREATE TABLE INGREDIENTS_IN_DISHES
	(
		INGREDIENT_CODE VARCHAR(4),
        DISH_CODE VARCHAR(3),
        PRIMARY KEY (INGREDIENT_CODE, DISH_CODE),
        FOREIGN KEY (DISH_CODE) REFERENCES DISHES(DISH_CODE),
		FOREIGN KEY (INGREDIENT_CODE) REFERENCES INGREDIENTS(INGREDIENT_CODE)

    );


-- STARTERS INGREDIENTS

INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','A01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','A01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','A02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','A02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I20','A02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','A02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','A03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','A03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','A03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','A03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','A04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','A04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','A05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','A05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','A05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','A05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','A06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','A06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','A06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','A06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','A06');

-- AGGIUNTA LATTICINI PER I FORMAGGI MISTI

INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','A07');

INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','A08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','A08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','A08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','A08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','A09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','A09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','A09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I09','A09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','A09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','A10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I14','A10');

-- FIRST COURSES INGREDIENTS

INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','P01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','P01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I09','P01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','P01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','P01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I25','P01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','P02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','P02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','P03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','P03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','P03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','P04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','P04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','P04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','P04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I25','P05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','P05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I22','P05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I13','P05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','P06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I08','P06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','P06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P07');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','P07');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P07');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','P08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','P08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I27','P08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','P08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','P09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','P09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','P09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','P10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','P10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I06','P10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','P10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','P10');

-- MAIN COURSES INGREDIENTS

INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','S01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','S01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I20','S01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I21','S01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','S01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','S02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','S02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','S02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','S02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','S02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','S02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','S03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','S03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','S04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I20','S04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','S04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','S05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','S05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','S05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I13','S05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I28','S06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','S06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','S06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I26','S06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S07');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','S07');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','S07');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I26','S07');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','S08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I03','S08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','S08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I23','S08');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','S09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I24','S09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','S09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','S09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I09','S09');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','S10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I02','S10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I07','S10');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I20','S10');

-- DESSERT INGREDIENTS

INSERT INTO INGREDIENTS_IN_DISHES VALUES('I01','D01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','D01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','D01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','D01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I12','D01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I20','D01');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I29','D02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I30','D02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','D02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','D02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I13','D02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I11','D02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I16','D02');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I16','D03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I20','D03');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','D04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','D04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I16','D04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','D04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I20','D04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I12','D04');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I05','D05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','D05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','D05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I16','D05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I12','D05');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I04','D06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I11','D06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I16','D06');
INSERT INTO INGREDIENTS_IN_DISHES VALUES('I10','D06');



CREATE TABLE RESERVATIONS
	(
		RES_CODE VARCHAR(6) PRIMARY KEY,
		N_GUESTS INT NOT NULL,
		RES_COST DOUBLE NOT NULL,
		EVENT_DATE DATE NOT NULL,
		CUSTOMER_NAME_SURNAME VARCHAR(50) NOT NULL,
		CUSTOMER_EMAIL VARCHAR(50) NOT NULL,
		DETAILS_FILE_PATH VARCHAR(150)
	);

CREATE TABLE MENUS
	(
		RES_CODE VARCHAR(6),
        MENU_CODE VARCHAR(6),
        MENU_NAME VARCHAR(50),
        MENU_N_GUESTS INT,
		PRIMARY KEY (RES_CODE,MENU_CODE),
        FOREIGN KEY (RES_CODE) REFERENCES RESERVATIONS(RES_CODE)

    );

CREATE TABLE DISH_IN_MENU
	(
		RES_CODE VARCHAR(6),
        MENU_CODE VARCHAR(6),
        DISH_CODE VARCHAR(3),
        PRIMARY KEY (RES_CODE,MENU_CODE,DISH_CODE),
		FOREIGN KEY (RES_CODE,MENU_CODE) REFERENCES MENUS(RES_CODE,MENU_CODE),
		FOREIGN KEY (DISH_CODE) REFERENCES DISHES(DISH_CODE)

    );

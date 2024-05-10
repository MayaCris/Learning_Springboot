CREATE TABLE ASSET_TYPE(
TYPE_ID NUMBER(4,0) GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
TYPE_NAME VARCHAR2(50),
DEPRECIATION_TIME_MONTHS NUMBER(5,1)
);

CREATE TABLE LOCATIONS (
LOCATION_ID NUMBER(4,0) GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
LOCATION_NAME VARCHAR2(50),
LOCATION_ADDRESS VARCHAR2(50),
LOCATION_CITY VARCHAR2(50),
LOCATION_COUNTRY VARCHAR2(50)
);

CREATE TABLE RESPONSIBLE_PERSON(
PERSON_ID VARCHAR2(20) PRIMARY KEY NOT NULL,
PERSON_NAME VARCHAR2(50),
PERSON_DEPARTMENT VARCHAR2(50)
);

CREATE TABLE FIXED_ASSETS(
ASSET_ID NUMBER(10,0) GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
ASSET_COD VARCHAR2(30),
ASSET_NAME VARCHAR(100),
ASSET_DESCRIPTION VARCHAR(200),
TYPE_ID NUMBER(2,0),
ACQUISITION_DATE DATE,
ACQUISITION_VALUE NUMBER(20,2),
LOCATION_ID NUMBER(4,0),
PERSON_ID VARCHAR2(20),
CONSTRAINT FK_TYPE_ID FOREIGN KEY (TYPE_ID) REFERENCES ASSET_TYPE(TYPE_ID),
CONSTRAINT FK_LOCATION_ID FOREIGN KEY (LOCATION_ID) REFERENCES LOCATIONS(LOCATION_ID),
CONSTRAINT FK_PERSON_ID FOREIGN KEY (PERSON_ID) REFERENCES RESPONSIBLE_PERSON(PERSON_ID)
);

CREATE TABLE DEPRECIATION (
DEPRECIATION_ID NUMBER(10,0) GENERATED ALWAYS AS IDENTITY NOT NULL PRIMARY KEY,
ASSET_ID NUMBER(10,0)NOT NULL,
DEPRECIATION_DATE DATE,
DEPRECIATION_VALUE NUMBER(20,2),
CONSTRAINT FK_ASSET_ID FOREIGN KEY (ASSET_ID) REFERENCES FIXED_ASSETS(ASSET_ID)
);

--PROCEDIMIENTOS ALMACENADOS PARA INSERTAR Y ACTUALIZAR DATOS--


--PROCEDIMIENTO PARA INGRESAR TIPO DE ACTIVO--
CREATE PROCEDURE ADD_ASSET_TYPE(pTYPE_NAME VARCHAR2, pDEPRECIATION_TIME_MONTHS NUMBER)
AS
BEGIN
    INSERT INTO ASSET_TYPE(TYPE_NAME, DEPRECIATION_TIME_MONTHS)
    VALUES (pTYPE_NAME,pDEPRECIATION_TIME_MONTHS);
    COMMIT;
END;

EXECUTE ADD_ASSET_TYPE('EQUIPO DE COMUNICACION', 60)

-- VER LOS TIPOS DE ACTIVOS CREADOS--

CREATE VIEW ASSET_TYPE_VIEW AS

SELECT  CONCAT('TYPE-',LPAD(TYPE_ID,4,'0')) AS ID, TYPE_NAME AS NAME, 
        DEPRECIATION_TIME_MONTHS AS TIME_MONTHS
FROM ASSET_TYPE;



--PROCEDIMIENTO PARA MODIFICAR EL TIPO DE ACTIVO

CREATE OR REPLACE PROCEDURE UPDATE_ASSET_TYPE(pTYPE_ID NUMBER, pTYPE_NAME VARCHAR2, pDEPRECIATION_TIME_MONTHS NUMBER)
AS
BEGIN
    UPDATE ASSET_TYPE SET TYPE_NAME=pTYPE_NAME, DEPRECIATION_TIME_MONTHS=pDEPRECIATION_TIME_MONTHS
    WHERE TYPE_ID=pTYPE_ID;
    COMMIT;
            
END;

--PROCEDIMIENTO PARA INGRESAR UNA UBICACION --

CREATE PROCEDURE ADD_LOCATION(
                pLOCATION_NAME VARCHAR2, 
                pLOCATION_ADDRESS VARCHAR2,
                pLOCATION_CITY VARCHAR2,
                pLOCATION_COUNTRY VARCHAR2
                )
AS
BEGIN
    INSERT INTO LOCATIONS(LOCATION_NAME,LOCATION_ADDRESS,LOCATION_CITY,LOCATION_COUNTRY)
    VALUES (pLOCATION_NAME, pLOCATION_ADDRESS,pLOCATION_CITY, pLOCATION_COUNTRY);
    COMMIT;
END;

EXECUTE ADD_LOCATION('SEDE SUR', 'CRA 35 N 48A 23', 'SABANETA', 'COLOMBIA');

SELECT * FROM LOCATIONS;

--PROCEDIMIENTO PARA ACTUALIZAR UNA UBICACION--

CREATE PROCEDURE UPDATE_LOCATION(
                pLOCATION_ID NUMBER,
                pLOCATION_NAME VARCHAR2, 
                pLOCATION_ADDRESS VARCHAR2,
                pLOCATION_CITY VARCHAR2,
                pLOCATION_COUNTRY VARCHAR2
                               
)
AS
BEGIN
    UPDATE LOCATIONS SET LOCATION_NAME=pLOCATION_NAME,
                        LOCATION_ADDRESS=pLOCATION_ADDRESS,
                        LOCATION_CITY=pLOCATION_CITY,
                        LOCATION_COUNTRY=pLOCATION_COUNTRY

    WHERE LOCATION_ID=pLOCATION_ID;
    COMMIT;
END;

--PROCEDIMIENTO ALMACENADO PARA INGRESAR PERSONAS--

CREATE PROCEDURE ADD_PERSON(
                    pPERSON_ID VARCHAR2,
                    pPERSON_NAME VARCHAR2,
                    pPERSON_DEPARTMENT VARCHAR2
                        
)
AS
BEGIN
    INSERT INTO RESPONSIBLE_PERSON(
                        PERSON_ID,
                        PERSON_NAME,
                        PERSON_DEPARTMENT
                        )
            
    VALUES(pPERSON_ID, pPERSON_NAME,pPERSON_DEPARTMENT);
    COMMIT;
END;

EXECUTE ADD_PERSON('12345','ANDRES EJEMPLO', 'GESTION HUMANA')

SELECT * FROM RESPONSIBLE_PERSON;


--MODIFICAR DATOS DE LAS PERSONAS RESPONSABLES--

CREATE OR REPLACE PROCEDURE UPDATE_PERSON(
                pPERSON_ID VARCHAR2,
                pPERSON_NAME VARCHAR2,
                pPERSON_DEPARTMENT VARCHAR2                     
)
AS
BEGIN
    UPDATE RESPONSIBLE_PERSON SET
                        PERSON_NAME=pPERSON_NAME,
                        PERSON_DEPARTMENT=pPERSON_DEPARTMENT
                        
    WHERE PERSON_ID=pPERSON_ID;
    COMMIT;
END;


--CREAR UN ACTIVO FIJO--

CREATE OR REPLACE PROCEDURE ADD_ASSET (
                        pASSET_COD VARCHAR2,
                        pASSET_NAME VARCHAR2,
                        pASSET_DESCRIPTION VARCHAR2,
                        pTYPE_ID NUMBER,
                        pACQUISITION_DATE DATE,
                        pACQUISITION_VALUE NUMBER,
                        pLOCATION_ID NUMBER,
                        pPERSON_ID VARCHAR2
)
AS
BEGIN
    INSERT INTO FIXED_ASSETS (
                    ASSET_COD,
                    ASSET_NAME,
                    ASSET_DESCRIPTION,
                    TYPE_ID,
                    ACQUISITION_DATE,
                    ACQUISITION_VALUE,
                    LOCATION_ID,
                    PERSON_ID
    )
    VALUES(
            pASSET_COD,
            pASSET_NAME,
            pASSET_DESCRIPTION,
            pTYPE_ID,
            pACQUISITION_DATE,
            pACQUISITION_VALUE,
            pLOCATION_ID,
            pPERSON_ID
    );
    COMMIT;
END;

EXECUTE ADD_ASSET('ASSET.V.001','PORTATIL HP', 'HP 16GB I7 207ED106', 5,'10/01/2024',3200000,1,'104582545');
EXECUTE ADD_ASSET('ASSET.V.002','PORTATIL', 'LENOVO 16GB I5 ', 5,'19/03/2024',3500000,1,'104582545');
EXECUTE ADD_ASSET('ASSET.V.003','ESCRITORIO', 'ESCRITORIO EN L FINLANDEK', 4,'05/02/2024',6800000,1,'12345');
EXECUTE ADD_ASSET('ASSET.V.004','MESA REUNIONES', 'MESA REUNIONES STANDARD', 4,'01/02/2024',10400000,1,'12345');
EXECUTE ADD_ASSET('ASSET.V.005','CELULAR SAMSUNG ', 'S24 ULTRA', 6,'01/02/2024',6100000,2,'12345');
EXECUTE ADD_ASSET('ASSET.V.006','CELULAR ASUS ', 'ROG 8 PRO', 6,'01/03/2024',7100000,1,'104582545');



SELECT * FROM FIXED_ASSETS;


CREATE OR REPLACE PROCEDURE UPDATE_ASSET (
                        pASSET_ID NUMBER,
                        pASSET_COD VARCHAR2,
                        pASSET_NAME VARCHAR2,
                        pASSET_DESCRIPTION VARCHAR2,
                        pTYPE_ID NUMBER,
                        pACQUISITION_DATE DATE,
                        pACQUISITION_VALUE NUMBER,
                        pLOCATION_ID NUMBER,
                        pPERSON_ID VARCHAR2
)
AS
BEGIN
    UPDATE FIXED_ASSETS SET 
                        ASSET_COD=pASSET_COD,
                        ASSET_NAME=pASSET_NAME,
                        ASSET_DESCRIPTION=pASSET_DESCRIPTION,
                        TYPE_ID=pTYPE_ID,
                        ACQUISITION_DATE=pACQUISITION_DATE,
                        ACQUISITION_VALUE=pACQUISITION_VALUE,
                        LOCATION_ID=pLOCATION_ID,
                        PERSON_ID=pPERSON_ID
    WHERE ASSET_ID=pASSET_ID;
    
    COMMIT;
END;

CREATE FUNCTION CALCULATE_DEPRECIATION(
                            pDEPRECIATION_TIME_MONTHS NUMBER,
                            pACQUISITION_VALUE NUMBER
)
RETURN NUMBER
AS pDEPRECIATION_VALUE NUMBER;
BEGIN
    RETURN ROUND((pACQUISITION_VALUE / pDEPRECIATION_TIME_MONTHS),2);
END;

--PROCEDIMIENTO ALMACENADO PARA MODIFICAR LOS REGISTROS DE DEPRECIACION--

CREATE PROCEDURE UPDATE_DEPRECIATION (
                            pDEPRECIATION_ID NUMBER,
                            pASSET_ID NUMBER,
                            pDEPRECIATION_DATE DATE,
                            pDEPRECIATION_VALUE NUMBER
)

AS
BEGIN
    UPDATE DEPRECIATION SET 
                        DEPRECIATION_DATE = pDEPRECIATION_DATE,
                        DEPRECIATION_VALUE =pDEPRECIATION_VALUE
                        
    WHERE DEPRECIATION_ID=pDEPRECIATION_ID AND ASSET_ID = pASSET_ID;
    COMMIT;
END;

SELECT * FROM DEPRECIATION;

-------------------------------------------------------------------------------

CREATE OR REPLACE FUNCTION CONSULT_TIME_DEPRECIATION (pASSET_ID IN NUMBER)
RETURN NUMBER
AS
    vDEPRECIATION_TIME_MONTHS NUMBER;
BEGIN
    SELECT ASSET_TYPE.DEPRECIATION_TIME_MONTHS INTO vDEPRECIATION_TIME_MONTHS
    FROM FIXED_ASSETS 
    INNER JOIN ASSET_TYPE ON FIXED_ASSETS.TYPE_ID = ASSET_TYPE.TYPE_ID
    
    WHERE FIXED_ASSETS.ASSET_ID = pASSET_ID;
    RETURN vDEPRECIATION_TIME_MONTHS;
END;



---FIN DE LA FUNCION--------------------------------------------


CREATE FUNCTION ACQUISITION_V (pASSET_ID IN NUMBER)
RETURN NUMBER
AS
    vVALUE NUMBER;
BEGIN
    SELECT ACQUISITION_VALUE INTO vVALUE
    FROM FIXED_ASSETS
    WHERE ASSET_ID = pASSET_ID;
    RETURN vVALUE;
END;


CREATE OR REPLACE PROCEDURE ADD_DEPRECIATION(
                        pASSET_ID NUMBER,
                        pDEPRECIATION_DATE DATE
)
AS
BEGIN
    INSERT INTO DEPRECIATION (
                        ASSET_ID,
                        DEPRECIATION_DATE,
                        DEPRECIATION_VALUE
    )
    VALUES(
            pASSET_ID,
            pDEPRECIATION_DATE,
            ROUND(CALCULATE_DEPRECIATION(
                    CONSULT_TIME_DEPRECIATION(pASSET_ID),
                    ACQUISITION_V(pASSET_ID)
            ),0)
            
    );
    COMMIT;
END;

SELECT * FROM DEPRECIATION;


--CREAR VISTA PARA VER LA INFORMACION DETALLADA DE LOS ACTIVOS--

CREATE OR REPLACE VIEW ASSETS AS

    SELECT  FA.ASSET_ID,
            FA.ASSET_COD,
            FA.ASSET_NAME,
            FA.ASSET_DESCRIPTION,
            AT.TYPE_NAME,
            AT.DEPRECIATION_TIME_MONTHS,
            L.LOCATION_NAME,
            L.LOCATION_ADDRESS,
            RP.PERSON_NAME,
            RP.PERSON_DEPARTMENT,
            D.DEPRECIATION_VALUE
                
    FROM FIXED_ASSETS FA
    INNER JOIN ASSET_TYPE AT ON FA.TYPE_ID = AT.TYPE_ID
    INNER JOIN LOCATIONS L ON FA.LOCATION_ID = L.LOCATION_ID
    INNER JOIN RESPONSIBLE_PERSON RP ON FA.PERSON_ID = RP.PERSON_ID
    INNER JOIN DEPRECIATION D ON FA.ASSET_ID = D.ASSET_ID;


SELECT * FROM ASSETS;

SELECT * FROM FIXED_ASSETS;

--CREAR VISTA QUE MUESTRE EL TOTAL DEPRECIACION POR ACTIVO-- 

CREATE OR REPLACE VIEW SUBTOTAL_DEPRECIATION AS
    
    SELECT  
            FA.ASSET_NAME,
            SUM(D.DEPRECIATION_VALUE) AS SUBTOTAL_DEPRECIATION
                        
    FROM DEPRECIATION D
    INNER JOIN FIXED_ASSETS FA ON D.ASSET_ID = FA.ASSET_ID
    GROUP BY FA.ASSET_NAME;


----------------------------------------------------------------------------
SELECT FA.ASSET_NAME, SUM(D.DEPRECIATION_VALUE) AS SUBTOTAL_DEPRECIATION
FROM DEPRECIATION D
INNER JOIN FIXED_ASSETS FA ON D.ASSET_ID = FA.ASSET_ID
GROUP BY FA.ASSET_NAME;


---CREAR UNA VISTA QUE MUESTRE EL TOTAL DEPRECIACION

CREATE OR REPLACE VIEW TOTAL_DEPRECIATION AS
    
    SELECT  
            SUM(D.DEPRECIATION_VALUE) AS TOTAL_DEPRECIATION
                        
    FROM DEPRECIATION D
    INNER JOIN FIXED_ASSETS FA ON D.ASSET_ID = FA.ASSET_ID;
    
    
--CREAR UNA VISTA QUE MUESTRE LA CANTIDAD DE ACTIVOS POR CIUDAD---   

CREATE OR REPLACE VIEW ASSETS_PER_CITY AS
    SELECT
            L.LOCATION_CITY,
            COUNT(FA.ASSET_NAME) AS ASSETS
    FROM LOCATIONS L
    INNER JOIN FIXED_ASSETS FA ON L.LOCATION_ID = FA.LOCATION_ID
    GROUP BY L.LOCATION_CITY;
            

SELECT * FROM ASSET_TYPE_VIEW;
SELECT * FROM ASSETS;
SELECT * FROM ASSETS_PER_CITY;
SELECT * FROM SUBTOTAL_DEPRECIATION;
SELECT * FROM TOTAL_DEPRECIATION;


EXECUTE ADD_DEPRECIATION (1,'30/03/2022');

SELECT * FROM DEPRECIATION ORDER BY ASSET_ID;

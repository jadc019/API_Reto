-- Database: retobbva

-- DROP DATABASE retobbva;

CREATE DATABASE retobbva
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Peru.1252'
    LC_CTYPE = 'Spanish_Peru.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;


-- Table public.documento

-- DROP TABLE public.documento;

CREATE TABLE public.documento
(
    "idDocumento" serial NOT NULL,
    "url" character varying NOT NULL,
    "cantidadPaginas" integer,
    CONSTRAINT documento_pkey PRIMARY KEY ("idDocumento"),
    CONSTRAINT XU_Documento_01 UNIQUE (url)
);

ALTER TABLE public.documento OWNER to postgres;
	
	
-- Table: public.nota

-- DROP TABLE public.nota;

CREATE TABLE public.nota
(
    "idNota" serial NOT NULL,
    "pagina" integer NOT NULL,
    "left" integer NOT NULL,
    "top" integer NOT NULL,
    "contenido" character varying,
    "idDocumento" integer NOT NULL,
	"color" character varying(30),
    "indActivo" character varying(1),
    CONSTRAINT nota_pkey PRIMARY KEY ("idNota"),
    CONSTRAINT "FK_NOTA_DOCUMENTO_01" FOREIGN KEY ("idDocumento")
        REFERENCES public.documento ("idDocumento")
);

ALTER TABLE public.nota OWNER to postgres;
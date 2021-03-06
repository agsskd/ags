--------------------------------------------------------
--  File created - Selasa-Mei-09-2017   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Table MST_CUSTOMER
--------------------------------------------------------

  CREATE TABLE "MST_CUSTOMER" 
   (	"KODE_CUSTOMER" VARCHAR2(20 CHAR), 
	"ALAMAT_CUSTOMER" VARCHAR2(100 CHAR), 
	"EMAIL_CUSTOMER" VARCHAR2(100 CHAR), 
	"JENIS_KELAMIN" VARCHAR2(100 CHAR), 
	"KODE_KOTA" VARCHAR2(20 CHAR), 
	"NAMA_CUSTOMER" VARCHAR2(100 CHAR)
   ) SEGMENT CREATION IMMEDIATE 
  PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
REM INSERTING into MST_CUSTOMER
Insert into MST_CUSTOMER (KODE_CUSTOMER,ALAMAT_CUSTOMER,EMAIL_CUSTOMER,JENIS_KELAMIN,KODE_KOTA,NAMA_CUSTOMER) values ('012016','JL RAYA HANKAM ','jay@gmail.com','PRIA','JKTTIMUR','JAYA SENTOSA');
Insert into MST_CUSTOMER (KODE_CUSTOMER,ALAMAT_CUSTOMER,EMAIL_CUSTOMER,JENIS_KELAMIN,KODE_KOTA,NAMA_CUSTOMER) values ('022016','JL. PASAR MINGGU','arif@yahoo.com','PRIA','JKTPUSAT','ARIFIN');
Insert into MST_CUSTOMER (KODE_CUSTOMER,ALAMAT_CUSTOMER,EMAIL_CUSTOMER,JENIS_KELAMIN,KODE_KOTA,NAMA_CUSTOMER) values ('032016','PS.REBO','nit_01@gmail.com','WANITA','JKTSELATAN','NITA');
Insert into MST_CUSTOMER (KODE_CUSTOMER,ALAMAT_CUSTOMER,EMAIL_CUSTOMER,JENIS_KELAMIN,KODE_KOTA,NAMA_CUSTOMER) values ('ghghdg','ghfghfhgfh','ags@gmail.com','Pria','JKTPUSAT','ghfghg123');
Insert into MST_CUSTOMER (KODE_CUSTOMER,ALAMAT_CUSTOMER,EMAIL_CUSTOMER,JENIS_KELAMIN,KODE_KOTA,NAMA_CUSTOMER) values ('sdasds','sdsdsad','dasdasd@gmmail.com','Pria','JKTPUSAT','dsadasd');
--------------------------------------------------------
--  DDL for Index MST_CUSTOMER_PK
--------------------------------------------------------

  CREATE UNIQUE INDEX "MST_CUSTOMER_PK" ON "MST_CUSTOMER" ("KODE_CUSTOMER") 
  PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM" ;
--------------------------------------------------------
--  Constraints for Table MST_CUSTOMER
--------------------------------------------------------

  ALTER TABLE "MST_CUSTOMER" ADD CONSTRAINT "MST_CUSTOMER_PK" PRIMARY KEY ("KODE_CUSTOMER")
  USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
  STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
  PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
  TABLESPACE "SYSTEM"  ENABLE;
  ALTER TABLE "MST_CUSTOMER" MODIFY ("KODE_CUSTOMER" NOT NULL ENABLE);

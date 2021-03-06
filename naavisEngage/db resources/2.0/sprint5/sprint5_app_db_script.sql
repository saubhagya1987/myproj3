-------------------------------
-- Please use QA2 app database 
-------------------------------
use engage_app_20
Go

UPDATE [dbo].[client_naavis_databases] 
    SET facility_name='Mitchell County' WHERE client_database_id=1
GO

UPDATE [dbo].[account_medication_management_schedule] 
    SET reminder_day='1' 
    WHERE reminder_day LIKE '%sunday'
GO

UPDATE [dbo].[account_medication_management_schedule] 
    SET reminder_day='2' 
    WHERE reminder_day LIKE '%mon%'
GO

UPDATE [dbo].[account_medication_management_schedule] 
    SET reminder_day='3' 
    WHERE reminder_day LIKE '%tues%'
GO

UPDATE [dbo].[account_medication_management_schedule] 
    SET reminder_day='4' 
    WHERE reminder_day LIKE '%wed%'
GO

UPDATE [dbo].[account_medication_management_schedule] 
    SET reminder_day='5' 
    WHERE reminder_day LIKE '%thu%'
GO

UPDATE [dbo].[account_medication_management_schedule] 
    SET reminder_day='6' 
    WHERE reminder_day LIKE '%fri%'
GO
UPDATE [dbo].[account_medication_management_schedule] 
    SET reminder_day='7' 
    WHERE reminder_day LIKE '%sat%'
GO


ALTER TABLE [dbo].[account_medication_management] 
    DROP CONSTRAINT [FK_account_medication_management__medication_kind]
GO

ALTER TABLE [dbo].[medication_kind]
    ADD medication_dose nvarchar(64) NULL
GO

TRUNCATE TABLE [dbo].[medication_kind]
GO

INSERT INTO [dbo].[medication_kind]
    ([medication_kind_id],[medication_kind],[medication_dose])
    VALUES (1,'pill','pill')
GO
INSERT INTO [dbo].[medication_kind]
      ([medication_kind_id],[medication_kind],[medication_dose]) 
      VALUES (2,'tablet','tablet')
GO
INSERT INTO [dbo].[medication_kind]
      ([medication_kind_id],[medication_kind],[medication_dose]) 
     VALUES (3,'capsule','capsule')
GO
INSERT INTO [dbo].[medication_kind]
      ([medication_kind_id],[medication_kind],[medication_dose]) 
      VALUES (4,'syrup','mL')
GO
INSERT INTO [dbo].[medication_kind]
      ([medication_kind_id],[medication_kind],[medication_dose]) 
      VALUES (5,'powder','gram')
GO
INSERT INTO [dbo].[medication_kind]
      ([medication_kind_id],[medication_kind],[medication_dose]) 
      VALUES (6,'inhaler','puff')
GO
INSERT INTO [dbo].[medication_kind]
     ([medication_kind_id],[medication_kind],[medication_dose]) 
     VALUES (7,'cream','gram')
GO
INSERT INTO [dbo].[medication_kind]
     ([medication_kind_id],[medication_kind],[medication_dose]) 
     VALUES (8,'patch','patch')
GO
INSERT INTO [dbo].[medication_kind]
     ([medication_kind_id],[medication_kind],[medication_dose]) 
     VALUES (9,'drops','drops')
GO
INSERT INTO [dbo].[medication_kind]
     ([medication_kind_id],[medication_kind],[medication_dose]) 
     VALUES (10,'suppository','suppository')
GO
INSERT INTO [dbo].[medication_kind]
    ([medication_kind_id],[medication_kind],[medication_dose]) 
    VALUES (11,'lozenge','lozenge')
GO
INSERT INTO [dbo].[medication_kind]
    ([medication_kind_id],[medication_kind],[medication_dose]) 
    VALUES (12,'injection','injection')
GO
INSERT INTO [dbo].[medication_kind]
    ([medication_kind_id],[medication_kind],[medication_dose])
    VALUES (13,'liquid','mL')
GO
INSERT INTO [dbo].[medication_kind]
    ([medication_kind_id],[medication_kind],[medication_dose]) 
    VALUES (14,'chews','chews')
GO
INSERT INTO [dbo].[medication_kind]
    ([medication_kind_id],[medication_kind],[medication_dose]) 
    VALUES (15,'suspension','mg/mL')
GO
INSERT INTO [dbo].[medication_kind]
    ([medication_kind_id],[medication_kind],[medication_dose])
    VALUES (16,'solution','mL')
GO

ALTER TABLE [dbo].[account_medication_management] 
    ADD CONSTRAINT [FK_account_medication_management__medication_kind] 
    FOREIGN KEY (medication_kind_id) REFERENCES [dbo].[medication_kind](medication_kind_id)
GO


IF NOT EXISTS (SELECT * FROM [dbo].[engage_app_modules]
        WHERE module_id=8
        AND [module_name]='TO DO')
BEGIN
  INSERT INTO  [dbo].[engage_app_modules]
     ([module_id],[module_name]) 
     VALUES(8,'TO DO')
 
END
IF NOT EXISTS (SELECT * FROM [dbo].[engage_app_features]
     WHERE [feature_id]=38
     AND [feature_name]='To Do'
     AND [module_id]=8)
BEGIN
  INSERT INTO [dbo].[engage_app_features] 
    ([feature_id],[feature_name],[module_id],[diagnosis_code]) 
    VALUES(38,'To Do',8,NULL)
END

IF NOT EXISTS (SELECT * FROM [dbo].[engage_client_feature] 
    WHERE client_database_id=1 
    AND version='2.0'
    AND feature_order=13
    AND feature_id=38)
BEGIN
        INSERT INTO [dbo].[engage_client_feature] 
    ([client_database_id],[version],[feature_order],[feature_id]) 
    VALUES(1,'2.0',13,38)
END


ALTER TABLE dbo.account_medication_management Drop CONSTRAINT DF_account_medication_management__refill_date
ALTER TABLE dbo.account_medication_management DROP column refill_date

ALTER TABLE dbo.account_medication_management ADD refill_date datetime null CONSTRAINT DF_account_medication_management__refill_date DEFAULT(GETDATE())
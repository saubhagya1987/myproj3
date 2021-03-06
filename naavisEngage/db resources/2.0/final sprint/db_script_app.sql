---------------------------------------------------------
--USE QA-2 APP DATABASE
---------------------------------------------------------

--USE engage_app
GO

IF EXISTS(SELECT * FROM [dbo].[engage_app_features] 
	WHERE feature_id=38
	AND feature_name='To Do'
	)
BEGIN
	UPDATE [dbo].[engage_app_features] SET feature_name='Check-In' , module_id=4
	WHERE feature_id=38

END

IF NOT EXISTS(SELECT * from sys.objects
	WHERE type_desc='DEFAULT_CONSTRAINT'
	AND name ='DF_account_blood_pressure_engage__delete_flag')
BEGIN
	ALTER TABLE [dbo].[account_blood_pressure_engage] ADD CONSTRAINT [DF_account_blood_pressure_engage__delete_flag] DEFAULT (0) FOR delete_flag
END

IF EXISTS(SELECT * FROM [dbo].[account_blood_pressure_engage] 
WHERE delete_flag IS NULL)
BEGIN
	UPDATE [dbo].[account_blood_pressure_engage] 
	SET delete_flag = 0 WHERE delete_flag IS NULL
END

IF EXISTS(SELECT * FROM INFORMATION_SCHEMA.COLUMNS
	WHERE COLUMN_NAME='delete_flag'
	AND TABLE_NAME='account_blood_pressure_engage')
BEGIN
	ALTER TABLE dbo.account_blood_pressure_engage 
	ALTER COLUMN delete_flag BIT NOT NULL 
END




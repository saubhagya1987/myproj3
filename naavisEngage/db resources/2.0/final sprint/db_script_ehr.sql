
--------------------------------------------------------------
--USE QA 2 EHR DATABASE
--------------------------------------------------------------

--USE engage_ehr
GO

IF EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'feed_info')
BEGIN
    DROP TABLE dbo.feed_info
END
 
 
CREATE TABLE dbo.feed_info
(
    client_id int NOT NULL
    ,client_database_id int NOT NULL
    ,feature_id int NOT NULL
    ,feed_message_id int IDENTITY(1,1) NOT NULL
    ,account_id int NOT NULL
    ,clinical_date_time datetime NOT NULL
    ,feed_header nvarchar(128) NOT NULL
    ,feed_msg nvarchar(256) NOT NULL
    ,feature_name nvarchar(64) NOT NULL
    ,source_name nvarchar(64) NOT NULL
    ,is_new bit NOT NULL CONSTRAINT DF_feed_info__is_new DEFAULT(1)
    ,display_time bit NOT NULL
    ,date_added datetime NULL CONSTRAINT DF_feed_info__date_added DEFAULT(GETDATE())
    ,CONSTRAINT PK_feed_info PRIMARY KEY (feed_message_id)
)
GO
 
CREATE INDEX IX_feed_info__is_new ON dbo.feed_info (is_new) WHERE is_new = 1
CREATE INDEX IX_feed_info__display_time ON dbo.feed_info (display_time) WHERE display_time = 1
 
CREATE INDEX IX_feed_info__cover ON dbo.feed_info (feed_message_id)
    INCLUDE (client_id,client_database_id,feature_id,display_time,feed_header,feed_msg,feature_name,source_name,is_new,account_id,clinical_date_time)
 




IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=25062
              AND [feed_header]='Refill Reminder'  
			  AND [feed_msg]='refill reminder message'            
              AND [feature_id]=37 )
BEGIN
INSERT INTO [dbo].[feed_info]
           ([client_id]
           ,[client_database_id]
           ,[feature_id]
           ,[account_id]
           ,[clinical_date_time]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_name]
           ,[source_name]
           ,[is_new]
           ,[display_time]
           ,[date_added])
     VALUES
           (1
           ,1
           ,37
           ,25062
           ,'2016-01-01'
           ,'Refill Reminder'
           ,'refill reminder message'
           ,'Refill Reminder'
           ,'General Hospital'
           ,1
           ,1
           ,'2016-01-01')
END


IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=25062
              AND [feed_header]='Check-In' 
			  AND  [feed_msg]='check in message'           
              AND [feature_id]=38 )
BEGIN
INSERT INTO [dbo].[feed_info]
           ([client_id]
           ,[client_database_id]
           ,[feature_id]
           ,[account_id]
           ,[clinical_date_time]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_name]
           ,[source_name]
           ,[is_new]
           ,[display_time]
           ,[date_added])
     VALUES
           (1
           ,1
           ,38
           ,25062
           ,'2016-01-01'
           ,'Check-In'
           ,'check in message'
           ,'Check-In'
           ,'General Hospital'
           ,1
           ,1
           ,'2016-01-01')
END


IF NOT EXISTS(SELECT * FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME='patient_vital_sign'
AND COLUMN_NAME='heart_rate')
BEGIN
	ALTER TABLE [dbo].patient_vital_sign ADD heart_rate nvarchar(256) NULL 
END
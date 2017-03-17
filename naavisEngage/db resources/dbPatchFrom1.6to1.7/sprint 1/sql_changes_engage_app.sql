USE [engage_app]
GO

CREATE TABLE 
    [engage_app].[dbo].[linkedaccounts_rel] (
        [id] [int] IDENTITY(1,1) NOT NULL, 
        [account_id] int NOT NULL, 
        [linked_account_id] int NOT NULL, 
        [date_added] datetime CONSTRAINT [Default_date_added] DEFAULT(getdate () ) NULL, 
        [client_database_id] int NOT NULL, 
        [linked_account_clientdb_id] int NOT NULL, 
        [linked_facility_name] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [linked_facility_address] nvarchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL) ; 
GO
ALTER TABLE [engage_app].[dbo].[client_naavis_databases] ADD [facility_name] nvarchar(256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_app].[dbo].[client_naavis_databases] ADD [facillity_phone_number] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_app].[dbo].[linkedaccounts_rel] 
    ADD CONSTRAINT [PK_linkedaccounts_rel] 
            PRIMARY KEY ([id]) ;
GO
Alter Table [engage_app].[dbo].[account_notification_history] Drop Column [account_notification_history_id]
ALTER TABLE [engage_app].[dbo].[account_notification_history] add  [account_notification_history_id] int IDENTITY(1,1) NOT NULL;
GO
INSERT [engage_app].[dbo].[engage_app_features] ([feature_id], [feature_name], [module_id], [diagnosis_code]) VALUES (19, N'Apps', 3, NULL);
INSERT [engage_app].[dbo].[engage_app_features] ([feature_id], [feature_name], [module_id], [diagnosis_code]) VALUES (20, N'Pay My Bill', 3, NULL);
INSERT [engage_app].[dbo].[engage_app_features] ([feature_id], [feature_name], [module_id], [diagnosis_code]) VALUES (21, N'My Hospitals', 3, NULL);
GO
update [engage_app].[dbo].[client_naavis_databases] set facility_name='Cascade' , facillity_phone_number = '521-512-5412' where [client_database_id]=9;
update [engage_app].[dbo].[client_naavis_databases] set facility_name='Salina' , facillity_phone_number = '521-512-5412' where [client_database_id]=11;
update [engage_app].[dbo].[client_naavis_databases] set facility_name='Lindsborg' , facillity_phone_number = '521-512-5412' where [client_database_id]=13;
GO
INSERT [engage_app].[dbo].[engage_client_to_feature] ([id], [feature_id], [client_id]) VALUES (65, 19, 9);
INSERT [engage_app].[dbo].[engage_client_to_feature] ([id], [feature_id], [client_id]) VALUES (66, 20, 9);
INSERT [engage_app].[dbo].[engage_client_to_feature] ([id], [feature_id], [client_id]) VALUES (67, 21, 9);


USE [engage_ehr]
GO
CREATE TABLE 
    [engage_ehr].[dbo].[patient_bill_detail] (
        [account_id] int NOT NULL, 
        [bill_detail_id] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL, 
        [bill_id] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [bill_description] nvarchar (256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [quantity] decimal (8, 2) NULL, 
        [detail_amount] decimal (8, 2) NULL, 
        [client_id] int NULL, 
        [source_updated] datetime NOT NULL, 
        [date_added] datetime CONSTRAINT [Default_date_added] DEFAULT(getdate () ) NULL) ; 
GO
CREATE TABLE 
    [engage_ehr].[dbo].[patient_bill_summary] (
        [account_id] int NOT NULL, 
        [visit_identifier] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL, 
        [bill_id] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL, 
        [source_id] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [source_name] nvarchar (256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [address_1] nvarchar (256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [address_2] nvarchar (256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [city] nvarchar (256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [state] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [postal_code] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [insurance_provider] nvarchar (256) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [encounter_start_date] datetime NULL, 
        [encounter_end_date] datetime NULL, 
        [bill_date] datetime NULL, 
        [total_due] decimal (8, 2) NULL, 
        [client_id] int NULL, 
        [source_updated] datetime NOT NULL, 
        [date_added] datetime CONSTRAINT [Default_date_added_bill_detail] DEFAULT(getdate () ) NULL, 
        [is_bill_paid] bit NULL, 
        [is_viewed] bit NULL) ; 
GO
CREATE TABLE 
    [engage_ehr].[dbo].[patient_transaction_log] (
        [transaction_log_id] [int] IDENTITY(1,1) NOT NULL, 
        [bill_id] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [status] nvarchar (64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [amount] decimal (8, 2) NULL, 
        [response] nvarchar (max) COLLATE SQL_Latin1_General_CP1_CI_AS NULL, 
        [date_time] datetime NULL) ; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [plab_status] nvarchar (max) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [pall_status] nvarchar (max) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [img_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [prob_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [pmed_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [pvsi_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [care_team_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [procedure_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [care_plan_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [functional_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [immunalization_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [diagnosis_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [facility_notice_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [facility_provider_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [facility_service_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [inpatient_status] varchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_info] ADD [patient_visit_status] nvarchar(50) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[etl_logger] ADD [modules] nvarchar (max) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[client_naavis_databases] ADD [facility_name] nvarchar(64) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[client_naavis_databases] ADD [facillity_phone_number] nvarchar(20) COLLATE SQL_Latin1_General_CP1_CI_AS NULL; 
GO
ALTER TABLE [engage_ehr].[dbo].[patient_bill_detail] 
    ADD CONSTRAINT [PK_patient_bill_detail] 
            PRIMARY KEY ([account_id], [bill_detail_id]) ; 
GO
ALTER TABLE [engage_ehr].[dbo].[patient_bill_summary] 
    ADD CONSTRAINT [PK_patient_bill_summary] 
            PRIMARY KEY ([account_id], [visit_identifier], [bill_id]) ; 
GO
ALTER TABLE [engage_ehr].[dbo].[patient_transaction_log] 
    ADD CONSTRAINT [PK_patient_transaction_log] 
            PRIMARY KEY ([transaction_log_id]) ;
GO
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (28949, N'V000011', N'I-98569', N'NHH', N'NAvisHealth Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(0.01 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (28949, N'V000012', N'I-98570', N'NHH', N'Salina Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(124.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (7208, N'V0000013', N'I-98561', N'NHH', N'Salina Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(1958.40 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (7208, N'V000004', N'I-98560', N'NHH', N'NAvisHealth Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(1245.56 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (7593, N'V000005', N'I-98563', N'NHH', N'Salina Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(32.50 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (7593, N'V000014', N'I-98562', N'NHH', N'NAvisHealth Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(125.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (5750, N'V000006', N'I-98564', N'NHH', N'NAvisHealth Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(321.20 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (5750, N'V000007', N'I-98565', N'NHH', N'Salina Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(2863.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (5750, N'V000008', N'I-98566', N'NHH', N'Alameda Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(2658.56 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (6893, N'V000009', N'I-98567', N'NHH', N'NAvisHealth Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(265.20 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_summary] ([account_id], [visit_identifier], [bill_id], [source_id], [source_name], [address_1], [address_2], [city], [state], [postal_code], [insurance_provider], [encounter_start_date], [encounter_end_date], [bill_date], [total_due], [client_id], [source_updated], [date_added], [is_bill_paid], [is_viewed]) VALUES (6893, N'V000010', N'I-98568', N'NHH', N'Salina Hospital', N'2560', N'Mission Col', N'Santa Clara', N'CA', N'95054', N'21sy Century', CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(N'2015-06-24 00:00:00.000' AS DateTime), CAST(N'2015-06-25 00:00:00.000' AS DateTime), CAST(1.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 00:00:00.000' AS DateTime), CAST(N'2015-07-07 17:00:00.000' AS DateTime), 0, 0);
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7208, N'13', N'I-98561', N'Drug Spc ID Detail Coding', CAST(2.00 AS Decimal(8, 2)), CAST(71.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7208, N'19', N'I-98560', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7208, N'20', N'I-98561', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7208, N'30', N'I-98560', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7208, N'31', N'I-98561', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7208, N'8', N'I-98560', N'Physical Therapy', CAST(1.00 AS Decimal(8, 2)), CAST(108.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7593, N'14', N'I-98563', N'Drug Spc ID Detail Coding', CAST(2.00 AS Decimal(8, 2)), CAST(71.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7593, N'21', N'I-98562', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7593, N'22', N'I-98563', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7593, N'32', N'I-98562', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7593, N'33', N'I-98563', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (7593, N'9', N'I-98562', N'Physical Therapy', CAST(1.00 AS Decimal(8, 2)), CAST(108.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'10', N'I-98564', N'Physical Therapy', CAST(1.00 AS Decimal(8, 2)), CAST(108.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'15', N'I-98565', N'Drug Spc ID Detail Coding', CAST(2.00 AS Decimal(8, 2)), CAST(71.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'18', N'I-98566', N'Drug Spc ID Detail Coding', CAST(2.00 AS Decimal(8, 2)), CAST(71.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'23', N'I-98564', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'24', N'I-98565', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'25', N'I-98566', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'34', N'I-98564', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'35', N'I-98565', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (5750, N'36', N'I-98566', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (6893, N'11', N'I-98567', N'Physical Therapy', CAST(1.00 AS Decimal(8, 2)), CAST(108.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (6893, N'16', N'I-98568', N'Drug Spc ID Detail Coding', CAST(2.00 AS Decimal(8, 2)), CAST(71.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (6893, N'26', N'I-98567', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (6893, N'27', N'I-98568', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (6893, N'37', N'I-98567', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (6893, N'38', N'I-98568', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (28949, N'12', N'I-98569', N'Physical Therapy', CAST(1.00 AS Decimal(8, 2)), CAST(108.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (28949, N'17', N'I-98570', N'Drug Spc ID Detail Coding', CAST(2.00 AS Decimal(8, 2)), CAST(71.00 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (28949, N'28', N'I-98569', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (28949, N'29', N'I-98570', N'Pharmacy', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (28949, N'39', N'I-98569', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (28949, N'40', N'I-98570', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
INSERT [engage_ehr].[dbo].[patient_bill_detail] ([account_id], [bill_detail_id], [bill_id], [bill_description], [quantity], [detail_amount], [client_id], [source_updated], [date_added]) VALUES (28949, N'41', N'I-98570', N'Cat Scan', CAST(39.00 AS Decimal(8, 2)), CAST(34.13 AS Decimal(8, 2)), NULL, CAST(N'2015-07-07 17:20:31.607' AS DateTime), CAST(N'2015-07-07 17:20:31.607' AS DateTime));
GO
update [engage_ehr].[dbo].[client_naavis_databases] set facility_name='Cascade' , facillity_phone_number = '521-512-5412' where [client_database_id]=9;
update [engage_ehr].[dbo].[client_naavis_databases] set facility_name='Salina' , facillity_phone_number = '521-512-5412' where [client_database_id]=11;
update [engage_ehr].[dbo].[client_naavis_databases] set facility_name='Lindsborg' , facillity_phone_number = '521-512-5412' where [client_database_id]=13;


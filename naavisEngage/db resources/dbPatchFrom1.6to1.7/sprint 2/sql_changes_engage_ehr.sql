Alter table [engage_ehr].[dbo].[patient_bill_detail] add [client_database_id] [int] NULL
Alter table [engage_ehr].[dbo].[patient_bill_summary] add [client_database_id] [int] NULL
Alter table [engage_ehr].[dbo].[patient_lab] add [absolute_range] [nvarchar](64) NULL
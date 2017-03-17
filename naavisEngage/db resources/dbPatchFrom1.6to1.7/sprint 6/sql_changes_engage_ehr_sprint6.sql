USE [engage_app_qa_17]
GO
alter table [engage_ehr].[dbo].[patient_lab] add normal_range nvarchar(128);
GO

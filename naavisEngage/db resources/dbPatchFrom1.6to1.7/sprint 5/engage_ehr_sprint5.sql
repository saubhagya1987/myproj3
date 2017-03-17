USE [engage_ehr]
GO

ALTER TABLE [dbo].[patient_verification] DROP COLUMN [phone_number]
GO
ALTER TABLE [dbo].[patient_verification]
ADD [mobile_phone_number] [nvarchar](64) NULL
GO
ALTER TABLE [dbo].[patient_verification]
ADD [home_phone_number ] [nvarchar](64) NULL
GO
ALTER TABLE [dbo].[patient_verification]
ADD [other_phone_number] [nvarchar](64) NULL
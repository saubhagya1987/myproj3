ALTER TABLE [engage_app].[dbo].[facility_notice] ADD notice_header nvarchar(250);

USE [engage_app]
GO

ALTER TABLE [dbo].[engage_client_to_feature] DROP CONSTRAINT [FK_engage_client_to_feature__engage_app_features]
GO


DROP TABLE [dbo].[engage_client_to_feature]
GO


SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[engage_client_to_feature](
 [id] [int] IDENTITY(1,1) NOT NULL,
 [feature_id] [int] NOT NULL,
 [client_id] [int] NOT NULL,
 [feature_order] [int] NULL
) ON [PRIMARY]

GO

ALTER TABLE [dbo].[engage_client_to_feature]  WITH CHECK ADD  CONSTRAINT [FK_engage_client_to_feature__engage_app_features] FOREIGN KEY([feature_id])
REFERENCES [dbo].[engage_app_features] ([feature_id])
GO

ALTER TABLE [dbo].[engage_client_to_feature] CHECK CONSTRAINT [FK_engage_client_to_feature__engage_app_features]
GO


Insert into engage_client_to_feature values (11,9,1)
Insert into engage_client_to_feature values (21,9,2)
Insert into engage_client_to_feature values (20,9,3)
Insert into engage_client_to_feature values (18,9,4)
Insert into engage_client_to_feature values (13,9,5)
Insert into engage_client_to_feature values (17,9,6)
Insert into engage_client_to_feature values (14,9,7)

Insert into engage_client_to_feature values (11,13,1)
Insert into engage_client_to_feature values (21,13,2)
Insert into engage_client_to_feature values (20,13,3)
Insert into engage_client_to_feature values (18,13,4)
Insert into engage_client_to_feature values (13,13,5)
Insert into engage_client_to_feature values (17,13,6)
Insert into engage_client_to_feature values (14,13,7)


Insert into engage_client_to_feature values (11,11,1)
Insert into engage_client_to_feature values (21,11,2)
Insert into engage_client_to_feature values (20,11,3)
Insert into engage_client_to_feature values (18,11,4)
Insert into engage_client_to_feature values (13,11,5)
Insert into engage_client_to_feature values (17,11,6)
Insert into engage_client_to_feature values (14,11,7)

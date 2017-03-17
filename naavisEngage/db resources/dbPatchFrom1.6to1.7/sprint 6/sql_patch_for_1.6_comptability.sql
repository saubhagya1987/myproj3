USE [engage_app_qa_17]
GO
delete from [dbo].[engage_client_to_feature] where feature_id in (19,20,21);
GO
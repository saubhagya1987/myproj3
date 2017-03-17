-------------------------------
-- Please use QA2 ehr database 
-------------------------------

ALTER TABLE dbo.feed_info 
	ADD  source_name nvarchar(64) NULL
GO

ALTER TABLE dbo.feed_info 
	ADD date_added datetime NULL 
	CONSTRAINT DF_feed_info__date_added DEFAULT(GETDATE())
GO

UPDATE  [dbo].[feed_info] SET source_name='General Hospital' 
GO
UPDATE  [dbo].[feed_info]SET date_added='2016-01-01 00:00:00.000' 
GO
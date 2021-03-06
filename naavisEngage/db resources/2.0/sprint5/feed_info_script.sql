--This Script is meant for Account id: 33378,212974    
USE QA-2 Database
GO
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=33378
              AND [feed_header]='Test Results'
              AND [feed_msg] ='Lab: B-TYPE NATRIURETIC PEPTIDE'
              AND [feature_id]=35 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (33378
           ,'Test Results'
           ,'Lab: B-TYPE NATRIURETIC PEPTIDE'
           ,35
           ,'Test results'
           ,1
           ,'Mitchell County Hospital'
           ,'2012-08-13')
END

IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=33378
              AND [feed_header]='Refill Reminder'
              AND [feed_msg] ='ffffg'
              AND [feature_id]=9 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (33378
           ,'Refill Reminder'
           ,'ffffg'
           ,9
           ,'Medication'
           ,1
           ,'Mitchell County Hospital'
           ,GETDATE())
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=33378
              AND [feed_header]='Health Summary Update'
              AND [feed_msg] ='Your allergies list has been updated'
              AND [feature_id]=24 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (33378
           ,'Health Summary Update'
           ,'Your allergies list has been updated'
           ,24
           ,'Health Summary'
           ,1
           ,'Mitchell County Hospital'
           ,GETDATE())
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=33378
              AND [feed_header]='Past Appointment Details'
              AND [feed_msg] ='CONCANNON, CRAIG'
              AND [feature_id]=11 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (33378
           ,'Past Appointment Details'
           ,'CONCANNON, CRAIG'
           ,11
           ,'Appointments'
           ,0
           ,'Mitchell County Hospital'
           ,'2015-08-14')
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=33378
              AND [feed_header]='Past Appointment Details'
              AND [feed_msg] ='WHITLOW, JUSTIN'
              AND [feature_id]=11 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (33378
           ,'Past Appointment Details'
           ,'WHITLOW, JUSTIN'
           ,11
           ,'Appointments'
           ,0
           ,'Mitchell County Hospital'
           ,'2015-02-02')
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=33378
              AND [feed_header]='Test Results'
              AND [feed_msg] ='Lab: CBC'
              AND [feature_id]=35 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (33378
           ,'Test Results'
           ,'Lab: CBC'
           ,35
           ,'Test results'
           ,1
           ,'Mitchell County Hospital'
           ,'2012-08-13')
END


IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=212974
              AND [feed_header]='Test Results'
              AND [feed_msg] ='Lab: B12/FOLATE PANEL'
              AND [feature_id]=35 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (212974
           ,'Test Results'
           ,'Lab: B12/FOLATE PANEL'
           ,35
           ,'Test results'
           ,1
           ,'Mitchell County Hospital'
           ,'2015-11-13')
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=212974
              AND [feed_header]='Test Results'
              AND [feed_msg] ='Lab: CBC'
              AND [feature_id]=35 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (212974
           ,'Test Results'
           ,'Lab: CBC'
           ,35
           ,'Test results'
           ,1
           ,'Mitchell County Hospital'
           ,'2015-11-13')

END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=212974
              AND [feed_header]='Refill Reminder'
              AND [feed_msg] ='aspirin'
              AND [feature_id]=9 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (212974
           ,'Refill Reminder'
           ,'aspirin'
           ,9
           ,'Medication'
           ,1
           ,'Mitchell County Hospital'
           ,GETDATE())
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=212974
              AND [feed_header]='Refill Reminder'
              AND [feed_msg] ='painkiller'
              AND [feature_id]=9 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (212974
           ,'Refill Reminder'
           ,'painkiller'
           ,9
           ,'Medication'
           ,1
           ,'Mitchell County Hospital'
           ,GETDATE())
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=212974
              AND [feed_header]='Health Summary Update'
              AND [feed_msg] ='Your allergies list has been updated'
              AND [feature_id]=24 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (212974
           ,'Health Summary Update'
           ,'Your allergies list has been updated'
           ,24
           ,'Health Summary'
           ,1
           ,'Mitchell County Hospital'
           ,GETDATE())
END
IF NOT EXISTS(SELECT * FROM [dbo].[feed_info]
              WHERE [account_id]=212974
              AND [feed_header]='Past Appointment Details'
              AND [feed_msg] ='SHARMA, BAL'
              AND [feature_id]=11 )
BEGIN
    INSERT INTO [dbo].[feed_info]
           ([account_id]
           ,[feed_header]
           ,[feed_msg]
           ,[feature_id]
           ,[feature_name]
           ,[is_new]
           ,[source_name]
           ,[date_added])
     VALUES
           (212974
           ,'Past Appointment Details'
           ,'SHARMA, BAL'
           ,11
           ,'Appointments'
           ,0
           ,'Mitchell County Hospital'
           ,'2015-08-14')

END


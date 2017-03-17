USE [engage]
GO
ALTER TABLE [dbo].[patient_visit] DROP CONSTRAINT [FK_patient_visit_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_verification_log] DROP CONSTRAINT [FK_patient_verification_log_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_verification] DROP CONSTRAINT [FK_patient_verification_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_prescription] DROP CONSTRAINT [FK_patient_prescription_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_lab] DROP CONSTRAINT [FK_patient_lab_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_feedback] DROP CONSTRAINT [FK_patient_feedback_patient_visit]
GO
ALTER TABLE [dbo].[patient_appointment_request] DROP CONSTRAINT [FK_patient_appointment_request_account]
GO
ALTER TABLE [dbo].[hospital_service] DROP CONSTRAINT [FK_hospital_service_client_naavis_databases]
GO
ALTER TABLE [dbo].[hospital_provider] DROP CONSTRAINT [FK_hospital_provider_account]
GO
ALTER TABLE [dbo].[hospital_notice] DROP CONSTRAINT [FK_hospital_notice_client_naavis_databases]
GO
ALTER TABLE [dbo].[frequency] DROP CONSTRAINT [FK_frequency_date_part]
GO
ALTER TABLE [dbo].[account_to_security_question] DROP CONSTRAINT [FK_account_to_security_question_security_question]
GO
ALTER TABLE [dbo].[account_to_security_question] DROP CONSTRAINT [FK_account_to_security_question_account]
GO
ALTER TABLE [dbo].[account_reminder_medication_relation] DROP CONSTRAINT [FK_account_reminder_medication_relation_account_medication_management_reminder]
GO
ALTER TABLE [dbo].[account_reminder_medication_relation] DROP CONSTRAINT [FK_account_reminder_medication_relation_account_medication_management]
GO
ALTER TABLE [dbo].[account_medication_management_schedule] DROP CONSTRAINT [FK_account_medication_management_schedule_date_part]
GO
ALTER TABLE [dbo].[account_medication_management_schedule] DROP CONSTRAINT [FK_account_medication_management_schedule_account_medication_management_reminder]
GO
ALTER TABLE [dbo].[account_medication_management_reminder] DROP CONSTRAINT [FK_account_medication_management_reminder_medication_dosage]
GO
ALTER TABLE [dbo].[account_medication_management_reminder] DROP CONSTRAINT [FK_account_medication_management_reminder_frequency]
GO
ALTER TABLE [dbo].[account_medication_management_reminder] DROP CONSTRAINT [FK_account_medication_management_reminder_account]
GO
ALTER TABLE [dbo].[account_medication_management] DROP CONSTRAINT [FK_account_medication_management_medication_method]
GO
ALTER TABLE [dbo].[account_medication_management] DROP CONSTRAINT [FK_account_medication_management_medication_kind]
GO
ALTER TABLE [dbo].[account_medication_management] DROP CONSTRAINT [FK_account_medication_management_account]
GO
ALTER TABLE [dbo].[account_medication_engage] DROP CONSTRAINT [FK_account_medication_engage_response]
GO
ALTER TABLE [dbo].[account_blood_pressure_management_schedule] DROP CONSTRAINT [FK_account_blood_pressure_management_schedule_date_part]
GO
ALTER TABLE [dbo].[account_blood_pressure_management] DROP CONSTRAINT [FK_account_blood_pressure_management_frequency]
GO
ALTER TABLE [dbo].[account_blood_pressure_management] DROP CONSTRAINT [FK_account_blood_pressure_management_account]
GO
ALTER TABLE [dbo].[account] DROP CONSTRAINT [FK_account_client_naavis_databases]
GO
ALTER TABLE [dbo].[account] DROP CONSTRAINT [FK_account_account_role]
GO
ALTER TABLE [dbo].[security_question] DROP CONSTRAINT [DF_security_question_date_added]
GO
ALTER TABLE [dbo].[security_question] DROP CONSTRAINT [DF_security_question_is_active]
GO
ALTER TABLE [dbo].[patient_verification_log] DROP CONSTRAINT [DF_patient_verification_log_date_added]
GO
ALTER TABLE [dbo].[patient_verification] DROP CONSTRAINT [DF_patient_verification_date_added]
GO
ALTER TABLE [dbo].[patient_prescription] DROP CONSTRAINT [DF_patient_prescription_date_added]
GO
ALTER TABLE [dbo].[patient_lab] DROP CONSTRAINT [DF_patient_lab_date_added]
GO
ALTER TABLE [dbo].[patient_feedback] DROP CONSTRAINT [DF_patient_feedback_date_added]
GO
ALTER TABLE [dbo].[patient_appointment_request] DROP CONSTRAINT [DF_patient_appointment_request_date_added]
GO
ALTER TABLE [dbo].[hospital_service] DROP CONSTRAINT [DF_hospital_service_date_added]
GO
ALTER TABLE [dbo].[hospital_provider] DROP CONSTRAINT [DF_hospital_provider_date_added]
GO
ALTER TABLE [dbo].[account_to_security_question] DROP CONSTRAINT [DF_account_to_security_question_date_added]
GO
ALTER TABLE [dbo].[account_medication_management] DROP CONSTRAINT [DF_account_medication_management_date_added]
GO
ALTER TABLE [dbo].[account_medication_engage] DROP CONSTRAINT [DF_account_medication_engage_date_added]
GO
ALTER TABLE [dbo].[account_blood_pressure_management_schedule] DROP CONSTRAINT [DF_account_blood_pressure_management_schedule_date_added]
GO
ALTER TABLE [dbo].[account_blood_pressure_management] DROP CONSTRAINT [DF_account_blood_pressure_management_date_added]
GO
ALTER TABLE [dbo].[account_blood_pressure_engage] DROP CONSTRAINT [DF_account_blood_pressure_engage_date_added]
GO
ALTER TABLE [dbo].[account] DROP CONSTRAINT [DF_account_date_added]
GO
ALTER TABLE [dbo].[account] DROP CONSTRAINT [DF_account_date_account_role_id]
GO
ALTER TABLE [dbo].[account] DROP CONSTRAINT [DF_account_failed_login_attemps]
GO
/****** Object:  Index [ix_patient_visit]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP INDEX [ix_patient_visit] ON [dbo].[patient_visit]
GO
/****** Object:  Table [dbo].[security_question]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[security_question]
GO
/****** Object:  Table [dbo].[response]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[response]
GO
/****** Object:  Table [dbo].[patient_visit]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[patient_visit]
GO
/****** Object:  Table [dbo].[patient_verification_log]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[patient_verification_log]
GO
/****** Object:  Table [dbo].[patient_verification]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[patient_verification]
GO
/****** Object:  Table [dbo].[patient_prescription]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[patient_prescription]
GO
/****** Object:  Table [dbo].[patient_lab]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[patient_lab]
GO
/****** Object:  Table [dbo].[patient_feedback]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[patient_feedback]
GO
/****** Object:  Table [dbo].[patient_appointment_request]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[patient_appointment_request]
GO
/****** Object:  Table [dbo].[medication_method]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[medication_method]
GO
/****** Object:  Table [dbo].[medication_kind]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[medication_kind]
GO
/****** Object:  Table [dbo].[medication_dosage]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[medication_dosage]
GO
/****** Object:  Table [dbo].[hospital_service]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[hospital_service]
GO
/****** Object:  Table [dbo].[hospital_provider]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[hospital_provider]
GO
/****** Object:  Table [dbo].[hospital_notice]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[hospital_notice]
GO
/****** Object:  Table [dbo].[frequency]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[frequency]
GO
/****** Object:  Table [dbo].[date_part]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[date_part]
GO
/****** Object:  Table [dbo].[client_naavis_databases]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[client_naavis_databases]
GO
/****** Object:  Table [dbo].[account_to_security_question]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_to_security_question]
GO
/****** Object:  Table [dbo].[account_role]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_role]
GO
/****** Object:  Table [dbo].[account_reminder_medication_relation]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_reminder_medication_relation]
GO
/****** Object:  Table [dbo].[account_medication_management_schedule]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_medication_management_schedule]
GO
/****** Object:  Table [dbo].[account_medication_management_reminder]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_medication_management_reminder]
GO
/****** Object:  Table [dbo].[account_medication_management]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_medication_management]
GO
/****** Object:  Table [dbo].[account_medication_engage]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_medication_engage]
GO
/****** Object:  Table [dbo].[account_blood_pressure_management_schedule]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_blood_pressure_management_schedule]
GO
/****** Object:  Table [dbo].[account_blood_pressure_management]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_blood_pressure_management]
GO
/****** Object:  Table [dbo].[account_blood_pressure_engage]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account_blood_pressure_engage]
GO
/****** Object:  Table [dbo].[account]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP TABLE [dbo].[account]
GO
USE [master]
GO
/****** Object:  Database [engage]    Script Date: 7/7/2014 5:43:13 PM ******/
DROP DATABASE [engage]
GO
/****** Object:  Database [engage]    Script Date: 7/7/2014 5:43:13 PM ******/
CREATE DATABASE [engage]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'engage', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\engage.mdf' , SIZE = 4160KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'engage_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL11.SQLEXPRESS\MSSQL\DATA\engage_log.ldf' , SIZE = 1088KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [engage] SET COMPATIBILITY_LEVEL = 110
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [engage].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [engage] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [engage] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [engage] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [engage] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [engage] SET ARITHABORT OFF 
GO
ALTER DATABASE [engage] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [engage] SET AUTO_CREATE_STATISTICS ON 
GO
ALTER DATABASE [engage] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [engage] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [engage] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [engage] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [engage] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [engage] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [engage] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [engage] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [engage] SET  DISABLE_BROKER 
GO
ALTER DATABASE [engage] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [engage] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [engage] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [engage] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [engage] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [engage] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [engage] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [engage] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [engage] SET  MULTI_USER 
GO
ALTER DATABASE [engage] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [engage] SET DB_CHAINING OFF 
GO
ALTER DATABASE [engage] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [engage] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
USE [engage]
GO
/****** Object:  Table [dbo].[account]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account](
	[client_database_id] [int] NOT NULL,
	[account_id] [int] IDENTITY(1,1) NOT NULL,
	[account_name] [nvarchar](128) NOT NULL,
	[password] [nvarchar](128) NOT NULL,
	[unit_number] [nvarchar](128) NULL,
	[date_verified] [datetime] NULL,
	[first_name] [nvarchar](128) NOT NULL,
	[last_name] [nvarchar](128) NOT NULL,
	[email] [nvarchar](128) NOT NULL,
	[mobile_phone_number] [nvarchar](128) NOT NULL,
	[failed_login_attempts] [int] NULL,
	[last_login_date] [datetime] NULL,
	[disable_date] [datetime] NULL,
	[account_role_id] [int] NULL,
	[date_added] [datetime] NOT NULL,
	[date_modified] [datetime] NULL,
	[auth_token] [nvarchar](128) NULL,
	[birth_date] [datetime] NULL,
	[will_share_data] [bit] NULL,
	[will_provide_feedback] [bit] NULL,
	[unique_id] [nvarchar](128) NULL,
	[device_token] [nvarchar](128) NULL,
 CONSTRAINT [pk_account] PRIMARY KEY CLUSTERED 
(
	[account_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_blood_pressure_engage]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_blood_pressure_engage](
	[account_blood_pressure_engage_id] [int] IDENTITY(1,1) NOT NULL,
	[account_blood_pressure_management_id] [int] NULL,
	[account_id] [int] NOT NULL,
	[sys] [int] NOT NULL,
	[dia] [int] NOT NULL,
	[pulse] [int] NOT NULL,
	[reminder_date] [datetime] NOT NULL,
	[comment] [nvarchar](512) NULL,
	[date_added] [datetime] NOT NULL,
	[delete_flag] [bit] NULL,
 CONSTRAINT [pk_account_blood_pressure_engage] PRIMARY KEY CLUSTERED 
(
	[account_blood_pressure_engage_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_blood_pressure_management]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_blood_pressure_management](
	[account_blood_pressure_management_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NOT NULL,
	[is_active] [bit] NOT NULL,
	[reminder_for] [nvarchar](128) NULL,
	[reminder_begin_date] [datetime] NOT NULL,
	[reminder_end_date] [datetime] NOT NULL,
	[frequency_id] [int] NOT NULL,
	[date_added] [datetime] NOT NULL,
	[delete_flag] [bit] NULL,
 CONSTRAINT [PK_account_blood_pressure_management] PRIMARY KEY CLUSTERED 
(
	[account_blood_pressure_management_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_blood_pressure_management_schedule]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_blood_pressure_management_schedule](
	[account_blood_pressure_management_schedule_id] [int] IDENTITY(1,1) NOT NULL,
	[account_blood_pressure_management_id] [int] NULL,
	[reminder_time] [time](7) NOT NULL,
	[date_part_id] [int] NULL,
	[interval] [int] NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [pk_account_blood_pressure_management_schedule] PRIMARY KEY CLUSTERED 
(
	[account_blood_pressure_management_schedule_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_medication_engage]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_medication_engage](
	[account_medication_engage_id] [int] IDENTITY(1,1) NOT NULL,
	[account_medication_management_id] [int] NULL,
	[account_id] [int] NOT NULL,
	[response_id] [int] NOT NULL,
	[reminder_date] [datetime] NOT NULL,
	[comment] [nvarchar](512) NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [PK_account_medication_engage] PRIMARY KEY CLUSTERED 
(
	[account_medication_engage_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_medication_management]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_medication_management](
	[account_medication_management_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NOT NULL,
	[medication_name] [nvarchar](128) NOT NULL,
	[rx_number] [nvarchar](128) NOT NULL,
	[medication_method_id] [int] NULL,
	[medication_kind_id] [int] NULL,
	[notes] [nvarchar](128) NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [PK_account_medication_management] PRIMARY KEY CLUSTERED 
(
	[account_medication_management_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_medication_management_reminder]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_medication_management_reminder](
	[account_medication_management_reminder_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NOT NULL,
	[reminder_for] [nvarchar](128) NOT NULL,
	[reminder_begin_date] [datetime] NOT NULL,
	[reminder_end_date] [datetime] NOT NULL,
	[frequency_id] [int] NOT NULL,
	[medication_dosage_id] [int] NOT NULL,
	[dosage] [nvarchar](64) NOT NULL,
	[date_added] [datetime] NOT NULL,
	[delete_flag] [nchar](10) NOT NULL,
	[is_active] [bit] NULL,
 CONSTRAINT [PK_account_medication_management_reminder] PRIMARY KEY CLUSTERED 
(
	[account_medication_management_reminder_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_medication_management_schedule]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_medication_management_schedule](
	[account_medication_management_schedule_id] [int] IDENTITY(1,1) NOT NULL,
	[account_medication_management_id] [int] NULL,
	[account_medication_management_reminder_id] [int] NULL,
	[reminder_time] [time](7) NOT NULL,
	[date_part_id] [int] NULL,
	[interval] [int] NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [pk_account_medication_management_schedule] PRIMARY KEY CLUSTERED 
(
	[account_medication_management_schedule_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_reminder_medication_relation]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_reminder_medication_relation](
	[account_reminder_medication_relation_id] [int] IDENTITY(1,1) NOT NULL,
	[account_medication_management_reminder_id] [int] NOT NULL,
	[account_medication_management_id] [int] NOT NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [PK_account_reminder_medication_relation] PRIMARY KEY CLUSTERED 
(
	[account_reminder_medication_relation_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_role]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_role](
	[account_role_id] [int] NOT NULL,
	[role_name] [nvarchar](128) NULL,
 CONSTRAINT [pk_account_role] PRIMARY KEY CLUSTERED 
(
	[account_role_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[account_to_security_question]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[account_to_security_question](
	[account_id] [int] NOT NULL,
	[security_question_id] [int] NOT NULL,
	[answer] [nvarchar](128) NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [pk_account_to_security_question] PRIMARY KEY CLUSTERED 
(
	[account_id] ASC,
	[security_question_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[client_naavis_databases]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[client_naavis_databases](
	[client_id] [int] NOT NULL,
	[client_database_id] [int] IDENTITY(1,1) NOT NULL,
	[address_1] [nvarchar](128) NULL,
	[address_2] [nvarchar](128) NULL,
	[city] [nvarchar](128) NULL,
	[state] [nvarchar](64) NULL,
	[postal_code] [nvarchar](64) NULL,
	[country] [nvarchar](64) NULL,
 CONSTRAINT [PK_client_naavis_databases] PRIMARY KEY CLUSTERED 
(
	[client_database_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[date_part]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[date_part](
	[date_part_id] [int] NOT NULL,
	[date_part_description] [nvarchar](128) NULL,
	[date_part] [nvarchar](64) NOT NULL,
 CONSTRAINT [pk_date_part] PRIMARY KEY CLUSTERED 
(
	[date_part_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[frequency]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[frequency](
	[frequency_id] [int] NOT NULL,
	[frequency] [nvarchar](128) NOT NULL,
	[date_part_id] [int] NULL,
	[interval] [int] NULL,
 CONSTRAINT [pk_frequency] PRIMARY KEY CLUSTERED 
(
	[frequency_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[hospital_notice]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hospital_notice](
	[hospital_notice_id] [int] IDENTITY(1,1) NOT NULL,
	[client_database_id] [int] NULL,
	[begin_date] [datetime] NULL,
	[end_date] [datetime] NULL,
	[notice_message] [nvarchar](max) NULL,
	[account_role_id] [int] NULL,
 CONSTRAINT [PK_hospital_notice] PRIMARY KEY CLUSTERED 
(
	[hospital_notice_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]

GO
/****** Object:  Table [dbo].[hospital_provider]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hospital_provider](
	[client_database_id] [int] NOT NULL,
	[source_id] [nvarchar](64) NOT NULL,
	[provider_id] [nvarchar](64) NOT NULL,
	[first_name] [nvarchar](128) NOT NULL,
	[last_name] [nvarchar](128) NOT NULL,
	[suffix] [nvarchar](64) NULL,
	[specialty] [nvarchar](64) NOT NULL,
	[address_1] [nvarchar](128) NOT NULL,
	[address_2] [nvarchar](128) NULL,
	[city] [nvarchar](128) NOT NULL,
	[state] [nvarchar](8) NOT NULL,
	[postal_code] [nvarchar](64) NOT NULL,
	[contact_phone_number] [nvarchar](64) NOT NULL,
	[contact_phone_extention] [nvarchar](64) NULL,
	[fax_number] [nvarchar](64) NULL,
	[contact_email] [nvarchar](64) NULL,
	[date_added] [datetime] NOT NULL,
	[date_modified] [datetime] NULL,
	[account_id] [int] NULL
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[hospital_service]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[hospital_service](
	[client_database_id] [int] NOT NULL,
	[source_id] [nvarchar](64) NOT NULL,
	[service_group] [nvarchar](64) NOT NULL,
	[service_id] [nvarchar](128) NOT NULL,
	[service] [nvarchar](128) NOT NULL,
	[contact_phone_number] [nvarchar](64) NOT NULL,
	[contact_phone_extention] [nvarchar](64) NULL,
	[contact_email] [nvarchar](64) NULL,
	[date_added] [datetime] NOT NULL,
	[date_modified] [datetime] NULL,
 CONSTRAINT [pk_hospital_service] PRIMARY KEY CLUSTERED 
(
	[client_database_id] ASC,
	[source_id] ASC,
	[service_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[medication_dosage]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[medication_dosage](
	[medication_dosage_id] [int] NOT NULL,
	[medication_dosage] [nvarchar](128) NULL,
 CONSTRAINT [pk_medication_dosage] PRIMARY KEY CLUSTERED 
(
	[medication_dosage_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[medication_kind]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[medication_kind](
	[medication_kind_id] [int] NOT NULL,
	[medication_kind] [nvarchar](128) NULL,
 CONSTRAINT [pk_medication_kind] PRIMARY KEY CLUSTERED 
(
	[medication_kind_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[medication_method]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[medication_method](
	[medication_method_id] [int] NOT NULL,
	[medication_method] [nvarchar](128) NULL,
 CONSTRAINT [pk_medication_method] PRIMARY KEY CLUSTERED 
(
	[medication_method_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[patient_appointment_request]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient_appointment_request](
	[patient_appointment_request_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NOT NULL,
	[appointment_date] [date] NOT NULL,
	[appointment_time] [time](7) NOT NULL,
	[contact_phone] [nvarchar](64) NOT NULL,
	[contact_email] [nvarchar](64) NOT NULL,
	[provider_id] [nvarchar](64) NULL,
	[comment] [nvarchar](512) NULL,
	[request_sent_date] [datetime] NULL,
	[date_added] [datetime] NOT NULL,
	[patient_name] [nvarchar](64) NULL,
	[confirm_date] [datetime] NULL,
	[confirmed_by] [nvarchar](128) NULL,
 CONSTRAINT [pk_patient_appointment_request] PRIMARY KEY CLUSTERED 
(
	[patient_appointment_request_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[patient_feedback]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient_feedback](
	[patient_feedback_id] [int] IDENTITY(1,1) NOT NULL,
	[patient_visit_id] [int] NOT NULL,
	[visit_rating] [int] NOT NULL,
	[recovery_rating] [int] NOT NULL,
	[comment] [nvarchar](512) NULL,
	[last_viewed_date] [datetime] NULL,
	[last_viewed_by_acount_id] [int] NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [pk_patient_feedback] PRIMARY KEY CLUSTERED 
(
	[patient_feedback_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[patient_lab]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient_lab](
	[client_database_id] [int] NOT NULL,
	[source_id] [nvarchar](64) NOT NULL,
	[unit_number] [nvarchar](128) NOT NULL,
	[account_number] [nvarchar](128) NOT NULL,
	[specimen_id] [nvarchar](128) NOT NULL,
	[test_number] [nvarchar](128) NOT NULL,
	[test_mnemonic] [nvarchar](128) NOT NULL,
	[test_name] [nvarchar](128) NOT NULL,
	[test_result] [nvarchar](128) NOT NULL,
	[test_unit] [nvarchar](64) NOT NULL,
	[result_date] [datetime] NOT NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [pk_patient_lab] PRIMARY KEY CLUSTERED 
(
	[client_database_id] ASC,
	[source_id] ASC,
	[unit_number] ASC,
	[specimen_id] ASC,
	[test_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[patient_prescription]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[patient_prescription](
	[client_database_id] [int] NOT NULL,
	[source_id] [nvarchar](64) NOT NULL,
	[unit_number] [nvarchar](128) NOT NULL,
	[account_number] [nvarchar](128) NOT NULL,
	[prescription_id] [int] NOT NULL,
	[drug_id] [nvarchar](64) NOT NULL,
	[drug_name] [nvarchar](128) NOT NULL,
	[drug_type] [nvarchar](64) NULL,
	[drug_status] [nvarchar](64) NULL,
	[issued_date] [datetime] NOT NULL,
	[discontinued_date] [datetime] NULL,
	[date_added] [datetime] NOT NULL,
	[dose] [varchar](29) NULL,
 CONSTRAINT [pk_patient_prescription] PRIMARY KEY CLUSTERED 
(
	[client_database_id] ASC,
	[source_id] ASC,
	[unit_number] ASC,
	[prescription_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO
/****** Object:  Table [dbo].[patient_verification]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient_verification](
	[client_database_id] [int] NOT NULL,
	[source_id] [nvarchar](64) NOT NULL,
	[unit_number] [nvarchar](128) NOT NULL,
	[first_name] [nvarchar](128) NOT NULL,
	[last_name] [nvarchar](128) NOT NULL,
	[birth_date] [datetime] NOT NULL,
	[date_validated] [datetime] NULL,
	[account_id] [int] NULL,
	[date_added] [datetime] NOT NULL,
	[date_modified] [datetime] NULL,
 CONSTRAINT [pk_patient_verification] PRIMARY KEY CLUSTERED 
(
	[client_database_id] ASC,
	[source_id] ASC,
	[unit_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[patient_verification_log]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient_verification_log](
	[client_database_id] [int] NOT NULL,
	[source_id] [nvarchar](64) NOT NULL,
	[unit_number] [nvarchar](128) NOT NULL,
	[ssn] [nvarchar](128) NULL,
	[first_name] [nvarchar](128) NOT NULL,
	[last_name] [nvarchar](128) NOT NULL,
	[birth_date] [datetime] NOT NULL,
	[date_verified] [datetime] NULL,
	[account_id] [int] NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [pk_patient_verification_log] PRIMARY KEY CLUSTERED 
(
	[client_database_id] ASC,
	[source_id] ASC,
	[unit_number] ASC,
	[date_added] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[patient_visit]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[patient_visit](
	[patient_visit_id] [int] IDENTITY(1,1) NOT NULL,
	[client_database_id] [int] NOT NULL,
	[source_id] [nvarchar](64) NOT NULL,
	[unit_number] [nvarchar](128) NOT NULL,
	[account_number] [nvarchar](128) NOT NULL,
	[first_name] [nvarchar](128) NOT NULL,
	[middle_name] [nvarchar](128) NULL,
	[last_name] [nvarchar](128) NOT NULL,
	[visit_date] [datetime] NOT NULL,
	[location] [nvarchar](128) NOT NULL,
	[admit_date] [datetime] NOT NULL,
	[discharge_date] [datetime] NULL,
	[attending_physician_name] [nvarchar](128) NOT NULL,
	[provider_id] [nvarchar](64) NOT NULL,
 CONSTRAINT [pk_patient_visit] PRIMARY KEY CLUSTERED 
(
	[patient_visit_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[response]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[response](
	[response_id] [int] NOT NULL,
	[response] [nvarchar](128) NULL,
 CONSTRAINT [pk_response] PRIMARY KEY CLUSTERED 
(
	[response_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
/****** Object:  Table [dbo].[security_question]    Script Date: 7/7/2014 5:43:13 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[security_question](
	[security_question_id] [int] NOT NULL,
	[question] [nvarchar](128) NULL,
	[is_active] [bit] NULL,
	[date_added] [datetime] NOT NULL,
 CONSTRAINT [pk_security_question] PRIMARY KEY CLUSTERED 
(
	[security_question_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[account] ON 

INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 1, N'wwong', N'abc123', N'M323458787', NULL, N'Wil', N'Wong', N'w@versaworks.com', N'408-780-0230', 0, NULL, NULL, 1, CAST(0x0000A31400A4C93D AS DateTime), NULL, NULL, NULL, 1, 1, NULL, NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 62, N'vishva.singh@ireslab.com', N'9834876DCFB05CB167A5C24953EBA58C4AC89B1ADF57F28F2F9D09AF107EE8F0', N'M000111133', NULL, N'vishva', N'singh', N'vishva.singh@ireslab.com', N'555', NULL, NULL, NULL, 3, CAST(0x0000A32E00EFF470 AS DateTime), CAST(0x0000A347003FFD6B AS DateTime), N'C08A4F63158773B8677045EF8119FD6B', CAST(0x0000738200000000 AS DateTime), 0, 0, N'675FB0', N'c13a786e5b0fc5565ba9980d5361aa25650078c7820a1f3541ff3d903e36affc')
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 63, N'dheeraj.borra@ireslab.com', N'03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4', N'M116544787', NULL, N'Dheeraj', N'Borra', N'dheeraj.borra@ireslab.com', N'0', NULL, NULL, NULL, 3, CAST(0x0000A32E00F73BF8 AS DateTime), CAST(0x0000A346002306F6 AS DateTime), N'FDB0981C78E00D1524A5B83315FA2F19', CAST(0x0000848700000000 AS DateTime), 1, 1, N'530051', N'c13a786e5b0fc5565ba9980d5361aa25650078c7820a1f3541ff3d903e36affc')
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 64, N'sunil.yadav@ireslab.com', N'AF674ED66A726918583170C53C585B02055E388D07642154616E10D0BD04FC63', NULL, NULL, N'Sunil', N'yadav', N'sunil.yadav@ireslab.com', N'', NULL, NULL, NULL, 2, CAST(0x0000A32E0131E904 AS DateTime), NULL, N'B2F3FD0D076906CD3FD6ED9BC00351EF', CAST(0x000063D50121D2F4 AS DateTime), 1, 1, N'E53643', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 1060, N'richa.sahu@versaworks.com', N'6ABAB5A4453C611F799F7D1B7B2C1CDD5040AE19691875F7EF36DB8234668BF4', N'M000111144', NULL, N'richa', N'sahu', N'richa.sahu@versaworks.com', N'', NULL, NULL, NULL, 3, CAST(0x0000A33100317B62 AS DateTime), NULL, N'F88F3F8BD48CB62E419B749F51E45CA1', CAST(0x00007E1800000000 AS DateTime), 1, 1, N'F065FC', N'0915659844d1cea1ba84de2a3414bd8c7353f54297783fb8ed70de22d717d9b1')
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 1063, N'Preet.Singh@ireslab.com', N'EBEC234D3688CDC37DFAF581733DE95F826F90F36040648D48011BA7E524A5F0', N'25482', NULL, N'Pragati', N'Singh', N'pragati.singh@ireslab.com', N'8800559323', NULL, NULL, NULL, 3, CAST(0x0000A33200D16054 AS DateTime), NULL, N'4B4802C34A14C9AFD62E363B2FE0B057', CAST(0x00007AAF00000000 AS DateTime), 1, 1, N'5DDDF5', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2060, N'ajay@ireslab.com', N'E3B0C44298FC1C149AFBF4C8996FB92427AE41E4649B934CA495991B7852B855', NULL, NULL, N'ajay', N'puru', N'ajay@ireslab.com', N'', NULL, NULL, NULL, 2, CAST(0x0000A33200FE6911 AS DateTime), NULL, N'67D072C20488C1A707C9C43318EF8A6C', CAST(0x00006ACE00000000 AS DateTime), 1, 1, N'2FD9CC', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2063, N'Nikhil@versaworks.com', N'C9A824355DAA6DFFD0CFDBDBEF2E845242418FABD469BE95FC0CB022CF32A49A', NULL, NULL, N'Nikhil', N'Kumar', N'nikhil@versaworks.com', N'', NULL, NULL, NULL, 4, CAST(0x0000A3320189DCB7 AS DateTime), CAST(0x0000A3470064428F AS DateTime), N'FE4C4E1BA30223CA8CF9130DBBB67E50', CAST(0x0000A33200000000 AS DateTime), 1, 1, N'9E647C', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2064, N'Raj@versaworks.com', N'9834876DCFB05CB167A5C24953EBA58C4AC89B1ADF57F28F2F9D09AF107EE8F0', NULL, NULL, N'Raj', N'Sharma', N'raj@versaworks.com', N'', NULL, NULL, NULL, 4, CAST(0x0000A33300322A1D AS DateTime), NULL, N'A12DDC11A9A8EC561D1DA973D807DDDD', CAST(0x0000826200000000 AS DateTime), 1, 1, N'000A80', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2065, N'info@versaworks.com', N'46437AB18A6657040B4535297FF247B20C535C02263713F88B6A9E17484F1F3F', NULL, NULL, N'MARK', N'ANDERSON', N'info@versaworks.com', N'', NULL, NULL, NULL, 4, CAST(0x0000A3330032DC9A AS DateTime), CAST(0x0000A33400C27347 AS DateTime), N'A6F2509C102C5EEEC41E246761D13722', CAST(0x0000A33300000000 AS DateTime), 1, 1, N'2F5FF4', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2066, N'Ashish@versaworks.com', N'03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4', NULL, NULL, N'Ashish', N'Singh', N'ashish@versaworks.com', N'', NULL, NULL, NULL, 4, CAST(0x0000A33300F613C9 AS DateTime), NULL, N'C28BC92B62DBC74994415B57DAB6A329', CAST(0x000082CF00000000 AS DateTime), 1, 1, N'84168A', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2067, N'Nidhi@versaworks.com', N'03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4', NULL, NULL, N'Nidhi', N'Garg', N'nidhi@versaworks.com', N'', NULL, NULL, NULL, 4, CAST(0x0000A33300F75444 AS DateTime), NULL, N'8D57E9D769E9D49B93F251F26477C312', CAST(0x0000901A00000000 AS DateTime), 1, 1, N'2CB40C', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2068, N'Kanak@versaworks.com', N'03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4', NULL, NULL, N'Kanak', N'Ray', N'kanak@versaworks.com', N'', NULL, NULL, NULL, 4, CAST(0x0000A33300F92AF5 AS DateTime), NULL, N'C1CC5EDADC042A69EEE0F5ED862E7489', CAST(0x0000717300000000 AS DateTime), 1, 1, N'771767', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2069, N'sekhar.patra@ireslab.com', N'9834876DCFB05CB167A5C24953EBA58C4AC89B1ADF57F28F2F9D09AF107EE8F0', NULL, NULL, N'Sekhar', N'Patra', N'sekhar.patra@ireslab.com', N'', NULL, NULL, NULL, 2, CAST(0x0000A3330118E66F AS DateTime), NULL, N'970043BB4D7631A2B336A9BD6CAD3EFA', CAST(0x00007B7100000000 AS DateTime), 1, 1, N'EF0088', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2070, N'mohit@versaworks.com', N'46437AB18A6657040B4535297FF247B20C535C02263713F88B6A9E17484F1F3F', N'M876544787', NULL, N'Mohit', N'Jain', N'mohit@versaworks.com', N'', NULL, NULL, NULL, 3, CAST(0x0000A333012F8066 AS DateTime), NULL, N'54E854AAE2411BB0D235CB2021B9513E', CAST(0x00007D0900000000 AS DateTime), 1, 1, N'643C26', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2071, N'subham@versaworks.com', N'C9A824355DAA6DFFD0CFDBDBEF2E845242418FABD469BE95FC0CB022CF32A49A', N'M336544787', NULL, N'Subham', N'Jain', N'subham@versaworks.com', N'', NULL, NULL, NULL, 3, CAST(0x0000A3330130FB89 AS DateTime), CAST(0x0000A334004596FB AS DateTime), N'91743F0167BE007B053BEB713E6ACC07', CAST(0x000079CA00000000 AS DateTime), 1, 1, N'67CAAE', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2074, N'shawn.savadkohi@versaworks.com', N'C9A824355DAA6DFFD0CFDBDBEF2E845242418FABD469BE95FC0CB022CF32A49A', NULL, NULL, N'Shawn', N'Savadkohi', N'shawn.savadkohi@versaworks.com', N'408-780-789', 0, NULL, NULL, 4, CAST(0x0000A334005C5E18 AS DateTime), CAST(0x0000A3370068C6F2 AS DateTime), N'FDB0981C78E00D1524A5B83315FA2F12', CAST(0x00006FD500000000 AS DateTime), 1, 0, N'A8A8A9', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2075, N'wilbur.wong@versaworks.com', N'C9A824355DAA6DFFD0CFDBDBEF2E845242418FABD469BE95FC0CB022CF32A49A', N'M000111112', NULL, N'Wil', N'Wong', N'wilbur.wong@versaworks.com', N'07838898323', NULL, NULL, NULL, 3, CAST(0x0000A33400709808 AS DateTime), CAST(0x0000A348005CA4F5 AS DateTime), N'3F4F443238E002297CD8B1989D22A75D', CAST(0x0000A34700000000 AS DateTime), 1, 0, N'E19A2E', N'0915659844d1cea1ba84de2a3414bd8c7353f54297783fb8ed70de22d717d9b1')
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2076, N'preet.singh@ireslab.com', N'C9A824355DAA6DFFD0CFDBDBEF2E845242418FABD469BE95FC0CB022CF32A49A', N'M000111122', NULL, N'Preet', N'Singh', N'preet.singh@ireslab.com', N'', NULL, NULL, NULL, 3, CAST(0x0000A339006B8B35 AS DateTime), NULL, N'BE5FE7B6AFFB43B5CF6CFB5801B88ABA', CAST(0x0000606700000000 AS DateTime), 1, 1, N'0F4776', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2077, N'nsns@gmail.com', N'BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD', NULL, NULL, N'Hejsj', N'Whehs', N'nsns@gmail.com', N'455446', NULL, NULL, NULL, 2, CAST(0x0000A3450051087C AS DateTime), NULL, N'24370AAFEC424EB53D9FDB0EC6CBC9A9', CAST(0x0000AD4200000000 AS DateTime), 1, 1, N'610FF5', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2078, N'jshubham16@gmail.com', N'BA7816BF8F01CFEA414140DE5DAE2223B00361A396177A9CB410FF61F20015AD', NULL, NULL, N'Shubham', N'Jain', N'jshubham16@gmail.com', N'9540174127', NULL, NULL, NULL, 2, CAST(0x0000A345005810E8 AS DateTime), NULL, N'06BE67CBE70DD9A06A3E301C40B3AE22', CAST(0x0000A30800000000 AS DateTime), 1, 1, N'90DD8B', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2079, N'richa.sahu86@gmail.com', N'6ABAB5A4453C611F799F7D1B7B2C1CDD5040AE19691875F7EF36DB8234668BF4', NULL, NULL, N'Richa', N'Sahu', N'richa.sahu86@gmail.com', N'23568974', NULL, NULL, NULL, 2, CAST(0x0000A34500E43342 AS DateTime), NULL, N'6D9805B77A69D32EA8F393B8C720A4F9', CAST(0x0000810700000000 AS DateTime), 1, 1, N'B93BEB', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2080, N'sp.pat@v.com', N'CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB', NULL, NULL, N'Sp', N'Pat', N'sp.pat@v.com', N'000000000000000000', NULL, NULL, NULL, 2, CAST(0x0000A348004EE831 AS DateTime), CAST(0x0000A3480058B448 AS DateTime), N'95CFE663DE63C1700AA1F181F4EE8205', CAST(0x0000A34800000000 AS DateTime), 1, 1, N'995EA6', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2081, N'pk@v.com', N'CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB', NULL, NULL, N'Preet', N'KanwR', N'pk@v.com', N'085269', NULL, NULL, NULL, 2, CAST(0x0000A34800555112 AS DateTime), CAST(0x0000A3480055A6AC AS DateTime), N'29371D5AA48F9BF389F5E0B334342C4C', CAST(0x0000B55C00000000 AS DateTime), 0, 0, N'81A35F', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2082, N'sp.pat1@v.com', N'CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB', NULL, NULL, N'Sp', N'Pat', N'sp.pat1@v.com', N'0123456789', NULL, NULL, NULL, 2, CAST(0x0000A349003D463B AS DateTime), NULL, N'9E3FE2E7187BDA84F0DD7C50C758D3DA', CAST(0x0000A34900000000 AS DateTime), 1, 1, N'AA126D', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2083, N'sekpat@v.com', N'6B86B273FF34FCE19D6B804EFF5A3F5747ADA4EAA22F1D49C01E52DDB7875B4B', NULL, NULL, N'Sek', N'Pat', N'sekpat@v.com', N'0123', NULL, NULL, NULL, 2, CAST(0x0000A349003E40BB AS DateTime), NULL, N'69729B8440754B10AFE643C54F87EA3D', CAST(0x0000A34900000000 AS DateTime), 1, 1, N'B58E29', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2084, N'sp@v.com', N'CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB', NULL, NULL, N'Sek', N'Pat', N'sp@v.com', N'123456', NULL, NULL, NULL, 2, CAST(0x0000A349004D05E0 AS DateTime), NULL, N'219965DA62240DD398797D4E11D73BD2', CAST(0x0000A34900000000 AS DateTime), 1, 1, N'DA014B', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2085, N'richa.sahu1586@gmail.com', N'6ABAB5A4453C611F799F7D1B7B2C1CDD5040AE19691875F7EF36DB8234668BF4', NULL, NULL, N'Richa', N'Sahu', N'richa.sahu1586@gmail.com', N'4562364789', NULL, NULL, NULL, 2, CAST(0x0000A34900D64D23 AS DateTime), NULL, N'A026D4FD0876F2920D6DF78E59904BBA', CAST(0x00007F9E00000000 AS DateTime), 1, 1, N'7D267D', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2086, N'richa@versaworks.com', N'C6CAD79C8520A44FC1E52B920CDC8DAEDCBC5B6127D189EEC66D3407F18DCE17', NULL, NULL, N'Richa', N'Sahu', N'richa@versaworks.com', N'4556889741235', NULL, NULL, NULL, 2, CAST(0x0000A34900D7CF0D AS DateTime), NULL, N'FE6A005CC7D3D4FCCB0C775C88CA7FA3', CAST(0x0000882D00000000 AS DateTime), 1, 1, N'BDC05E', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2087, N'richa150786@gmail.com', N'7B909F2B072D082D6BCBB7826FAADD74BBB41D2616972DAC5BE2E351AAAC0F9F', NULL, NULL, N'Richa', N'Sahu', N'richa150786@gmail.com', N'456897563', NULL, NULL, NULL, 2, CAST(0x0000A34900DD6D47 AS DateTime), NULL, N'D16213777D9CB25F646FF8663E909751', CAST(0x00007F9E00000000 AS DateTime), 1, 1, N'BE5FD0', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2088, N'richa.sahu2013@gmail.com', N'6ABAB5A4453C611F799F7D1B7B2C1CDD5040AE19691875F7EF36DB8234668BF4', NULL, NULL, N'Richa', N'Sahu', N'richa.sahu2013@gmail.com', N'1237896542', NULL, NULL, NULL, 2, CAST(0x0000A34900E110FB AS DateTime), NULL, N'56AE76E25AD1A29B1E0A19232E228A6F', CAST(0x00007B5600000000 AS DateTime), 1, 1, N'683C80', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2089, N'a.b@v.com', N'CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB', NULL, NULL, N'S', N'K', N'a.b@v.com', N'0852369', NULL, NULL, NULL, 2, CAST(0x0000A34B0170E6A9 AS DateTime), NULL, N'A230D75A21DC2B28236A6F2E2F5E5A2C', CAST(0x0000A34C00000000 AS DateTime), 1, 1, N'BCDB7E', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2090, N'skp@v.com', N'CA978112CA1BBDCAFAC231B39A23DC4DA786EFF8147C4E72B9807785AFEE48BB', NULL, NULL, N'patra', N'sekh', N'skp@v.com', N'1472583690', NULL, NULL, NULL, 2, CAST(0x0000A353006BA708 AS DateTime), NULL, N'F0F4B0AB5684C84AAAFB2E0E28671429', CAST(0x0000A1A900000000 AS DateTime), 1, 1, N'2A867C', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2091, N'arun.kumar@gmail.com', N'03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4', NULL, NULL, N'Arun', N'Kumar', N'arun.kumar@gmail.com', N'0123456789', NULL, NULL, NULL, 2, CAST(0x0000A353017BC97F AS DateTime), NULL, N'F0BECFD190FEAFCA9AC8AC74E945316C', CAST(0x00009B3700000000 AS DateTime), 1, 1, N'24F9F3', NULL)
INSERT [dbo].[account] ([client_database_id], [account_id], [account_name], [password], [unit_number], [date_verified], [first_name], [last_name], [email], [mobile_phone_number], [failed_login_attempts], [last_login_date], [disable_date], [account_role_id], [date_added], [date_modified], [auth_token], [birth_date], [will_share_data], [will_provide_feedback], [unique_id], [device_token]) VALUES (1, 2092, N'arun.kawdiya@gmail.com', N'03AC674216F3E15C761EE1A5E255F067953623C8B388B4459E13F978D7C846F4', NULL, NULL, N'Arun', N'Kawdiya', N'arun.kawdiya@gmail.com', N'0123456789', NULL, NULL, NULL, 2, CAST(0x0000A3530183178B AS DateTime), NULL, N'2FD058A104E307F1992A3755F5D91C9C', CAST(0x00007F8F00000000 AS DateTime), 1, 1, N'8CACEF', NULL)
SET IDENTITY_INSERT [dbo].[account] OFF
SET IDENTITY_INSERT [dbo].[account_blood_pressure_engage] ON 

INSERT [dbo].[account_blood_pressure_engage] ([account_blood_pressure_engage_id], [account_blood_pressure_management_id], [account_id], [sys], [dia], [pulse], [reminder_date], [comment], [date_added], [delete_flag]) VALUES (1, NULL, 63, 100, 100, 100, CAST(0x0000A36100A78AA0 AS DateTime), N'High', CAST(0x0000A35500ADAA8E AS DateTime), 1)
INSERT [dbo].[account_blood_pressure_engage] ([account_blood_pressure_engage_id], [account_blood_pressure_management_id], [account_id], [sys], [dia], [pulse], [reminder_date], [comment], [date_added], [delete_flag]) VALUES (2, NULL, 63, 150, 150, 150, CAST(0x0000A36100860880 AS DateTime), N'blood pressure', CAST(0x0000A35500AA1E0B AS DateTime), 0)
INSERT [dbo].[account_blood_pressure_engage] ([account_blood_pressure_engage_id], [account_blood_pressure_management_id], [account_id], [sys], [dia], [pulse], [reminder_date], [comment], [date_added], [delete_flag]) VALUES (3, NULL, 63, 120, 120, 120, CAST(0x0000A36100860880 AS DateTime), N'blood pressure', CAST(0x0000A35500AA2BD5 AS DateTime), 0)
INSERT [dbo].[account_blood_pressure_engage] ([account_blood_pressure_engage_id], [account_blood_pressure_management_id], [account_id], [sys], [dia], [pulse], [reminder_date], [comment], [date_added], [delete_flag]) VALUES (4, NULL, 63, 250, 150, 180, CAST(0x0000A3420053C550 AS DateTime), N'blood pressure', CAST(0x0000A35801241CA2 AS DateTime), 0)
INSERT [dbo].[account_blood_pressure_engage] ([account_blood_pressure_engage_id], [account_blood_pressure_management_id], [account_id], [sys], [dia], [pulse], [reminder_date], [comment], [date_added], [delete_flag]) VALUES (5, NULL, 63, 200, 200, 200, CAST(0x0000A3420053C550 AS DateTime), N'Dheeraj''s blood pressure', CAST(0x0000A36100E08CA2 AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[account_blood_pressure_engage] OFF
SET IDENTITY_INSERT [dbo].[account_blood_pressure_management] ON 

INSERT [dbo].[account_blood_pressure_management] ([account_blood_pressure_management_id], [account_id], [is_active], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [date_added], [delete_flag]) VALUES (1, 63, 0, N'Dheeraj', CAST(0x0000A36500000000 AS DateTime), CAST(0x0000A4D200000000 AS DateTime), 2, CAST(0x0000A358010F445F AS DateTime), 0)
INSERT [dbo].[account_blood_pressure_management] ([account_blood_pressure_management_id], [account_id], [is_active], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [date_added], [delete_flag]) VALUES (2, 63, 0, N'Preet', CAST(0x0000A37100000000 AS DateTime), CAST(0x0000A4DE00000000 AS DateTime), 1, CAST(0x0000A35A00A2D6C4 AS DateTime), 0)
INSERT [dbo].[account_blood_pressure_management] ([account_blood_pressure_management_id], [account_id], [is_active], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [date_added], [delete_flag]) VALUES (3, 63, 1, N'Preet', CAST(0x0000A37100000000 AS DateTime), CAST(0x0000A4DE00000000 AS DateTime), 1, CAST(0x0000A35D00F8C779 AS DateTime), 0)
SET IDENTITY_INSERT [dbo].[account_blood_pressure_management] OFF
SET IDENTITY_INSERT [dbo].[account_blood_pressure_management_schedule] ON 

INSERT [dbo].[account_blood_pressure_management_schedule] ([account_blood_pressure_management_schedule_id], [account_blood_pressure_management_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (4, 1, CAST(0x0700CC4C37550000 AS Time), 2, 2, CAST(0x0000A358010F446D AS DateTime))
INSERT [dbo].[account_blood_pressure_management_schedule] ([account_blood_pressure_management_schedule_id], [account_blood_pressure_management_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (5, 1, CAST(0x0700CC4C37550000 AS Time), 2, 2, CAST(0x0000A358010F44C3 AS DateTime))
INSERT [dbo].[account_blood_pressure_management_schedule] ([account_blood_pressure_management_schedule_id], [account_blood_pressure_management_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (6, 2, CAST(0x070066A69B2A0000 AS Time), 1, 1, CAST(0x0000A35A00A2D712 AS DateTime))
INSERT [dbo].[account_blood_pressure_management_schedule] ([account_blood_pressure_management_schedule_id], [account_blood_pressure_management_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (7, 2, CAST(0x070066A69B2A0000 AS Time), 1, 1, CAST(0x0000A35A00A2D719 AS DateTime))
INSERT [dbo].[account_blood_pressure_management_schedule] ([account_blood_pressure_management_schedule_id], [account_blood_pressure_management_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (8, 3, CAST(0x070010ACD1530000 AS Time), 1, 1, CAST(0x0000A35D00F8C780 AS DateTime))
SET IDENTITY_INSERT [dbo].[account_blood_pressure_management_schedule] OFF
SET IDENTITY_INSERT [dbo].[account_medication_engage] ON 

INSERT [dbo].[account_medication_engage] ([account_medication_engage_id], [account_medication_management_id], [account_id], [response_id], [reminder_date], [comment], [date_added]) VALUES (1, 0, 63, 1, CAST(0x0000A32700229B60 AS DateTime), N'will take now', CAST(0x0000A35801225187 AS DateTime))
SET IDENTITY_INSERT [dbo].[account_medication_engage] OFF
SET IDENTITY_INSERT [dbo].[account_medication_management] ON 

INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (1, 63, N'DCold', N'DCold12354', 1, 1, N'Take twice', CAST(0x0000A35500AEE9F4 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (2, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A35500F8BE65 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (3, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A35601031EFC AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (4, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A3560103B423 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (5, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A35601046089 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (6, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A35601048D82 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (7, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A3560104DDF9 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (8, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A35601058373 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (9, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A3560107306D AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (10, 63, N'Paracetamol', N'Paracetamol1254', 1, 1, N'Twice a day', CAST(0x0000A356010C8290 AS DateTime))
INSERT [dbo].[account_medication_management] ([account_medication_management_id], [account_id], [medication_name], [rx_number], [medication_method_id], [medication_kind_id], [notes], [date_added]) VALUES (11, 63, N'Sporolac', N'Sporolac1254', 1, 1, N'Twice a day', CAST(0x0000A3580122A390 AS DateTime))
SET IDENTITY_INSERT [dbo].[account_medication_management] OFF
SET IDENTITY_INSERT [dbo].[account_medication_management_reminder] ON 

INSERT [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id], [account_id], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [medication_dosage_id], [dosage], [date_added], [delete_flag], [is_active]) VALUES (1, 63, N'Vishva', CAST(0x0000A3CA00000000 AS DateTime), CAST(0x0000A55600000000 AS DateTime), 2, 1, N'2', CAST(0x0000A35801415C69 AS DateTime), N'0         ', 1)
INSERT [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id], [account_id], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [medication_dosage_id], [dosage], [date_added], [delete_flag], [is_active]) VALUES (6, 63, N'Dheeraj', CAST(0x0000A36100000000 AS DateTime), CAST(0x0000A36100000000 AS DateTime), 1, 1, N'2', CAST(0x0000A3550101BE0A AS DateTime), N'0         ', 1)
INSERT [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id], [account_id], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [medication_dosage_id], [dosage], [date_added], [delete_flag], [is_active]) VALUES (7, 63, N'Preet', CAST(0x0000A36100000000 AS DateTime), CAST(0x0000A36100000000 AS DateTime), 1, 1, N'2', CAST(0x0000A35601027957 AS DateTime), N'0         ', 1)
INSERT [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id], [account_id], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [medication_dosage_id], [dosage], [date_added], [delete_flag], [is_active]) VALUES (8, 63, N'Preet', CAST(0x0000A3C000000000 AS DateTime), CAST(0x0000A53800000000 AS DateTime), 2, 1, N'2', CAST(0x0000A35801160E3A AS DateTime), N'0         ', 1)
INSERT [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id], [account_id], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [medication_dosage_id], [dosage], [date_added], [delete_flag], [is_active]) VALUES (13, 63, N'Preet', CAST(0x0000A3C000000000 AS DateTime), CAST(0x0000A53800000000 AS DateTime), 2, 1, N'2', CAST(0x0000A35A01411E6B AS DateTime), N'0         ', 1)
INSERT [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id], [account_id], [reminder_for], [reminder_begin_date], [reminder_end_date], [frequency_id], [medication_dosage_id], [dosage], [date_added], [delete_flag], [is_active]) VALUES (15, 63, N'Preet', CAST(0x0000A3C000000000 AS DateTime), CAST(0x0000A53800000000 AS DateTime), 2, 1, N'2', CAST(0x0000A35D00F3DB64 AS DateTime), N'0         ', 1)
SET IDENTITY_INSERT [dbo].[account_medication_management_reminder] OFF
SET IDENTITY_INSERT [dbo].[account_medication_management_schedule] ON 

INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (1, NULL, 6, CAST(0x070066A69B2A0000 AS Time), 1, 1, CAST(0x0000A3550101BE20 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (2, NULL, 6, CAST(0x070066A69B2A0000 AS Time), 1, 1, CAST(0x0000A3550101BE23 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (4, NULL, 7, CAST(0x070066A69B2A0000 AS Time), 1, 1, CAST(0x0000A35601027A09 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (5, NULL, 7, CAST(0x070066A69B2A0000 AS Time), 1, 1, CAST(0x0000A35601027A30 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (6, NULL, 8, CAST(0x0700CC4C37550000 AS Time), 2, 2, CAST(0x0000A35801160EF4 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (7, NULL, 8, CAST(0x0700CC4C37550000 AS Time), 2, 2, CAST(0x0000A35801160EFF AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (9, NULL, 1, CAST(0x0700D6E3C76E0000 AS Time), 2, 2, CAST(0x0000A35801415CB2 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (10, NULL, 1, CAST(0x0700D6E3C76E0000 AS Time), 2, 2, CAST(0x0000A35801415CB6 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (11, NULL, 1, CAST(0x0700E68F99C20000 AS Time), 2, 2, CAST(0x0000A35801415CB8 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (12, NULL, 13, CAST(0x07001882BA7D0000 AS Time), 2, 2, CAST(0x0000A35A01411E7C AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (13, NULL, 13, CAST(0x0700881C05B00000 AS Time), 2, 2, CAST(0x0000A35A01411EB9 AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (14, NULL, 15, CAST(0x070058A5C8C00000 AS Time), 2, 2, CAST(0x0000A35D00F3DB6B AS DateTime))
INSERT [dbo].[account_medication_management_schedule] ([account_medication_management_schedule_id], [account_medication_management_id], [account_medication_management_reminder_id], [reminder_time], [date_part_id], [interval], [date_added]) VALUES (15, NULL, 15, CAST(0x070010ACD1530000 AS Time), 2, 2, CAST(0x0000A35D00F3DBCB AS DateTime))
SET IDENTITY_INSERT [dbo].[account_medication_management_schedule] OFF
SET IDENTITY_INSERT [dbo].[account_reminder_medication_relation] ON 

INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (1, 6, 1, CAST(0x0000A3550101BE1C AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (2, 6, 2, CAST(0x0000A3550101BE1F AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (4, 7, 1, CAST(0x0000A356010279D6 AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (5, 7, 2, CAST(0x0000A35601027A08 AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (6, 8, 1, CAST(0x0000A35801160EBA AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (7, 8, 2, CAST(0x0000A35801160EEC AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (9, 1, 10, CAST(0x0000A35801415C99 AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (18, 13, 1, CAST(0x0000A35A01411E78 AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (19, 13, 2, CAST(0x0000A35A01411E7B AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (22, 15, 1, CAST(0x0000A35D00F3DB66 AS DateTime))
INSERT [dbo].[account_reminder_medication_relation] ([account_reminder_medication_relation_id], [account_medication_management_reminder_id], [account_medication_management_id], [date_added]) VALUES (23, 15, 2, CAST(0x0000A35D00F3DB6B AS DateTime))
SET IDENTITY_INSERT [dbo].[account_reminder_medication_relation] OFF
INSERT [dbo].[account_role] ([account_role_id], [role_name]) VALUES (1, N'Casual')
INSERT [dbo].[account_role] ([account_role_id], [role_name]) VALUES (2, N'Account')
INSERT [dbo].[account_role] ([account_role_id], [role_name]) VALUES (3, N'Patient')
INSERT [dbo].[account_role] ([account_role_id], [role_name]) VALUES (4, N'Provider')
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (1, 1, N'Wil', CAST(0x0000A31400A4C945 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (62, 1, N'aaa', CAST(0x0000A32E00EFF470 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (62, 2, N'aaa', CAST(0x0000A32E00EFF470 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (62, 3, N'aaa', CAST(0x0000A32E00EFF470 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (63, 1, N'aaa', CAST(0x0000A32E00F73BF8 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (63, 2, N'aaa', CAST(0x0000A32E00F73BF8 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (63, 3, N'aaa', CAST(0x0000A32E00F73BF8 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (64, 1, N'sunil', CAST(0x0000A32E0131E904 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (64, 2, N'alto', CAST(0x0000A32E0131E904 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (64, 3, N'Sunil', CAST(0x0000A32E0131E904 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (1060, 1, N'abc', CAST(0x0000A33100317B62 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (1060, 2, N'abc', CAST(0x0000A33100317B62 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (1060, 3, N'abc', CAST(0x0000A33100317B62 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (1063, 1, N'testAnswer', CAST(0x0000A33200D16054 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (1063, 2, N'testAnswer', CAST(0x0000A33200D16054 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (1063, 3, N'testAnswer', CAST(0x0000A33200D16054 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2060, 1, N'kaku', CAST(0x0000A33200FE6911 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2060, 2, N'fait', CAST(0x0000A33200FE6911 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2060, 3, N'jatinder', CAST(0x0000A33200FE6911 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2063, 1, N'1', CAST(0x0000A3320189DCB7 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2063, 2, N'1', CAST(0x0000A3320189DCB7 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2063, 3, N'1', CAST(0x0000A3320189DCB7 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2064, 1, N'aaa', CAST(0x0000A33300322A1D AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2064, 2, N'aaa', CAST(0x0000A33300322A1D AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2064, 3, N'aaa', CAST(0x0000A33300322A1D AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2065, 1, N'jf', CAST(0x0000A3330032DC9A AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2065, 2, N'vfuo', CAST(0x0000A3330032DC9A AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2065, 3, N'bduik', CAST(0x0000A3330032DC9A AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2066, 1, N'aaa', CAST(0x0000A33300F613C9 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2066, 2, N'aaa', CAST(0x0000A33300F613C9 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2066, 3, N'aaa', CAST(0x0000A33300F613C9 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2067, 1, N'aaa', CAST(0x0000A33300F75444 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2067, 2, N'aaa', CAST(0x0000A33300F75444 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2067, 3, N'aaa', CAST(0x0000A33300F75444 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2068, 1, N'aaa', CAST(0x0000A33300F92AF5 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2068, 2, N'aaa', CAST(0x0000A33300F92AF5 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2068, 3, N'aaa', CAST(0x0000A33300F92AF5 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2069, 1, N'sonu', CAST(0x0000A3330118E66F AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2069, 2, N'maruti800', CAST(0x0000A3330118E66F AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2069, 3, N'don', CAST(0x0000A3330118E66F AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2070, 1, N'aaa', CAST(0x0000A333012F8066 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2070, 2, N'aaa', CAST(0x0000A333012F8066 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2070, 3, N'aaa', CAST(0x0000A333012F8066 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2071, 1, N'aaaaaaa', CAST(0x0000A3330130FB89 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2071, 2, N'aaa', CAST(0x0000A3330130FB89 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2071, 3, N'aaa', CAST(0x0000A3330130FB89 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2075, 1, N'aaa', CAST(0x0000A33400709808 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2075, 2, N'aaa', CAST(0x0000A33400709808 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2075, 3, N'aaa', CAST(0x0000A33400709808 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2076, 1, N'aaa', CAST(0x0000A339006B8B35 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2076, 2, N'aaa', CAST(0x0000A339006B8B35 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2076, 3, N'aaa', CAST(0x0000A339006B8B35 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2077, 1, N'ahahh', CAST(0x0000A3450051087C AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2077, 2, N'ahahh', CAST(0x0000A3450051087C AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2077, 3, N'susu', CAST(0x0000A3450051087C AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2078, 1, N'aaa', CAST(0x0000A345005810E8 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2078, 2, N'aaa', CAST(0x0000A345005810E8 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2078, 3, N'aaa', CAST(0x0000A345005810E8 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2079, 1, N'abcd', CAST(0x0000A34500E43342 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2079, 2, N'abcd', CAST(0x0000A34500E43342 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2079, 3, N'abcd', CAST(0x0000A34500E43342 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2080, 1, N'a', CAST(0x0000A348004EE831 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2080, 2, N'a', CAST(0x0000A348004EE831 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2080, 3, N'a', CAST(0x0000A348004EE831 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2081, 1, N'a', CAST(0x0000A34800555112 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2081, 2, N'a', CAST(0x0000A34800555112 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2081, 3, N'a', CAST(0x0000A34800555112 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2082, 1, N'a', CAST(0x0000A349003D463B AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2082, 2, N'a', CAST(0x0000A349003D463B AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2082, 3, N'a', CAST(0x0000A349003D463B AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2083, 1, N'a', CAST(0x0000A349003E40BB AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2083, 2, N'a', CAST(0x0000A349003E40BB AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2083, 3, N'a', CAST(0x0000A349003E40BB AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2084, 1, N'a', CAST(0x0000A349004D05E0 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2084, 2, N'a', CAST(0x0000A349004D05E0 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2084, 3, N'a', CAST(0x0000A349004D05E0 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2085, 1, N'abcd', CAST(0x0000A34900D64D23 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2085, 2, N'abcd', CAST(0x0000A34900D64D23 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2085, 3, N'abcd', CAST(0x0000A34900D64D23 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2086, 1, N'124456', CAST(0x0000A34900D7CF0D AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2086, 2, N'23447;', CAST(0x0000A34900D7CF0D AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2086, 3, N'237(', CAST(0x0000A34900D7CF0D AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2087, 1, N'abcd', CAST(0x0000A34900DD6D47 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2087, 2, N'abcd', CAST(0x0000A34900DD6D47 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2087, 3, N'abcd', CAST(0x0000A34900DD6D47 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2088, 1, N'abcd', CAST(0x0000A34900E110FB AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2088, 2, N'abcd', CAST(0x0000A34900E110FB AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2088, 3, N'abcd', CAST(0x0000A34900E110FB AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2089, 1, N'a', CAST(0x0000A34B0170E6A9 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2089, 2, N'a', CAST(0x0000A34B0170E6A9 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2089, 3, N'a', CAST(0x0000A34B0170E6A9 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2090, 1, N'a', CAST(0x0000A353006BA708 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2090, 2, N'a', CAST(0x0000A353006BA708 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2090, 3, N'a', CAST(0x0000A353006BA708 AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2092, 1, N'a', CAST(0x0000A3530183178B AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2092, 2, N'a', CAST(0x0000A3530183178B AS DateTime))
INSERT [dbo].[account_to_security_question] ([account_id], [security_question_id], [answer], [date_added]) VALUES (2092, 3, N'a', CAST(0x0000A3530183178B AS DateTime))
SET IDENTITY_INSERT [dbo].[client_naavis_databases] ON 

INSERT [dbo].[client_naavis_databases] ([client_id], [client_database_id], [address_1], [address_2], [city], [state], [postal_code], [country]) VALUES (1, 1, N'2560 Mission College Blvd.', NULL, N'Santa Clara', N'CA', N'95054', N'US')
SET IDENTITY_INSERT [dbo].[client_naavis_databases] OFF
INSERT [dbo].[date_part] ([date_part_id], [date_part_description], [date_part]) VALUES (1, N'Hour', N'hh')
INSERT [dbo].[date_part] ([date_part_id], [date_part_description], [date_part]) VALUES (2, N'Day', N'd')
INSERT [dbo].[date_part] ([date_part_id], [date_part_description], [date_part]) VALUES (3, N'Week', N'wk')
INSERT [dbo].[date_part] ([date_part_id], [date_part_description], [date_part]) VALUES (4, N'Month', N'm')
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (1, N'One Time Only', NULL, 0)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (2, N'Every 12 hours', 1, 12)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (3, N'Every day', 2, 1)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (4, N'Every 2 Days', 2, 2)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (5, N'Every 3 Days', 2, 3)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (6, N'Every 4 Days', 2, 4)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (7, N'Every 5 Days', 2, 5)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (8, N'Every 6 Days', 2, 6)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (9, N'Every Week', 3, 1)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (10, N'Every Month', 4, 1)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval]) VALUES (11, N'Custom Schedule', NULL, NULL)
SET IDENTITY_INSERT [dbo].[hospital_notice] ON 

INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1003, 1, CAST(0x0000A31400A4C93D AS DateTime), CAST(0x0000A31400A4C93D AS DateTime), N'message1', 1)
INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1004, 1, CAST(0x0000A31400A4C93D AS DateTime), CAST(0x0000A31400A4C93D AS DateTime), N'message2', 1)
INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1005, 1, CAST(0x0000A334009C8E20 AS DateTime), CAST(0x0000A353011DA500 AS DateTime), N'Eye checkup camp', 3)
INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1006, 1, CAST(0x0000A337012A0110 AS DateTime), CAST(0x0000A357012A0110 AS DateTime), N'Health checkup', 2)
INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1007, 1, CAST(0x0000A337012A0110 AS DateTime), CAST(0x0000A357012A0110 AS DateTime), N'Cancer awareness', 3)
INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1008, 1, CAST(0x0000A337012A0110 AS DateTime), CAST(0x0000A356012A0110 AS DateTime), N'Blood donation camp', 4)
INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1009, 1, CAST(0x0000A337012A0110 AS DateTime), CAST(0x0000A35A012A0110 AS DateTime), N'Meditation Programs', 4)
INSERT [dbo].[hospital_notice] ([hospital_notice_id], [client_database_id], [begin_date], [end_date], [notice_message], [account_role_id]) VALUES (1010, 1, CAST(0x0000A337012A0110 AS DateTime), CAST(0x0000A358012A0110 AS DateTime), N'Assertivess Program ', 2)
SET IDENTITY_INSERT [dbo].[hospital_notice] OFF
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMA', N'ANDEMA', N'Nikhil', N'Kumar', N'DC', N'Radiology', N'2560 Mission College Blvd', N'2560 Mission College Blvd', N'Santa Clara', N'GA', N'8989', N'66775', N'46', N'456', N'nikhil@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, 2063)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMA', N'ANDEMB', N'Ashish', N'Singh', N'AP', N'Cadiology', N'2560 Mission College Blvd', N'2560 Mission College Blvd', N'Santa Clara', N'GA', N'1212123', N'667723235', N'46676', N'457786', N'ashish@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, 2066)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMA', N'ANDEMC', N'Raj', N'Sharma', N'DP', N'Cadiology', N'2560 Mission College Blvd', N'2560 Mission College Blvd', N'Santa Clara', N'GA', N'122', N'454', N'877', N'987', N'raj@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, 2064)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMA', N'ANDEMF', N'Nidhi', N'Garg', N'GP', N'Padiology', N'2560 Mission College Blvd', N'2560 Mission College Blvd', N'Santa Clara', N'GA', N'122', N'454', N'877', N'987', N'nidhi@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, 2067)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMA', N'ANDEMK', N'Kanak', N'Ray', N'KP', N'Kadiology', N'2560 Mission College Blvd', N'2560 Mission College Blvd', N'Santa Clara', N'KA', N'122', N'454', N'877', N'987', N'kanak@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, 2068)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMA', N'ANDEMN', N'Shashi', N'Pal', N'PP', N'Tadiology', N'2560 Mission College Blvd', N'2560 Mission College Blvd', N'Santa Clara', N'GA', N'8999', N'9876', N'877', N'987', N'shas@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, NULL)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMA', N'ANDEMQ', N'Sandeep', N'lohia', N'BP', N'Radiology', N'2560 Mission College Blvd', N'2560 Mission College Blvd', N'Santa Clara', N'GA', N'9888', N'54544', N'234', N'457766786', N'sandeep@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, NULL)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMC', N'ANDEM', N'MARK', N'ANDERSON', N'MD', N'General', N'2560 Mission College Blvd', NULL, N'Santa Clara', N'CA', N'95054', N'408-780-0230', N'999', N'555-555-5555', N'info@versaworks.com', CAST(0x0000A31400A4CA28 AS DateTime), NULL, 2065)
INSERT [dbo].[hospital_provider] ([client_database_id], [source_id], [provider_id], [first_name], [last_name], [suffix], [specialty], [address_1], [address_2], [city], [state], [postal_code], [contact_phone_number], [contact_phone_extention], [fax_number], [contact_email], [date_added], [date_modified], [account_id]) VALUES (1, N'NMC', N'ANDEMS', N'Shawn', N'Savadkohi', N'MD', N'Oncology', N'3900 Versaworks', N'Santa Clara', N'California', N'CA', N'95054', N'408-780-0230', N'2345', N'1234567', N'shawn.savadkohi@versaworks.com', CAST(0x0000A334011DB43C AS DateTime), NULL, 2074)
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMAA', N'E', N'ED', N'Emergency Department', N'555-555-1234', N'100', N'hospital@versaworks.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMB', N'E', N'EDB', N'Business Office', N'1248548795', N'122', N'business@versaworks.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMC', N'E', N'ED', N'Emergency Department', N'555-555-1234', N'100', N'emergency@versaworks.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMCc', N'E', N'EDD', N'EDC', N'123456', N'122', N'edc@versaworks.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NME', N'E', N'EDE', N'Diabetes Education', N'78456', N'122', N'diabeties@versaworks.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMF', N'E', N'EDF', N'Food Service', N'852146', N'122', N'service@versaworks.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMG', N'E', N'EDG', N'Gift Shop', N'963258', N'122', N'jdjd@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMH', N'E', N'EDH', N'Health Talk', N'741258', N'122', N'jdjd@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NML', N'E', N'EDL', N'Laboratory', N'789456', N'122', N'jdjd@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMLL', N'E', N'EDLA', N'Laboratory', N'789456123', N'122', N'1233@yahoo.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMM', N'E', N'EDM', N'Mammography', N'321654987', N'122', N'asd@yahoo.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMO', N'E', N'EDO', N'Outpatient Surgery', N'75315976', N'122', N'qww@yahoo.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMP', N'E', N'EDD', N'Cancer Care Hospital', N'9517522418', N'122', N'cnnc@yahoo.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMP', N'E', N'EDP', N'Patient Relation', N'12457895', N'122', N'zcc@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMPP', N'E', N'EDPP', N'Pediatric Unit', N'12468532', N'122', N'asd@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMPQ', N'E', N'EDPQ', N'Quality Service', N'134684216', N'122', N'jdjd@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMPR', N'E', N'EDPR', N'Radiology', N'134521875', N'122', N'jsjs@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMPRR', N'E', N'EDPRR', N'Respiratory', N'1542785412', N'122', N'ldld@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMPRS', N'E', N'EDPRS', N'Security', N'41245695478', N'122', N'sjdjd@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMPRU', N'E', N'EDPRU', N'Utilization Review', N'1249578541', N'122', N'etrt@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMPRV', N'E', N'EDPRV', N'Volunteer Service', N'124584512215', N'122', N'qww@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[hospital_service] ([client_database_id], [source_id], [service_group], [service_id], [service], [contact_phone_number], [contact_phone_extention], [contact_email], [date_added], [date_modified]) VALUES (1, N'NMQ', N'E', N'EDQ', N'Cancer Registry', N'24858466214', N'122', N'12142@gmail.com', CAST(0x0000A31400A4CA2D AS DateTime), CAST(0x0000A31400A4CA2D AS DateTime))
INSERT [dbo].[medication_dosage] ([medication_dosage_id], [medication_dosage]) VALUES (1, N'pills')
INSERT [dbo].[medication_dosage] ([medication_dosage_id], [medication_dosage]) VALUES (2, N'drops')
INSERT [dbo].[medication_dosage] ([medication_dosage_id], [medication_dosage]) VALUES (3, N'tsp')
INSERT [dbo].[medication_dosage] ([medication_dosage_id], [medication_dosage]) VALUES (4, N'tbsp')
INSERT [dbo].[medication_dosage] ([medication_dosage_id], [medication_dosage]) VALUES (5, N'fl oz')
INSERT [dbo].[medication_dosage] ([medication_dosage_id], [medication_dosage]) VALUES (6, N'cc')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (1, N'Pill')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (2, N'Tablet')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (3, N'Capsule')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (4, N'Liquid')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (5, N'Syrup')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (6, N'Suspension')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (7, N'Solution')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (8, N'Cream')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (9, N'Gel')
INSERT [dbo].[medication_kind] ([medication_kind_id], [medication_kind]) VALUES (10, N'Lotion')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (1, N'Oral')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (2, N'Buccal')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (3, N'Periodontal')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (4, N'Sublingual')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (5, N'Nasal')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (6, N'Respiratory (Inhalation)')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (7, N'Octic (Ear)')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (8, N'Ophthalmic (Eye)')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (9, N'Topical')
INSERT [dbo].[medication_method] ([medication_method_id], [medication_method]) VALUES (10, N'Transdermal')
SET IDENTITY_INSERT [dbo].[patient_appointment_request] ON 

INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1, 1, CAST(0x6E380B00 AS Date), CAST(0x070040230E430000 AS Time), N'555-555-1234', N'info@versaworks.com', N'ANDEM', N'Mark Anderson is my primary physician', NULL, CAST(0x0000A31400A4CA32 AS DateTime), N'sujeet', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1039, 2071, CAST(0x3AF90A00 AS Date), CAST(0x07003CB8192E0000 AS Time), N'9711914755', N'ss@gmail.com', N'ash', N' Hi', CAST(0x0000A32C00D327BF AS DateTime), CAST(0x0000A32C00D327BF AS DateTime), N'sujeet', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1041, 2071, CAST(0x2EF90A00 AS Date), CAST(0x07003CB8192E0000 AS Time), N'9717255563', N'reer@gmail.com', N'ash', N'Please approve', CAST(0x0000A32C00E8909F AS DateTime), CAST(0x0000A32C00E8909F AS DateTime), N'Reer', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1095, 63, CAST(0x3AF90A00 AS Date), CAST(0x07C0FC4415C50000 AS Time), N'8800559323', N'dheeraj.borra@ireslab.com', N'ash', N'Please help the patient', CAST(0x0000A32E00F9C731 AS DateTime), CAST(0x0000A32E00F9C731 AS DateTime), N'dhee', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1098, 2070, CAST(0x2EF90A00 AS Date), CAST(0x0730DEF5F9C40000 AS Time), N'ghh', N'fgggg@gmail.com', N'ash', N'Type Comment Hereffxxxxffxhhcxdt', CAST(0x0000A32E00FD6D88 AS DateTime), CAST(0x0000A32E00FD6D88 AS DateTime), N'ghhj', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1100, 2070, CAST(0x3AF90A00 AS Date), CAST(0x07605549FAC40000 AS Time), N'ghffg', N'hghfh@gmail.com', N'ash', N'Type Comment Here', CAST(0x0000A32E00FE573C AS DateTime), CAST(0x0000A32E00FE573C AS DateTime), N'dffgfg', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1102, 2070, CAST(0x39F90A00 AS Date), CAST(0x07D04CF5FAC40000 AS Time), N'fggh', N'hgggh@gmail.com', N'ash', N'Type Comment Here', CAST(0x0000A32E00FF783D AS DateTime), CAST(0x0000A32E00FF783D AS DateTime), N'geehh why', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1103, 2070, CAST(0x23F90A00 AS Date), CAST(0x07F0F79BFCC40000 AS Time), N'1235788', N'sass@gmail.com', N'ash', N'Type Comment Here', CAST(0x0000A32E00FFF7A0 AS DateTime), CAST(0x0000A32E00FFF7A0 AS DateTime), N'shshh', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1104, 2070, CAST(0x35F90A00 AS Date), CAST(0x07902F3CFDC40000 AS Time), N'123466', N'sujeetsingh09@gmail.com', N'ash', N'Type Comment Here', CAST(0x0000A32E0100C53B AS DateTime), CAST(0x0000A32E0100C53B AS DateTime), N'fhhh', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1105, 2070, CAST(0x3AF90A00 AS Date), CAST(0x07607A9FFCC40000 AS Time), N'1245', N'ffgggh@bbnn.com', N'ash', N'Type Comment Here', CAST(0x0000A32E0101721D AS DateTime), CAST(0x0000A32E0101721D AS DateTime), N'hshdjkd', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1107, 2070, CAST(0x3CF90A00 AS Date), CAST(0x0750DBB2FCC40000 AS Time), N'12344566', N'shhhh@gmail.com', N'ash', N'Type Comment Here', CAST(0x0000A32E01032318 AS DateTime), CAST(0x0000A32E01032318 AS DateTime), N'ghhjj', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (1126, 63, CAST(0x3AF90A00 AS Date), CAST(0x0770B9A1FBC40000 AS Time), N'1234567890', N'vishva.singh@ireslab.com', N'ash', N'Farewell party', CAST(0x0000A32E01351421 AS DateTime), CAST(0x0000A32E01351421 AS DateTime), N'mamohan singh', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3095, 63, CAST(0x8E380B00 AS Date), CAST(0x070024C397BC0000 AS Time), N'123456890', N'dheeraj.borra@ireslab.com', N'Ashish Singh', N'Send confirmation asap.', CAST(0x0000A332017304EC AS DateTime), CAST(0x0000A332017304EC AS DateTime), N'Rahul gandhi', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3096, 63, CAST(0x8F380B00 AS Date), CAST(0x0700D0144A580000 AS Time), N'8800559323', N'dheeraj.borra@ireslab.com', N'MARK ANDERSON', N'Please confirm', CAST(0x0000A333002A3236 AS DateTime), CAST(0x0000A333002A3236 AS DateTime), N'Raj', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3097, 63, CAST(0x46340B00 AS Date), CAST(0x0700524AC4620000 AS Time), N'8800559323', N'dheeraj.borra@ireslab.com', N'MARK ANDERSON', N'Type Comment Ok', CAST(0x0000A33300C25E4B AS DateTime), CAST(0x0000A33300C25E4B AS DateTime), N'Rahul', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3098, 63, CAST(0x21370B00 AS Date), CAST(0x0700980DE8620000 AS Time), N'880559323', N'dheeraj.borra@ireslab.com', N'Kanak Ray', N'Type Comment Done', CAST(0x0000A33300C2C523 AS DateTime), CAST(0x0000A33300C2C523 AS DateTime), N'Shekar', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3099, 63, CAST(0xFB390B00 AS Date), CAST(0x07006A5753630000 AS Time), N'88005592', N'dheeraj.borra@ireslab.com', N'MARK ANDERSON', N'Working', CAST(0x0000A33300C35050 AS DateTime), CAST(0x0000A33300C35050 AS DateTime), N'Raj', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3102, 63, CAST(0x8E380B00 AS Date), CAST(0x07000236D36C0000 AS Time), N'8888523699', N'dheeraj.borra@ireslab.com', N'Shashi Pal', N'Type Comment Here. Good mrning', CAST(0x0000A33300D45DB8 AS DateTime), CAST(0x0000A33300D45DB8 AS DateTime), N'Dherru bhaii', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3104, 63, CAST(0x8E380B00 AS Date), CAST(0x070048F9F66C0000 AS Time), N'1231231', N'a@gmail.com', N'Ashish Singh', N'Type Comment Here', CAST(0x0000A33300D6836B AS DateTime), CAST(0x0000A33300D6836B AS DateTime), N'Test', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3106, 2068, CAST(0xAE380B00 AS Date), CAST(0x0700489CD87E0000 AS Time), N'8800559323', N'dheeraj.borra@ireslab.com', N'Shashi Pal', N'Appointment please', CAST(0x0000A33300F9A6CF AS DateTime), CAST(0x0000A33300F9A6CF AS DateTime), N'Kanak', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3107, 63, CAST(0x8E380B00 AS Date), CAST(0x070028A2058A0000 AS Time), N'085296337411', N'dheeraj.borra@ireslab.com', N'Kanak Ray', N'Fixing report', CAST(0x0000A333010F3D49 AS DateTime), CAST(0x0000A333010F3D49 AS DateTime), N'Dheeraj', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3108, 63, CAST(0xB4350B00 AS Date), CAST(0x0700926DD2A50000 AS Time), N'5286632', N'sekhar.patra@ireslab.com', N'Sandeep lohia', N'Type Comment Here good', CAST(0x0000A3330146EC32 AS DateTime), CAST(0x0000A3330146EC32 AS DateTime), N'Dheeraj', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3109, 2071, CAST(0x8F380B00 AS Date), CAST(0x0700543AD4AB0000 AS Time), N'1234567896', N'vishva.singh@ireslab.com', N'Ashish Singh', N'Please confirm asap', CAST(0x0000A3330146EE38 AS DateTime), CAST(0x0000A3330146EE38 AS DateTime), N'Robin', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3110, 63, CAST(0x90380B00 AS Date), CAST(0x07002AC0CB670000 AS Time), N'985632', N'vishva.singh@ireslab.com', N'Nikhil Kumar', N'Type Comment Here', CAST(0x0000A33400AB7EED AS DateTime), CAST(0x0000A33400AB7EED AS DateTime), N'Test', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3111, 63, CAST(0x90380B00 AS Date), CAST(0x0700448E02580000 AS Time), N'9856321457', N'vishva.singh@ireslab.com', N'Nikhil Kumar', N'Type Comment Herefhxhfxhfjgdgdf', CAST(0x0000A33400AC0A13 AS DateTime), CAST(0x0000A33400AC0A13 AS DateTime), N'Test3', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3112, 63, CAST(0xAE380B00 AS Date), CAST(0x0700448E02580000 AS Time), N'8800559323', N'vishva.singh@ireslab.com', N'Nikhil Kumar', N'Confirm', CAST(0x0000A33400AD4454 AS DateTime), CAST(0x0000A33400AD4454 AS DateTime), N'Tes', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3113, 63, CAST(0x8F380B00 AS Date), CAST(0x070060A9677F0000 AS Time), N'1234567890', N'wilbur.wong@versaworks.com', N'Nikhil Kumar', N'Test', CAST(0x0000A33400B1450F AS DateTime), CAST(0x0000A33400B1450F AS DateTime), N'U', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3114, 63, CAST(0x8F380B00 AS Date), CAST(0x0700327F59C70000 AS Time), N'865542425', N'vishva.singh@ireslab.com', N'Nikhil Kumar', N'Type Comment Here', CAST(0x0000A33400B21DF2 AS DateTime), CAST(0x0000A33400B21DF2 AS DateTime), N'Test etyff', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3115, 2071, CAST(0x8F380B00 AS Date), CAST(0x070050CFDF960000 AS Time), N'01202589696', N'mohit@versaworks.com', N'Kanak Ray', N'Sick having fever.', CAST(0x0000A334004657A8 AS DateTime), CAST(0x0000A334004657A8 AS DateTime), N'Mohit', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3116, 63, CAST(0x8F380B00 AS Date), CAST(0x07005E2E83B30000 AS Time), N'57566', N't@mail.com', N'ANDEMK', N'Dhh', CAST(0x0000A33400610228 AS DateTime), CAST(0x0000A33400610228 AS DateTime), N'Test', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3117, 63, CAST(0x33380B00 AS Date), CAST(0x0700E649D69E0000 AS Time), N'8525551212', N'preet@gmail.com', N'ANDEMB', N'Asap', CAST(0x0000A3340069D08D AS DateTime), CAST(0x0000A3340069D08D AS DateTime), N'Preet', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3118, 63, CAST(0xB5350B00 AS Date), CAST(0x07002E7183A00000 AS Time), N'8800559323', N'Preet.Singh@ireslab.com', N'ANDEMK', N'Please confirm', CAST(0x0000A334006B2513 AS DateTime), CAST(0x0000A334006B2513 AS DateTime), N'Clark', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3119, 63, CAST(0xB13F0B00 AS Date), CAST(0x07001664F49F0000 AS Time), N'8800559323', N'dheeraj.borra@ireslab.com', N'ANDEM', N'Xyz', CAST(0x0000A334006C0EFE AS DateTime), CAST(0x0000A334006C0EFE AS DateTime), N'Karl', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3120, 63, CAST(0x28390B00 AS Date), CAST(0x070078FCB9A30000 AS Time), N'88005536562', N'dheeraj.borra@ireslab.com', N'ANDEMB', N'Please confirm', CAST(0x0000A3340073808B AS DateTime), CAST(0x0000A3340073808B AS DateTime), N'Cla', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3121, 2075, CAST(0x8F380B00 AS Date), CAST(0x0700349D1FA50000 AS Time), N'258369074', N'shekh.patra@gmail.com', N'ANDEMK', N'Hi', CAST(0x0000A33400759693 AS DateTime), CAST(0x0000A33400759693 AS DateTime), N'Wil', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3122, 2075, CAST(0x91380B00 AS Date), CAST(0x07004CAAAEA50000 AS Time), N'4085923360', N'wilbur.wong@versaworks.com', N'ANDEMN', N'Please approve asap', CAST(0x0000A33400771AE3 AS DateTime), CAST(0x0000A33400771AE3 AS DateTime), N'Wil', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3123, 2075, CAST(0xCC380B00 AS Date), CAST(0x070094D15BA70000 AS Time), N'6520851472', N'robot@gmail.com', N'ANDEMN', N'Please approve', CAST(0x0000A334007A9D50 AS DateTime), CAST(0x0000A334007A9D50 AS DateTime), N'Robot', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3124, 63, CAST(0x8D380B00 AS Date), CAST(0x070098996EAA0000 AS Time), N'12548863', N'sashipal@versawark.com', N'ANDEMC', N'Hi', CAST(0x0000A33400800BB6 AS DateTime), CAST(0x0000A33400800BB6 AS DateTime), N'Ddd', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3125, 63, CAST(0x8F380B00 AS Date), CAST(0x07006AE3D9AA0000 AS Time), N'528636', N'sushil@versaworks.com', N'ANDEMK', N'Hi', CAST(0x0000A3340080F68A AS DateTime), CAST(0x0000A3340080F68A AS DateTime), N'Rrr', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3126, 63, CAST(0x8F380B00 AS Date), CAST(0x07003C2D45AB0000 AS Time), N'58357', N'sushil@verasawork.com', N'ANDEMS', N'Hi', CAST(0x0000A334008156E7 AS DateTime), CAST(0x0000A334008156E7 AS DateTime), N'Rer', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3127, 63, CAST(0x90380B00 AS Date), CAST(0x0700B0A6FDAA0000 AS Time), N'5286', N's@ail.com', N'ANDEMA', N'Hi', CAST(0x0000A3340081B969 AS DateTime), CAST(0x0000A3340081B969 AS DateTime), N'Drd', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3128, 63, CAST(0x8F380B00 AS Date), CAST(0x0700645A1FB80000 AS Time), N'6546542139', N'preet@gmail.com', N'ANDEM', N'Test', CAST(0x0000A334008856C1 AS DateTime), CAST(0x0000A334008856C1 AS DateTime), N'Preet', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3129, 63, CAST(0x8F380B00 AS Date), CAST(0x0700881C05B00000 AS Time), N'258', N'c.@gmail.com', N'ANDEMF', N'Hi', CAST(0x0000A334008AD922 AS DateTime), CAST(0x0000A334008AD922 AS DateTime), N'Pppppppppppp', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3130, 2075, CAST(0x90380B00 AS Date), CAST(0x07004C64EB810000 AS Time), N'4568972587', N'richa.sahu@versaworks.com', N'ANDEMN', N'Dgjadf', CAST(0x0000A33400C7D964 AS DateTime), CAST(0x0000A33400C7D964 AS DateTime), N'Richa', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3131, 2075, CAST(0x8F380B00 AS Date), CAST(0x070050E63A610000 AS Time), N'1254879562', N'richa.sahu@versaworks.com', N'ANDEMS', N'Abc', CAST(0x0000A33400CF0912 AS DateTime), CAST(0x0000A33400CF0912 AS DateTime), N'Abc', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3132, 63, CAST(0x92380B00 AS Date), CAST(0x0700227669850000 AS Time), N'07838898323', N'dheeaj.bora@ireslab.com', N'ANDEMB', N'He needs medication. ', CAST(0x0000A337003655DB AS DateTime), CAST(0x0000A337003655DB AS DateTime), N'Dheeraj', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3133, 2075, CAST(0x92380B00 AS Date), CAST(0x07004052769C0000 AS Time), N'0124578963', N'wil.burg@versaworks.com', N'ANDEMA', N'Good', CAST(0x0000A3370064A42E AS DateTime), CAST(0x0000A3370064A42E AS DateTime), N'Wil burg', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3134, 2075, CAST(0x93380B00 AS Date), CAST(0x070048F9F66C0000 AS Time), N'4562587536', N'abc@versaworks.com', N'ANDEMS', N'Test', CAST(0x0000A33800B8A314 AS DateTime), CAST(0x0000A33800B8A314 AS DateTime), N'Richa', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3135, 62, CAST(0x95380B00 AS Date), CAST(0x0700048301A40000 AS Time), N'8800559323', N'vishva.singh@ireslab.com', N'ANDEMS', N'Please confirm the time
', CAST(0x0000A3390073EF50 AS DateTime), CAST(0x0000A3390073EF50 AS DateTime), N'Vishva', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3136, 62, CAST(0x95380B00 AS Date), CAST(0x07001882BA7D0000 AS Time), N'2589631470', N'n.v@gmail.com', N'ANDEMB', N'Hi


', CAST(0x0000A33A0027E74F AS DateTime), CAST(0x0000A33A0027E74F AS DateTime), N'Ram', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3137, 1060, CAST(0x9A380B00 AS Date), CAST(0x0700DC6C82610000 AS Time), N'456238975', N'richa.sahu@versaworks.com', N'ANDEMS', N'Dhh


', CAST(0x0000A33E00AE8B60 AS DateTime), CAST(0x0000A33E00AE8B60 AS DateTime), N'Richa', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3138, 2075, CAST(0xDD380B00 AS Date), CAST(0x070074D788B20000 AS Time), N'1111', N'wilbur.wong@versaworks.com', N'ANDEMK', N'Shshs', CAST(0x0000A345006F6237 AS DateTime), CAST(0x0000A345006F6237 AS DateTime), N'Wil Wong', CAST(0x0000A34501364CB8 AS DateTime), N'shekar')
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3139, 2075, CAST(0xA1380B00 AS Date), CAST(0x070010ACD1530000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMS', N'Need opd.', CAST(0x0000A345007A57EE AS DateTime), CAST(0x0000A345007A57EE AS DateTime), N'Wil Wong', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3140, 2075, CAST(0xA1380B00 AS Date), CAST(0x07007CDB27710000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMS', N'Test', CAST(0x0000A34500BBE9F6 AS DateTime), CAST(0x0000A34500BBE9F6 AS DateTime), N'Wil Wong', CAST(0x0000A34601364CB8 AS DateTime), N'shekar')
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3141, 2075, CAST(0xA0380B00 AS Date), CAST(0x07007CDB27710000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMC', N'Test', CAST(0x0000A34500D84776 AS DateTime), CAST(0x0000A34500D84776 AS DateTime), N'Wil Wong', CAST(0x0000A34500DE7920 AS DateTime), N'rajr')
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3142, 63, CAST(0xA2380B00 AS Date), CAST(0x0700E80A7E8E0000 AS Time), N'0', N'dheeraj.borra@ireslab.com', N'ANDEMB', N'Need opd ', CAST(0x0000A346004135F8 AS DateTime), CAST(0x0000A346004135F8 AS DateTime), N'Dheeraj Borra', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3143, 2075, CAST(0xBF380B00 AS Date), CAST(0x0700DEB9B0980000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMK', N'Jejej', CAST(0x0000A346005D0DFD AS DateTime), CAST(0x0000A346005D0DFD AS DateTime), N'Wil Wong', CAST(0x0000A34701364CB8 AS DateTime), N'shekar')
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3144, 2075, CAST(0xA1380B00 AS Date), CAST(0x07007870335C0000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMA', N'Test', CAST(0x0000A34600AF189C AS DateTime), CAST(0x0000A34600AF189C AS DateTime), N'Wil Wong', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3145, 2075, CAST(0xA1380B00 AS Date), CAST(0x07007870335C0000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMK', N'Test', CAST(0x0000A34600AF8BB9 AS DateTime), CAST(0x0000A34600AF8BB9 AS DateTime), N'Wil Wong', CAST(0x0000A34600B54640 AS DateTime), N'kanak')
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3146, 2075, CAST(0xA2380B00 AS Date), CAST(0x0700CC92FA780000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMB', N'Need ambulence immideatly                                                       ', CAST(0x0000A347001F2E84 AS DateTime), CAST(0x0000A347001F2E84 AS DateTime), N'Wil Wong', CAST(0x0000A34000E3E6F8 AS DateTime), N'dheeraj')
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3147, 2075, CAST(0xDF380B00 AS Date), CAST(0x070014006B9E0000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMK', N'Hahah', CAST(0x0000A34700680688 AS DateTime), CAST(0x0000A34700680688 AS DateTime), N'Wil Wong', NULL, NULL)
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3148, 2075, CAST(0xA2380B00 AS Date), CAST(0x07004C64EB810000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMB', N'Test', CAST(0x0000A34700E95C85 AS DateTime), CAST(0x0000A34700E95C85 AS DateTime), N'Wil Wong', CAST(0x0000A34700FF6EA0 AS DateTime), N'ashish')
INSERT [dbo].[patient_appointment_request] ([patient_appointment_request_id], [account_id], [appointment_date], [appointment_time], [contact_phone], [contact_email], [provider_id], [comment], [request_sent_date], [date_added], [patient_name], [confirm_date], [confirmed_by]) VALUES (3149, 2075, CAST(0xA4380B00 AS Date), CAST(0x0700448E02580000 AS Time), N'22222', N'wilbur.wong@versaworks.com', N'ANDEMB', N'Need emergency .', CAST(0x0000A34800497DFA AS DateTime), CAST(0x0000A34800497DFA AS DateTime), N'Wil Wong', NULL, NULL)
SET IDENTITY_INSERT [dbo].[patient_appointment_request] OFF
SET IDENTITY_INSERT [dbo].[patient_feedback] ON 

INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2, 1, 3, 4, N'Fully Satisfied, Good Job', CAST(0x0000A32C00CF416C AS DateTime), 1, CAST(0x0000A32C00CF416C AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2016, 8, 4, 3, N'Very good doctor', CAST(0x0000A332010BF08C AS DateTime), 1, CAST(0x0000A332010BF08C AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2034, 13, 2, 2, N'Good Physician', CAST(0x0000A33400CA02D8 AS DateTime), 1, CAST(0x0000A33400CA02D8 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2035, 14, 5, 5, N'Great', CAST(0x0000A33400CA02D8 AS DateTime), 1, CAST(0x0000A33400CA02D8 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2037, 12, 5, 5, N'Delighted', CAST(0x0000A3330148F505 AS DateTime), 1, CAST(0x0000A3330148F505 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2040, 12, 5, 4, N'Satisfied', CAST(0x0000A333014E85DC AS DateTime), 1, CAST(0x0000A333014E85DC AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2041, 12, 4, 3, N'Good', CAST(0x0000A334002EA617 AS DateTime), 1, CAST(0x0000A334002EA617 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2047, 16, 5, 2, N'Good', CAST(0x0000A33400C440B0 AS DateTime), 1, CAST(0x0000A33400C440B0 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2048, 12, 3, 0, N'', CAST(0x0000A33400C8A182 AS DateTime), 1, CAST(0x0000A33400C8A182 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2049, 12, 0, 0, N'Bobbin', CAST(0x0000A33400CAA84B AS DateTime), 1, CAST(0x0000A33400CAA84B AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2050, 8, 0, 0, N'', CAST(0x0000A33400D084AA AS DateTime), 1, CAST(0x0000A33400D084AA AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2051, 12, 0, 0, N'', CAST(0x0000A33400D0C3F2 AS DateTime), 1, CAST(0x0000A33400D0C3F2 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2052, 8, 3, 3, N'Asdasdasd', CAST(0x0000A33400D0D759 AS DateTime), 1, CAST(0x0000A33400D0D759 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2053, 1, 3, 3, N'Good', CAST(0x0000A33400D0F6C4 AS DateTime), 1, CAST(0x0000A33400D0F6C4 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2054, 12, 4, 4, N'gjhjghjg', CAST(0x0000A33400D10B72 AS DateTime), 1, CAST(0x0000A33400D10B72 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2055, 12, 4, 4, N'Hh', CAST(0x0000A33400D14505 AS DateTime), 1, CAST(0x0000A33400D14505 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2056, 8, 3, 5, N'Thank you', CAST(0x0000A33400815F53 AS DateTime), 1, CAST(0x0000A33400815F53 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2057, 12, 4, 4, N'Etgdf', CAST(0x0000A33400CA0216 AS DateTime), 1, CAST(0x0000A33400CA0216 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2058, 8, 3, 4, N'Rhythm', CAST(0x0000A33400D2C2D4 AS DateTime), 1, CAST(0x0000A33400D2C2D4 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2059, 12, 4, 3, N'Ruff', CAST(0x0000A33400D42D30 AS DateTime), 1, CAST(0x0000A33400D42D30 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2060, 12, 3, 4, N'TEST', CAST(0x0000A33400D4AFFC AS DateTime), 1, CAST(0x0000A33400D4AFFC AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2061, 12, 5, 5, N'Test2', CAST(0x0000A33400D4E522 AS DateTime), 1, CAST(0x0000A33400D4E522 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2062, 1, 5, 5, N'The doc was really great', CAST(0x0000A3360179CBCE AS DateTime), 1, CAST(0x0000A3360179CBCE AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2063, 1, 4, 2, N'This was a good visit', CAST(0x0000A3370179128A AS DateTime), 1, CAST(0x0000A3370179128A AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2064, 1, 4, 5, N'Test', CAST(0x0000A33800AA70A8 AS DateTime), 1, CAST(0x0000A33800AA70A8 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2065, 12, 3, 4, N'Test', CAST(0x0000A33800ABC596 AS DateTime), 1, CAST(0x0000A33800ABC596 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2066, 8, 4, 5, N'Test 5/27', CAST(0x0000A33800AD6075 AS DateTime), 1, CAST(0x0000A33800AD6075 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2067, 1, 4, 4, N'Test 5/27', CAST(0x0000A3380106A189 AS DateTime), 1, CAST(0x0000A3380106A189 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2068, 8, 3, 4, N'Test5/27', CAST(0x0000A3380106B680 AS DateTime), 1, CAST(0x0000A3380106B680 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2069, 12, 5, 2, N'Great experience', CAST(0x0000A338016B682C AS DateTime), 1, CAST(0x0000A338016B682C AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2070, 8, 3, 2, N'', CAST(0x0000A345007F805C AS DateTime), 1, CAST(0x0000A345007F805C AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2071, 1, 0, 0, N'




', CAST(0x0000A3480031DF02 AS DateTime), 1, CAST(0x0000A3480031DF02 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2072, 1, 2, 2, N'



U
', CAST(0x0000A34800321EE8 AS DateTime), 1, CAST(0x0000A34800321EE8 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2073, 1, 2, 2, N'Red



Junk', CAST(0x0000A34800428044 AS DateTime), 1, CAST(0x0000A34800428044 AS DateTime))
INSERT [dbo].[patient_feedback] ([patient_feedback_id], [patient_visit_id], [visit_rating], [recovery_rating], [comment], [last_viewed_date], [last_viewed_by_acount_id], [date_added]) VALUES (2074, 8, 2, 3, N'Tutu', CAST(0x0000A34800434EB8 AS DateTime), 1, CAST(0x0000A34800434EB8 AS DateTime))
SET IDENTITY_INSERT [dbo].[patient_feedback] OFF
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5150', N'SQUAM EPI', N'UR SQUAM EPI', N'mL', N'< 1', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C993 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5175', N'TREP', N'TRANSITIONAL EPITHELIAL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C993 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5550', N'UCACARB', N'CALCIUM CARBONATE CRYSTALS,URN', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C994 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5600', N'UCAOX', N'CALCIUM OXALATE CRYSTALS,URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C994 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5700', N'UURIC', N'URIC ACID CRYSTALS, URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C995 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5750', N'UCYST', N'URINE CYSTEINE', N'mL', N'NP', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C995 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5800', N'UTRPHOS', N'TRIPLE PHOSPHATE CRYSTAL,URINE', N'mL', N'NP', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C995 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.5900', N'UB', N'BACTERIA, URINE', N'mL', N'NP', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C996 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6050', N'HCAS', N'HYALINE CASTS-URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C996 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6060', N'CGCAST', N'COARSELY GRANULAR CASTS-URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C997 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6070', N'FGCAST', N'FINELY GRANULAR CASTS-URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C997 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6080', N'WBCCAST', N'WBC CASTS-URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C997 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6090', N'RBCCAST', N'RBC CASTS-URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C998 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6100', N'FATCAST', N'FATTY CASTS-URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C998 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6110', N'WAXCAST', N'WAXY CASTS-URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C998 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6700', N'UM', N'MUCUS, URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C999 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6800', N'UAS', N'AMORPHOUS SEDIMENT, URINE', N'mL', N'NP', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C999 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.6900', N'UO', N'OTHER SEDIMENT, URINE', N'mL', N'NP', CAST(0x0000A33800AD08E0 AS DateTime), CAST(0x0000A31400A4C99A AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.7000', N'UTRICH', N'TRICHOMONAS, URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99A AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.7100', N'UYEAST', N'YEAST, URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99A AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.7150', N'UBUDYST', N'BUDDING YEAST URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99A AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.7200', N'USPERM', N'SPERM, URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99B AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471033', N'300.7300', N'UOFB', N'OVAL FAT BODIES, URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99B AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9000', N'AMP DAU', N'AMPHETAMINES DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9005', N'AMPC DAU', N'AMPHETAMINE DRG ABUSE URN', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9010', N'METHAMPC DAU', N'METHAMPHETAMINE DRG ABUSE URN', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9015', N'BARB DAU', N'BARBITURATES DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9020', N'BENZO DAU', N'BENZODIAZEPINES DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9025', N'COC DAU', N'COCAINE METAB DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9030', N'THC DAU', N'MARIJUANA METAB DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9035', N'METHDN DAU', N'METHADONE DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9040', N'METHQLN DAU', N'METHAQUALONE DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C6 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9045', N'OP DAU', N'OPIATES DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C6 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9050', N'MORPHC DAU', N'MORPHINE DRG ABUSE URN', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C7 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9055', N'CODC DAU', N'CODEINE DRG ABUSE URN', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C7 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9060', N'HYDMORC DAU', N'HYDROMOPHONE DRG ABUSE URN', N'mL', N'NP', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C9C8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9065', N'HYDCODN DAU', N'HYDROCODONE DRG ABUSE URN', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9070', N'PCP DAU', N'PCP DRG ABUSE URN', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471034', N'805.9075', N'PROPOX DAU', N'PROPOXYPHENE DRG ABUSE URN', N'mL', N'', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C9C9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0100', N'WBC', N'WHITE BLOOD COUNT', N'mL', N'8.8', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99C AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0200', N'CWBC', N'CORRECTED WHITE BLOOD COUNT', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99C AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0300', N'RBC', N'RED BLOOD COUNT', N'mL', N'4.12', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99D AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0400', N'HGB', N'HEMOGLOBIN', N'mL', N'12.6', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99D AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0500', N'HCT', N'HEMATOCRIT', N'mL', N'36.9', CAST(0x0000A33900AD08E0 AS DateTime), CAST(0x0000A31400A4C99D AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0600', N'MCV', N'MEAN CORPUSCULAR VOLUME', N'mL', N'89.4', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99E AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0700', N'MCH', N'MEAN CORPUSCULAR HEMOGLOBIN', N'mL', N'30.5', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99E AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0800', N'MCHC', N'MEAN CORPUSCULAR HGB CONC', N'mL', N'34.1', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C99F AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.0900', N'RDW', N'RED CELL DISTRIBUTION WIDTH', N'mL', N'13.1', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1000', N'PLT', N'PLATELET COUNT', N'mL', N'208', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C9A0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1100', N'MPV', N'MEAN PLATELET VOLUME', N'mL', N'9.0', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C9A1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1200', N'NE%', N'NEUTROPHILS %', N'mL', N'66.5', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C9A2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1300', N'LY%', N'LYMPH %', N'mL', N'22.6', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1400', N'MO%', N'MONO %', N'mL', N'8.8', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1425', N'EO%', N'EOS %', N'mL', N'1.7', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1450', N'BA%', N'BASO %', N'mL', N'0.3', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1500', N'NE#', N'NEUTROPHILS #', N'mL', N'5.9', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1600', N'LY#', N'LYMPH #', N'mL', N'2.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1700', N'MO#', N'MONO #', N'mL', N'0.8', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1800', N'EO#', N'EOS #', N'mL', N'0.2', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1900', N'BA#', N'BASO #', N'mL', N'0.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A6 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1905', N'nRBC', N'nUCLEATED RED BLOOD CELL', N'mL', N'0.00', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A7 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1915', N'SCAN SMEAR', N'SCAN SMEAR', N'mL', N'NO', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.1920', N'ADDMDIFF', N'ADD MANUAL DIFF', N'mL', N'AUTO DIFF', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2200', N'NEUT', N'NEUTROPHIL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2300', N'BAND', N'BAND', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2400', N'LYMPH', N'LYMPHOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9A9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2450', N'ATYPLYM', N'ATYPICAL LYMPHOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2500', N'MON', N'MONOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2600', N'EOS', N'EOSINOPHIL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2700', N'BASO', N'BASOPHIL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2800', N'META', N'METAMYELOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.2900', N'MYELO', N'MYELOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.3000', N'PROM', N'PROMYELOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.3100', N'BLAST', N'BLAST', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.3200', N'NRBC', N'NUCLEATED RED BLOOD CELL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.3400', N'OCT', N'OTHER CELL TYPE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.3600', N'**PLTE', N'**PLATELET ESTIMATE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9AF AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.3800', N'RM', N'RBC MORPHOLOGY', N'mL', N'NORMAL', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.3900', N'POLC', N'POLYCHROMASIA', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.4000', N'HYPO', N'HYPOCHROMASIA', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.4200', N'STP', N'BASOPHILIC STIPPLING', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.4400', N'MICR', N'MICROCYTOSIS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.4500', N'MACR', N'MACROCYTOSIS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.4600', N'SPH', N'SPHEROCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.4800', N'SCH', N'SCHISTOCYTES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.4900', N'TGT', N'TARGET CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B6 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5000', N'TEAR', N'TEAR DROP CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B6 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5100', N'OVAL', N'OVALOCYTES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B7 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5200', N'STO', N'STOMATOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B7 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5300', N'HEL', N'HELMET CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5400', N'HJB', N'HOWELL-JOLLY BODIES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5500', N'CREN', N'CRENATED RBC', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5600', N'TOX', N'TOXIC GRANULATION', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5700', N'DB', N'DOHLE BODIES   ', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5800', N'BUR', N'BURR CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9B9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.5900', N'ACAN', N'ACANTHOCYTES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.6000', N'HYPP', N'HYPERSEGMENTED POLYS  ', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.6100', N'AUR', N'AUER RODS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.6200', N'ROU', N'ROULEAUX', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.6210', N'TVAC', N'TOXIC VACUOLATION', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BB AS DateTime))
GO
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.6220', N'SMUC', N'SMUDGE CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.6230', N'GPLT', N'GIANT PLATELETS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471040', N'100.6240', N'SICK', N'SICKLE CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9200', N'PCP-UR', N'PHENCYCLIDINE-URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9210', N'BENZO-UR', N'BENZODIAZEPINE-URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9220', N'COC-UR', N'COCAINE-URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9230', N'AMP-UR', N'AMPHETAMINE-URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9240', N'THC-UR', N'CANNABINOID-UR', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9250', N'OPI-UR', N'OPIATE-URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9260', N'BARB-UR', N'BARBITURATE-URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9270', N'UR TOX POS QC', N'URINE TOX POS QC', N'mL', N'POSITIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471056', N'300.9280', N'UR TOX NEG QC', N'URINE TOXICOLOGY NEG QC', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0100', N'WBC', N'WHITE BLOOD COUNT', N'mL', N'11.9', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0200', N'CWBC', N'CORRECTED WHITE BLOOD COUNT', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0300', N'RBC', N'RED BLOOD COUNT', N'mL', N'3.32', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0400', N'HGB', N'HEMOGLOBIN', N'mL', N'10.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0500', N'HCT', N'HEMATOCRIT', N'mL', N'30.2', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0600', N'MCV', N'MEAN CORPUSCULAR VOLUME', N'mL', N'90.8', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0700', N'MCH', N'MEAN CORPUSCULAR HEMOGLOBIN', N'mL', N'30.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BF AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0800', N'MCHC', N'MEAN CORPUSCULAR HGB CONC', N'mL', N'33.1', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BF AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.0900', N'RDW', N'RED CELL DISTRIBUTION WIDTH', N'mL', N'13.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9BF AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1000', N'PLT', N'PLATELET COUNT', N'mL', N'177', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1100', N'MPV', N'MEAN PLATELET VOLUME', N'mL', N'8.8', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1200', N'NE%', N'NEUTROPHILS %', N'mL', N'75.6', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1300', N'LY%', N'LYMPH %', N'mL', N'16.2', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1400', N'MO%', N'MONO %', N'mL', N'7.6', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9C1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1425', N'EO%', N'EOS %', N'mL', N'0.4', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1450', N'BA%', N'BASO %', N'mL', N'0.2', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1500', N'NE#', N'NEUTROPHILS #', N'mL', N'9.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9CF AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1600', N'LY#', N'LYMPH #', N'mL', N'1.9', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1700', N'MO#', N'MONO #', N'mL', N'0.9', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1800', N'EO#', N'EOS #', N'mL', N'0.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1900', N'BA#', N'BASO #', N'mL', N'0.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1905', N'nRBC', N'nUCLEATED RED BLOOD CELL', N'mL', N'0.00', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1915', N'SCAN SMEAR', N'SCAN SMEAR', N'mL', N'YES', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.1920', N'ADDMDIFF', N'ADD MANUAL DIFF', N'mL', N'AUTO DIFF', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2200', N'NEUT', N'NEUTROPHIL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2300', N'BAND', N'BAND', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2400', N'LYMPH', N'LYMPHOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2450', N'ATYPLYM', N'ATYPICAL LYMPHOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2500', N'MON', N'MONOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D4 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2600', N'EOS', N'EOSINOPHIL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2700', N'BASO', N'BASOPHIL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D5 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2800', N'META', N'METAMYELOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D6 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.2900', N'MYELO', N'MYELOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D6 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.3000', N'PROM', N'PROMYELOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D7 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.3100', N'BLAST', N'BLAST', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D7 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.3200', N'NRBC', N'NUCLEATED RED BLOOD CELL', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.3400', N'OCT', N'OTHER CELL TYPE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D8 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.3600', N'**PLTE', N'**PLATELET ESTIMATE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.3800', N'RM', N'RBC MORPHOLOGY', N'mL', N'NORMAL', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.3900', N'POLC', N'POLYCHROMASIA', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9D9 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.4000', N'HYPO', N'HYPOCHROMASIA', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.4200', N'STP', N'BASOPHILIC STIPPLING', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.4400', N'MICR', N'MICROCYTOSIS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DA AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.4500', N'MACR', N'MACROCYTOSIS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.4600', N'SPH', N'SPHEROCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DB AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.4800', N'SCH', N'SCHISTOCYTES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.4900', N'TGT', N'TARGET CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5000', N'TEAR', N'TEAR DROP CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DC AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5100', N'OVAL', N'OVALOCYTES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5200', N'STO', N'STOMATOCYTE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5300', N'HEL', N'HELMET CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DD AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5400', N'HJB', N'HOWELL-JOLLY BODIES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5500', N'CREN', N'CRENATED RBC', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DE AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5600', N'TOX', N'TOXIC GRANULATION', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DF AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5700', N'DB', N'DOHLE BODIES   ', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9DF AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5800', N'BUR', N'BURR CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.5900', N'ACAN', N'ACANTHOCYTES', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E0 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.6000', N'HYPP', N'HYPERSEGMENTED POLYS  ', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.6100', N'AUR', N'AUER RODS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.6200', N'ROU', N'ROULEAUX', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E1 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.6210', N'TVAC', N'TOXIC VACUOLATION', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.6220', N'SMUC', N'SMUDGE CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.6230', N'GPLT', N'GIANT PLATELETS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E2 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111112', N'V111111112', N'471322', N'100.6240', N'SICK', N'SICKLE CELLS', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C9E3 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M000111133', N'V111111112', N'471033', N'300.5650', N'UCAPHOS', N'CALCIUM PHOSPHATE CRYSTALS,URN', N'mL', N'NP', CAST(0x0000A33700AD08E0 AS DateTime), CAST(0x0000A31400A4C994 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4450', N'URURO', N'URINE UROBILINOGEN', N'mL', N'0.2', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C990 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4600', N'UNIT', N'NITRITE, URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C990 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4700', N'ULE', N'URINE LEUKOCYTE ESTERASE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C990 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4750', N'MODE OF', N'MODE OF COLLECTION', N'mL', N'CATHETER', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C991 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4800', N'UMIC', N'MICROSCOPIC, URINE', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C991 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4850', N'RBCU', N'RBC''S URINE MICRO', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C991 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4950', N'WBCU', N'WBC''S URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C992 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.4975', N'WBCC', N'WBC CLUMPS URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C992 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M336544787', N'V111111112', N'471033', N'300.5125', N'REEP', N'RENAL EPITHELIAL CELLS,URINE', N'mL', N'NP', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C993 AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.3200', N'UDIP', N'URINE DIPSTICK', N'mL', N'', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98C AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.3300', N'UCOL', N'COLOR, URINE', N'mL', N'YELLOW', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98C AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.3400', N'UAPP', N'APPEARANCE, URINE', N'mL', N'CLEAR', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98D AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.3500', N'UGL', N'GLUCOSE, URINE (UA)', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98D AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.3700', N'UBIL', N'BILIRUBIN, URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98E AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.3900', N'UKET', N'KETONE, URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98E AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.4000', N'SGU', N'SPECIFIC GRAVITY,URINE', N'mL', N'1.009', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98E AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.4100', N'UBL', N'OCCULT BLOOD URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98E AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.4150', N'URPH', N'URINE PH', N'mL', N'7.0', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98F AS DateTime))
INSERT [dbo].[patient_lab] ([client_database_id], [source_id], [unit_number], [account_number], [specimen_id], [test_number], [test_mnemonic], [test_name], [test_result], [test_unit], [result_date], [date_added]) VALUES (1, N'NMC', N'M876544787', N'V111111112', N'471033', N'300.4300', N'UPRO', N'PROTEIN, URINE', N'mL', N'NEGATIVE', CAST(0x0000A312016BEC10 AS DateTime), CAST(0x0000A31400A4C98F AS DateTime))
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320376, N'FNT.05A2', N'FNT.05A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95B AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320377, N'FNT.05A2', N'FNT.05A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C956 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320378, N'OXTPREMIX', N'OXTPREMIX', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95B AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320379, N'FNT.05A2', N'FNT.05A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C957 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320380, N'PROM25VI', N'PROM25VI', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95B AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320381, N'FNT.05A2', N'FNT.05A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C957 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320382, N'FNT.05A2', N'FNT.05A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95C AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320383, N'MS1A2', N'MS1A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C958 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320384, N'METO5VIA7', N'METO5VIA7', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95C AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320385, N'DPH50V', N'DPH50V', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C958 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320386, N'ONDN2V2', N'ONDN2V2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95D AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320387, N'FNT.05A2', N'FNT.05A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C958 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320388, N'METO5VIA7', N'METO5VIA7', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95D AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320389, N'FMT10V2', N'FMT10V2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C958 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320390, N'CTRSDC30U', N'CTRSDC30U', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95D AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320391, N'MS1A2', N'MS1A2', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C959 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320402, N'IBP600T', N'IBP600T', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C963 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320403, N'BSC10SU', N'BSC10SU', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C961 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320404, N'DCS100C', N'DCS100C', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C964 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320411, N'LD1A5', N'LD1A5', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C962 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M000111112', N'V111111112', 320412, N'OXT10V', N'OXT10V', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C965 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320373, N'PPD5V5', N'PPD5V5', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C955 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320374, N'LD1V50', N'LD1V50', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95A AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320375, N'OXTPREMIX', N'OXTPREMIX', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C956 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320405, N'MOM30U', N'MOM30U', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C961 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320406, N'SMT80CT', N'SMT80CT', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C964 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320407, N'BSC5TEC', N'BSC5TEC', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C961 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320408, N'CFZ1V', N'CFZ1V', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C964 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320409, N'CFZ1V', N'CFZ1V', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C962 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M336544787', N'V111111112', 320410, N'BP.75A10', N'BP.75A10', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C965 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320392, N'METO5VIA7', N'METO5VIA7', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95E AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320393, N'MS2I', N'MS2I', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C959 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320394, N'DCS100C', N'DCS100C', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95E AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320395, N'HYDR-144', N'HYDR-144', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95A AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320396, N'MOM30U', N'MOM30U', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95F AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320397, N'SMT80CT', N'SMT80CT', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95A AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320398, N'PPD5V5', N'PPD5V5', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C95F AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320399, N'MMRVXI', N'MMRVXI', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C960 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320400, N'HYDR-144', N'HYDR-144', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C963 AS DateTime), N'5 mg')
INSERT [dbo].[patient_prescription] ([client_database_id], [source_id], [unit_number], [account_number], [prescription_id], [drug_id], [drug_name], [drug_type], [drug_status], [issued_date], [discontinued_date], [date_added], [dose]) VALUES (1, N'NMC', N'M876544787', N'V111111112', 320401, N'HYDR-144', N'HYDR-144', N'DC', N'P', CAST(0x0000A3120168E6A0 AS DateTime), CAST(0x0000000000000000 AS DateTime), CAST(0x0000A31400A4C960 AS DateTime), N'5 mg')
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'MNC', N'M000111122', N'Preet', N'Singh', CAST(0x00007AB300000000 AS DateTime), NULL, NULL, CAST(0x0000A33901384950 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMA', N'M323458787', N'Nidhi', N'Singh', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMC', N'M000111112', N'Wil', N'Wong', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMC', N'M000111133', N'vishva', N'singh', CAST(0x000071EB00000000 AS DateTime), NULL, NULL, CAST(0x0000A33900000000 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMC', N'M000111144', N'Richa', N'Sahu', CAST(0x000077D800000000 AS DateTime), NULL, NULL, CAST(0x0000A31B01445F10 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMC', N'M000111155', N'Pradeep', N'Kumar', CAST(0x000063DF00000000 AS DateTime), NULL, NULL, CAST(0x0000A33900000000 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMD', N'M3458787', N'Sunil', N'Sharma', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMI', N'M776544787', N'Tripta', N'Bartwal', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMP', N'M123458787', N'Raj', N'Kumar', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMQ', N'M876544787', N'Mohit', N'Jain', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMS', N'M336544787', N'Subham', N'Jain', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
INSERT [dbo].[patient_verification] ([client_database_id], [source_id], [unit_number], [first_name], [last_name], [birth_date], [date_validated], [account_id], [date_added], [date_modified]) VALUES (1, N'NMZ', N'M116544787', N'Dheeraj', N'Borra', CAST(0x00006B0100000000 AS DateTime), NULL, NULL, CAST(0x0000A31400A4C934 AS DateTime), NULL)
SET IDENTITY_INSERT [dbo].[patient_visit] ON 

INSERT [dbo].[patient_visit] ([patient_visit_id], [client_database_id], [source_id], [unit_number], [account_number], [first_name], [middle_name], [last_name], [visit_date], [location], [admit_date], [discharge_date], [attending_physician_name], [provider_id]) VALUES (1, 1, N'NMC', N'M000111112', N'V111111112', N'Wil', N'J', N'Wong', CAST(0x0000A1A700C4FB46 AS DateTime), N'LOC', CAST(0x0000A31400C4FB46 AS DateTime), CAST(0x0000A31400C4FB46 AS DateTime), N'Shawn', N'ANDEMS')
INSERT [dbo].[patient_visit] ([patient_visit_id], [client_database_id], [source_id], [unit_number], [account_number], [first_name], [middle_name], [last_name], [visit_date], [location], [admit_date], [discharge_date], [attending_physician_name], [provider_id]) VALUES (8, 1, N'NMC', N'M000111112', N'ACT1234567', N'Rahul', NULL, N'Gandhi', CAST(0x0000A3E100C99960 AS DateTime), N'Santaclara', CAST(0x0000A3CB00B80560 AS DateTime), CAST(0x0000A3CB00B80560 AS DateTime), N'Nikhil', N'ANDEMA')
INSERT [dbo].[patient_visit] ([patient_visit_id], [client_database_id], [source_id], [unit_number], [account_number], [first_name], [middle_name], [last_name], [visit_date], [location], [admit_date], [discharge_date], [attending_physician_name], [provider_id]) VALUES (12, 1, N'NMC', N'M000111112', N'ACT777543', N'Dheeraj', N'Borra', N'Borra', CAST(0x0000A2D000C9DFB0 AS DateTime), N'CA', CAST(0x0000A2D000C9DFB0 AS DateTime), CAST(0x0000A3E100C9DFB0 AS DateTime), N'Nidhi', N'ANDEMF')
INSERT [dbo].[patient_visit] ([patient_visit_id], [client_database_id], [source_id], [unit_number], [account_number], [first_name], [middle_name], [last_name], [visit_date], [location], [admit_date], [discharge_date], [attending_physician_name], [provider_id]) VALUES (13, 1, N'NMC', N'M000111113', N'ACT1524854', N'Mark', N'H', N'Henry', CAST(0x0000A2D000CA02D8 AS DateTime), N'CA', CAST(0x0000A2D000CA02D8 AS DateTime), CAST(0x0000A2D000CA02D8 AS DateTime), N'Anderson', N'ANDEM')
INSERT [dbo].[patient_visit] ([patient_visit_id], [client_database_id], [source_id], [unit_number], [account_number], [first_name], [middle_name], [last_name], [visit_date], [location], [admit_date], [discharge_date], [attending_physician_name], [provider_id]) VALUES (14, 1, N'NMC', N'M000111115', N'ACT1154875', N'Flin', N'J', N'Karl', CAST(0x0000A2D000CA02D8 AS DateTime), N'LA', CAST(0x0000A2D000CA02D8 AS DateTime), CAST(0x0000A2D000CA02D8 AS DateTime), N'Anderson', N'ANDEM')
INSERT [dbo].[patient_visit] ([patient_visit_id], [client_database_id], [source_id], [unit_number], [account_number], [first_name], [middle_name], [last_name], [visit_date], [location], [admit_date], [discharge_date], [attending_physician_name], [provider_id]) VALUES (16, 1, N'NMC', N'M336544787', N'ACT8521456', N'Subham', N'J', N'Jain', CAST(0x0000A3330130FB89 AS DateTime), N'IND', CAST(0x0000A3330130FB89 AS DateTime), CAST(0x0000A3330130FB89 AS DateTime), N'Anderson', N'ANDEM')
SET IDENTITY_INSERT [dbo].[patient_visit] OFF
INSERT [dbo].[response] ([response_id], [response]) VALUES (1, N'Yes')
INSERT [dbo].[response] ([response_id], [response]) VALUES (2, N'No')
INSERT [dbo].[security_question] ([security_question_id], [question], [is_active], [date_added]) VALUES (1, N'What was your childhood nickname?', 1, CAST(0x0000A31400A4C94A AS DateTime))
INSERT [dbo].[security_question] ([security_question_id], [question], [is_active], [date_added]) VALUES (2, N'What was the make and model of your first car?', 1, CAST(0x0000A31400A4C94B AS DateTime))
INSERT [dbo].[security_question] ([security_question_id], [question], [is_active], [date_added]) VALUES (3, N'What is the name of your favorite childhood friend?', 1, CAST(0x0000A31400A4C94B AS DateTime))
SET ANSI_PADDING ON

GO
/****** Object:  Index [ix_patient_visit]    Script Date: 7/7/2014 5:43:13 PM ******/
CREATE UNIQUE NONCLUSTERED INDEX [ix_patient_visit] ON [dbo].[patient_visit]
(
	[client_database_id] ASC,
	[source_id] ASC,
	[unit_number] ASC,
	[account_number] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
ALTER TABLE [dbo].[account] ADD  CONSTRAINT [DF_account_failed_login_attemps]  DEFAULT ((0)) FOR [failed_login_attempts]
GO
ALTER TABLE [dbo].[account] ADD  CONSTRAINT [DF_account_date_account_role_id]  DEFAULT ((1)) FOR [account_role_id]
GO
ALTER TABLE [dbo].[account] ADD  CONSTRAINT [DF_account_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[account_blood_pressure_engage] ADD  CONSTRAINT [DF_account_blood_pressure_engage_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[account_blood_pressure_management] ADD  CONSTRAINT [DF_account_blood_pressure_management_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[account_blood_pressure_management_schedule] ADD  CONSTRAINT [DF_account_blood_pressure_management_schedule_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[account_medication_engage] ADD  CONSTRAINT [DF_account_medication_engage_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[account_medication_management] ADD  CONSTRAINT [DF_account_medication_management_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[account_to_security_question] ADD  CONSTRAINT [DF_account_to_security_question_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[hospital_provider] ADD  CONSTRAINT [DF_hospital_provider_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[hospital_service] ADD  CONSTRAINT [DF_hospital_service_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[patient_appointment_request] ADD  CONSTRAINT [DF_patient_appointment_request_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[patient_feedback] ADD  CONSTRAINT [DF_patient_feedback_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[patient_lab] ADD  CONSTRAINT [DF_patient_lab_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[patient_prescription] ADD  CONSTRAINT [DF_patient_prescription_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[patient_verification] ADD  CONSTRAINT [DF_patient_verification_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[patient_verification_log] ADD  CONSTRAINT [DF_patient_verification_log_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[security_question] ADD  CONSTRAINT [DF_security_question_is_active]  DEFAULT ((1)) FOR [is_active]
GO
ALTER TABLE [dbo].[security_question] ADD  CONSTRAINT [DF_security_question_date_added]  DEFAULT (getdate()) FOR [date_added]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [FK_account_account_role] FOREIGN KEY([account_role_id])
REFERENCES [dbo].[account_role] ([account_role_id])
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [FK_account_account_role]
GO
ALTER TABLE [dbo].[account]  WITH CHECK ADD  CONSTRAINT [FK_account_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[account] CHECK CONSTRAINT [FK_account_client_naavis_databases]
GO
ALTER TABLE [dbo].[account_blood_pressure_management]  WITH CHECK ADD  CONSTRAINT [FK_account_blood_pressure_management_account] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([account_id])
GO
ALTER TABLE [dbo].[account_blood_pressure_management] CHECK CONSTRAINT [FK_account_blood_pressure_management_account]
GO
ALTER TABLE [dbo].[account_blood_pressure_management]  WITH CHECK ADD  CONSTRAINT [FK_account_blood_pressure_management_frequency] FOREIGN KEY([frequency_id])
REFERENCES [dbo].[frequency] ([frequency_id])
GO
ALTER TABLE [dbo].[account_blood_pressure_management] CHECK CONSTRAINT [FK_account_blood_pressure_management_frequency]
GO
ALTER TABLE [dbo].[account_blood_pressure_management_schedule]  WITH CHECK ADD  CONSTRAINT [FK_account_blood_pressure_management_schedule_date_part] FOREIGN KEY([date_part_id])
REFERENCES [dbo].[date_part] ([date_part_id])
GO
ALTER TABLE [dbo].[account_blood_pressure_management_schedule] CHECK CONSTRAINT [FK_account_blood_pressure_management_schedule_date_part]
GO
ALTER TABLE [dbo].[account_medication_engage]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_engage_response] FOREIGN KEY([response_id])
REFERENCES [dbo].[response] ([response_id])
GO
ALTER TABLE [dbo].[account_medication_engage] CHECK CONSTRAINT [FK_account_medication_engage_response]
GO
ALTER TABLE [dbo].[account_medication_management]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_account] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([account_id])
GO
ALTER TABLE [dbo].[account_medication_management] CHECK CONSTRAINT [FK_account_medication_management_account]
GO
ALTER TABLE [dbo].[account_medication_management]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_medication_kind] FOREIGN KEY([medication_kind_id])
REFERENCES [dbo].[medication_kind] ([medication_kind_id])
GO
ALTER TABLE [dbo].[account_medication_management] CHECK CONSTRAINT [FK_account_medication_management_medication_kind]
GO
ALTER TABLE [dbo].[account_medication_management]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_medication_method] FOREIGN KEY([medication_method_id])
REFERENCES [dbo].[medication_method] ([medication_method_id])
GO
ALTER TABLE [dbo].[account_medication_management] CHECK CONSTRAINT [FK_account_medication_management_medication_method]
GO
ALTER TABLE [dbo].[account_medication_management_reminder]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_reminder_account] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([account_id])
GO
ALTER TABLE [dbo].[account_medication_management_reminder] CHECK CONSTRAINT [FK_account_medication_management_reminder_account]
GO
ALTER TABLE [dbo].[account_medication_management_reminder]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_reminder_frequency] FOREIGN KEY([frequency_id])
REFERENCES [dbo].[frequency] ([frequency_id])
GO
ALTER TABLE [dbo].[account_medication_management_reminder] CHECK CONSTRAINT [FK_account_medication_management_reminder_frequency]
GO
ALTER TABLE [dbo].[account_medication_management_reminder]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_reminder_medication_dosage] FOREIGN KEY([medication_dosage_id])
REFERENCES [dbo].[medication_dosage] ([medication_dosage_id])
GO
ALTER TABLE [dbo].[account_medication_management_reminder] CHECK CONSTRAINT [FK_account_medication_management_reminder_medication_dosage]
GO
ALTER TABLE [dbo].[account_medication_management_schedule]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_schedule_account_medication_management_reminder] FOREIGN KEY([account_medication_management_reminder_id])
REFERENCES [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id])
GO
ALTER TABLE [dbo].[account_medication_management_schedule] CHECK CONSTRAINT [FK_account_medication_management_schedule_account_medication_management_reminder]
GO
ALTER TABLE [dbo].[account_medication_management_schedule]  WITH CHECK ADD  CONSTRAINT [FK_account_medication_management_schedule_date_part] FOREIGN KEY([date_part_id])
REFERENCES [dbo].[date_part] ([date_part_id])
GO
ALTER TABLE [dbo].[account_medication_management_schedule] CHECK CONSTRAINT [FK_account_medication_management_schedule_date_part]
GO
ALTER TABLE [dbo].[account_reminder_medication_relation]  WITH CHECK ADD  CONSTRAINT [FK_account_reminder_medication_relation_account_medication_management] FOREIGN KEY([account_medication_management_id])
REFERENCES [dbo].[account_medication_management] ([account_medication_management_id])
GO
ALTER TABLE [dbo].[account_reminder_medication_relation] CHECK CONSTRAINT [FK_account_reminder_medication_relation_account_medication_management]
GO
ALTER TABLE [dbo].[account_reminder_medication_relation]  WITH CHECK ADD  CONSTRAINT [FK_account_reminder_medication_relation_account_medication_management_reminder] FOREIGN KEY([account_medication_management_reminder_id])
REFERENCES [dbo].[account_medication_management_reminder] ([account_medication_management_reminder_id])
GO
ALTER TABLE [dbo].[account_reminder_medication_relation] CHECK CONSTRAINT [FK_account_reminder_medication_relation_account_medication_management_reminder]
GO
ALTER TABLE [dbo].[account_to_security_question]  WITH CHECK ADD  CONSTRAINT [FK_account_to_security_question_account] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([account_id])
GO
ALTER TABLE [dbo].[account_to_security_question] CHECK CONSTRAINT [FK_account_to_security_question_account]
GO
ALTER TABLE [dbo].[account_to_security_question]  WITH CHECK ADD  CONSTRAINT [FK_account_to_security_question_security_question] FOREIGN KEY([security_question_id])
REFERENCES [dbo].[security_question] ([security_question_id])
GO
ALTER TABLE [dbo].[account_to_security_question] CHECK CONSTRAINT [FK_account_to_security_question_security_question]
GO
ALTER TABLE [dbo].[frequency]  WITH CHECK ADD  CONSTRAINT [FK_frequency_date_part] FOREIGN KEY([date_part_id])
REFERENCES [dbo].[date_part] ([date_part_id])
GO
ALTER TABLE [dbo].[frequency] CHECK CONSTRAINT [FK_frequency_date_part]
GO
ALTER TABLE [dbo].[hospital_notice]  WITH CHECK ADD  CONSTRAINT [FK_hospital_notice_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[hospital_notice] CHECK CONSTRAINT [FK_hospital_notice_client_naavis_databases]
GO
ALTER TABLE [dbo].[hospital_provider]  WITH CHECK ADD  CONSTRAINT [FK_hospital_provider_account] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([account_id])
GO
ALTER TABLE [dbo].[hospital_provider] CHECK CONSTRAINT [FK_hospital_provider_account]
GO
ALTER TABLE [dbo].[hospital_service]  WITH CHECK ADD  CONSTRAINT [FK_hospital_service_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[hospital_service] CHECK CONSTRAINT [FK_hospital_service_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_appointment_request]  WITH CHECK ADD  CONSTRAINT [FK_patient_appointment_request_account] FOREIGN KEY([account_id])
REFERENCES [dbo].[account] ([account_id])
GO
ALTER TABLE [dbo].[patient_appointment_request] CHECK CONSTRAINT [FK_patient_appointment_request_account]
GO
ALTER TABLE [dbo].[patient_feedback]  WITH CHECK ADD  CONSTRAINT [FK_patient_feedback_patient_visit] FOREIGN KEY([patient_visit_id])
REFERENCES [dbo].[patient_visit] ([patient_visit_id])
GO
ALTER TABLE [dbo].[patient_feedback] CHECK CONSTRAINT [FK_patient_feedback_patient_visit]
GO
ALTER TABLE [dbo].[patient_lab]  WITH CHECK ADD  CONSTRAINT [FK_patient_lab_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[patient_lab] CHECK CONSTRAINT [FK_patient_lab_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_prescription]  WITH CHECK ADD  CONSTRAINT [FK_patient_prescription_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[patient_prescription] CHECK CONSTRAINT [FK_patient_prescription_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_verification]  WITH CHECK ADD  CONSTRAINT [FK_patient_verification_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[patient_verification] CHECK CONSTRAINT [FK_patient_verification_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_verification_log]  WITH CHECK ADD  CONSTRAINT [FK_patient_verification_log_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[patient_verification_log] CHECK CONSTRAINT [FK_patient_verification_log_client_naavis_databases]
GO
ALTER TABLE [dbo].[patient_visit]  WITH CHECK ADD  CONSTRAINT [FK_patient_visit_client_naavis_databases] FOREIGN KEY([client_database_id])
REFERENCES [dbo].[client_naavis_databases] ([client_database_id])
GO
ALTER TABLE [dbo].[patient_visit] CHECK CONSTRAINT [FK_patient_visit_client_naavis_databases]
GO
USE [master]
GO
ALTER DATABASE [engage] SET  READ_WRITE 
GO

--USE [engage_app_20] --USE DEV app 02 Database
GO
/****** Object:  Table [dbo].[weight]    Script Date: 27-01-2016 10:38:32 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[weight](
	[weight_id] [int] IDENTITY(1,1) NOT NULL,
	[account_id] [int] NOT NULL,
	[date] [datetime] NOT NULL,
	[weight] [nvarchar](64) NULL,
	[weight_units] [nvarchar](64) NULL,
 CONSTRAINT [PK_Weight] PRIMARY KEY CLUSTERED 
(
	[weight_id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

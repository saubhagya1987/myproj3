USE [engage_app_qa_17]
GO
/****** Object:  Table [dbo].[engage_client_feature]    Script Date: 24-09-2015 19:11:01 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
SET ANSI_PADDING ON
GO
CREATE TABLE [dbo].[engage_client_feature](
	[client_database_id] [int] NOT NULL,
	[id] [int] identity(1,1) NOT NULL,
	[version] [varchar](255) NOT NULL,
	[feature_order] [int] NULL,
	[feature_id] [int] NOT NULL,
PRIMARY KEY CLUSTERED 
(
	[client_database_id] ASC,
	[id] ASC,
	[version] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET ANSI_PADDING OFF
GO

INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (9,  N'1.7', 1, 11)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (9,  N'1.7', 2, 21)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (9,  N'1.7', 3, 20)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (9,  N'1.7', 4, 18)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (9,  N'1.7', 5, 13)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (9,  N'1.7', 6, 17)

INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (11,  N'1.7', 1, 11)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (11,  N'1.7', 2, 21)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (11,  N'1.7', 3, 20)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (11,  N'1.7', 4, 18)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (11,  N'1.7', 5, 13)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (11,  N'1.7', 6, 17)

INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (13,  N'1.7', 1, 11)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (13,  N'1.7', 2, 21)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (13,  N'1.7', 3, 20)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (13,  N'1.7', 4, 18)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (13,  N'1.7', 5, 13)
INSERT [dbo].[engage_client_feature] ([client_database_id],  [version], [feature_order], [feature_id]) VALUES (13,  N'1.7', 6, 17)

ALTER TABLE [dbo].[engage_client_feature]  WITH CHECK ADD  CONSTRAINT [FKDCEE5F204758FF0F] FOREIGN KEY([feature_id])
REFERENCES [dbo].[engage_app_features] ([feature_id])
GO
ALTER TABLE [dbo].[engage_client_feature] CHECK CONSTRAINT [FKDCEE5F204758FF0F]
GO

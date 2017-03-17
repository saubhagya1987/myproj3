--USE [engage_app_20] Use QA2 database
GO
TRUNCATE TABLE [dbo].[frequency];
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval], [lang_code], [type]) VALUES (3, N'Daily', 2, 1, N'en', 1)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval], [lang_code], [type]) VALUES (9, N'Weekly', 3, 1, N'en', 2)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval], [lang_code], [type]) VALUES (13, N'每天', 2, 1, N'zh', 1)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval], [lang_code], [type]) VALUES (19, N'每周', 3, 1, N'zh', 2)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval], [lang_code], [type]) VALUES (23, N'Todos los días', 2, 1, N'es', 1)
INSERT [dbo].[frequency] ([frequency_id], [frequency], [date_part_id], [interval], [lang_code], [type]) VALUES (29, N'Todas las semanas', 3, 1, N'es', 2)

Alter table [engage_ehr].[dbo].[etl_info] drop column module_list
ALTER TABLE [engage_ehr].[dbo].[patient_lab] DROP COLUMN normal_range;
ALTER TABLE [engage_ehr].[dbo].[patient_lab] DROP COLUMN absolute_range;
ALTER TABLE [engage_ehr].[dbo].[patient_lab] ADD normal_range_min  numeric(20,2);
ALTER TABLE [engage_ehr].[dbo].[patient_lab] ADD normal_range_max numeric(20,2);
ALTER TABLE [engage_ehr].[dbo].[patient_lab] ADD absolute_range_min numeric(20,2);
ALTER TABLE [engage_ehr].[dbo].[patient_lab] ADD absolute_range_max numeric(20,2);     

/*
Created: 12/10/2019
Modified: 12/12/2019
Model: Microsoft SQL Server 2017
Database: MS SQL Server 2017
*/




-- Create tables section -------------------------------------------------

-- Table patient

CREATE TABLE [patient]
(
 [patient_id] Int IDENTITY NOT NULL,
 [first_name] Varchar(20) NOT NULL,
 [middle_name] Varchar(20) NULL,
 [last_name] Varchar(30) NOT NULL,
 [contact_number] Char(10) NOT NULL,
 [date_of_birth] Date NOT NULL,
 [addressline1] Varchar(200) NOT NULL,
 [addressline2] Varchar(150) NULL,
 [zip_code] Char(5) NOT NULL,
 [emergency_contact_number] Char(10) NULL,
 [gender] Char(1) NULL,
 [email_address] Varchar(100) NOT NULL
)
go

-- Add keys for table patient

ALTER TABLE [patient] ADD CONSTRAINT [PK_patient] PRIMARY KEY ([patient_id])
go

--constraints on patient table
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([email_address] like '%@%.%'))
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([contact_number] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([first_name] like '[A-Z]%'))
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([middle_name] like '[A-Z]%'))
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([last_name] like '[A-Z]%'))
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([emergency_contact_number] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([zip_code] like '[0-9][0-9][0-9][0-9][0-9]'))
ALTER TABLE [patient]  WITH CHECK ADD CHECK  (([gender] = 'M' OR [gender] = 'F'))


-- Table dentist

CREATE TABLE [dentist]
(
 [dentist_id] Int IDENTITY NOT NULL,
 [first_name] Varchar(20) NOT NULL,
 [middle_name] Varchar(20) NULL,
 [last_name] Varchar(30) NOT NULL,
 [contact_number] Char(10) NOT NULL,
 [dentist_photo] Varbinary(max) NULL
)
go

-- Add keys for table dentist

ALTER TABLE [dentist] ADD CONSTRAINT [PK_dentist] PRIMARY KEY ([dentist_id])
go

--constraints on dentist table
ALTER TABLE [dentist]  WITH CHECK ADD CHECK  (([contact_number] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))
ALTER TABLE [dentist]  WITH CHECK ADD CHECK  (([first_name] like '[A-Z]%'))
ALTER TABLE [dentist]  WITH CHECK ADD CHECK  (([middle_name] like '[A-Z]%'))
ALTER TABLE [dentist]  WITH CHECK ADD CHECK  (([last_name] like '[A-Z]%'))


-- Table location

CREATE TABLE [location]
(
 [location_id] Varchar(6) NOT NULL,
 [addressline1] Varchar(200) NOT NULL,
 [addressline2] Varchar(150) NULL,
 [contact_number] Char(10) NOT NULL,
 [zip_code] Char(5) NOT NULL
)
go

ALTER TABLE [location]  WITH CHECK ADD CHECK  (([zip_code] like '[0-9][0-9][0-9][0-9][0-9]'))
ALTER TABLE [location]  WITH CHECK ADD CHECK  (([contact_number] like '[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'))

-- Create indexes for table location

CREATE INDEX [IX_Relationship5] ON [location] ([zip_code])
go

-- Add keys for table location

ALTER TABLE [location] ADD CONSTRAINT [PK_location] PRIMARY KEY ([location_id])
go

-- Table room

CREATE TABLE [room]
(
 [room_number] Varchar(6) NOT NULL,
 [room_type] Varchar(45) NULL,
 [location_id] Varchar(6) NOT NULL
)
go

-- Add keys for table room

ALTER TABLE [room] ADD CONSTRAINT [PK_room] PRIMARY KEY ([room_number])
go

-- Table appointment

CREATE TABLE [appointment]
(
 [appointment_id] Int IDENTITY NOT NULL,
 [patient_id] Int NOT NULL,
 [status] Varchar(20) NOT NULL,
 [date] Date NOT NULL,
 [start_time] Time NOT NULL,
 [end_time] Time NOT NULL,
 [dentist_id] Int NULL,
 [room_number] Varchar(6) NULL
)
go

ALTER TABLE [appointment]  WITH CHECK ADD CHECK  (([end_time]>[start_time]))
ALTER TABLE [appointment]  WITH CHECK ADD CHECK  (([date] >= getdate()))

-- Create indexes for table appointment

CREATE INDEX [IX_patient makes an appointment] ON [appointment] ([patient_id])
go

CREATE INDEX [IX_Relationship24] ON [appointment] ([room_number])
go

CREATE INDEX [IX_Relationship11] ON [appointment] ([dentist_id])
go

-- Add keys for table appointment

ALTER TABLE [appointment] ADD CONSTRAINT [PK_appointment] PRIMARY KEY ([appointment_id])
go

-- Table medical_history

CREATE TABLE [medical_history]
(
 [medications] Bit NULL,
 [patient_id] Int NOT NULL,
 [latex_allergy] Bit NULL,
 [amalgam_allergy] Bit NULL,
 [anaesthetic_allergy] Bit NULL,
 [fluoride_allergy] Bit NULL,
 [dentures_allergy] Bit NULL,
 [total_teeth] Int NULL,
 [other_allergies] Bit NULL
)
go

-- Create indexes for table medical_history

CREATE INDEX [IX_Relationship1] ON [medical_history] ([patient_id])
go

-- Add keys for table medical_history

ALTER TABLE [medical_history] ADD CONSTRAINT [PK_medical_history] PRIMARY KEY ([patient_id])
go

-- Table treatment_catalog

CREATE TABLE [treatment_catalog]
(
 [treatment_name] Varchar(100) NOT NULL,
 [treatment_cost] Int NOT NULL
)
go

-- Add keys for table treatment_catalog

ALTER TABLE [treatment_catalog] ADD CONSTRAINT [PK_treatment_catalog] PRIMARY KEY ([treatment_name])
go

-- Table treatment

CREATE TABLE [treatment]
(
 [treatment_cost] Int NOT NULL,
 [appointment_id] Int NOT NULL,
 [treatment_name] Varchar(100) NULL
)
go

-- Create indexes for table treatment

CREATE INDEX [IX_Relationship28] ON [treatment] ([treatment_name])
go

CREATE INDEX [IX_app can have treatment(s)] ON [treatment] ([appointment_id])
go

-- Table supplies

CREATE TABLE [supplies]
(
 [supplies_name] Varchar(30) NOT NULL,
 [no_of_units] Int NOT NULL,
 [cost_of_each_unit] Money NOT NULL,
 [location_id] Varchar(6) NOT NULL,
 [supply_id] Int NOT NULL
)
go

-- Create indexes for table supplies

CREATE INDEX [IX_Relationship26] ON [supplies] ([location_id])
go

-- Add keys for table supplies

ALTER TABLE [supplies] ADD CONSTRAINT [PK_supplies] PRIMARY KEY ([supply_id])
go

-- Table Invoice

CREATE TABLE [Invoice]
(
 [invoice_id] Int IDENTITY NOT NULL,
 [date] Date NOT NULL,
 [time] Time NOT NULL,
 [amount] Int NULL,
 [appointment_id] Int NOT NULL
)
go

-- Add keys for table Invoice

ALTER TABLE [Invoice] ADD CONSTRAINT [PK_Invoice] PRIMARY KEY ([invoice_id],[appointment_id])
go

-- Table prescription

CREATE TABLE [prescription]
(
 [medicine_name] Varchar(40) NOT NULL,
 [morning] Bit NOT NULL,
 [afternoon] Bit NOT NULL,
 [night] Bit NOT NULL,
 [duration] Smallint NOT NULL,
 [notes] Varchar(50) NULL,
 [appointment_id] Int NOT NULL
)
go

-- Add keys for table prescription

ALTER TABLE [prescription] ADD CONSTRAINT [PK_prescription] PRIMARY KEY ([appointment_id],[medicine_name])
go

-- Table equipment

CREATE TABLE [equipment]
(
 [equipment_name] Varchar(20) NOT NULL,
 [maintenance_due_date] Date NOT NULL,
 [warranty_end_date] Date NOT NULL,
 [purchase_date] Date NOT NULL,
 [equipment_id] Int NOT NULL,
 [room_number] Varchar(6) NULL
)
go

-- Create indexes for table equipment

CREATE INDEX [IX_Relationship25] ON [equipment] ([room_number])
go

-- Add keys for table equipment

ALTER TABLE [equipment] ADD CONSTRAINT [PK_equipment] PRIMARY KEY ([equipment_id])
go

-- Table insurance

CREATE TABLE [insurance]
(
 [policy_number] Varchar(25) NOT NULL,
 [provider] Varchar(40) NOT NULL,
 [max_coverage] Money NOT NULL,
 [patient_id] Int NOT NULL,
 [begin_date] Date NOT NULL,
 [end_date] Date NOT NULL,
 [subscriber] Varchar(20) NOT NULL
)
go

--constraints on insurance table
ALTER TABLE [insurance]  WITH CHECK ADD CHECK  (([end_date] > [begin_date]))
-- Create indexes for table insurance

CREATE INDEX [IX_Relationship12] ON [insurance] ([patient_id])
go

-- Add keys for table insurance

ALTER TABLE [insurance] ADD CONSTRAINT [PK_insurance] PRIMARY KEY ([policy_number])
go

-- Table dentist_license

CREATE TABLE [dentist_license]
(
 [license_number] Varchar(50) NOT NULL,
 [authority] Varchar(50) NOT NULL,
 [expiry_date] Date NOT NULL,
 [dentist_id] Int NOT NULL
)
go

-- Add keys for table dentist_license

ALTER TABLE [dentist_license] ADD CONSTRAINT [PK_dentist_license] PRIMARY KEY ([license_number],[dentist_id])
go

-- Table location_catalouge

CREATE TABLE [location_catalouge]
(
 [zip_code] Char(5) NOT NULL,
 [city] Varchar(20) NOT NULL,
 [county] Varchar(50) NOT NULL
)
go

-- Add keys for table location_catalouge

ALTER TABLE [location_catalouge] ADD CONSTRAINT [PK_location_catalouge] PRIMARY KEY ([zip_code])
go

-- Table specialization

CREATE TABLE [specialization]
(
 [specialization] Varchar(20) NOT NULL,
 [dentist_id] Int NOT NULL
)
go

ALTER TABLE [location_catalouge]  WITH CHECK ADD CHECK  (([zip_code] like '[0-9][0-9][0-9][0-9][0-9]'))
ALTER TABLE [location_catalouge]  WITH CHECK ADD CHECK  (([city] like '[A-Z]%'))
ALTER TABLE[location_catalouge]  WITH CHECK ADD CHECK  (([county] like '[A-Z]%'))


-- Add keys for table specialization

ALTER TABLE [specialization] ADD CONSTRAINT [PK_specialization] PRIMARY KEY ([dentist_id])
go

-- Table tooth

CREATE TABLE [tooth]
(
 [tooth_id] Char(4) NOT NULL,
 [tooth_type] Varchar(20) NULL,
 [treatment_description] Varchar(max) NULL,
 [patient_id] Int NULL
)
go

-- Create indexes for table tooth

CREATE INDEX [IX_Relationship2] ON [tooth] ([patient_id])
go

-- Add keys for table tooth

ALTER TABLE [tooth] ADD CONSTRAINT [PK_tooth] PRIMARY KEY ([tooth_id])
go

-- Table dentist_schedule

CREATE TABLE [dentist_schedule]
(
 [slot1] Bit NOT NULL,
 [slot2] Bit NOT NULL,
 [slot3] Bit NOT NULL,
 [slot4] Bit NOT NULL,
 [slot5] Bit NOT NULL,
 [slot6] Bit NOT NULL,
 [slot7] Bit NOT NULL,
 [slot8] Bit NOT NULL,
 [date] Date NOT NULL,
 [dentist_id] Int NOT NULL
)
go

-- Add keys for table dentist_schedule

ALTER TABLE [dentist_schedule] ADD CONSTRAINT [PK_dentist_schedule] PRIMARY KEY ([dentist_id])
go

-- Table login

CREATE TABLE [login]
(
 [username] Varchar(30) NOT NULL,
 [password] Varchar(20) NOT NULL,
 [role] Varchar(20) NOT NULL,
 [dentist_id] Int NULL
)
go

-- Create indexes for table login

CREATE INDEX [IX_Relationship4] ON [login] ([dentist_id])
go

-- Add keys for table login

ALTER TABLE [login] ADD CONSTRAINT [PK_login] PRIMARY KEY ([username])
go

-- Create foreign keys (relationships) section ------------------------------------------------- 


ALTER TABLE [tooth] ADD CONSTRAINT [patient can have a tooth] FOREIGN KEY ([patient_id]) REFERENCES [patient] ([patient_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [treatment] ADD CONSTRAINT [treatment reference from reatment_catalog] FOREIGN KEY ([treatment_name]) REFERENCES [treatment_catalog] ([treatment_name]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [supplies] ADD CONSTRAINT [location has supplies] FOREIGN KEY ([location_id]) REFERENCES [location] ([location_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [equipment] ADD CONSTRAINT [room has equipment(s)] FOREIGN KEY ([room_number]) REFERENCES [room] ([room_number]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [appointment] ADD CONSTRAINT [appointment takes place in a room] FOREIGN KEY ([room_number]) REFERENCES [room] ([room_number]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [specialization] ADD CONSTRAINT [dentist has specialization(s)] FOREIGN KEY ([dentist_id]) REFERENCES [dentist] ([dentist_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [prescription] ADD CONSTRAINT [appointment has a prescription] FOREIGN KEY ([appointment_id]) REFERENCES [appointment] ([appointment_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [dentist_license] ADD CONSTRAINT [dentist has license(s)] FOREIGN KEY ([dentist_id]) REFERENCES [dentist] ([dentist_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [treatment] ADD CONSTRAINT [app can have treatment(s)] FOREIGN KEY ([appointment_id]) REFERENCES [appointment] ([appointment_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go

-- Creating view

CREATE VIEW [dbo].[Supplies Availability] AS
SELECT supplies_name, no_of_units
FROM supplies
WHERE no_of_units < = 15
GO

ALTER TABLE [insurance] ADD CONSTRAINT [patient has insurance(s)] FOREIGN KEY ([patient_id]) REFERENCES [patient] ([patient_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [Invoice] ADD CONSTRAINT [each app generates invoice(s)] FOREIGN KEY ([appointment_id]) REFERENCES [appointment] ([appointment_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [appointment] ADD CONSTRAINT [patient can have many appointment(s)] FOREIGN KEY ([patient_id]) REFERENCES [patient] ([patient_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [medical_history] ADD CONSTRAINT [each patient has a medical history] FOREIGN KEY ([patient_id]) REFERENCES [patient] ([patient_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [login] ADD CONSTRAINT [every dentist will have a login] FOREIGN KEY ([dentist_id]) REFERENCES [dentist] ([dentist_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [location] ADD CONSTRAINT [one location reference can have multiple locations] FOREIGN KEY ([zip_code]) REFERENCES [location_catalouge] ([zip_code]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [dentist_schedule] ADD CONSTRAINT [every dentist will have schedule] FOREIGN KEY ([dentist_id]) REFERENCES [dentist] ([dentist_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [appointment] ADD CONSTRAINT [one dentist will have many appointments] FOREIGN KEY ([dentist_id]) REFERENCES [dentist] ([dentist_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go



ALTER TABLE [room] ADD CONSTRAINT [one location can have multiple rooms] FOREIGN KEY ([location_id]) REFERENCES [location] ([location_id]) ON UPDATE NO ACTION ON DELETE NO ACTION
go





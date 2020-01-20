 CREATE PROCEDURE  dbo.insertAppointmentRow   
 @patient_id Int,
 @status Varchar(20) ,
 @room_number Smallint ,
 @date Date ,
 @start_time Time ,
 @end_time Time ,
 @dentist_id Int 
AS   
	IF     @patient_id IS NULL OR @status IS NULL OR @room_number IS NULL 
	     OR @date IS NULL OR @start_time IS NULL OR @end_time IS NULL OR @dentist_id IS NULL
		  raiserror ('Values cannot be null' , 16 , 16)
   
	ELSE
			INSERT INTO dbo.appointment( patient_id,status,room_number,date,start_time,end_time,dentist_id) 
			VALUES (    
		             @patient_id ,
					 @status ,
					 @room_number  ,
					 @date ,
					 @start_time ,
					 @end_time ,
					 @dentist_id )
GO  
------------------------------------------------------------------------------------------------------------------------------
CREATE PROCEDURE  dbo.insertToothRow   
 @appointment_id Int ,
 @tooth_id char(4),
 @tooth_type Varchar(20) ,
 @treatment_description varchar(max) 

AS   
	IF    @appointment_id IS NULL OR @tooth_id IS NULL OR @tooth_type IS NULL
		  raiserror ('Values cannot be null' , 16 , 16)
   
	ELSE					 
			INSERT INTO [dbo].[tooth]
           ([tooth_id]
           ,[tooth_type]
           ,[treatment_description]
           ,[patient_id])
     VALUES
           (@tooth_id, @tooth_type , @treatment_description , 
           (select patient_id from appointment where appointment_id = @appointment_id) )
GO



-----------------------------------------------------------------------------------------------------------------------------

CREATE PROCEDURE dbo.send_mail_db
       @profile_name2 varchar(max), 
       @recipients2 varchar(max), 
       @body2 varchar(max), 
       @subject2 varchar(max) 
     AS 
     BEGIN   
         EXEC msdb.dbo.sp_send_dbmail  
			 @profile_name = @profile_name2,  
			 @recipients = @recipients2,  
			 @body =  @body2,  
             @subject = @subject2 ;
       END
-----------------------------------------------------------------------------------------------------------------------------	   
	   
CREATE TRIGGER trg_dentist_schedule
ON appointment
AFTER INSERT
AS
BEGIN
DECLARE @dentist int
DECLARE @dateInserted Date
DECLARE @Slot varchar(20)
DECLARE @start_time time(7)
SELECT  @dentist = dentist_id FROM inserted
SELECT  @dateInserted = date FROM inserted
SELECT  @start_time = start_time FROM inserted


IF @start_time = '10:00:00.0000000'
    UPDATE dentist_schedule
	SET Slot1= 1
	where date = @dateInserted AND
	dentist_id =@dentist
ELSE IF @start_time = '10:30:00.0000000'
    UPDATE dentist_schedule
	SET Slot2= 1
	where date = @dateInserted AND
	dentist_id =@dentist	
ELSE IF @start_time = '11:00:00.0000000'
    UPDATE dentist_schedule
	SET Slot3= 1
	where date = @dateInserted AND
	dentist_id =@dentist	
ELSE IF @start_time = '11:30:00.0000000'
    UPDATE dentist_schedule
	SET Slot4= 1
	where date = @dateInserted AND
	dentist_id =@dentist	
ELSE IF @start_time = '13:00:00.0000000'
    UPDATE dentist_schedule
	SET Slot5= 1
	where date = @dateInserted AND
	dentist_id =@dentist	
ELSE IF @start_time = '13:30:00.0000000'
    UPDATE dentist_schedule
	SET Slot6= 1
	where date = @dateInserted AND
	dentist_id =@dentist
ELSE IF @start_time = '14:00:00.0000000'
    UPDATE dentist_schedule
	SET Slot7= 1
	where date = @dateInserted AND
	dentist_id =@dentist
ELSE IF @start_time = '14:30:00.0000000'
    UPDATE dentist_schedule
	SET Slot8= 1
	where date = @dateInserted AND
	dentist_id =@dentist

END

---------------------------------------------------------------------------------------------------------------------------
CREATE FUNCTION getDentistPatient (@appointment_id int)
RETURNS TABLE
AS
RETURN 
(

select b.first_name + ' ' + b.last_name as 'Dentist'  , b.contact_number , c.first_name + ' ' + c.last_name as 'Patient'  , c.contact_number as 'Con'
from appointment a
join dentist b ON
a.dentist_id = b.dentist_id
join patient c ON
a.dentist_id = b.dentist_id
where a.appointment_id = @appointment_id

);
GO

---------------------------------------------------------------------------------------------------------------------------

CREATE PROCEDURE  dbo.insertInvoiceRow   
 @appointment_id Int

AS   
	IF     @appointment_id IS NULL 
		  raiserror ('Values cannot be null' , 16 , 16)
   
	ELSE
			INSERT INTO dbo.invoice( date,amount , appointment_id) 
			VALUES (GETDATE(),
			       ( select sum(treatment_cost) from treatment where appointment_id = @appointment_id) ,
					 @appointment_id
		              )
GO  

---------------------------------------------------------------------------------------------------------------------------



CREATE DATABASE db_persons
GO
USE db_persons
GO

CREATE TABLE [dbo].[person_types]
(
    [id] INT NOT NULL IDENTITY (1, 1),
    [name] NVARCHAR(50) NOT NULL,
    CONSTRAINT [pk_person_types] PRIMARY KEY CLUSTERED ([id])
)
GO

CREATE PROCEDURE [dbo].[sp_select_per_types]
    @create_by NVARCHAR(25),
    @ip NVARCHAR(15),
    @result INT OUTPUT
AS
    SELECT  [id],
            [name]
    FROM [dbo].[person_types]

    SET @result = 1;
RETURN 0
GO

IF NOT EXISTS (SELECT 1 FROM [person_types] WHERE [name] = 'Normal')
BEGIN
    INSERT INTO [person_types] ([name])
    VALUES ('Normal');
END 

DECLARE @type INT;
SET @type = (SELECT [id] FROM [person_types] WHERE [name] = 'Normal');

IF NOT EXISTS (SELECT 1 FROM [persons] WHERE [ssn] = '48645331')
BEGIN
    INSERT INTO [persons] ([ssn], [name], [type], [born], [state])
    VALUES ('48645331', 'Mario Gaviria', @type, GETDATE(), 1);
END 

IF NOT EXISTS (SELECT 1 FROM [persons] WHERE [ssn] = '234253454')
BEGIN
    INSERT INTO [persons] ([ssn], [Name], [type], [born], [state])
    VALUES ('234253454', 'Martha Estrada', @type, GETDATE(), 1);
END 
GO
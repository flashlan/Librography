@echo off

copy ..\dist\Librography.jar C:\Librography\
copy Librography.bat C:\Librography\

7z.exe  a -tZip ../setup/data C:\Librography\

rename data.zip data
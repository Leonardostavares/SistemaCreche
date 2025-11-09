@echo off
echo Iniciando Sistema de Creche...
echo.
echo Compilando projeto...
call mvnw.cmd clean compile
if %errorlevel% neq 0 (
    echo Erro na compilacao!
    pause
    exit /b 1
)
echo.
echo Iniciando aplicacao (Backend + Frontend integrados)...
call mvnw.cmd spring-boot:run
pause
@REM
@REM Spore
@REM

@if "%WRAPPER_DEBUG%" == "" @echo off

if "%OS%"=="Windows_NT" goto begin
echo Unsupported Windows version: %OS%

goto :eof

:begin
setlocal enableextensions

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.\

set DIST_BITS=32
if "%PROCESSOR_ARCHITECTURE%" == "AMD64" goto amd64
if not "%ProgramW6432%" == "" set DIST_BITS=64
goto pickwrapper

:amd64
set DIST_BITS=64

:pickwrapper
set WRAPPER_EXE=%DIRNAME%jsw\windows-x86-%DIST_BITS%\xmap-web.exe
if exist "%WRAPPER_EXE%" goto pickconfig
echo Missing wrapper executable: %WRAPPER_EXE%

goto end

:pickconfig
set WRAPPER_CONF=%DIRNAME%\jsw\conf\wrapper.conf
if exist "%WRAPPER_CONF%" goto execute
echo Missing wrapper config: %WRAPPER_CONF%

goto end

:execute
for /F %%v in ('echo %1^|findstr "^console$ ^start$ ^stop$ ^restart$ ^install$ ^uninstall"') do call :exec set COMMAND=%%v

if "%COMMAND%" == "" (
    echo Usage: %0 { console : start : stop : restart : install : uninstall }
    
    goto end
) else (
    shift
)

call :%COMMAND%
goto end

:console
"%WRAPPER_EXE%" -c "%WRAPPER_CONF%"
goto :eof

:start
"%WRAPPER_EXE%" -t "%WRAPPER_CONF%"
goto :eof

:stop
"%WRAPPER_EXE%" -p "%WRAPPER_CONF%"
goto :eof

:install
"%WRAPPER_EXE%" -i "%WRAPPER_CONF%"
goto :eof

:uninstall
call :stop
"%WRAPPER_EXE%" -r "%WRAPPER_CONF%"
goto :eof

:restart
call :stop
call :start
goto :eof

:exec
%*
goto :eof

:end
endlocal

:finish
cmd /C exit /B %ERRORLEVEL%
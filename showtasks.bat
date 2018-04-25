call runcrud
if "%ERRORLEVEL%" == "0" goto startweb
echo There were errors
goto end

:startweb
sleep 5
start http://localhost:8080/crud/v1/task/getTasks

:end
echo.
echo Work is finished.
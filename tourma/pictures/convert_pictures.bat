


call:convertPictures avatar
call:convertPictures flags

echo.&pause&goto:eof

:convertPictures
pushd %~1
for /R %%f in (*.*) do (

            echo %%~nf
			convert "%%f" -resize 96x96 "../../src/tourma/images/%~1/%%~nf.png"
    )
popd
goto:eof
	

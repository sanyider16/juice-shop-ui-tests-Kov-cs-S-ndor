# OWASP Juice Shop - Automatizálst felületi tesztek

## A projekt célja

Az OWASP Juice Shop webalkalmazás funkcióinak ellenőrzése Java és Selenium WebDriver segítsségével.

## Használt eszközök

-Java21
-Maven
-Selenium WebDriver
-Junit
-Google Chrome
-Eclipse IDE

## Tesztelt alkalmazás

https://demo.owasp-juice.shop/

A tesztek nyilvános, megosztott demókörnyezetet használnak.
Ennek elérhetősége és változó állapota befolyásolhatja a futásokat.

## Tesztelési területek

-Regisztráció
-Bejelentkezés és kijelentkezés
-Adatkezelési nyilatkozatok megtekintése
-Termékek listázása és lapozás
Cím hozzáadása, módosítása, törlése
-Több cím rögzítése CSV-adatforrásból
-Vásárlási folyamatok
-Fiókadatok exportálása és fájlba mentése

## Futattás Eclipse-ben

Egy tesztosztályon jobb katintás
Run As ->Junit Test

## Futtatás Maven segítségével

A projekt gyökérkönyvtárban:

mvn test

## Tesztadatok és eredmények

A sorozatos adatforrása:
src/test/resources/addresses.csv

A mentet fiókexportok helye:
target/export

 A Maven teszteredmények helye:
 target/surefire-reports
 
 ## Ismert problémák
 
 -A demókörnyezet időnként Application Error hibát ad.
 -Egyes futásoknél a terméklista üresen jelenik meg.
 -A rendelési PDF megnyitása kézi ellenőrzéskor 404/ EnOENT hibával végződik.
 

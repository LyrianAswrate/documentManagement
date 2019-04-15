# Dokumentum Kezelő és Iktató Rendszer
|DFAL-INF-630| Informatikai projektvezetés és gyakorlat  - Beadandó címe: Dokumentum Kezelő és Iktató Rendszer

Alkalmazás szerkezete (csak modulok):
* document-management-service
* document-management-ui
* document-management-web
* document-management-widgetset

**Fontos! "document-management-widgetset" csak és csakis akkor szerkeszd a bene lévő fájlokat ha tudod mit csinálsz.**

**Build/futatás menete:**

1. Eclipse vagy NetBean vagy bármilyen más IDE-ban ha van telepített maven pluginod akkor futtatsz rá egy "***clean install***" "***goal***"-t.
2. Ha lefut sikeresen akkor a ***document-management-web/target*** mappában található egy jar azt másold be a ***runtime*** mappába. 
3. Windows-on futtatásra ott a ***run.bat***. Linuxon is hasonlóképen kell indítani parancssorból mint a ***bat***-ban van leírva.

**Adatbázis táblák létrehozása:**

1. A "***eclipse run configs***" mappában található *run Konfigurációkat* importáljuk be az "***eclipse***"-be.
2. A "***document-management-service"*** tartalmaza az adatbázis scriptett.
3. A DB konfigurációt a "***document-management-service"*** *pom.xml*-je tartalmazza a "***flyway***" számára.
4. Az alkalmazás DB konfigurációja a ***document-management-web/src/main/resources/properties/application.properties***-ben található meg.
5. A beimportáltak közül a "***clean***" és "***migrate***" névvel ellátottakat kell lefuttatni hogy az adatbázisba a táblák létrejöjjenek ("***flyway***").
6. A ***clean*** törli a adatbázis tartalmat. A ***migrate*** létrehozza a sémához tartozó verzió táblát és lefuttatja a adatbázis szkripteket.

**Alkalmazás elérése**

Lokális elérése az alkalmazásnak: http://localhost:8444/main

**Felhasználók**

Az alkalmazásban új felhasználót nem lehet regisztrálni a bejelentkezés képernyőn.
Csak az admin tud definiálni új felhasználókat miután bejelentkezett.

Admin felhasználó név: **admin**

Admin jelszó: **admin**

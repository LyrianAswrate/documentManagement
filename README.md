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

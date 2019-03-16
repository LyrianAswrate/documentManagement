# Projekt Alapító Dokumentum

## 1. Projekt alapadatai

### 1.1. Projekt neve

A projekt neve **Dokumentum Menedzsment és Iktató Rendszer** a BT Bt. számára.

### 1.2. Projekt célja

A projekt célja, hogy a vállalaton belül keletkezett dokumentumokat rendezetten tároljuk, beérkezés szerint iktassuk, kereshetővé és hozzáférhetővé, olvashatóvá és letölthetővé tegyük őket. 

Jelenleg a vállalat papír alapú és egyéb, nem strukturált formában végez dokumentumkezelést és iktatást, ezen kíván változatni a megvalósítandó szoftver.

## 2. Projekt tagok

X
Y
Z

## 3. Projektvezető kinevezése

A tagok aláírásukkal igazolják, hogy a projekt vezetésével X (cím, stb.) bízzuk meg. A kinevezett projektvezető aláírásával igazolja, hogy vállalja a projekt vezetésével, szervezésével kapcsolatos teendőket, amelyek:

- Feladatok kiosztása

- Feladatok számonkérése, határidők betartása és betartatása

- Feladatok elvégzésének koordinálása

- Kapcsolattartás a projekt tagjaival és a belső megrendelővel

## 4. Projekt scope kijelölése

### 4.1. Fő tevékenységek

- Igényfelmérés

- Tervezés

- Adatbázismodell elkészítése

- Fejlesztőkörnyezet kialakítása

- Implementáció

- Tesztelés

- Dokumentációkészítés
  - fejlesztői
  - felhasználói
  - telepítési és üzemeltetési
  - tesztelési
  - oktatási

- Felhasználók oktatása

### 4.2. Projekt scope-on kívüli feladatok

- Kezelendő dokumentumok körének meghatározása.

- Dokumentumok feltöltése/kezelése, az alkalmazás feltöltése.

- Rendszeres adatmentés

### 4.3. A projekt kapcsolatai más projektekkel

Önálló projekt, nincs kapcsolatban a vállalaton belüli más, informatikai projektekkel.

## 5. Projekt legfontosabb kritériumai

- Az alkalmazás WEB alkalmazásként kerüljön kifejlesztésre
- Az alkalmazás legyen képes ~10.000 dokumentum gyors és biztonságos kezelésére
- Az alkalmazás minimum 2 szintű felhasználó jogosultság kezelésére legyen képes (adminisztrátor, felhasználó)

## 6. Fontosabb üzleti határidők, mérföldkövek

### 6.1. Határidők, egyben mérföldkövek

|                                         |                   |
| --------------------------------------- | ----------------- |
| Projekt indulás                         | 2019. március 4.  |
| Igényfelmérés, rendszerterv elkészítése | 2019. március 18. |
| Fejlesztői környezet kialakítása        | 2019. április 1.  |
| Fejlesztés                              | 2019. április 22. |
| Tesztüzem                               | 2019. május 6.    |
| Éles bevezetés                          | 2019. május 20.   |

### 6.2. A projekt csúszás kockázatai

- Nem készült el időben az igényfelmérés.

- A tárgyi eszközök beszerzése csúszást szenved el, az éles bevezetés eltolódik.

### 6.3. Projekt várható befejezése

A projekt várható befejezése: 2019. május 20. Ez a dátum az alkalmazás élesbe állításának várható időpontja. Az utólagos támogatás más projekt keretein belül kerül megvalósításra.

## 7. Szervezeti felépítés

![szervezeti_felepites](/szervezeti_felepites.png)

## 8. Költségbecslés

| Típus                      | Egység | Mennyiség | Egységár/Ft. | Teljes összeg/Ft. |
| -------------------------- | :----- | --------: | -----------: | ----------------: |
|                            |        |           |              |                   |
| **Materiális költségek**   |        |           |              |                   |
| Eszközök                   |        |           |              |                   |
| Számítógépek               | darab  |         3 |       300000 |           1500000 |
| Switch                     | darab  |         1 |       230000 |            230000 |
| Nyomtató                   | darab  |         1 |        45000 |             45000 |
| Szerver                    | darab  |         1 |      1500000 |           1500000 |
| Kellékanyagok              |        |           |              |                   |
| Nyomtatópapír              | csomag |         6 |         1800 |             10800 |
| Toner                      | darab  |         2 |        12000 |             24000 |
| **Immateriális költségek** |        |           |              |                   |
| Bérek                      |        |           |              |                   |
| X                          | nap    |        56 |        28000 |           1568000 |
| Y                          | nap    |        56 |        28000 |           1568000 |
| Z                          | nap    |        56 |        25000 |           1400000 |
| Oktatás                    |        |           |              |                   |
| Terembérlet                | nap    |        12 |        16000 |            192000 |
| Projektor bérlés           | nap    |        12 |         3500 |             42000 |
| **Összköltség**            |        |           |              |           8079800 |

## 9. Kompetenciamátrix

|      |  K1  |  K2  |  K3  |  K4  |  K5  |  K6  |
| ---- | :--: | :--: | :--: | :--: | :--: | :--: |
| X    |  X   |      |      |      |  X   |  X   |
| Y    |      |  X   |  X   |  X   |  X   |      |
| Z    |      |  X   |      |      |  X   |  X   |

|        | Jelmagyarázat                         |
| ------ | ------------------------------------- |
| **K1** | Igényfelmérés, tervezés, dokumentálás |
| **K2** | Adatbázis kialakítás                  |
| **K3** | Fejlesztő környezet kialakítás        |
| **K4** | Szoftverfejlesztés, hibajavítás       |
| **K5** | Tesztelés                             |
| **K6** | Oktatás                               |

## 10. Fejlesztési módszertan és kommunikáció

### 10.1. Fejlesztési módszertan

Mivel alapvetően földrajzilag a csapattagok távol helyezkednek el egymástól, ezért a modern technika minden vívmányát kihasználjuk az egyeztetésre, email, telefon, Discord, Facebook. A fejlesztés az Agilis módszertan szerint megy, rövid, pár napos, sprintek formájában. Az elvégzendő feladatokat GitHubon tároljuk és menedzseljük. A forráskódokat Git verziókezelővel menedzseljük.

### 10.2. Projekt információáramlás

A projekttagok egymással a Scrum/Kanban módszertan szerint tartják a kapcsolatot, rendszeres időközönkénti rövid beszámolóval. A projekt szponzorok felé a projektvezető ta rtozik elszámolással, eseti személyes beszámolóval, illetve a sprintek végén rövid, szöveges beszámoló formájában az előmenetelről.

### 10.3. Kommunikációs terv

|                     | Információigény                         | Gyakoriság | Kommunikáció módja | Válasz  |
| ------------------- | --------------------------------------- | ---------- | ------------------ | ------- |
| Szponzor/Megrendelő | Mérfödkövek                             | Hetente    | Beszámoló          | 2 nap   |
| Projektvezető       | Folyamat                                | Hetente    | Jelentés           | 2 nap   |
| DBA                 | Adatbázisterv, módosítások, tesztadatok | Hetente    | Jelentés           | 1 nap   |
| Devops              | Infrastruktúrális változások            | Hetente    | Jelentés           | 1 nap   |
| Szoftverfejlesztő   | Szoftverfunkciók                        | Hetente    | Jelentés           | 1 nap   |
| Gyűlések            | Előrehaladás                            | 2 hetente  | Jelentés           | Azonnal |
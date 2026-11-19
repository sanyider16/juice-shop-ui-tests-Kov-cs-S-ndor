A felhasználói felület fő funkcióinak ellenőrzése
automatizált tesztekkel.

A részletes teszteseteket a tesztesetek.csv tartalmazza.

## Tesztkörnyezet

- Java 21
- Maven, Selenium WebDriver, JUnit
- Google Chrome
- Windows, Eclipse IDE
- Nyilvános demó: https://demo.owasp-juice.shop/

## Eredmények

A legutóbb dokumentált teljes futásban:
- Lefutott tesztek: 12
- Sikeres tesztek: 11
- Hibával megállt tesztek: 1
- Assertion miatti sikertelen tesztek: 0

A  hibával megállt teszt:




## Tapasztalt problémák

- A demókörnyezet időnként Application Error hibát ad.
- A terméklista egyes futásoknál üresen jelenik meg.
- Értesítési sávok időnként eltakarják a kattintás helyét.
- A rendelési PDF megnyitása kézi ellenőrzéskor
  404 / ENOENT hibával végződött.


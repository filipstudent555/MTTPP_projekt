## Što je testirano
Testira se javna demo web aplikacija: https://www.saucedemo.com/

## Korišteni alati i tehnologije
- Java 17
- Maven
- Selenium WebDriver (4.40.0)
- TestNG (7.12.0)
- WebDriverManager (6.3.3)
- Maven Surefire Plugin (3.5.2)

## Struktura projekta
- `src/test/java/com/filip/base/BaseTest.java`  
  - zajednički setup/teardown (otvaranje i zatvaranje preglednika prije/poslije svakog testa)
- `src/test/java/com/filip/pages/*`  
  - Page Object Model klase (LoginPage, InventoryPage, CartPage, CheckoutStepOnePage)
- `src/test/java/com/filip/tests/SauceDemoTests.java`  
  - testni slučajevi
- `testng.xml`  
  - TestNG konfiguracija

## Testni slučajevi (5)
1. **validLogin**  
   - provjera uspješnog logina
2. **invalidPassword**  
   - provjera error poruke kod pogrešne lozinke
3. **lockedOutUser**  
   - provjera blokiranog (locked) korisnika
4. **addToCart**  
   - dodavanje proizvoda u košaricu i provjera badge-a "1"
5. **checkoutWithoutFirstName**  
   - checkout bez obaveznog polja "First Name" i provjera validacijske poruke
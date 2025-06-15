# Rezervačný Systém pre Zasadačky

Tento projekt je RESTful webová aplikácia vytvorená v rámci školského zadania. Aplikácia slúži ako backend pre systém na správu a rezerváciu zasadacích miestností vo firme. Umožňuje používateľom vytvárať, spravovať a zobrazovať rezervácie, spravovať miestnosti, ich vybavenie a používateľské účty.

Celé API je navrhnuté tak, aby bolo logické, škálovateľné a ľahko použiteľné pre potenciálnu klientskú (frontend) aplikáciu.

## Kľúčové Funkcie

* **Správa Používateľov:** Kompletné CRUD operácie (vytvorenie, čítanie, úprava, mazanie) pre používateľov systému.
* **Správa Miestností:** CRUD operácie pre zasadacie miestnosti, vrátane ich kapacity, lokácie a stavu (aktívna/neaktívna).
* **Správa Vybavenia:** Samostatná správa vybavenia (projektory, tabule, atď.), ktoré môže byť následne priradené ku konkrétnym miestnostiam.
* **Tvorba a Správa Rezervácií:** Komplexná logika pre vytváranie a úpravu rezervácií s definovaným časom, účelom, organizátorom a zoznamom účastníkov.
* **Inteligentná Správa Účastníkov:** Počet účastníkov v rezervácii sa počíta automaticky a systém bráni duplicitnému pridaniu organizátora medzi účastníkov.
* **API Dokumentácia:** Automaticky generovaná a interaktívna dokumentácia všetkých endpointov pomocou SpringDoc OpenAPI (Swagger).

## Použité Technológie

* **Java 17**
* **Spring Boot 3.2.2**
    * Spring Web
    * Spring Data JPA
    * Spring Boot Starter Validation
* **Hibernate** (ako JPA provider)
* **SQLite** (pre lokálnu databázu)
* **Maven** (pre správu závislostí a build)
* **SpringDoc OpenAPI** (pre generovanie Swagger UI)
* **Lombok** (pre zjednodušenie kódu)

## API Dokumentácia (Swagger UI)

Interaktívna dokumentácia všetkých dostupných API endpointov je generovaná automaticky a je dostupná po spustení aplikácie.

* **Lokálne:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
* **Nasadená verzia:** https://java-hibernate.onrender.com/swagger-ui/index.html#

## Ako Spustiť Aplikáciu Lokálne

Pre spustenie projektu na vašom počítači postupujte podľa nasledujúcich krokov.

### Požiadavky
* Java Development Kit (JDK) - verzia 17 alebo novšia
* Apache Maven

### Inštalácia a Spustenie
1.  Naklonujte si tento repozitár:
    ```bash
    git clone https://github.com/norko0722/Java-Hibernate
    ```
2.  Prejdite do adresára projektu:
    ```bash
    cd java-hibernate
    ```
3.  Spustite aplikáciu pomocou Mavenu:
    ```bash
    mvn spring-boot:run
    ```
4.  Aplikácia bude bežať na adrese [http://localhost:8080](http://localhost:8080). Pri prvom spustení sa v koreňovom adresári automaticky vytvorí databázový súbor `meeting_reservation.db` a naplní sa počiatočnými dátami.

## Nasadená Aplikácia (Deployment)

Aplikácia je nasadená a verejne dostupná na nasledujúcej URL adrese:

**URL Aplikácie:** https://java-hibernate.onrender.com/

## Autor

* **[Norbert Balucha]**

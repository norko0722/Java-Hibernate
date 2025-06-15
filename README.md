# Meeting Reservation System

## Popis projektu
Meeting Reservation System je webová aplikácia pre správu a rezerváciu zasedacích miestností. Umožňuje používateľom rezervovať miestnosti, spravovať vybavenie a organizovať stretnutia.

### Hlavné funkcie
- Správa zasedacích miestností (pridávanie, úprava, mazanie)
- Rezervácia miestností s možnosťou výberu dátumu a času
- Správa používateľov a ich rolí
- Správa vybavenia miestností
- Prehľad rezervácií a histórie

### Technické požiadavky
- Java 17
- Spring Boot 3.2.2
- Hibernate
- PostgreSQL
- Maven

### Spustenie aplikácie
1. Naklonujte repozitár
2. Nastavte databázu v `application.properties`
3. Spustite aplikáciu príkazom: `mvn spring-boot:run`
4. Aplikácia bude dostupná na: `http://localhost:8080`

### API Endpointy
1. Správa miestností
   - GET /api/rooms - zoznam všetkých miestností
   - POST /api/rooms - vytvorenie novej miestnosti
   - PUT /api/rooms/{id} - úprava miestnosti
   - GET /api/rooms/{id} - detail miestnosti

2. Správa rezervácií
   - GET /api/reservations - zoznam rezervácií
   - POST /api/reservations - vytvorenie rezervácie
   - PUT /api/reservations/{id} - úprava rezervácie
   - GET /api/reservations/{id} - detail rezervácie

3. Správa používateľov
   - GET /api/users - zoznam používateľov
   - POST /api/users - registrácia používateľa
   - PUT /api/users/{id} - úprava používateľa

4. Správa vybavenia
   - GET /api/equipment - zoznam vybavenia
   - POST /api/equipment - pridanie vybavenia
   - PUT /api/equipment/{id} - úprava vybavenia

### Databázový model
Aplikácia používa nasledujúce entity:
- MeetingRoom - zasedacie miestnosti
- Reservation - rezervácie
- User - používatelia
- Equipment - vybavenie miestností

### Dokumentácia API
API dokumentácia je dostupná na: `http://localhost:8080/swagger-ui.html`

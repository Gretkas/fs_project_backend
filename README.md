# fs_project_backend

## Installasjon
Tatt fra systemutvikling 2 prosjekt, da installasjon vil fungere på samme måte 
Du trenger:

Git-klient

Et Java-støttet IDE eller Maven installert

JDK 11

Det er enda ikke satt opp noe konfigurasjon for å bygge en JAR-fil for serveren. Instruksjonene gjelder derfor kjøring av backend gjennom en IDE (for eksempel IntelliJ) eller via kommandolinje dersom man har Maven:

/* I terminalen */
```
git clone https://github.com/Gretkas/fs_project_backend.git
cd fs_project_backend
git checkout master
git pull
mvn spring-boot:run
```

Dersom applikasjonen skal kjøres via IDE, kjør “GiddApplication”

## Sikkerhet

Tatt fra systemutvikling 2 prosjekt, da sikkerhet er implementert på samme måte 
Teamet har implementert flere måter å sikre applikasjonen på, både i Backend og Frontend. 

### SQL-injections:

SQL injections er tatt hensyn til ved hjelp av PreparedStatement. PreparedStatements er gjenbrukbare og parameteriserte, og sørger for at queries og inndata fra bruker blir håndtert sikkert.  

### XSS:

(Cross-Site Scripting) håndteres av React, da det er et ganske trygt bibliotek “by design”. Stringvariabler i Views blir escapet automatisk. Med JSX bruker man i tillegg funksjoner som event handlers istedenfor stringer som kan inneholde kode. Dette unngår ikke problemet helt, men i praksis gjør det XSS-angrep mye mindre sannsynlig. 

### Sensitive Data Exposure:

Sensitive Data Exposure håndteres ved å alltid sende den minste mengden informasjon frontenden trenger for å fungere. En bruker som sendes til frontenden inneholder da ikke sensitiv informasjon som passord. 

### Spring Security:

Produktet implementerer Spring Security-modulen. Denne benyttes for å autentisere brukere ved hjelp av legitimasjon og sesjoner. Sesjonscookien vil dermed legges til i videre forespørsler til APIet for å forsikre at brukeren er autentisert. I tillegg brukes Spring Security til å kontrollere tilgangen brukere har til å hente ressurser. Dette tillater oss å konfigurere backenden slik at mesteparten av APIet ikke er tilgjengelig for en bruker som enda ikke er logget inn. Innloggete brukere har heller ikke lov til å se eller manipulere ressurser som ikke er nødvendig for at applikasjonen deres skal fungere. 

Spring Security brukes også for autorisasjon. I dette prosjektet er de 2 nivåene USER og ADMIN. ADMIN har tilgang til alt, USER har begrenset tilgang til bare det som er relevant for å bruke applikasjonen som en bruker.

### Passord Hashing:

Passordene til brukere blir hashet med BCrypt. Dette gjør at passordene til brukerne ikke trengs å lagres i databasen, og heller bare en hashet versjon blir det.

### Videre arbeid på sikkerhet:

Selv om applikasjonen allerede dekker sikkerhetskravene for prosjektet(Punkt 1 og 3 i OWASP top 10, SQL Injections og Sensitive Data Exposure)Er dette fortsatt ikke en komplett løsning, da blant annet CSRF protection fortsatt ikke er implementert i backenden vår. Dette betyr at session cookien i prinsippet kan bli stjålet av en angriper. TLS er heller ikke implementert, noe som betyr at kommunikasjon mellom klient og server ikke krypteres.

## CI


CI Pipeline kjører Maven Verify. Maven Verify kjører alle tester som finnes i prosjektet.


### Sikkerhetstester:

Sikkerhetsmodulen i prosjektet har to tester som begge kobler seg til APIet. Her verifiseres det at en ikke-innlogget bruker ikke får tilgang, og motsatt.


### Service & Controller:

Testes ved hjelp av integrasjonstester. Metodene for laget under blir mocket. Deretter sjekkes det at returverdien er lik verdien vi forventer å få tilbake.

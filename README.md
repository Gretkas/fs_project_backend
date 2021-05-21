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
mvn spring-boot:run
```

Dersom applikasjonen skal kjøres via IDE, kjør “GiddApplication”

Applikasjonen kjører på localhost:8080

## Docker

som en del av byggefasen i CICD pipeline blir prosjektet pakket som en dockercontainer og publisert på sigmundgranaas/fs_backend
Applikasjonen kjører ved default på port 8080, og har CORS-instillinger for frontend på http://localhost:3000

```
docker run -it sigmundgranaas/fs_backend
```

## Api

### users
Bak /users finnes det endepunkter som omhandler henting, oppretting, oppdatering og sletting av brukere. Bare admin har tilgang til disse endepunktene.

### reservations
Bak /reservations finnes det endepunkter som omhandler henting, oppretting oppdatering og sletting av reservasjon, et endepunkt for å se ledigheten av en viss type reservasjon med tanke på tid, endepunkter for å hente fremtidige eller fortidige reservasjoner basert på bruker, og et endepunkt for sortering, søk og paginering av reservasjoner.

### rooms
Bak /rooms finnes det endpunkter som omhandler henting, oppretting, oppdatering og sletting av rom. Her fins det også et endepunkt for søk og paginering av rom.
### auth
Bak /auth fins det et endepunkt for autentisering av bruker basert på cookie. Dette endepunktet vil returnere brukeren dersom autentisering går gjennom.

## Arkitektur

### Trelagsmodellen

![Untitled Diagram-Page-2](https://user-images.githubusercontent.com/46557903/119189648-985ee900-ba7c-11eb-9986-4ecd9646713e.png)

Systemet teamet har laget følger trelagsmodellen. En webfrontend(view) et REST-API(controller) og en database(Model). Disse er tre forskjellige logiske og fysiske lag. Frontend er brukerens inngang til applikasjonen, denne kan kun kommunisere med kontrolleren, som inneholder bussiness/domenelogikken for hvordan teamets tjeneste skal fungere. Controlleren vil se hva brukeren ber om og selv gjøre valg for hvilken data som skal hentes fra en eventuell database(eller andre datamedium) og hvordan den skal behandles. Denne dataen vil da bli formatert i henhold til hvilke regler controlleren er programmert til. Til slutt vil dette sendes tilbake til brukerens view, som er ansvarli
g for hvordan dataen blir presentert. (Fra systemutviklingsprosjekt)

![Untitled Diagram-Page-3](https://user-images.githubusercontent.com/46557903/119190125-394da400-ba7d-11eb-84ed-c4c5bdf2c445.png)
### Spring-arkitektur

Api-et er delt inn i tre lag. Dette er Presentasjonslaget (HTTP/REST-controller) Businesslaget (services/Userstories), persistence (Repo/DAO). Database er det siste laget, men ikke direkte en del av Spring. Kontrollere er ansvarlig for kommunikasjon gjennom HTTP. De vil bare kalle services for å håndtere kall til forskjellige endepunkter. Serviceklasser er ansvarlig for å utføre logikken knyttet til selve funkjsonaliteten ti produktet. Servicelaget kaller også et CrudRepository som abstraherer bort selve implementasjonen av et lagrinsmedium. 


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

Dette er fortsatt ikke en komplett løsning da blant annet CSRF protection fortsatt ikke er implementert i backenden vår. Dette betyr at session cookien i prinsippet kan bli stjålet av en angriper. TLS er heller ikke implementert, noe som betyr at kommunikasjon mellom klient og server ikke krypteres.

## CI


CI Pipeline kjører Maven Verify. Maven Verify kjører alle tester som finnes i prosjektet.


### Sikkerhetstester:

Sikkerhetsmodulen i prosjektet har to tester som begge kobler seg til APIet. Her verifiseres det at en ikke-innlogget bruker ikke får tilgang, og motsatt.


### Service:

Testes ved hjelp av integrasjonstester. Metodene for laget under blir mocket. Deretter sjekkes det at returverdien er lik verdien vi forventer å få tilbake.

## Videre arbeid

- Generalisere bruken av DTO-klasser
- Mer solid error-håndtering og bedre tilbakemeldinger til bruker
- Flere tester
- Utvide søk og filtrering for reservasjoner og rom, samt legge til søk og filtrering av brukere
- Ferdigstille produksjonsmiljø
- Statistikk for reservasjoner

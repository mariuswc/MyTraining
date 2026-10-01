# WorkOutApp

En treningsapp under utvikling, med backend i Kotlin og Spring Boot. Målet er å koble treningsdata i Google Sheets med videoer av øvelser.


## Teknologi

- Kotlin og Spring Boot
- Gradle med Kotlin DSL
- Google OAuth 2.0, Google Sheets og Google Drive
- JDK 24

## Kjør lokalt

Du trenger JDK 24 og en Google OAuth-klient for en webapplikasjon. Registrer denne redirect-adressen for klienten:

```text
http://localhost:8080/auth/google/callback
```

1. Åpne prosjektet i IntelliJ IDEA og importer det som et Gradle-prosjekt.
2. Åpne **Run → Edit Configurations** og velg Gradle-konfigurasjonen som kjører `bootRun`.
3. Legg inn følgende under **Environment variables**:

   | Variabel | Verdi |
   | --- | --- |
   | `CLIENT_ID` | Client ID fra Google OAuth-klienten |
   | `CLIENT_SECRET` | Client secret fra Google OAuth-klienten |

4. Start appen med denne `bootRun`-konfigurasjonen.
5. Åpne [http://localhost:8080/auth/google](http://localhost:8080/auth/google) og logg inn med Google.

Ved vellykket tilkobling vises `Connection successful`.

Du kan også starte fra terminalen dersom miljøvariablene er satt i terminalmiljøet:

```powershell
# Windows
.\gradlew.bat bootRun
```

```bash
# macOS / Linux
./gradlew bootRun
```


## Adresser

| Adresse                 | Formål                     |
|-------------------------|----------------------------|
| `/auth/google`          | Starter Google-innlogging  |
| `/auth/google/callback` | Tar imot svaret fra Google |

Bruk `/auth/google` uten skråstrek på slutten. Appen har foreløpig ingen forside på `/`, så denne adressen gir 404.

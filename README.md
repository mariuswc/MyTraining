# WorkOutApp

Treningsapp med backend i Kotlin og Spring Boot. Målet er å samle treningsdata fra Google Sheets og knytte videoer til øvelser.

**Status:** Under utvikling.

## Teknologi

Kotlin · Spring Boot · Gradle (Kotlin DSL) · Google OAuth 2.0 · Google Sheets API · Google Drive API

## Kjør lokalt

Krever **JDK 24** og en **Google OAuth-klient** av typen webapplikasjon.

Registrer følgende redirect-URI i Google-klienten:

```text
http://localhost:8080/auth/google/callback
```

Sett miljøvariablene i terminalen eller i kjørekonfigurasjonen i IntelliJ:

| Variabel | Beskrivelse |
| --- | --- |
| `CLIENT_ID` | Google OAuth Client ID |
| `CLIENT_SECRET` | Google OAuth Client Secret |

Start applikasjonen:

```bash
# macOS / Linux
./gradlew bootRun
```

```powershell
# Windows
.\gradlew.bat bootRun
```

Åpne [localhost:8080/auth/google](http://localhost:8080/auth/google) for å koble til Google. Ved vellykket tilkobling vises `Connection successful`.

Applikasjonen har foreløpig ingen forside på `/`.

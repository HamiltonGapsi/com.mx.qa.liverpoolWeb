# QA Web Automation - Liverpool

Scaffolding de automatizacion web con Serenity BDD + Cucumber + Screenplay para las funcionalidades:

- Landing
- PLP (Product Listing Page)
- PDP (Product Detail Page)

## 1) Prerequisitos en local

1. Instala **JDK 21** (recomendado) o deja que Gradle lo descargue automaticamente via toolchain.
2. Instala **Git**.
3. Instala **IntelliJ IDEA Community o Ultimate**.
4. En IntelliJ instala plugins:
   - Gherkin
   - Cucumber for Java
   - SonarLint (opcional)
5. Verifica acceso de red al sitio objetivo y a Maven Central.

## 2) Configuracion inicial del proyecto

1. Abre el proyecto `com.mx.qa.liverpool` en IntelliJ.
2. Selecciona SDK del proyecto en `File > Project Structure > Project SDK`.
3. Ejecuta sincronizacion Gradle.
4. Define variables sensibles (opcional para flujos autenticados):
   - `LVP_DEV_USER`, `LVP_DEV_PASSWORD`
   - `LVP_QA_USER`, `LVP_QA_PASSWORD`
   - `LVP_PROD_USER`, `LVP_PROD_PASSWORD`

## 3) Ambientes

Se controlan por `-Denvironment` usando `serenity.conf`:

- `dev`
- `qa`
- `prod`

## 4) Estructura de pruebas (Suites y Casos)

- **Suites funcionales**:
  - `src/test/java/com/liverpool/qa/web/runners/functional/LandingTestSuite.java`
  - `src/test/java/com/liverpool/qa/web/runners/functional/PlpTestSuite.java`
  - `src/test/java/com/liverpool/qa/web/runners/functional/PdpTestSuite.java`
- **Suites por ambiente**:
  - `src/test/java/com/liverpool/qa/web/runners/env/DevTestSuite.java`
  - `src/test/java/com/liverpool/qa/web/runners/env/QaTestSuite.java`
  - `src/test/java/com/liverpool/qa/web/runners/env/ProdTestSuite.java`
- **Casos (BDD)**: archivos `.feature` en `src/test/resources/features`.

## 5) Banco de datos externo

Fuente configurable por ambiente con `data.source.type` y `data.source.path`:

- `local-json`
- `local-csv`
- `local-excel`
- `firebase`
- `gcp-bucket`

Implementacion en `src/main/java/com/liverpool/qa/screenplay/utils/TestDataRepository.java`.

## 6) Evidencias y reportes

### Screenshots indexados

El hook `WebTestHooks` guarda capturas por:

- funcionalidad (`landing`, `plp`, `pdp`)
- iteracion (`-Diteration=1`)
- fecha (`yyyyMMdd`)
- ambiente (`dev|qa|prod`)

Directorio configurable con `-Devidence.dir=target/evidences`.

### Reportes

Serenity genera reporte en:

- `target/site/serenity/index.html`

Indicadores recomendados a revisar:

- cobertura por tags/suites
- casos ejecutados, pasados, fallidos
- tiempo por escenario
- tendencias de calidad por corrida

## 7) Scripts Firebase Test Lab

- RoboTest: `scripts/firebase/robotest.ps1`
- XCTest iOS: `scripts/firebase/xctest-ios.ps1`

## 8) Diagrama de arquitectura

- Mermaid: `docs/architecture/qa-web-automation.mmd`

## 9) Comandos de ejecucion

```powershell
.\gradlew.bat clean test --tests "com.liverpool.qa.web.runners.contract.DataContractRunner"
.\gradlew.bat clean testDev
.\gradlew.bat clean testQa
.\gradlew.bat clean testProd
```

### Flujo Landing (`LandingTestSuite`)

Escenarios en `src/test/resources/features/landing/landing.feature`:

- `@busqueda-global`: búsqueda de "iPhone" en la barra superior y validación de la PLP.
- `@navegacion-menu`: menú Categorías > Mujer > Bolsas > Tote.
- `@acceso-login`: clic en "Iniciar sesión" y validación del formulario de autenticación.
- `@inicio-sesion`: login con credenciales de `environments.qa` (serenity.conf); pasa cuando, tras enviar el formulario, se carga otra vista (p. ej. la validación de código).

```powershell
.\gradlew.bat clean test --tests "com.liverpool.qa.web.runners.functional.LandingTestSuite"
.\gradlew.bat clean test --tests "com.liverpool.qa.web.runners.functional.LandingTestSuite" -Ptags="@acceso-login"
```

### Flujo PDP (`PdpTestSuite`)

`src/test/resources/features/pdp/pdp.feature` (`@agregar-carrito`): busca un término en la landing, selecciona el primer producto de la PLP, valida el PDP, elige la primera talla disponible (si aplica), agrega a la bolsa y espera la actualización de su contador. Al abrir la bolsa, `ValidarBolsa.conProductos()` comprueba que existe al menos un artículo visible, sin depender de su nombre completo. Finalmente valida que el botón "Comprar" está habilitado. Esta comprobación no valida la identidad del artículo en la bolsa.

```powershell
.\gradlew.bat clean test --tests "com.liverpool.qa.web.runners.functional.PdpTestSuite"
```

### Flujo PLP (`PlpTestSuite`)

`src/test/resources/features/plp/plp.feature` incluye dos escenarios independientes:

- `@plp-resultados`: busca desde la barra de la landing y valida el termino en la URL, la grilla visible y un primer producto con nombre.
- `@plp-seleccion`: consulta el catalogo, selecciona el primer articulo y verifica que el titulo de su PDP corresponde al producto elegido.

La validacion de resultados se reutiliza mediante `ValidarResultados.para(termino)`. No se agrega al carrito ni se inicia checkout.

```powershell
.\gradlew.bat test --tests "com.liverpool.qa.web.runners.functional.PlpTestSuite"
.\gradlew.bat test --tests "com.liverpool.qa.web.runners.functional.PlpTestSuite" -Ptags="@plp-seleccion"
```

## 10) Nota para mapeo XPath/CSS

Se dejaron metodos base en:

- `src/main/java/com/liverpool/qa/screenplay/ui/LandingPage.java`
- `src/main/java/com/liverpool/qa/screenplay/ui/PlpPage.java`
- `src/main/java/com/liverpool/qa/screenplay/ui/PdpPage.java`

En esos metodos se realiza el mapeo de localizadores reales de la nueva tienda.

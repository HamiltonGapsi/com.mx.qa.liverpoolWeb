# language: es
@landing @dev @qa @prod
Característica: Landing de Liverpool
  Como cliente digital
  Quiero abrir la landing
  Para validar el punto de entrada del journey web

  Antecedentes:
    Dado que el cliente abre la landing de Liverpool

  @landing-smoke
  Escenario: Validar contexto de la landing
    Cuando consulta el titulo esperado de la landing
    Entonces la landing se muestra con el contexto correcto

  @busqueda-global
  Esquema del escenario: Búsqueda global desde la barra superior
    Cuando el cliente busca el término "<termino>" en la barra superior
    Entonces el sistema redirige a la PLP con los artículos de "<termino>"

    Ejemplos:
      | termino |
      | iPhone  |

  @navegacion-menu
  Escenario: Navegación por el menú de Categorías
    Cuando el cliente navega en el menú de Categorías por Mujer > Bolsas > Tote
    Entonces se carga la vista de la categoría "tote"

  @acceso-login
  Escenario: Acceso al formulario de inicio de sesión
    Cuando el cliente da clic en "Iniciar sesión" del banner personalizado
    Entonces se presenta el formulario de autenticación

  @inicio-sesion @qa
  Escenario: Inicio de sesión con credenciales del ambiente QA
    Cuando el cliente da clic en "Iniciar sesión" del banner personalizado
    Y inicia sesión con las credenciales del ambiente "qa"
    Entonces se carga una nueva vista después de iniciar sesión
# language: es
@plp @dev @qa @prod
Característica: Product Listing Page
  Como cliente digital
  Quiero buscar y seleccionar productos en el catalogo
  Para validar el descubrimiento de productos

  Antecedentes:
    Dado que el cliente esta en la landing para explorar el catalogo

  @plp-regression @plp-resultados
  Esquema del escenario: Consultar productos desde la busqueda global
    Cuando busca "<termino>" para consultar el catalogo
    Entonces la PLP muestra productos para la busqueda realizada

    Ejemplos:
      | termino |
      | iPhone  |

  @plp-regression @plp-seleccion
  Escenario: Abrir el detalle de un producto desde la PLP
    Cuando busca "playera" para consultar el catalogo
    Entonces la PLP muestra productos para la busqueda realizada
    Cuando abre el primer articulo del catalogo
    Entonces accede al detalle del articulo elegido desde la PLP

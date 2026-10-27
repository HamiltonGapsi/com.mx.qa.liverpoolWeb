# language: es
@plp @dev @qa @prod
Característica: Product Listing Page
  Como cliente digital
  Quiero navegar a la PLP
  Para validar el descubrimiento de productos

  @plp-regression
  Escenario: Validar URL de PLP con datos externos
    Dado que el cliente abre una PLP usando datos externos
    Cuando navega a la lista de productos
    Entonces la URL contiene el criterio de búsqueda


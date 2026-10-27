# language: es
@pdp @dev @qa @prod
Característica: Product Detail Page
  Como cliente digital
  Quiero elegir un producto desde los resultados de búsqueda
  Para agregarlo a mi bolsa y continuar con la compra

  @pdp-regression @agregar-carrito
  Esquema del escenario: Agregar a la bolsa un producto seleccionado desde la búsqueda
    Dado que el cliente busca "<termino>" desde la landing de Liverpool
    Cuando selecciona el primer producto de la lista de resultados
    Entonces se muestra el detalle del producto seleccionado
    Cuando elige la primera talla disponible
    Y agrega el producto a su bolsa
    Y abre su bolsa de compras desde el header
    Entonces su bolsa contiene al menos un producto
    Y puede continuar con la compra

    Ejemplos:
      | termino |
      | playera |
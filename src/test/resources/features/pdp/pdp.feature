# language: es
@pdp @dev @qa @prod
Característica: Product Detail Page
  Como cliente digital
  Quiero abrir la tienda web
  Para realizar el flujo completo de compra desde un producto destacado

  @pdp-regression
  Escenario: Preparar add to cart con SKU externo
    Dado que el cliente abre un PDP con datos externos
    Cuando solicita agregar el producto al carrito
    Entonces se conserva el SKU objetivo para mapear el flujo real

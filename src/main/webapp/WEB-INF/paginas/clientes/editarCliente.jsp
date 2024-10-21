<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
  <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
  <title>Editar Cliente</title>
</head>
<body>
<!--Cabecero-->
<jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"/>
<jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicion.jsp"/>

<form action="${pageContext.request.contextPath}/controller?accion=modificar" method="POST" class="was-validated">
  <input type="hidden" name="idCliente" value="${cliente.idcliente}"> <!-- Campo oculto para el ID del cliente -->

  <div class="modal-body">
    <div class="form-group">
      <label for="nombre">Nombre</label>
      <input type="text" class="form-control" name="nombre" value="${cliente.nombre}" required>
    </div>
    <div class="form-group">
      <label for="apellido">Apellido</label>
      <input type="text" class="form-control" name="apellido" value="${cliente.apellido}" required>
    </div>
    <div class="form-group">
      <label for="correo">Correo</label>
      <input type="email" class="form-control" name="correo" value="${cliente.correo}" required>
    </div>
    <div class="form-group">
      <label for="telefono">Teléfono</label>
      <input type="tel" class="form-control" name="telefono" value="${cliente.telefono}" required>
    </div>
    <div class="form-group">
      <label for="saldo">Saldo</label>
      <input type="number" class="form-control" name="saldo" value="${cliente.saldo}" required step="any">
    </div>
  </div>
  <div class="modal-footer">
    <button class="btn btn-secondary" data-dismiss="modal">Cerrar</button>
    <button class="btn btn-primary" type="submit">Guardar cambios</button>
  </div>
</form>





<!--Pie de Pagina-->
<jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"/>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>


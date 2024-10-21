<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <!-- Botón para regresar al inicio -->
            <div class="col-md-3">
                <a href="index.jsp" class="btn btn-light btn-block">
                    <i class="fas fa-arrow-left"></i> Regresar al inicio
                </a>
            </div>
            <!-- Botón para guardar los cambios del cliente -->
            <div class="col-md-3">
                <!-- El atributo "form" conecta este botón con el formulario que tenga id="formId" -->
                <button type="submit" class="btn btn-success btn-block" form id="formId">
                    <i class="fas fa-check"></i> Guardar Cliente
                </button>
            </div>
            <!-- Botón para eliminar el cliente -->
            <div class="col-md-3">
                <a href="${pageContext.request.contextPath}/controller?accion=eliminar&idCliente=${cliente.idcliente}"
                   class="btn btn-danger btn-block"
                   onclick="return confirm('¿Estás seguro de que deseas eliminar este cliente?');">
                    <i class="fas fa-trash"></i> Eliminar Cliente
                </a>
            </div>
        </div>
    </div>
</section>


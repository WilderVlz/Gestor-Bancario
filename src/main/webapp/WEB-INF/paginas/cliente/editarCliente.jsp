<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/07aee11805.js" crossorigin="anonymous"></script>
        <title>Editar Cliente</title>
    </head>
    <body>

        <!-- cabecero-->
        <jsp:include page="/WEB-INF/paginas/comunes/cabecero.jsp"></jsp:include>

            <!--Formulario de modificar clientes-->
            <form action="${pageContext.request.contextPath}/ServletControlador?accion=modificar&idCliente=${clienteEditar.idCliente}" method="post" class="was-validated"> 

            <!--Botones de Navegacion-->
            <jsp:include page="/WEB-INF/paginas/comunes/botonesNavegacionEdicion.jsp"></jsp:include>

                <section id="details">
                    <div class="container">
                        <div class="row">
                            <div class="col">
                                <div class="card">
                                    <div class="card-header">
                                        <h4>Editar Cliente</h4>
                                    </div>
                                    <div class="card-body">
                                        <div class="form-group">

                                            <label for="nombre">Nombre</label>
                                            <input type="text" class="form-control" name="nombre" required="" value="${clienteEditar.nombre}">

                                    </div>
                                    <div class="form-group">

                                        <label for="apellido">Apellido</label>
                                        <input type="text" class="form-control" name="apellido" required="" value="${clienteEditar.apellido}">

                                    </div>
                                    <div class="form-group">

                                        <label for="email">Email</label>
                                        <input type="email" class="form-control" name="email" required="" value="${clienteEditar.email}">

                                    </div>
                                    <div class="form-group">

                                        <label for="telefono">Telefono</label>
                                        <input type="tel" class="form-control" name="telefono" required="" value="${clienteEditar.telefono}" >

                                    </div>
                                    <div class="form-group">

                                        <label for="saldo">Saldo</label>
                                        <input type="number" class="form-control" name="saldo" required="" value="${clienteEditar.saldo}" step="any" >

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </section>
        </form>

        <!-- pie de pagina -->
        <jsp:include page="/WEB-INF/paginas/comunes/piePagina.jsp"></jsp:include>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-HwwvtgBNo3bZJJLYd8oVXjrBZt8cqVSpeBNS5n7C8IVInixGAoxmnlMuBnhbgrkm" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
    </body>
</html>
function anadirProducto(idProducto, idProveedor, accion) {
    if (accion === 1) {
        var precio1 = document.getElementById("introducirPrecio"+idProducto);
        if (precio1.value.replace(" ","") === "") {
            alert("Introduce algun precio en el producto")
        } else {
            window.location.replace("/anadirProductoProveedor?idProveedor="+idProveedor+"&idProducto="+idProducto+"&precio="+precio1.value+"&accion="+accion);
        }
    } else if (accion === 2) {
        var precio2 = document.getElementById("eliminarPrecio"+idProducto).value;
        window.location.replace("/anadirProductoProveedor?idProveedor="+idProveedor+"&idProducto="+idProducto+"&precio="+precio2.replace("€","").replace(" ","")+"&accion="+accion);
    } else if (accion === 3) {
        var precio2 = document.getElementById("eliminarPrecio"+idProducto).value;
        window.location.replace("/anadirProductoProveedor?idProveedor="+idProveedor+"&idProducto="+idProducto+"&precio="+precio2.replace("€","").replace(" ","")+"&accion="+accion);
    }
}
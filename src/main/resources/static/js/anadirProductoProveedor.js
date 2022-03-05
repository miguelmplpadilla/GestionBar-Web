function anadirProducto(idProducto, idProveedor, accion) {
    if (accion === 1) {
        var precio1 = document.getElementById("introducirPrecio"+idProducto);
        var iva1 = document.getElementById("introducirIva"+idProducto);
        if (precio1.value.replace(" ","") === "" && iva1.value.replace(" ","") === "") {
            alert("Rellena todos los campos del producto que vas a añadir")
        } else {
            window.location.replace("/anadirProductoProveedor?idProveedor="+idProveedor+"&idProducto="+idProducto+"&precio="+precio1.value+"&accion="+accion+"&iva="+iva1.value);
        }
    } else if (accion === 2) {
        var precio2 = document.getElementById("eliminarPrecio"+idProducto).value;
        window.location.replace("/anadirProductoProveedor?idProveedor="+idProveedor+"&idProducto="+idProducto+"&precio="+precio2.replace("€","").replace(" ","")+"&accion="+accion);
    } else if (accion === 3) {
        var precio2 = document.getElementById("eliminarPrecio"+idProducto).value;
        window.location.replace("/anadirProductoProveedor?idProveedor="+idProveedor+"&idProducto="+idProducto+"&precio="+precio2.replace("€","").replace(" ","")+"&accion="+accion);
    }
}
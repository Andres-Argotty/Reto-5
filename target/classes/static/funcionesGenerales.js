function pintarDatos(datos, columnaMostrar,tipoTabla) {
    let htmlParaInsertar="";
    htmlParaInsertar+="<thead><tr><th>Titulo</th></tr></thead>";

    htmlParaInsertar+="<tbody>";

    for (let i = 0; i <  datos.length; i++) {
        htmlParaInsertar+="<tr>";
        htmlParaInsertar+="<td><a href='#' onclick='RedireccionarDetalle("+datos[i].id+",\""+tipoTabla+"\")'>"+datos[i][columnaMostrar]+"</a></td>"; //En la columna establecemos que datos va a mostrar datos[0]['name']
        htmlParaInsertar+="</tr>";

    }
    htmlParaInsertar+="</tbody>";
    $("#tabla").empty(); //Vaciamos la tabla
    $("#tabla").append(htmlParaInsertar); //Insertamos detro de el elemento con el id tabla la variable htmlParaInsertar

}
function RedireccionarDetalle(id,tipoTabla) {
     //Almacenamiento de la sesión, como un almacenamiento volátil
     sessionStorage.setItem("id",id);
     sessionStorage.setItem("tipoTabla",tipoTabla);
     location.href='detalle.html';


    
}
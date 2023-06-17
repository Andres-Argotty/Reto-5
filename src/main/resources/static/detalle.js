function TraerDetalle() {
    let id= sessionStorage.getItem("id");
    let tipoTabla= sessionStorage.getItem("tipoTabla");

    $.ajax({
        url: 'https://g31412992e9c34c-ocr4caldha7x2c94.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/'+tipoTabla+'/'+tipoTabla+'/'+id,
        type:'GET',
        datatype:'json',
        success: function(respuesta){
            pintarDetalle(respuesta.items);
        },
        error: function(respuesta,xhr){
            alert("Error de petición");
        }
    })
}

    function pintarDetalle(datos) {
        let htmlParaInsertar="";
        htmlParaInsertar+= "<thead><tr>";
        
        Object.keys(datos[0]).forEach(elemento=> htmlParaInsertar+="<th>"+elemento+"</th>");
        htmlParaInsertar+="</tr></thead>";
        htmlParaInsertar+="<tbody>";
        htmlParaInsertar+="<tr>";

        Object.values(datos[0]).forEach((elemento,indice)=>{
        if (indice<1) {
        htmlParaInsertar+="<td>"+elemento+"</td>"

            
        }else{
            htmlParaInsertar+="<td><input value="+elemento+"></td>"

        }
        });
        htmlParaInsertar+="</tr>";
        htmlParaInsertar+="</tbody>";
        
        $("#tabla").empty();
        $("#tabla").append(htmlParaInsertar);


            
        }

        function EliminarDatos() {
            let id= sessionStorage.getItem("id");
            let tipoTabla= sessionStorage.getItem("tipoTabla");

            let datos= {
                'id':id
            };

            $.ajax({
                url:'https://g31412992e9c34c-ocr4caldha7x2c94.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/'+tipoTabla+'/'+tipoTabla,
                type:'DELETE',
                contentType:'application/json',
                data: JSON.stringify(datos),
                success: function(respuesta){

                    alert("Se han eliminiado los datos");
                    location.href="index.html";

                },
                error: function(respuesta,xhr){
                    alert("Error de petición");
                }
            })
        }

    function ActualizarDatos() {
        let tipoTabla= sessionStorage.getItem("tipoTabla");
        let datos="";

        if (tipoTabla=='car') {
           datos=DatosCarro();
        }else if (tipoTabla=='client') {
            datos= DatosCliente();
            
        }else if (tipoTabla=='message') {
            datos=DatosMensaje();
        }
        $.ajax({
            url:'https://g31412992e9c34c-ocr4caldha7x2c94.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/'+tipoTabla+'/'+tipoTabla,
            type:'PUT',
            contentType:'application/json',
            data: JSON.stringify(datos),
            success: function(respuesta){

                alert("Se han actualizado los datos");
                location.href="index.html";

            },
            error: function(respuesta,xhr){
                alert("Error de petición");
            }
        });

        
    }

    function DatosCarro() {

        let fila= document.getElementById('tabla').rows.item(1).cells; // Accedemos a las fila uno de la tabla y extraemos las celdas de esa fila.

        let Datos= {
            'id': fila.item(0).innerText,
            'brand':fila.item(1).children[0].value,
            'model':fila.item(2).children[0].value,
            'category_id':fila.item(3).children[0].value
        }

        return Datos;
     
    }

    function DatosCliente() {

        // Accedemos a las fila uno de la tabla y extraemos las celdas de esa fila.

        let fila=document.getElementById('tabla').rows.item(1).cells;
        let Datos= {
            'id': fila.item(0).innerText,
            'name':fila.item(1).children[0].value,
            'email':fila.item(2).children[0].value,
            'age':fila.item(3).children[0].value
        }

        return Datos;
        
    }

    function DatosMensaje() {
        let fila= document.getElementById("tabla").rows.item(1).cells; // Accedemos a las fila uno de la tabla y extraemos las celdas de esa fila.

        let Datos= {
            'id': fila.item(0).innerText,
            'messagetext':fila.item(1).children[0].value,
        }

        return Datos;
        
    }
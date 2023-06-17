function traerDatosMensajes() {
    $.ajax({  //Llamdo a AJAX
        url: 'https://g31412992e9c34c-ocr4caldha7x2c94.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/message/message', //Link a la API
        type: 'GET', //Petición tipo GET
        dataType: 'json', //Esperamos que nos retorne datos tipo JSON
        success: function (respuesta) {  //
            // Si todo funciona bien, entonces va a ejecutar la función  pintarDatos y va a obtener como parametro la respuesta del get, es decir los objetos que tenemos en la API.
            pintarDatos(respuesta.items,'messagetext','message');

            
        },
        error: function (respuesta, xhr) {
            alert("Error de petición"); //en caso de error mostrará el mensaje de error.
        }
    })
}
function guardarMensaje() {
    let datos = {
        'id':$("#id").val(),
        'messagetext':$("#messagetext").val(),
    };

        $.ajax({  //Llamado a AJAX
            url: 'https://g31412992e9c34c-ocr4caldha7x2c94.adb.sa-saopaulo-1.oraclecloudapps.com/ords/admin/message/message', //Link a la API
            type: 'POST', //Petición tipo GET
            contentType: 'application/json',  //Enviamos este tipo de dato
            data: JSON.stringify(datos),
            
            success: function (respuesta) {  //
                // Si todo funciona bien, entonces va a mandar una alerta 
                alert("El mensaje se ha creado correctamente")
                traerDatosMensajes(); //Una vez creado el carro, vamos a hacer la solicitud GET para traer los nuevos datos.
    
                
            },
            error: function (respuesta, xhr) {
                alert("Error de petición"); //en caso de error mostrará el mensaje de error.
            }
        })

}

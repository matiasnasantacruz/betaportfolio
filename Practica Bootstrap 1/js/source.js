function prueba(){
    alert("CALLESE LA BOCA VIEJO PUTOOOO!!");
}

function evento(){

    console.log("Esto funciona de maravillas invernales j1j11j1");

}

function revealInfo1(){
var resumen = document.getElementById('resumenProducto1');
var detalle = document.getElementById("detalleProducto1");
var linkProducto1 = document.getElementById("linkProducto1");

$(document).ready(function(){

    if(linkProducto1.textContent=="Mas informacion"){
        $("#resumenProducto1").hide();
        $("#detalleProducto1").show();
        linkProducto1.textContent="Menos informacion";
    }
    else{
        $("#resumenProducto1").show();
        $("#detalleProducto1").hide();
        linkProducto1.textContent="Mas informacion";
    }
    
  });

}
<?php
require_once './Negocio.php';
$opc=$_REQUEST['tag'];
if ($opc=="consulta1") {
    
$obj=new Negocio();
echo json_encode($obj->lisVehi());
}
if ($opc=="consulta2") {
    $cad=$_REQUEST["cad"];
$obj=new Negocio();
echo json_encode($obj->filtra($cad));
}

if ($opc=="consulta3") {
    $cad=$_REQUEST["cad"];
$obj=new Negocio();
echo json_encode($obj->LisInfra($cad));
}

if ($opc=="adicion") {
    $pla=$_REQUEST["pla"];
    $cod=$_REQUEST["cod"];
    $pol=$_REQUEST["pol"];
$obj=new Negocio();
echo json_encode($obj->adicionFac($pla,$cod,$pol));
}

?>
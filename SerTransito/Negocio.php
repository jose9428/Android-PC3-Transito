<?php
require_once 'Conexion.php';
class Negocio {  
    public function  lisVehi(){
        $obj=new Conexion();
        $sql="select nropla,nompro,color,modelo from vehiculo ";
         $res=  mysqli_query($obj->Conecta(),$sql) or die(mysqli_error($obj->Conecta()));
         $response["dato"]=array();
         while($f=  mysqli_fetch_array($res)){
           
             array_push($response["dato"], array("pla"=>$f[0],"nombre"=>$f[1],
                 "color"=>$f[2],"modelo"=>$f[3]));    
         }
        return $response;
         }
          public function  filtra($cad){
        $obj=new Conexion();
        $sql="select nropla,nompro,color,modelo from vehiculo  where nompro like '$cad%' ";
         $res=  mysqli_query($obj->Conecta(),$sql) or die(mysqli_error($obj->Conecta()));
         $response["dato"]=array();
         while($f=  mysqli_fetch_array($res)){
           
             array_push($response["dato"], array("pla"=>$f[0],"nombre"=>$f[1],
                 "color"=>$f[2],"modelo"=>$f[3]));    
         }
        return $response;
         } 
         public function  LisInfra($pla){
        $obj=new Conexion();
        $sql="select nropap,papfecha,infdes, infimp from papeleta p,infraccion b where p.infcod=b.infcod and nropla='$pla' ";
              
         $res=  mysqli_query($obj->Conecta(),$sql) or die(mysqli_error($obj->Conecta()));
         $response["dato"]=array(); 
         while($f=  mysqli_fetch_array($res)){
           
             array_push($response["dato"], array("pape"=>$f[0],"fecha"=>$f[1],
                 "desp"=>$f[2],"monto"=>$f[3]));    
         }
        return $response;
         }
         //inserta papeletas
         public function  adicionFac($pla, $cod,$pol){
        $obj=new Conexion();
        $sql="call spadicion('$pla','$cod','$pol')";
        $res=  mysqli_query($obj->Conecta(),$sql) or die(mysqli_error($obj->Conecta()));
        if($res)
         $response["dato"]="ok";
        else
             $response["dato"]="error";
        return $response;
         }
        
}


?>
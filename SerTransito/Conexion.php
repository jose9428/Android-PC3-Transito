<?php
class Conexion {
  private $cn=null;
  function Conecta(){
      if($this->cn==null){
      $this->cn=  @mysqli_connect("localhost","root","","bdtransito");
      }
      return $this->cn;
  }
  function Cierra(){
      $this->cn=null;
      return $this->cn;
  }
}
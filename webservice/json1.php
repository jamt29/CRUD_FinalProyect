<?php
header('Content-type: application/json; charset=utf-8');
$json_string = json_encode(array("id" => 10,"nombre" => "Irvin","apellidos" => "Cortéz"));
echo $json_string;
?>
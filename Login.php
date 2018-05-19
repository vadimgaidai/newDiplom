<?php
error_reporting(0);
$db_name = "id5825654_diplomandroid";
$mysql_user = "id5825654_diplomandroid";
$mysql_pass = "karaganda123";
$server_name = "localhost";
 
$con = mysqli_connect($server_name, $mysql_user, $mysql_pass, $db_name);
 
if(!$con){
    echo '{"init ne rabotaet"}';
}
 
$name = $_POST["name"];
$password = $_POST["password"];


 
$sql = "SELECT * FROM `user_info` WHERE `name`='".$name."' AND `password`='".$password."';";
 
$result = mysqli_query($con, $sql);
 
$response = array();



 
while($row = mysqli_fetch_array($result)){
 
    $response = array("id"=>$row[0],"name"=>$row[1],"password"=>$row[2],"email"=>$row[3]);
  
}
 
 echo json_encode(array("user_data"=>$response));

 
?>
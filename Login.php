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


if (isset($_GET["action"])) { 
    $action = $_GET['action'];
}

if($action == select ){

	$name = $_GET["name"];
	$password = $_GET["password"];

	$sql = "SELECT * FROM `user_info` WHERE `name`='".$name."' AND `password`='".$password."';";

	$result = mysqli_query($con, $sql);

	while($row = mysqli_fetch_array($result)){
	 
	    $response = array("name"=>$row[1],"password"=>$row[2]);
	}
	 
	 echo json_encode(array($response));
}
else{

	$name = $_POST["name"];
	$password = $_POST["password"];


	$sql = "SELECT * FROM `user_info` WHERE `name`='".$name."' AND `password`='".$password."';";
	 
	$result = mysqli_query($con, $sql);
	 
	$response = array();
	
	while($row = mysqli_fetch_array($result)){
	 
	    $response = array("name"=>$row[1],"password"=>$row[2]);
	}
	 
	 echo json_encode(array("user_data"=>$response));
}
 


 
?>
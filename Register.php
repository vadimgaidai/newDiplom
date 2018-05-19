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


if($action == insert ){ // если действие INSERT и есть все что нужно

	$name = $_GET["name"];
	$password = $_GET["password"];
	
	$sql = "INSERT INTO `user_info` (`id`, `name`, `password`) VALUES (NULL, '$name', '$password');";
	if(!mysqli_query($con, $sql)){
	    echo '{"message":"Unable to save the data to the database."}';
	}

}else{

	$name = $_POST["name"];
	$password = $_POST["password"];
	
	$sql = "INSERT INTO `user_info` (`id`,`name`, `password`) VALUES (NULL, '".$name."', '".$password."');";
	if(!mysqli_query($con, $sql)){
	    echo '{"message":"Unable to save the data to the database."}';
	}

}

 
?>
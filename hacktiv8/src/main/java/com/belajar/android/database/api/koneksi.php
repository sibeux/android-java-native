<?php
$hostname = 'localhost';
$username = 'sibb8757';
$password = 'M41hpwg4TZTi82';
$database = 'sibb8757_web_porto';

$conn = mysqli_connect($hostname,$username,$password,$database);
if(!$conn){
    echo "gagal";
}
?>
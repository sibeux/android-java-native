<?php
include('Connection.php');

$username    = $_POST['username'];
$password    = $_POST['password'];

if(!empty($username) || !empty($password)){

    $sql = "SELECT * FROM admin WHERE username = '$username' AND password = '$password'";

    $query = mysqli_query($conn,$sql);

    if(mysqli_affected_rows($conn) > 0){
        $data['status'] = true;
        $data['result'] = "Berhasil";
    }else{
        $data['status'] = false;
        $data['result'] = "Gagal";
    }
} else{
      $data['status'] = false;
      $data['result'] = "Username/Password Tidak Sesuai! Periksa Kembali!";
  }

print_r(json_encode($data));

?>
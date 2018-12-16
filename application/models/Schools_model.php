<?php
/**
 * Created by PhpStorm.
 * User: adnan
 * Date: 9/30/2017
 * Time: 8:57 PM
 */
class Schools_model extends CI_Model {


public function __destruct() {  
    $this->db->close();  
} 


 public function app_auth_modle($email,$password){

 return $this->db->select('id,first_name,last_name ,father-name,email,address,token,image')
                        ->from('student')
                        ->where('email', $email)
                        ->where('password', $password)
                        ->get()->row();
}







}
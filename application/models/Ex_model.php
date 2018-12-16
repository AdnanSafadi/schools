<?php
/**
 * Created by PhpStorm.
 * User: adnan
 * Date: 9/30/2017
 * Time: 8:57 PM
 */
class Ex_model extends CI_Model {


public function __destruct() {  
    $this->db->close();  
} 

 public function get_menu(){

   $this->db->select('id,title');
   $this->db->from('menu');
   $this->db->order_by("orders","ASC");
   $query = $this->db->get();
   return $query->result();

 }

 public function get_menu_details($menu){


   $this->db->select('*');
   $this->db->from('menu_details');
   $this->db->order_by("orders","ASC");
   $this->db->where('is_private =', 1);
   $this->db->where('menu =', $menu);
   $query = $this->db->get();
   return $query->result();

 }

 public function get_offers(){

  $this->db->select('*');
  $this->db->from('offers');
  $this->db->where('is_private=','1');
  $this->db->order_by("orders","ASC");
  $query = $this->db->get();

    //    $query = $this->db->get_where('offers', array('is_private' => 1));

  return $query->result();

}

public function get_branches(){
  $query = $this->db->get('branches');
  return $query->result();
}

public function set_orders($data){

  $query =  $this->db->insert('orders', $data);
  return ($this->db->affected_rows() != 1) ? false : true;
}

public function get_about_as(){
  $query = $this->db->get('about');
  return $query->result();
}


public function set_user_register($data){

  $query =  $this->db->insert('subscribers', $data);
  return ($this->db->affected_rows() != 1) ? false : true;
}

public function set_user_notes($data){

  $query =  $this->db->insert('subscribers_notes', $data);
  return ($this->db->affected_rows() != 1) ? false : true;
}


public function set_order($data){

  $query =  $this->db->insert('orders', $data);
  return ($this->db->affected_rows() != 1) ? false : true;
}

public function update_profile_date($old_phone, $data){

  $this->db->where('phone', $old_phone);
  $this->db->update('subscribers', $data);
  return ($this->db->affected_rows() != 1) ? false : true;
}


public function send_fcm_to_user($id,$title,$message) {
  $registrationIds = array();
  $registrationIds[] = $id;
  $registrationIds = ($registrationIds);

  $this->send_data($registrationIds,$title,$message);

}

public function firebase($title,$message){


  $all = $this->db->select('token')->get('subscribers')->result();

  $registrationIds = array();


  foreach ($all as $value) {
            //if (in_array($value->username, $array)) {

    $registrationIds[] = $value->token;

            //}
  }
  $registrationIds = ($registrationIds); 
  $this->send_data($registrationIds,$title,$message);

// #prep the bundle

}

function has_phone_before($phone)
{
     $query = $this->db->get_where('subscribers', array(//making selection
            'phone' => $phone
        ));
        $ss=$this->db->last_query();
        log_message('error', $ss);
        if( $query->num_rows() > 0 ) {
              return true;
        } else {
             return false;
        }
    
}

private function send_data($registrationIds,$title,$message) {

 $notification = array
 (
  'body' => $message,
  'title' => $title,
  'sound' => 'default',
  'priority'=>  "high",
  "show_in_foreground"=> True
);
 $data = array
 (
  'data_body' =>  $message,
  'data_title' => $title,

);
 $fields1 = array
 (
  'registration_ids' => $registrationIds,
  "data" => $data,
);
 $fields2 = array
 (
  'registration_ids' => $registrationIds,
  'notification' => $notification,
);
 $API_ACCESS_KEY='AAAAzc7YuVE:APA91bEPd4ZzmTAwEhrGb0eKYbUXGSXBmP7awZBIs9CeoTklDwjbd5aljzldoCg2fFPGN3PK6TfU5Y-JPfCQ1OmljDmsgrIt9wJeK1abWoHHb3Lai0Tj8B2E-BmpS6xA2otbIQ6ItenM';

 $headers = array
 (
  'Authorization: key=' . $API_ACCESS_KEY,
  'Content-Type: application/json'
);
 $ch = curl_init();
 curl_setopt($ch, CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send');
 curl_setopt($ch, CURLOPT_POST, true);
 curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
 curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
 curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields1));
 $result = curl_exec($ch);
 curl_close($ch);
 echo $result;
 $ch = curl_init();
 curl_setopt($ch, CURLOPT_URL, 'https://fcm.googleapis.com/fcm/send');
 curl_setopt($ch, CURLOPT_POST, true);
 curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
 curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
 curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
 curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields2));
 $result = curl_exec($ch);
 curl_close($ch);
 echo "Successfully";


}


}
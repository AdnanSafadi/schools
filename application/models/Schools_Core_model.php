<?php
/**
 * Created by PhpStorm.
 * User: adnan
 * Date: 9/30/2017
 * Time: 8:57 PM
 */

class Schools_Core_model extends CI_Model {


  public function get_student_point($student_id){
        // $query = $this->db->query('SELECT st.id, st.material_id as materials , st.point as studentPoint ,m.point as materialPoint FROM `student_point` as st INNER JOIN materials AS m ON m.id = st.student_id WHERE st.id = '.$student_id);

        //  return $query->result();

   $this->db->select('student_point.id, materials.material_number as materials , materials.point as studentPoint ,materials.point as materialPoint');    
   $this->db->from('student_point');
   $this->db->join('materials', 'student_point.material_id = materials.id');
   $this->db->join('student', 'student.id = student_point.student_id');
   $this->db->where('student.user_key =', $student_id);
   $query = $this->db->get();
   return $query->result();

 }

 public function check_student($student_id,$password){
  $this->db->select('user_key,first_name,last_name,father_name,email,address,image');
  $this->db->from('student');
  $this->db->where('user_key =', $student_id);
  $this->db->where('password =', $password);
  $query = $this->db->get();

  if ($query->num_rows() > 0) {
    return $query->row();
  } else {
    return false;
  }
}

public function get_all_material($student_id) {
  $this->db->select('*');
  $this->db->from('materials');
  $query = $this->db->get();
  $data = $query->result();
       // die( $data['id'] );

  foreach ($data as $variable) {

   $check_student =  $this->checkIfIsExistInStudentPoint($student_id,$variable->id);
           //die($check_student);
   if ($check_student) {
    $variable->updated_at = true;
  }
}
return $data;
}

public function get_student_data_model($student_id) {
 $this->db->select('*');
 $this->db->from('student');
 $this->db->where('user_key =',$student_id);
 $query = $this->db->get();
 return $data = $query->result();
}

public function set_student_point_model($student_id, $material_num, $point,$reason){

 $this->db->select('id,point');
 $this->db->from('materials');
 $this->db->where('material_number =', $material_num);
 $this->db->limit(1);
 $query = $this->db->get();
 $data = $query->result();

 if (empty($reason)) {
  $data = array('student_id' => $student_id,
    'material_id' => $data[0]->id,
    'point' => $data[0]->point,
    'reason' => ''
  );
  $query =  $this->db->insert('student_point', $data);
  return ($this->db->affected_rows() != 1) ? false : true;

}else {
  $data = array('student_id' => $student_id,
    'material_id' => $data[0]->id,
    'point' => $point,
    'reason' => $reason
  );
  $query =  $this->db->insert('student_point', $data);
  return ($this->db->affected_rows() != 1) ? false : true;
}

}

private function checkIfIsExistInStudentPoint($student_id,$material_id){
  $this->db->select('*');
  $this->db->from('student_point');
  $this->db->join('student', 'student.id = student_point.student_id');
  $this->db->where('student.user_key =', $student_id);
  $this->db->where('material_id =', $material_id);
  $query = $this->db->get();

        // $data = $query->result();
  if ($query->num_rows() != 1) {
                    // there should only be one row - anything else is an error
    return false;
  }else {
    return true;
  }

}


}
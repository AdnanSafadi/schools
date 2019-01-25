<?php
/**
 * Created by PhpStorm.
 * User: adnan
 * Date: 9/30/2017
 * Time: 8:57 PM
 */

class Schools_Core_model extends CI_Model {


public function get_student_point(){
        $query = $this->db->query('SELECT st.id, st.material_id as materials , st.point as studentPoint ,m.point as materialPoint FROM `student_point` as st INNER JOIN materials AS m ON m.id = st.student_id WHERE st.id = 1');

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

}
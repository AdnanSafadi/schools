<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Api extends CI_Controller {


	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html
	 */

	public function __construct()
	{
		parent::__construct();
		$this->load->model('Schools_Core_model');
		
	}

	
	public function index()
	{
		$this->load->view('welcome_message');
	}

	public function student_point_list() {

 	//echo "string";
		$student_id = $this->input->post("student_id"); 

		$this->load->model('Schools_Core_model');
		$output = $this->Schools_Core_model->get_student_point($student_id);
		$out = array('data' => $output ,'status' => true, 'message' => '');
		echo json_encode($out);
	}

	public function check_student_auth() {
		$student_id = $this->input->post("student_id"); 
		$password = $this->input->post("password");
		$output = $this->Schools_Core_model->check_student($student_id,$password);
		if ($output == false) {
			$out = array('data' => '' ,'status' => false, 'message' => '505');
		}else {
			$out = array('data' => $output ,'status' => true, 'message' => '');
		}

		echo json_encode($out); 
	}

	public function get_all_material(){
		$student_id = $this->input->post("student_id"); 
		$output = $this->Schools_Core_model->get_all_material($student_id);
		$out = array('data' => $output ,'status' => true, 'message' => '');
		echo json_encode($out);
	}


	public function get_student_data(){
		$student_id = $this->input->post("student_id"); 
		$output = $this->Schools_Core_model->get_student_data_model($student_id);
		$out = array('data' => $output ,'status' => true, 'message' => '');
		echo json_encode($out);
	}

	public function set_student_point(){
		$student_id = $this->input->post("student_id"); 
		$material_num = $this->input->post("material_num"); 
		$point = $this->input->post("point"); 
		$reason = $this->input->post("reason");

		$data = $this->Schools_Core_model->set_student_point_model($student_id, $material_num, $point,$reason);

		if ($data == false) {
			$out = array('data' => '' ,'status' => false, 'message' => '');
		}else {
			$out = array('data' => $data ,'status' => true, 'message' => '');
		}

		echo json_encode($out);
	}

	
}

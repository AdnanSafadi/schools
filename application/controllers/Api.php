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

 public function menu_list() {
 	
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

	
}

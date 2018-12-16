<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Api extends CI_Controller {


	function __construct() {
		parent::__construct();
		$this->load->model('Schools_model');
	}


	// public function orders() {
	// 	$this->load->model('Ze_Core_model');

	// 	$data = array('username' => $this->input->post('username'),
	// 		'address' => $this->input->post('address'),
	// 		'menu_item' => $this->input->post('menu_item'),
	// 		'notes' => $this->input->post('notes')
	// 	);
	// 	$out = array('res' => $this->Ze_Core_model->set_orders($data));
	// 	echo json_encode($out);
	// }

	function appAuth(){
		$this->load->model('Schools_model');
		$email = $this->input->post('email');
		$password = $this->input->post('password');

		$this->load->library('form_validation');
		$this->form_validation->set_rules('name', 'lang:name', 'required');
		$this->form_validation->set_rules('password', 'lang:password', 'required|min_length[6]|max_length[30]');
		
		if ($this->form_validation->run() == TRUE)
		{ 
			
			$output = array('status' => false, 'data' => "", "message" => "500");
			
		} else {
		
		$data = $this->Schools_model->app_auth_modle($email,$password) == null ? false: true;
		$output = array('status' => $data, 'data' => $this->Schools_model->app_auth_modle($email,$password), "message" => "");

	}
	echo json_encode($output);
}
	
}

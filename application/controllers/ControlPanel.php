	<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

	/**
	 * 
	 */
	class ControlPanel extends CI_Controller {
		
		// function __construct() {
		// 	parent::__construct();
		// 	$this->load->database();
		// 	$this->load->helper('url');
		// 	$this->load->library('grocery_CRUD');
		// 	if (!$this->ion_auth->logged_in()) {
		// 		redirect('auth/login', 'refresh');
		// 	}
		// }

		public function __construct()
	{
		parent::__construct();
		$this->load->library('grocery_CRUD');

		if (!$this->ion_auth->logged_in()) {
            redirect('auth/login', 'refresh');

        }
   }

		public function _example_output($output = null)
		{
			$this->load->view('home.php',(array)$output);
		}

		public function index()
		{
			//$this->load->view('home.php');
			$this->students();
		}

		public function students()
		{
			try{
				$crud = new grocery_CRUD();

				$crud->set_theme('Flexigrid');
				$crud->set_table('student');
				$crud->set_subject('Student');
				$crud->columns('first_name','last_name','father_name','email');
				$crud->set_relation('class_id','clazz','name');
				$output = $crud->render();

				$this->_example_output($output);

			}catch(Exception $e){
				show_error($e->getMessage().' --- '.$e->getTraceAsString());
			}
		}

		public function calzz()
		{
			try{
				$crud = new grocery_CRUD();

				$crud->set_theme('Flexigrid');
				$crud->set_table('clazz');
				$crud->set_subject('Class');
				$crud->columns('id','name');
				$output = $crud->render();

				$this->_example_output($output);

			}catch(Exception $e){
				show_error($e->getMessage().' --- '.$e->getTraceAsString());
			}
		}

		public function financial_student()
		{
			try{
				$crud = new grocery_CRUD();

				$crud->set_theme('Flexigrid');
				$crud->set_table('financial_student');
				$crud->set_subject('Financial Student');
				$crud->columns('id','student_id','financial_total','creation_at','updated_at','reason_updated_at');
				$crud->set_relation('student_id','student','first_name');
				$crud->unset_delete();
				$output = $crud->render();

				$this->_example_output($output);

			}catch(Exception $e){
				show_error($e->getMessage().' --- '.$e->getTraceAsString());
			}
		}


		

		public function materials()
		{
			try{
				$crud = new grocery_CRUD();

				$crud->set_theme('Flexigrid');
				$crud->set_table('materials');
				$crud->set_subject('Materials');
				$crud->columns('id','name','creation_at','updated_at');
				
				$output = $crud->render();

				$this->_example_output($output);

			}catch(Exception $e){
				show_error($e->getMessage().' --- '.$e->getTraceAsString());
			}
		}
		public function student_points()
		{
			try{
				$crud = new grocery_CRUD();

				$crud->set_theme('Flexigrid');
				$crud->set_table('student_point');
				$crud->set_subject('Student Point');
				$crud->columns('id','student_id','material_id','point');
				$crud->set_relation('student_id','student','first_name');
				$crud->set_relation('material_id','materials','name');
				$output = $crud->render();

				$this->_example_output($output);

			}catch(Exception $e){
				show_error($e->getMessage().' --- '.$e->getTraceAsString());
			}
		}


		public function student_points_log()
		{
			try{
				$crud = new grocery_CRUD();

				$crud->set_theme('Flexigrid');
				$crud->set_table('student_point_log');
				$crud->set_subject('Student Point Log');
				$crud->columns('id','student_id','points','reason');
				$crud->set_relation('student_id','student','first_name');
				$crud->unset_add()->unset_delete()->unset_edit();
				$output = $crud->render();

				$this->_example_output($output);

			}catch(Exception $e){
				show_error($e->getMessage().' --- '.$e->getTraceAsString());
			}
		}


	}
<?php defined('BASEPATH') OR exit('No direct script access allowed');

/**
 * Class Auth
 * @property Ion_auth|Ion_auth_model $ion_auth        The ION Auth spark
 * @property CI_Form_validation      $form_validation The form validation library
 */
class Auth extends CI_Controller
{
	public function __construct()
	{
		parent::__construct();
		$this->load->database();
		$this->load->library(array('ion_auth', 'form_validation'));
		$this->load->helper(array('url', 'language'));

		$this->form_validation->set_error_delimiters($this->config->item('error_start_delimiter', 'ion_auth'), $this->config->item('error_end_delimiter', 'ion_auth'));

		$this->lang->load('auth');
	}

	/**
	 * Redirect if needed, otherwise display the user list
	 */
	public function index()
	{

		if (!$this->ion_auth->logged_in())
		{
			// redirect them to the login page
			redirect('auth/login', 'refresh');
		}
		else if (!$this->ion_auth->is_admin()) // remove this elseif if you want to enable this for non-admins
		{
			// redirect them to the home page because they must be an administrator to view this
			return show_error('You must be an administrator to view this page.');
		}
		else
		{
			// set the flash data error message if there is one
			$this->data['message'] = (validation_errors()) ? validation_errors() : $this->session->flashdata('message');

			//list the users
			$this->data['users'] = $this->ion_auth->users()->result();
			foreach ($this->data['users'] as $k => $user)
			{
				$this->data['users'][$k]->groups = $this->ion_auth->get_users_groups($user->id)->result();
			}

			$this->_render_page('auth' . DIRECTORY_SEPARATOR . 'index', $this->data);
		}
	}

	/**
	 * Log the user in
	 */
	public function login()
	{
		$this->data['title'] = $this->lang->line('login_heading');

		// validate form input
		$this->form_validation->set_rules('identity', str_replace(':', '', $this->lang->line('login_identity_label')), 'required');
		$this->form_validation->set_rules('password', str_replace(':', '', $this->lang->line('login_password_label')), 'required');

		if ($this->form_validation->run() === TRUE)
		{
			// check to see if the user is logging in
			// check for "remember me"
			$remember = (bool)$this->input->post('remember');

			if ($this->ion_auth->login($this->input->post('identity'), $this->input->post('password'), $remember))
			{
				//if the login is successful
				//redirect them back to the home page
				$this->session->set_flashdata('message', $this->ion_auth->messages());
				redirect('/', 'refresh');
			}
			else
			{
				// if the login was un-successful
				// redirect them back to the login page
				$this->session->set_flashdata('message', $this->ion_auth->errors());
				redirect('auth/login', 'refresh'); // use redirects instead of loading views for compatibility with MY_Controller libraries
			}
		}
		else
		{
			// the user is not logging in so display the login page
			// set the flash data error message if there is one
			$this->data['message'] = (validation_errors()) ? validation_errors() : $this->session->flashdata('message');

			$this->data['identity'] = array('name' => 'identity',
				'id' => 'identity',
				'type' => 'text',
				'value' => $this->form_validation->set_value('identity'),
			);
			$this->data['password'] = array('name' => 'password',
				'id' => 'password',
				'type' => 'password',
			);

			$this->_render_page('auth' . DIRECTORY_SEPARATOR . 'login', $this->data);
		}
	}

	/**
	 * Log the user out
	 */
	public function logout()
	{
		$this->data['title'] = "Logout";

		// log the user out
		$logout = $this->ion_auth->logout();

		// redirect them to the login page
		$this->session->set_flashdata('message', $this->ion_auth->messages());
		redirect('auth/login', 'refresh');
	}

	// /**
	//  * Change password
	//  */
	// public function changes_password()
	// {
	// 	$this->form_validation->set_rules('old', $this->lang->line('change_password_validation_old_password_label'), 'required');
	// 	$this->form_validation->set_rules('new', $this->lang->line('change_password_validation_new_password_label'), 'required|min_length[' . $this->config->item('min_password_length', 'ion_auth') . ']|max_length[' . $this->config->item('max_password_length', 'ion_auth') . ']|matches[new_confirm]');
	// 	$this->form_validation->set_rules('new_confirm', $this->lang->line('change_password_validation_new_password_confirm_label'), 'required');

	// 	if (!$this->ion_auth->logged_in())
	// 	{
	// 		redirect('auth/login', 'refresh');
	// 	}

	// 	$user = $this->ion_auth->user()->row();

	// 	if ($this->form_validation->run() === FALSE)
	// 	{
	// 		// display the form
	// 		// set the flash data error message if there is one
	// 		$this->data['message'] = (validation_errors()) ? validation_errors() : $this->session->flashdata('message');

	// 		$this->data['min_password_length'] = $this->config->item('min_password_length', 'ion_auth');
	// 		$this->data['old_password'] = array(
	// 			'name' => 'old',
	// 			'id' => 'old',
	// 			'type' => 'password',
	// 		);
	// 		$this->data['new_password'] = array(
	// 			'name' => 'new',
	// 			'id' => 'new',
	// 			'type' => 'password',
	// 			'pattern' => '^.{' . $this->data['min_password_length'] . '}.*$',
	// 		);
	// 		$this->data['new_password_confirm'] = array(
	// 			'name' => 'new_confirm',
	// 			'id' => 'new_confirm',
	// 			'type' => 'password',
	// 			'pattern' => '^.{' . $this->data['min_password_length'] . '}.*$',
	// 		);
	// 		$this->data['user_id'] = array(
	// 			'name' => 'user_id',
	// 			'id' => 'user_id',
	// 			'type' => 'hidden',
	// 			'value' => $user->id,
	// 		);

	// 		// render
	// 		$this->_render_page('auth' . DIRECTORY_SEPARATOR . 'change_password', $this->data);
	// 	}
	// 	else
	// 	{
	// 		$identity = $this->session->userdata('identity');

	// 		$change = $this->ion_auth->change_password($identity, $this->input->post('old'), $this->input->post('new'));

	// 		if ($change)
	// 		{
	// 			//if the password was successfully changed
	// 			$this->session->set_flashdata('message', $this->ion_auth->messages());
	// 			$this->logout();
	// 		}
	// 		else
	// 		{
	// 			$this->session->set_flashdata('message', $this->ion_auth->errors());
	// 			redirect('auth/change_password', 'refresh');
	// 		}
	// 	}
	// }


	
	/**
	* Redirect a user checking if is admin
	*/
	public function redirectUser(){
		if ($this->ion_auth->is_admin()){
			redirect('auth', 'refresh');
		}
		redirect('/', 'refresh');
	}

	
	/**
	 * @return array A CSRF key-value pair
	 */
	public function _get_csrf_nonce()
	{
		$this->load->helper('string');
		$key = random_string('alnum', 8);
		$value = random_string('alnum', 20);
		$this->session->set_flashdata('csrfkey', $key);
		$this->session->set_flashdata('csrfvalue', $value);

		return array($key => $value);
	}

	/**
	 * @return bool Whether the posted CSRF token matches
	 */
	public function _valid_csrf_nonce(){
		$csrfkey = $this->input->post($this->session->flashdata('csrfkey'));
		if ($csrfkey && $csrfkey === $this->session->flashdata('csrfvalue')){
			return TRUE;
		}
			return FALSE;
	}

	/**
	 * @param string     $view
	 * @param array|null $data
	 * @param bool       $returnhtml
	 *
	 * @return mixed
	 */
	public function _render_page($view, $data = NULL, $returnhtml = FALSE)//I think this makes more sense
	{

		$this->viewdata = (empty($data)) ? $this->data : $data;

		$view_html = $this->load->view($view, $this->viewdata, $returnhtml);

		// This will return html on 3rd argument being true
		if ($returnhtml)
		{
			return $view_html;
		}
	}

}

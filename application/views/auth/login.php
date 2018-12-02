<?php
defined('BASEPATH') OR exit('No direct script access allowed');
?>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <title>Login - Schools UCM</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <meta name="apple-mobile-web-app-capable" content="yes">   
        <!-- Styles -->
        <link href="<?php echo base_url('assets/AppData/css/bootstrap.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('assets/AppData/css/bootstrap-responsive.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('assets/AppData/css/bootstrap-overrides.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('assets/AppData/css/ui-lightness/jquery-ui-1.8.21.custom.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('assets/AppData/css/slate.css'); ?>" rel="stylesheet">
        <link href="<?php echo base_url('assets/AppData/css/components/signin.css'); ?>" rel="stylesheet" type="text/css">   
        <!-- Javascript -->
        <script src="<?php echo base_url('assets/AppData/js/jquery-1.7.2.min.js'); ?>"></script>
        <script src="<?php echo base_url('assets/AppData/js/jquery-ui-1.8.18.custom.min.js'); ?>"></script>    
        <script src="<?php echo base_url('assets/AppData/js/jquery.ui.touch-punch.min.js'); ?>"></script>
        <script src="<?php echo base_url('assets/AppData/js/bootstrap.js'); ?>"></script>
        <script src="<?php echo base_url('assets/AppData/js/demos/signin.js'); ?>"></script>
    </head>
    <body>
        <div class="account-container login">
            <div class="content clearfix" id="infoMessage">
                <?php if ($message != NULL) { ?>
                    <div class="alert alert-error">
                        <a class="close" data-dismiss="alert" href="#">×</a>
                        <h4 class="alert-heading">Error!</h4>
                        <?php echo $message; ?>
                    </div> 
                <?php } ?>
                <?php echo form_open("auth/login");?>
                    <h1>Schools UCM</h1>    
                    <div class="login-fields">
                        <p>Sign in to access School UCM :</p>
                        <div class="field">                             
                            <input class="login username-field" type="text" placeholder="Email" name="identity" value="<?php echo set_value('identity'); ?>" size="50" />
                        </div> <!-- /field -->
                        <div class="field">                            
                             <input class="login password-field" type="password" placeholder="password" name="password" value="<?php echo set_value('password'); ?>" size="50" />
                        </div> <!-- /password -->
                    </div> <!-- /login-fields -->
                     <input type="hidden" name="<?= $this->security->get_csrf_token_name(); ?>" value="<?= $this->security->get_csrf_hash() ?>" />
                    <?php echo form_submit('submit', 'Sign In', 'class="button btn btn-secondary btn-large"'); ?>  
                </form>
            </div> <!-- /content -->
        </div> <!-- /account-container -->
        <!-- Text Under Box -->
        <div class="login-extra">
            
        </head>

        <body >

            <div id="txt"></div>
    </div> <!-- /login-extra -->
</body>
</html> 

<!-- end document-->





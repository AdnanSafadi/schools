$("document").ready(function () {




 
 

            $("#notification_").submit(function () {

                $(".alert-success").css("visibility", "hidden");



			if (($.trim($("#message_title").val())).length == 0) {
	                    $(".err_").html($("#fill_title").html());
	                    return false;
	                }

	                if (($.trim($("#message_text").val())).length == 0) {
	                    $(".err_").html($("#fill_message_text").html());
	                    return false;
	                }
	
	               
	
	         
	                if (!confirm($("#confirm_to_send").html())) {
	                        return false;
	                }
	               


            });




  });


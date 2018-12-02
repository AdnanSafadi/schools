$("document").ready(function () {



    var $remaining = $('#remaining'), $messages = $remaining.next();


    var ar_character_number = 70;
    $('#message_text').keyup(function () {
        var chars = this.value.length,
                messages = Math.ceil(chars / ar_character_number),
                remaining = messages * ar_character_number - (chars % (messages * ar_character_number) || messages * ar_character_number);

        $remaining.text(' عدد المحارف المتبقية  ' + remaining);
        $messages.text(' عدد الرسائل  ' + messages);

    });


    // add phone number 
    $("#add_number").click(function () {
        var txt = $('#number').val();
        if ($.trim((txt)) != "") {
            $("ol").append("<li>" + txt + "</li>");
            $('#number_list').append('<option value="' + $.trim((txt)) + '" selected="selected">' + $.trim((txt)) + '</option>');
        } else {
            alert($("#add_mobile_number").html());
        }
    });



    $("#delete_all").click(function () {
        $("ol").text("");
        $('#number_list').text('');
    });


    document.getElementById("filename").onchange = function () {
        document.getElementById("uploadFile").value = this.value;
    };


    $("#filename").change(function (e) {
        var ext = $("input#filename").val().split(".").pop().toLowerCase();

        if ($.inArray(ext, ["csv"]) == -1) {
            alert($("#select_csv_file").html());
            return false;
        }

        if (e.target.files != undefined) {
            var reader = new FileReader();
            reader.onload = function (e) {
                //var csvval = e.target.result.split("\n");
                var csvval = e.target.result.split(/(D|S|\r\n|\n|[^DS\r\n]+)/);
                for (var i = 0; i < csvval.length; i++) {
                    if (csvval[i] != "" && csvval[i] != "\r" && csvval[i] != "\r\n" && csvval[i] != "\n") {
                        $("ol").append("<li>" + csvval[i] + "</li>");
                        $('#number_list').append('<option value="' + $.trim((csvval[i])) + '" selected="selected">' + $.trim((csvval[i])) + '</option>');
                    }
                }
            };
            reader.readAsText(e.target.files.item(0));
        }
        return false;
    });







    // input is numeric only 
    $("#number").keydown(function (e) {
        // Allow: backspace, delete, tab, escape, enter and .
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
                // Allow: Ctrl+A
                        (e.keyCode == 65 && e.ctrlKey === true) ||
                        // Allow: home, end, left, right, down, up
                                (e.keyCode >= 35 && e.keyCode <= 40) ||
                                // plus             
                                        (e.keyCode == 61)) {
                            // let it happen, don't do anything
                            return;
                        }
                        // Ensure that it is a number and stop the keypress
                        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
                            e.preventDefault();
                        }
                    });

            $("#sms_").submit(function () {

                $(".alert-success").css("visibility", "hidden");


                if (($.trim($("#message_text").val())).length == 0) {
                    $(".err_").html($("#fill_message_text").html());
                    return false;
                }

                if ($('#number_list option').length == 0) {
                    $(".err_").html($("#fill_numbers").html());
                    return false;
                }

                var b = true;
                $("ol li").each(function ()
                {

                    if ($(this).text().match(/^[a-zA-Z\s]+$/)) {

                        $(this).addClass("alert-danger");
                        // $(this).css("background" , "red");
                        $(this).css("padding", "5px");
                        $(".err_").html($("#number_not_correct").html());
                        b = false;
                    }
                });

                if (b == false) {
                    return false;
                }


                if (b == true) {
                    if (!confirm($("#confirm_to_send").html())) {
                        return false;
                    }
                }


            });




        });


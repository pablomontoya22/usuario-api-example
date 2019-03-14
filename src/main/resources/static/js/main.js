var postId, userId, menuItems = $("#sidebar-menu a");

$(document).ready(function() {
	$('#clients').addClass('display').DataTable();
    var dialog = $('#client-form').dialog({
    	title: "Cliente",
        resizable: false,
        autoOpen: false,
        height: "auto",
        width: 700,
        modal: true,
        buttons: {
          'Crear': function() {
            $( this ).dialog( "close" );
          },
          'Cancelar': function() {
            $( this ).dialog( "close" );
          }
        }
      });

    $('#add-client').click(function() {
    	dialog.dialog("open");
    });

    menuItems.click(function() {
    	menuItems.removeClass("active");
    	$(this).addClass("active");
    });
});
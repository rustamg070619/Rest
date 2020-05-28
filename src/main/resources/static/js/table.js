$(document).ready(function() {
    $.getJSON('http://localhost:8080/admin/all', function(json) {
        var tr=[];
        for (var i = 0; i < json.length; i++) {
            tr.push('<tr>');
            tr.push('<td>' + json[i].id + '</td>');
            tr.push('<td>' + json[i].login + '</td>');
            tr.push('<td>' + json[i].password + '</td>');
            tr.push('<td>' + (json[i].roles[0]).role + '</td>');
            tr.push('<td><button class=btn btn-primary>Edit</button>&nbsp;&nbsp;<button class=\'delete\' id=' + json[i].id + '>Delete</button></td>');
            tr.push('</tr>');
        }
        $('table').append($(tr.join('')));
    });

    $(document).delegate('#addNew', 'click', function(event) {
        event.preventDefault();

        var login = $('#login').val();
        var password = $('#password').val();
        var role = $('#role').val();
        var roles = [role];

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/admin/save",
            data: JSON.stringify({'login': login, 'password': password, roles: roles}),
            cache: false,
            success: function(result) {
                $("#msg").html( "<span style='color: green'>Company added successfully</span>" );
                window.setTimeout(function(){location.reload()},1000)
            },
            error: function(err) {
                $("#msg").html( "<span style='color: red'>Name is required</span>" );
            }
        });
    });

    $(document).delegate('.delete', 'click', function() {
        if (confirm('Do you really want to delete record?')) {
            var id = $(this).attr('id');
            var parent = $(this).parent().parent();
            $.ajax({
                type: "DELETE",
                url: "http://localhost:8080/admin/delete/" + id,
                cache: false,
                success: function() {
                    parent.fadeOut('slow', function() {
                        $(this).remove();
                    });
                    location.reload(true)
                },
                error: function() {
                    $('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error deleting record').fadeIn().fadeOut(4000, function() {
                        $(this).remove();
                    });
                }
            });
        }
    });

    $(document).delegate('.edit', 'click', function() {
        var parent = $(this).parent().parent();

        var id = parent.children("td:nth-child(1)");
        var login = parent.children("td:nth-child(2)");
        var password = parent.children("td:nth-child(3)");
        var roles = parent.children("td:nth-child(4)");
        var buttons = parent.children("td:nth-child(5)");

        login.html("<input type='text' id='txtName' value='" + login.html() + "'/>");
        password.html("<input type='text' id='txtPassword' value='" + password.html() + "'/>");
        roles.html("<input type='text' id='txtRoles' value='" + roles.html() + "'/>");
        buttons.html("<button id='save'>Save</button>&nbsp;&nbsp;<button class='delete' id='" + id.html() + "'>Delete</button>");
    });

    $(document).delegate('#save', 'click', function() {
        var parent = $(this).parent().parent();

        var id = parent.children("td:nth-child(1)");
        var login = parent.children("td:nth-child(2)");
        var password = parent.children("td:nth-child(3)");
        var role = parent.children("td:nth-child(4)");
        var buttons = parent.children("td:nth-child(5)");

        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/admin/save",
            data: JSON.stringify({'id' : id.html(), 'login' : login.children("input[type=text]").val(), 'password' : password.children("input[type=text]").val()}),
            cache: false,
            success: function() {
                login.html(login.children("input[type=text]").val());
                password.html(password.children("input[type=text]").val());
                buttons.html("<button class='edit' id='" + id.html() + "'>Edit</button>&nbsp;&nbsp;<button class='delete' id='" + id.html() + "'>Delete</button>");
            },
            error: function() {
                $('#err').html('<span style=\'color:red; font-weight: bold; font-size: 30px;\'>Error updating record').fadeIn().fadeOut(4000, function() {
                    $(this).remove();
                });
            }
        });
    });

});
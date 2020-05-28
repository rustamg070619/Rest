$(document).ready(function () {

    allUsers();

    $('#btnAddUser').click(function () {
        var login = $('#LoginInput').val();
        var password = $('#PasswordInput').val();
        var role = $('#RoleInput').val();
        var roles = [role];
        $.ajax({
            type: "POST",
            contentType: "application/json; charset=utf-8",
            url: "http://localhost:8080/admin/save",
            data: JSON.stringify({'login': login, 'password': password, roles: roles}),
            cache: false,
            success: function (result) {
                window.setTimeout(function () {
                    location.reload()
                }, 1000)
            },
            error: function (err) {
                alert(err);
            }
        })
    });

});

function allUsers() {
    $.getJSON("/admin/all", function (data) {

        let userRow = '';
        let userId = '';
        $.each(data, function (key, value) {
            userRow += '<tr>';
            userRow += '<td>' + value.id + '</td>>';
            userId = value.id;
            userRow += '<td>' + value.login + '</td>>';
            userRow += '<td>' + value.password + '</td>>';
            userRow += '<td>' + value.roles[0].role + '</td>>';
            userRow += '<td><a class="btn btn-info" id="btnEditUser' + value.id + '" data-toggle="modal" data-target="#editModal" onclick="editUser(' + value.id + ')" role="button">Edit</a></td>';
            userRow += '<td><a class="btn btn-danger" id="btnDeleteUser" onclick="deleteUser(' + value.id + ')" role="button">Delete</a>' + '</td>';
            userRow += '</tr>';
        });
        $('#tableBootstrap').append(userRow);
    });
}

function editUser(id) {
    $.getJSON("/admin/get/" + id, function (data) {
        var idEdit = data.id;
        var login = data.login;
        var password = data.password;
        $('#IdEditModal').html(idEdit);
        $('#LoginEditModal').html(login);
        $('#PasswordEditModal').html(password);
        return false;
    });
}

function deleteUser(id) {
    $.ajax({
        type: "DELETE",
        url: "http://localhost:8080/admin/delete/" + id,
        cache: false,
        success: function () {
            window.setTimeout(function () {
                location.reload()
            }, 1000)
        },
        error: function () {
            alert("some error from delete!");
        }
    });
}


document.addEventListener("DOMContentLoaded", function (Event) {
    eventForUserPage();
    addUserBtnEvent();

});

function addUserBtnEvent() {
    document.getElementById("addBtn")
        .addEventListener('click', function (event) {
            event.preventDefault();
            let href = this.getAttribute('href');
            fetch(href).then(response => response.text()).then(fragment => {
                document.querySelector('#addModal').innerHTML = fragment;
            }).then(() => {
                let modal = new bootstrap.Modal(document.querySelector('#addModal'), {});
                modal.show();
                document.getElementById("add_user").addEventListener
                ('submit', event => submitNewUserForm(event))
            })
        })

}

function editAsyncFetch(href) {
    fetch(href).then(response => response.text().then(fragment => {
        document.querySelector('#editModal').innerHTML = fragment;
    })).then(() => {
        let modal = new bootstrap.Modal(document.querySelector('#editModal'), {});
        modal.show();
        document.getElementById("edit_user")
            .addEventListener('submit', event => submitEditUserForm(event))
    })
}

function editEventButton(el) {
    el.addEventListener('click', function (event){
        event.preventDefault();
        let href = this.getAttribute('href');
        editAsyncFetch(href);
    })
}

function editEventRow(el){
    el.addEventListener('dblclick', function (event) {
        event.preventDefault();
        let editBtn = el.querySelector('.editBtn');
        let href = editBtn.getAttribute('href');
        editAsyncFetch(href);
    })
}

function eventForUserPage() {
    document.querySelectorAll('table .editBtn')
        .forEach(editBtn => editEventButton(editBtn));

    document.querySelectorAll('table tr')
        .forEach(tr => editEventRow(tr));


    document.querySelectorAll('.table .deleteBtn').forEach(deleteBtn => deleteBtn
        .addEventListener('click', function (event) {
        event.preventDefault();
        let href = this.getAttribute('href')
        document.querySelector('#deleteModal .modal-footer a').setAttribute('href', href)
        let modal = new bootstrap.Modal(document.querySelector('#deleteModal'), {});
        modal.show();
        document.getElementById('delUser')
            .addEventListener('click', function (event) {
                event.preventDefault();
                fetch(href).then(response => response.text()).then(fragment => {
                    document.querySelector(".user_list").innerHTML = fragment;
                    modal.hide();
                    eventForUserPage();
                })
            })
    }))


}


async function submitNewUserForm(event) {
    event.preventDefault();

    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });

    const param = new URLSearchParams({
        "login": formData.get('login'),
    });
    fetch("users/checkLogin?" + param).then(response => {
        if (response.ok) {
            saveUser(request)
        } else {
            return Promise.reject(response);
        }
    }).catch(error => error.text()).then(errorFr => {
        document.querySelector("#addModal .custom-alert").innerHTML = errorFr;
    });


}


async function submitEditUserForm(event) {
    event.preventDefault();
    console.log(event)
    let formData = new FormData(event.target),
        request = new Request(event.target.action, {
            method: 'POST',
            body: formData
        });
    let response = await fetch(request);
    let userTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('editModal'))
    modal.hide();
    document.querySelector(".user_list").innerHTML = userTable;

    eventForUserPage();

}

async function saveUser(request) {

    let response = await fetch(request);
    let userTable = await response.text();
    let modal = bootstrap.Modal.getInstance(document.getElementById('addModal'))
    modal.hide();
    document.querySelector(".user_list").innerHTML = userTable;

    eventForUserPage();

}

async function search() {
    await console.log('Search from JS')

    let table = document.getElementById('tableUser');
    let searchString = document.getElementById('search').value.trim();
    console.log(searchString)
    const param = new URLSearchParams({
        "filterText": searchString,
    });

    let response = await fetch("users/filter?" + param);
    console.log(response);
    let users = await response.text();
    console.log(users);
    document.querySelector(".user_list").innerHTML = users;
    eventForUserPage();

}

const PORT = 8080
const loginButton = document.getElementById('loginButton');

loginButton.addEventListener('click', () => {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", `http://localhost:${PORT}/login`, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify({
        userName: document.getElementById('login').value,
        password: document.getElementById('password').value
    }));
})

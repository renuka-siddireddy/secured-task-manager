let token = "";

function register() {

    const username = document.getElementById("regUsername").value.trim();
    const password = document.getElementById("regPassword").value.trim();

    fetch("/api/v1/auth/register", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(res => res.text())
    .then(data => showOutput(data));
}

function login() {

    const username = document.getElementById("loginUsername").value.trim();
    const password = document.getElementById("loginPassword").value.trim();

    fetch("/api/v1/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, password })
    })
    .then(res => res.json())
    .then(data => {
        token = data.token;
        showOutput("Login successful!");
    })
    .catch(() => showOutput("Login failed"));
}

function createTask() {

    if (!token) {
        showOutput("Please login first!");
        return;
    }

    const title = document.getElementById("taskTitle").value;

    fetch("/api/v1/tasks", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({ title })
    })
    .then(res => res.json())
    .then(data => showOutput(JSON.stringify(data, null, 2)));
}

function getTasks() {

    if (!token) {
        showOutput("Please login first!");
        return;
    }

    fetch("/api/v1/tasks", {
        headers: {
            "Authorization": "Bearer " + token
        }
    })
    .then(res => res.json())
    .then(data => showOutput(JSON.stringify(data, null, 2)));
}

function showOutput(message) {
    document.getElementById("output").innerText = message;
}
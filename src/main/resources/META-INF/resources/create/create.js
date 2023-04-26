const form = document.querySelector('form');

form.addEventListener('submit', (event) => {
  event.preventDefault();
  postTweet();
});


function postTweet() {
    const textarea = document.querySelector('textarea');
    console.log(textarea.value)
    const formData = {
        "text": textarea.value,
        "user": {
            "id": "12345",
            "name": "carol",
            "email": "carol@mail.com",
            "password": "123",
        },
    }
    console.log(formData)
    const headers = new Headers();
    headers.append('Content-Type', 'application/json');
    fetch("http://localhost:8080/tweets", {
        method: 'POST',
        headers: headers,
        body: JSON.stringify(formData)
    })
    .then(response => {
        window.location = "../post/post.html";
    })
    .catch(error => {
        console.error(error);
    });
}
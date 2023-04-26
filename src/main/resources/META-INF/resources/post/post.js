
fetch('http://localhost:8080/tweets')
  .then(response => response.json())
  .then(data => {
    console.log(data);
    data.forEach(value => createTweet(value))
  })
  .catch(error => console.error('Error al obtener los datos', error));

  function createTweet(data){
  console.log(data)
  const miDiv = document.getElementById('tweets');
  const tweetDiv = document.createElement('div');
  tweetDiv.classList.add('tweet');

  const profileImg = document.createElement('img');
  profileImg.src = 'https://arc-anglerfish-arc2-prod-infobae.s3.amazonaws.com/public/DWNLMYWKHBAOJIBBR5SRCRUQ4Q';
  profileImg.classList.add('profile-img');

  const tweetContent = document.createElement('div');
  tweetContent.classList.add('tweet-content');

  const tweetAuthor = document.createElement('p');
  tweetAuthor.classList.add('tweet-author');
  tweetAuthor.textContent = data.user.name;

  const tweetText = document.createElement('p');
  tweetText.classList.add('tweet-text');
  tweetText.textContent = data.text;

  const tweetDate = document.createElement('p');
  tweetDate.classList.add('tweet-date');
  tweetDate.textContent = data.date;

  tweetContent.appendChild(tweetAuthor);
  tweetContent.appendChild(tweetText);
  tweetContent.appendChild(tweetDate);

  tweetDiv.appendChild(profileImg);
  tweetDiv.appendChild(tweetContent);

  miDiv.appendChild(tweetDiv);

  }

function clickbutton() {
    window.location = "../create/create.html";
}

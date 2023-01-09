



function register(){
    let _username = document.getElementById("username").value;
    let _password = document.getElementById("password").value;
    let __type = document.getElementById("type");
    var value = __type.options[__type.selectedIndex].value;
    // let _type = __type.options[__type.selectedIndex].text;
    // console.log(username + password)
    console.log(value)

    fetch('http://localhost:8080/user/register', {
        method: 'post',
        body: JSON.stringify({
            username: _username,
            password: _password,
            type: value

        }),
        headers: new Headers({'content-type': 'application/json',
                            'authorization': 'Bearer ',
                            'Access-Control-Allow-Origin':  'http://localhost:8080',
                            'Access-Control-Allow-Methods': 'POST',
                            'Access-Control-Allow-Headers': 'Content-Type, Authorization'
                        }),
        }, console.log("registered")
    )
    .then( async (response) => {
        let data = await response.json();
        console.log(response.status)
        if(response.status === 200){
            console.log(data);
            location.href = 'loginPage.html';
        }
    })
}





// function login(){
//     let _username = document.getElementById("username").value;
//     let _password = document.getElementById("password").value;
//     // let _type = __type.options[__type.selectedIndex].text;
//     // console.log(username + password)

//     fetch('http://localhost:8080/user/login', {
//         method: 'post',
//         mode: 'no-cors',
//         body: JSON.stringify({
//             username: _username,
//             password: _password
//         }),
//         headers: new Headers({  'content-type': 'application/json',
//                                 'authorization': 'Bearer ',
//                             }),
//         }
//     )
//     .then( async (response) => {
//         let data = await response.json();
//         console.log(response.status)
//         if(response.status === 200){
//             console.log(data);
//             sessionStorage.setItem("token", data['token'])
//             sessionStorage.setItem("username", _username)
//             location.href = 'mainPage.html';
//         }
//     })
// }


// function login() {
//     let _text = document.getElementById("username").value;
//     console.log(_text);
//     let _text2 = document.getElementById("password").value;
//     console.log(_text2);

//     fetch('http://localhost:8080/user/login', {
//         method: 'post',
//         body: JSON.stringify({
//             username: _text,
//             password: _text2
//         }),
//         headers: new Headers({'content-type': 'application/json',
//         'authorization': 'Bearer ',
//         'Access-Control-Allow-Origin':  '*',
//         'Access-Control-Allow-Methods': 'POST',
//         'Access-Control-Allow-Headers': 'Content-Type, Authorization'
//                         }),
//         }, console.log("login")
//     )
//     .then( async (response) => {
//         response.headers({
//             'Access-Control-Allow-Origin': '*'
//         })
//         console.log(response.headers)
//         // get json response here
//         let data = await response.json();
//         console.log(response.status)
//         if(response.status === 200){
//             console.log(data);
//             // token = data['token'];
//             sessionStorage.setItem('loginToken', data['token']);
//             location.href = 'mainPage.html';
//             // console.log(token);
//             sessionStorage.setItem('username', _text);
//         }
//     })
// }


function login(){

    var myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer ");
myHeaders.append("Content-Type", "application/json");

let username_ = document.getElementById("username").value
let password_ = document.getElementById("password").value

var raw = JSON.stringify({
  "username": username_,
  "password": password_
});

var requestOptions = {
  method: 'POST',
  headers: myHeaders,
  body: raw,
  redirect: 'follow'
};

fetch("http://localhost:8080/user/login", requestOptions)
  .then(async (response) => {
    if(response.status === 200){
        let data = await response.json();
        console.log(data);
        sessionStorage.setItem("loginToken", data['token'])
        sessionStorage.setItem("currentUsername", username_)
        location.href = 'homePage.html'
    }
    

  })
  .then(result => console.log(result))
  .catch(error => console.log('error', error));

  
}


function getAwolled(){
  var myHeaders = new Headers();
myHeaders.append("Authorization", "Bearer " + sessionStorage.getItem("loginToken"));

var requestOptions = {
  method: 'GET',
  headers: myHeaders,
  redirect: 'follow'
};

fetch("localhost:8080/unit/allowed", requestOptions)
  .then(response => response.text())
  .then(async (result) => {
    if(response.status === 200){
      let data = await response.json()
      for(let i = 0; i < length(data); i++){
        console.log(data[i]['title'])
      }
      sessionStorage.setItem("data", data[0]['title'])
    }
    
  })
  .catch(error => console.log('error', error));
}


function allowed(){
  var myHeaders = new Headers();
  myHeaders.append("Authorization", "Bearer " + sessionStorage.getItem("loginToken"));

  var requestOptions = {
    method: 'GET',
    headers: myHeaders,
    redirect: 'follow'
  };

  fetch("http://localhost:8080/unit/allowed", requestOptions)
    .then(async(response) => {
      
      if(response.status === 200){


        





        let data = await response.json()
        console.log(data)
        let mainDiv = document.getElementById("div")

        // const imageBuffer = new Buffer.from(data[0]["video"], 'bse64')
        // fs.writeFileSync('foo.jpeg', imageBuffer)

        for(var i = 0; i < data.length; i++){
          let div = document.createElement("div")


          let unitTitleHTML = document.createElement("p")
          let grade = document.createElement("p")
          let text = document.createElement("p")

          div.appendChild(text)
          div.appendChild(unitTitleHTML)
          div.appendChild(grade)

          unitTitleHTML.appendChild(document.createTextNode(data[i]["grade"]))
          grade.appendChild(document.createTextNode(data[i]["text"]))
          text.appendChild(document.createTextNode(data[i]["title"]))

          mainDiv.appendChild(div)
        }
      }

    })
    .then(result => console.log(result))
    .catch(error => console.log('error', error));
}

function logout(){
  sessionStorage.setItem('loginToken', null)
  sessionStorage.setItem('currentUsername', null)
  location.href = "mainPage.html"
}

var OV;
var session;

function joinSession() {
  var mySessionId = document.getElementById("sessionId").value;

  OV = new OpenVidu();
  session = OV.initSession();

  session.on("streamCreated", function (event) {
    session.subscribe(event.stream, "subscriber");
  });

  session.on('signal', function (event) {
	  let item = JSON.parse(event.data)
	  let card = `
		    <div class="card">
		        <img src="${item.productImgurl}" alt="Product 1">
		        <h3>${item.productName}</h3>
		        <p class="price">${item.productPrice}</p>
		    </div>
		`;
		$('#u-items').append(card);
  });
  
  getToken(mySessionId).then((token) => { 
    session
      .connect(token)
      .then(() => {
// document.getElementById("session-header").innerText = mySessionId;
        document.getElementById("join").style.display = "none";
        document.getElementById("session").style.display = "block";

        var publisher = OV.initPublisher("publisher");
        session.publish(publisher);
      })
      .catch((error) => {
        console.log(
          "There was an error connecting to the session:",
          error.code,
          error.message
        );
      });
  });
}

function leaveSession() {
  session.disconnect();
  document.getElementById("join").style.display = "block";
  document.getElementById("session").style.display = "none";
}

window.onbeforeunload = function () {
  if (session) session.disconnect();
};

function sendMessage(product) {
 session.signal({
 data: JSON.stringify(product),
 to: [],
 type: 'my-chat'
 })
 .then(() => {
 console.log("전송성공")
 })
 .catch((error) => {
 console.error(error)
 })
}

var APPLICATION_SERVER_URL = "https://192.168.0.89/theBeauty/";

function getProducts(roleName) {
      // AJAX GET 요청
      $.ajax({
        url: APPLICATION_SERVER_URL + 'product/list/' + roleName,
        type: 'GET',
        success: function(data) {
        	for(let i = 0; i < 20; i++) {
        		let card = `
        		    <div class="card">
        		        <img src="${data[i].productVO.productImgurl}" alt="Product 1">
        		        <h3>${data[i].productVO.productName}</h3>
        		        <p class="price">${data[i].productVO.productPrice}</p>
        		        <button onclick="sendMessage(${JSON.stringify(data[i].productVO).replace(/"/g, '&quot;')})">전송</button>
        		    </div>
        		`;
        		$('#c-items').append(card);
            }
  
        },
        error: function() {
          // 요청이 실패했을 때 실행되는 콜백 함수
          console.log('데이터를 불러오는데 실패했습니다.');
        }
      });
}

/**
 * -------------------------------------------- GETTING A TOKEN FROM YOUR
 * APPLICATION SERVER -------------------------------------------- The methods
 * below request the creation of a Session and a Token to your application
 * server. This keeps your OpenVidu deployment secure.
 * 
 * In this sample code, there is no user control at all. Anybody could access
 * your application server endpoints! In a real production environment, your
 * application server must identify the user to allow access to the endpoints.
 * 
 * Visit https://docs.openvidu.io/en/stable/application-server to learn more
 * about the integration of OpenVidu in your application server.
 */


function getToken(mySessionId) {
  return createSession(mySessionId).then((sessionId) => createToken(sessionId));
}

function createSession(sessionId) {
  return new Promise((resolve, reject) => {
    $.ajax({
      type: "POST",
      url: APPLICATION_SERVER_URL + "api/sessions",
      data: JSON.stringify({ customSessionId: sessionId }),
      headers: { "Content-Type": "application/json" },
      success: (response) => resolve(response), // The sessionId
      error: (error) => reject(error),
    });
  });
}

function createToken(sessionId) {
  return new Promise((resolve, reject) => {
    $.ajax({
      type: "POST",
      url:
        APPLICATION_SERVER_URL + "api/sessions/" + sessionId + "/connections",
      data: JSON.stringify({}),
      headers: { "Content-Type": "application/json" },
      success: (response) => resolve(response), // The token
      error: (error) => reject(error),
    });
  });
}

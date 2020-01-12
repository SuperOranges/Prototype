$("button").click(function () {
    $("#test").attr({
        "src": "a.html"
    });
});



function receiveMessage(event){
    var origin = event.origin || event.originalEvent.origin;
    if(origin !== window.location.href){
        return;
    }
    
    console.log("aaaaaaaaaaaaaa");
    console.log("aaaaaaaaaaaaaa");
    console.log(event);

}

window.addEventListener("message",receiveMessage,false);
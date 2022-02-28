let image_data_url = null;

let camera_button = document.querySelector("#start-camera");
let video = document.querySelector("#video");
let click_button = document.querySelector("#click-photo");
let canvas = document.querySelector("#canvas");
var cam = document.getElementById("video");
var img = document.getElementById("img");


camera_button.addEventListener('click', async function() {
    let stream;
    var userAgent = navigator.userAgent || navigator.vendor || window.opera;
    if (/android/i.test(userAgent)) {
        stream = await navigator.mediaDevices.getUserMedia({video: {facingMode: {exact: "environment"}}});
    } else {
        stream = await navigator.mediaDevices.getUserMedia({video: true});
    }

       //alert(stream);

       cam.style.display = "inline";
       img.style.display = "none";

	video.srcObject = stream;
});

function dataURLtoFile(dataurl, filename) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
        bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
        u8arr[n] = bstr.charCodeAt(n);
    }
    return new File([u8arr], filename, {type:mime});
}

click_button.addEventListener('click', function() {
    canvas.getContext('2d').drawImage(video, 0, 0, canvas.width, canvas.height);
    image_data_url = canvas.toDataURL('image/jpg');

    var formData = new FormData();
    // $("#myFile")[0].files[0]

    var file = dataURLtoFile(image_data_url, 'foto.png');

    formData.append("image", file);

    $.ajax({
        url: "https://api.imgur.com/3/image",
        type: "POST",
        datatype: "json",
        headers: {
            "Authorization": "Client-ID 67574d630dfa075"
        },
        data: formData,
        success: function(response) {
            console.log(response);
            var photo = response.data.link;

            var imagen = document.getElementById("imagen");
            imagen.value = photo;
            img.src = photo;

            var photo_hash = response.data.deletehash;
        },
        cache: false,
        contentType: false,
        processData: false
    });

    // data url of the image
    //console.log(image_data_url);

    cam.style.display = "none";
    img.style.display = "inline";
});

function comprobarFoto() {
    var imagen = document.getElementById("imagen");
    var img = document.getElementById("img");
    if (imagen.value !== "") {
        img.src = imagen.value;
    }
}
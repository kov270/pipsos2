document.addEventListener("DOMContentLoaded", function (event) {
    drawCanvas("canvas", 1);
    var buttons = $(".b");
    var view = $(".x-panel");

    buttons.click(function () {
        view.val($(this).val())
    });

});

function validate(_form) {
    var fail = false;
    var X = _form.X.value;
    var Y = _form.Y.value;
    var R = _form.R.value;

    if (Y <= -3 || Y >= 5 || isNaN(Y) || Y === "" || Y.length > 10) {
        fail = "Y value is incorrect ! \n";
    }

    if (fail) {
        alert(fail);
        return false;
    } else {
        createCanvas('canvas', X, Y, R);
        return true;
    }

}

function drawCanvas(id, r) {
    var canvas = document.getElementById(id),
        context = canvas.getContext("2d");
    //очистка
    context.clearRect(0, 0, canvas.width, canvas.height);

    //прямоугольник
    context.beginPath();
    context.rect(20, 150, 130, 130);
    context.closePath();
    context.strokeStyle = "#ffe5dc";
    context.fillStyle = "#ffe5dc";
    context.fill();
    context.stroke();

    // сектор
    context.beginPath();
    context.moveTo(150, 150);
    context.arc(150, 150, 130, 0, -Math.PI / 2, true);
    context.closePath();
    context.strokeStyle = "#ffe5dc";
    context.fillStyle = "#ffe5dc";
    context.fill();
    context.stroke();

    //треугольник
    context.beginPath();
    context.moveTo(150, 150);
    context.lineTo(150, 215);
    context.lineTo(150 + 130, 150);
    context.lineTo(150, 150);
    context.closePath();
    context.strokeStyle = "#ffe5dc";
    context.fillStyle = "#ffe5dc";
    context.fill();
    context.stroke();

    //отрисовка осей
    context.beginPath();
    context.font = "10px Verdana";
    context.moveTo(150, 0);
    context.lineTo(150, 300);
    context.moveTo(150, 0);
    context.lineTo(145, 15);
    context.moveTo(150, 0);
    context.lineTo(155, 15);
    context.strokeStyle = "#000000";
    context.fillStyle = "#000000";
    context.fillText("Y", 160, 10);
    context.moveTo(0, 150);
    context.lineTo(300, 150);
    context.moveTo(300, 150);
    context.lineTo(285, 145);
    context.moveTo(300, 150);
    context.lineTo(285, 155);
    context.fillText("X", 290, 135);

    // деления X
    context.moveTo(145, 20);
    context.lineTo(155, 20);
    context.fillText(r, 160, 20);
    context.moveTo(145, 85);
    context.lineTo(155, 85);
    context.fillText((r / 2), 160, 78);
    context.moveTo(145, 215);
    context.lineTo(155, 215);
    context.fillText(-(r / 2), 160, 215);
    context.moveTo(145, 280);
    context.lineTo(155, 280);
    context.fillText(-r, 160, 280);
    // деления Y
    context.moveTo(20, 145);
    context.lineTo(20, 155);
    context.fillText(-r, 20, 170);
    context.moveTo(85, 145);
    context.lineTo(85, 155);
    context.fillText(-(r / 2), 70, 170);
    context.moveTo(215, 145);
    context.lineTo(215, 155);
    context.fillText((r / 2), 215, 170);
    context.moveTo(280, 145);
    context.lineTo(280, 155);
    context.fillText(r, 280, 170);

    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();
}

function createCanvas(id, x, y, r) {
    drawCanvas(id, r);
    var canvas = document.getElementById(id),
        context = canvas.getContext("2d");
    context.beginPath();
    context.rect(Math.round(150 + ((x / r) * 130)) - 2, Math.round(150 - ((y / r) * 130)) - 2, 4, 4);
    context.closePath();
    context.strokeStyle = "red";
    context.fillStyle = "red";
    context.fill();
    context.stroke();
}

function clickCanvas(canvId, R) {
    var elem = document.getElementById(canvId);
    var br = elem.getBoundingClientRect();
    var left = br.left;
    var top = br.top;
    var event = window.event;
    var x = event.clientX - left;
    var y = event.clientY - top;

    var transf_x = R * (x - 150) / 130;
    var transf_y = R * (150 - y) / 130;

    $.ajax({
        url: "check",
        type: "POST",
        data: {"X": transf_x, "Y": transf_y, "R": R, "silent": "on"},
        success: function (data) {
            console.log(data);
            drawPoint(canvId, x, y, data["in_area"]);
            $(".tbl").append(data['data'])
        },
        error: function () {
            alert("Some error processing request")
        }
    });
}


function drawPoint(id, x, y, isArea) {
    var canvas = document.getElementById(id),
        context = canvas.getContext("2d");

    context.beginPath();
    context.ellipse(x - 1, y - 1, 2, 2, 1, 0, 2 * Math.PI, true);
    context.closePath();
    if (isArea) {
        context.strokeStyle = "#dc3545";
        context.fillStyle = "#dc3545";
    } else {
        context.strokeStyle = "#281845";
        context.fillStyle = "#281845";
    }
    context.fill();
    context.stroke();

}

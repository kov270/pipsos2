<%@page contentType="text/html" pageEncoding="UTF-8"
        language="java"
        session="true"
%>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Iliya Kovalenko">
    <title>Lab 2</title>
    <link rel="stylesheet" href="https://unpkg.com/terminal.css@0.7.1/dist/terminal.min.css"/>


    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="js/scripts.js"></script>
    <script src="js/jquery.arctext.js"></script>

</head>
<body>

<main role="main" class="container">
    <br><br><br>
    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-purple rounded shadow-lg">
        <div class="lh-100">
            <h2 class="mb-0 text-white lh-100 arc-text">Web Laboratory Ilya Kovalenko P3210 10782</h2>
        </div>
        <div class="slidecontainer">
            <input type="range" min="0" max="3000" value="3000" class="slider" id="myRange">
        </div>
    </div>

    <div class="my-3 p-3 bg-white rounded shadow-lg">
        <h6 class="pb-2 mb-0 arc-text">Enter Data:</h6>
        <div class="container">
            <div class="card-deck">
                <div class="card">
                    <form class="form card-body" id="form" action="check" method="post"
                          onsubmit="return validate(this);">
                        <table class="table radio_btn">
                            <tr>
                                <th scope="col"></th>
                                <th scope="col"><input class="btn b" type="button" id="-3" value="-3"></th>
                                <th scope="col"><input class="btn b" type="button" id="-2" value="-2"></th>
                                <th scope="col"><input class="btn b" type="button" id="-1" value="-3"></th>
                            </tr>
                            <tr>
                                <td scope="col"> Y =</td>
                                <th scope="col"><input class="btn b" type="button" id="0" value="0"></th>
                                <th scope="col"><input class="btn b" type="button" id="1" value="1"></th>
                                <th scope="col"><input class="btn b" type="button" id="2" value="2"></th>
                            </tr>
                            <tr>
                                <th scope="col"></th>
                                <th scope="col"><input class="btn b" type="button" id="3" value="3"></th>
                                <th scope="col"><input class="btn b" type="button" id="4" value="4"></th>
                                <th scope="col"><input class="btn b" type="button" id="5" value="5"></th>
                            </tr>
                        </table>
                        <input type="number" class="x-panel arc-text" name="X" value="0" readonly="true" REQUIRED>
                        <br><br>

                        <label for="X" class="arc-text"> X = </label>
                        <input class="input_X arc-text" id="X" type="text" name="Y" placeholder="(-3 ... 5)" REQUIRED><br>

                        <label> R = </label>
                        <input type="radio" name="R" CHECKED value="1" onclick="drawCanvas('canvas', 1)">1</input>
                        <input type="radio" name="R" value="1.5" onclick="drawCanvas('canvas', 1.5)">1.5</input>
                        <input type="radio" name="R" value="2" onclick="drawCanvas('canvas', 2)">2</input>
                        <input type="radio" name="R" value="2.5" onclick="drawCanvas('canvas', 2.5)">2.5</input>
                        <input type="radio" name="R" value="3" onclick="drawCanvas('canvas', 3)">3</input>
                        <br>
                        <small class="d-block text-left mt-3">
                            <input class="btn" type="submit" name="submit" value="Send">
                            <a href="check" class="btn arc-text">Show results</a>
                        </small>
                    </form>
                </div>
                <br>
                <div class="card">
                    <div class="card-body"
                         style="min-height: 300px;min-width: 300px; max-height: 300px;max-width: 300px">
                        <canvas id="canvas" onclick="clickCanvas('canvas', document.getElementById('form').R.value)"
                                style="background-color:#ffffff;" width="300"
                                height="300"></canvas>
                    </div>
                </div>

                <div class="my-3 p-3 bg-white rounded shadow-sm">
                    <table class="table table-bordered tbl">
                        <tr>
                            <td class="arc-text">X</td>
                            <td class="arc-text">Y</td>
                            <td class="arc-text">R</td>
                            <td class="arc-text">Result</td>
                        </tr>
                        ${table}
                    </table>
                    <br>
                </div>

            </div>

        </div>

    </div>

</main>
</body>
<script>
    $('.arc-text').arctext({radius: 3000, rotate: false, dir: -1});

    $("#myRange").change(function(){
        $('.arc-text').arctext('set', {radius: $(this).val(), rotate: false, dir: -1});
    })
</script>
</html>

$(document).ready(function (){
    $('#draw').click(function () {

        $('#planetCanvas').remove();
        $('#divCanvas').append('<canvas id="planetCanvas" width="610"'
                               +'height="310" style="border:3px solid white;"></canvas>'
                               );
        let planetCanvas = document.getElementById("planetCanvas");

        let planetOneRadius= $('#planetsOne').find(":selected").val();
        let planetTwoRadius = $('#planetsTwo').find(":selected").val();

        let ctxPlanetOne = planetCanvas.getContext("2d");
        ctxPlanetOne.beginPath();
        ctxPlanetOne.arc(152.5,155,planetOneRadius,0,2*Math.PI);
        ctxPlanetOne.fillStyle = "#c6f212";
        ctxPlanetOne.fill();
        ctxPlanetOne.stroke();

        let ctxPlanetTwo = planetCanvas.getContext("2d");
        ctxPlanetTwo.beginPath();
        ctxPlanetTwo.arc(457.5,155,planetTwoRadius,0,2*Math.PI);
        ctxPlanetTwo.fillStyle = "#2449fc";
        ctxPlanetTwo.fill();
        ctxPlanetTwo.stroke();

    });
});
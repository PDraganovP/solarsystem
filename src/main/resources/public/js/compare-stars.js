$(document).ready(function (){
    $('#compare').click(function () {

        $('#starCanvas').remove();
        $('#divCanvas').append('<canvas id="starCanvas" width="500"'
                               +'height="400" style="border:3px solid white;"></canvas>'
                               );
        let starCanvas = document.getElementById("starCanvas");

        let starOneLuminosity = $('#starsOne').find(":selected").val();
        let starTwoLuminosity = $('#starsTwo').find(":selected").val();
        let sum = +starOneLuminosity + +starTwoLuminosity;
        let percentOne =+starOneLuminosity / +sum;
        let percentTwo =+starTwoLuminosity / +sum;
        let maxHeight = 300;
        let luminosityHeightOne = +maxHeight * +percentOne;
        let luminosityHeightTwo = +maxHeight * +percentTwo;

        let ctxPlanetOne = starCanvas.getContext("2d");
        ctxPlanetOne.fillStyle = "#c6f212";
        ctxPlanetOne.fillRect(80,(+400 - +luminosityHeightOne),130,luminosityHeightOne);

        ctxPlanetOne.stroke();

        let ctxPlanetTwo = starCanvas.getContext("2d");
        ctxPlanetTwo.fillStyle = "#2449fc";
        ctxPlanetTwo.fillRect(290,(+400 - +luminosityHeightTwo),130,luminosityHeightTwo);
        ctxPlanetTwo.stroke();

    });
});
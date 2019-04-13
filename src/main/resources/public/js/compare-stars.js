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

        let ctxStarOne = starCanvas.getContext("2d");
        ctxStarOne.fillStyle = "#c6f212";
        ctxStarOne.fillRect(80,(+400 - +luminosityHeightOne),130,luminosityHeightOne);
        ctxStarOne.stroke();

        let ctxStarTwo = starCanvas.getContext("2d");
        ctxStarTwo.fillStyle = "#2449fc";
        ctxStarTwo.fillRect(290,(+400 - +luminosityHeightTwo),130,luminosityHeightTwo);
        ctxStarTwo.stroke();

    });
});
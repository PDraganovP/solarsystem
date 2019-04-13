$(document).ready(function (){
    $('#compare').click(function () {

        $('#satelliteCanvas').remove();
        $('#divCanvas').append('<canvas id="satelliteCanvas" width="500"'
                               +'height="400" style="border:3px solid white;"></canvas>'
                               );
        let satelliteCanvas = document.getElementById("satelliteCanvas");

        let satelliteOneTemperature  = $('#satellitesOne').find(":selected").val();
        let satelliteTwoTemperature  = $('#satellitesTwo').find(":selected").val();
        let sum = +satelliteOneTemperature  + +satelliteTwoTemperature ;
        let percentOne =+satelliteOneTemperature  / +sum;
        let percentTwo =+satelliteTwoTemperature  / +sum;
        let maxHeight = 300;
        let temperatureHeightOne = +maxHeight * +percentOne;
        let temperatureHeightTwo = +maxHeight * +percentTwo;

        let ctxSatelliteOne = satelliteCanvas.getContext("2d");
        ctxSatelliteOne.fillStyle = "#c6f212";
        ctxSatelliteOne.fillRect(80,(+400 - +temperatureHeightOne),130,temperatureHeightOne);
        ctxSatelliteOne.stroke();

        let ctxSatelliteTwo = satelliteCanvas.getContext("2d");
        ctxSatelliteTwo.fillStyle = "#2449fc";
        ctxSatelliteTwo.fillRect(290,(+400 - +temperatureHeightTwo),130,temperatureHeightTwo);
        ctxSatelliteTwo.stroke();

    });
});
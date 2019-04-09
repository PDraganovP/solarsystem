$(document).ready(function (){
    $('tr td button').click(function () {

        let radioAnswer = $(this).val();

        /*let text = $(this).attr('id');
        if(text==='hide'){
            $(this).closest('td').attr('id','close');
        }*/

        $('#ajax').remove();
        $('#planets').attr('id','');
        $('#hide').text('Show planets');
        $('#hide').attr('id','');



        $(this).closest('tr').attr('id','planets');
        $(this).text('Hide planets');
        $(this).attr('id','hide');

        $.ajax({
        type: "POST",
        url: "http://localhost:8080/show-planets",
        data: radioAnswer,
        dataType: "json",
        contentType:"text/plain"
    }).then(function (data, status) {

        $('#planets').after('<div id="ajax"><h3 class="text-center">All planets</h3>'
                         +'<table class="table table-hover" id="ajax-table">'    /*'<div id="ajax-table">'*/
                         +'<thead>'
                         +'<tr class="row mx-auto">'
                         +'<th class="col-md-1 text-center">#</th>'
                         +'<th class="col-md-2 text-center">Name</th>'
                         +'<th class="col-md-2 text-center">Planet type</th>'
                         +'<th class="col-md-1 text-center">Distance to the star system</th>'
                         +'<th class="col-md-1 text-center">Magnetic field</th>'
                         +'<th class="col-md-1 text-center">Atmosphere</th>'
                         +'<th class="col-md-1 text-center">Life</th>'
                         +'<th class="col-md-1 text-center">Ring</th>'
                         +'<th class="col-md-2 text-center">Actions</th>'
                         +'</tr>'
                         +'</thead>'
                         +'<tbody id="planets-data">'
                         +'</tbody>'
                         +'</table></div>');


         for(i=0;i<data.length;i++){
             $('#planets-data').append(
             '<tr class="row mx-auto"><td class="col-md-1 text-center">' + (i+1) + '</td>'
             + '<td class="col-md-2 text-center">' + data[i].name +'</td>'
             + '<td class="col-md-2 text-center">' + data[i].planetType +'</td>'
             + '<td class="col-md-1 text-center">' + data[i].distanceToStarSystem +'</td>'
             + '<td class="col-md-1 text-center">' + data[i].isThereMagneticField +'</td>'
             + '<td class="col-md-1 text-center">' + data[i].isThereAtmosphere +'</td>'
             + '<td class="col-md-1 text-center">' + data[i].isThereLife +'</td>'
             + '<td class="col-md-1 text-center">' + data[i].isThereRing +'</td>'
             + '<td class="col-md-1 text-center">'
                 + '<a type="button" class="btn btn-custom btn-sm"'
                    + 'href="/planets/edit/' + data[i].id + '">Edit planet</a>'
             + '</td>'
             + '<td class="col-md-1 text-center">'
                 + '<form action="/planets/delete/' + data[i].id + '" method="post">'
                     + '<button class="btn btn-custom btn-sm">Delete</button>'
                 +'</form>'
             + '</td></tr>');
         }
         /*if($('#close')){
            $('#ajax').remove();
            $('#close').attr('id','');
            $('#hide').text('Show planets');
            $('#hide').attr('id','');
         }*/

       });
    });
});







$(document).ready(function () {
    /*$("#nav").load("index.html #nav");*/

    $("nav a").each(function () {
        $(this).click(function () {
            $(this).classToggle("active");
        });
    });

    $(".btn").each(function () {
        $(this).click(function () {
            var $text = $(this).next("p");
            /*$(this).next("p").hide();*/
            if ($text.is(":hidden")) {
                $text.show(1000);
            } else {
                $text.hide(1500);
            }
        });
    });

    $(".btn").each(function () {
        var $text = $(this).next("p");
        $text.addClass("invisible");
    });

    $(".btn").each(function () {
        $(this).addClass("hover-effect");
    });

});




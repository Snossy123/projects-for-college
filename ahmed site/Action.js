$(document).ready(function(){
    $("button").click(function(){
        $("div")
            .css({backgroundColor:"#DDD"})
            .animate({top:"10px",
             width:"500px" })
            .fadeOut(2000)
            .fadeIn(2000)
            .slideUp(2000)
            .slideDown(2000)
            .hide(2000)
            .show(2000)
    });
});
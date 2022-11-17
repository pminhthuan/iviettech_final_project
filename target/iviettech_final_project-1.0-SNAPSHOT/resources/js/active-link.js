$(function(){
    var current = location.pathname;
    $('.main-menu li a').each(function(e){
        var $this = $(this);
        // if the current path is like this link, make it active
        if($this.attr('href').indexOf(current) !== -1){
            $('.main-menu li.active-menu').removeClass('active-menu');
            $this.parent().addClass('active-menu');
            e.preventDefault();
        } else if (current.startsWith("/shop") && $this.html() === 'Shop' ) {
            $('.main-menu li.active-menu').removeClass('active-menu');
            $this.parent().addClass('active-menu');
        }

    })
})


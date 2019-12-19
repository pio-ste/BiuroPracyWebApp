$(document).ready(function () {
    $('.webLinkLoop .editBtn').on('click', function (event) {
        event.preventDefault();
        var href = $(this).attr('href');
            $.get(href, function(webLink, status) {
                $('.myForm #urlAddress').val(webLink.urlAddress);
                $('.myForm #urlType').val(webLink.urlType);
                $('.myForm #idWebLink').val(webLink.idWebLink);
            });
            $('.myForm #exampleModal').modal();
    });
});
var Condomino = Condomino || {};

Condomino.DialogoVotacao = (function () {

    function DialogoVotacao() {
        this.exclusaoBtn = $('.js-votar-btn');
    }

    DialogoVotacao.prototype.iniciar = function () {
        this.exclusaoBtn.on('click', onVotarlicado.bind(this));

        if (window.location.search.indexOf('votou') > -1) {
            swal({
                title: "Pronto!",
                text: "Votado com sucesso.",
                timer: 1000,
                type: "success",
                showConfirmButton: false
            }, function () {
                setTimeout(function () {
                    window.location = document.URL.replace('votou', '');
                }, 1000)
            })
        }
    };

    function onVotarlicado(evento) {
        evento.preventDefault();
        var botaoClicado = $(evento.currentTarget);
        var objeto = botaoClicado.data('objeto');
        var categoria = botaoClicado.data('categoria-id');
        var usuarioSistema = botaoClicado.data('usuario-sistema');

        swal({
            title: 'Tem certeza',
            type: "warning",
            text: 'Votar "' + objeto + '" ? Você só poderá votar essa vez',
            showCancelButton: true,
            confirmButtonColor: '#1e94d2',
            confirmButtonText: 'Votar',
            closeOnConfirm: true,
            showLoaderOnConfirm: true
        }, onExcluirConfirmado.bind(this, categoria, usuarioSistema));
    }

    function onExcluirConfirmado(categoria, usuarioSistema) {
        console.log('usuarioSistema', usuarioSistema);
        $.ajax({
            url: '/votos/novo',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({categoria: {id: categoria}, usuario: {id: usuarioSistema}}),
            success: onExcluidoComSucesso.bind(this),
            error: onErroAoExcluir.bind(this)
        });
    }

    function onExcluidoComSucesso() {
        var urlAtual = window.location.href;
        var separador = urlAtual.indexOf('?') > -1 ? '&' : '?';
        var novaUrl = urlAtual.indexOf('votou') > -1 ? urlAtual : urlAtual + separador + 'votou';

        window.location = novaUrl;
    }

    function onErroAoExcluir(e) {
        console.log('erro', e.responseJSON.message);
        swal('Algo deu errado :(', e.responseJSON.message, 'error');
    }

    return DialogoVotacao;

}());

$(function () {

    var dialogoExclusao = new Condomino.DialogoVotacao();
    dialogoExclusao.iniciar();

});
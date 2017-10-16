var Condomino = Condomino || {};

Condomino.CadastroRapido = (function () {

    function CadastroRapido() {
        this.modal = $('#modalCadastroCategoria');
        this.botaoModal = $('.js-btn-salvar-categoria');
        this.botaoAbrirModal = $('.btn-abrir-modal');
        this.form = this.modal.find('form');
        this.url = this.form.attr('action');
        this.inputNovaCategoria = $('#input-nova-categoria');
        this.containerMensagemErro = $('.js-mensagem-cadastro-rapido-categoria');
        this.elementoInicial = $('#container-categoria');
    }

    CadastroRapido.prototype.iniciar = function () {
        this.botaoAbrirModal.on('click', onClicado.bind(this));
        this.modal.on('shown.bs.modal', onModalShow.bind(this));
        this.modal.on('hide.bs.modal', onModalClose.bind(this));
        this.botaoModal.on('click', onSubmit.bind(this));
    };

    function onModalShow() {
        this.inputNovaCategoria.focus();
    }

    function onModalClose() {
        this.inputNovaCategoria.val('');
        this.containerMensagemErro.addClass('hidden');
        this.form.find('.form-line').removeClass('error');
    }

    function onClicado(evento) {
        evento.preventDefault();
        this.inputNovaCategoria.val('');
        this.containerMensagemErro.addClass('hidden');
        this.form.find('.form-line').removeClass('error');
    }

    function onSubmit(evento) {

        console.log('onSubmit');

        evento.preventDefault();
        var descricaoCategoria = this.inputNovaCategoria.val().trim();
        var pautaId = $('#pauta').val();

        $.ajax({
            url: '/categorias/nova-opcao',
            method: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({descricao: descricaoCategoria, pauta: {id: pautaId}}),
            error: onErroSalvandoCategoria.bind(this),
            success: onCategoriaSalva.bind(this)
        });

        function onErroSalvandoCategoria(obj) {
            var mensagemErro = obj.responseText;
            this.containerMensagemErro.removeClass('hidden');
            this.containerMensagemErro.html('<span>' + mensagemErro + '</span>');
            this.form.find('.form-line').addClass('error focused');
        }

        function onCategoriaSalva() {
            this.modal.modal('hide');
            window.location.reload();
        }

    }

    return CadastroRapido;

}());

$(function () {

    var cadastroRapido = new Condomino.CadastroRapido();
    cadastroRapido.iniciar();

});
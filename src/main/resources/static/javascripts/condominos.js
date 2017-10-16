var Condominos = Condominos || {};

Condominos.MaskDate = (function() {

    function MaskDate() {
        this.inputDate = $('.js-date');
    }

    MaskDate.prototype.enable = function() {
        this.inputDate.datetimepicker({
            orientation: 'bottom',
            language: 'pt-BR',
            autoclose: true,
            format: 'dd/mm/yyyy hh:ii',
            todayBtn: true,
            pickerPosition: "bottom-left"
        });
    };

    return MaskDate;

}());

$(function() {

    var maskDate = new Condominos.MaskDate();
    maskDate.enable();

});
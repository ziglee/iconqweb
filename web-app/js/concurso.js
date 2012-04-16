var cargosCount = 0;

$(document).ready(function(){
    $('#cargo-add').click(function() {
        ++cargosCount;
        $('#cargos-td').append('<input type="text" name="cargos" id="cargos-'+ cargosCount +'" class="cargos-text"/><input type="button" id="cargos-'+ cargosCount +'-del" class="cargos-del-btn" value="-" onclick="removerTorre('+ cargosCount +');"/>');
    });

    cargosCount = $('.cargos-text').size();
});

function removerCargo(id) {
    $('#cargos-' + id).remove();
    $('#cargos-' + id + '-del').remove();
}
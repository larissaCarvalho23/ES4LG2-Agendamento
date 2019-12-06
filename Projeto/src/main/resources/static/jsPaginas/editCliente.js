$(document).ready(function(){

	$('#btnSalvar').click(function(){
		if(validaCadastro()){
			$('#formCadUso').submit();
		}
	});


	function validaCadastro(){
		var isValid = true;

		//nome
		if($('#nome').val() == ''){
			$('#nome').addClass('is-invalid');
			$('#nome').removeClass('is-valid');
			isValid = false;
		}else{
			$('#nome').addClass('is-valid');
			$('#nome').removeClass('is-invalid');
		}

		//sexo
		if($('#sexo').val() == ''){
			$('#sexo').addClass('is-invalid');
			$('#sexo').removeClass('is-valid');
			isValid = false;
		}else{
			$('#sexo').addClass('is-valid');
			$('#sexo').removeClass('is-invalid');
		}

		//data nascimento
		if($('#dataNasc').val() == ''){
			$('#dataNasc').addClass('is-invalid');
			$('#dataNasc').removeClass('is-valid');
			isValid = false;
		}else{
			$('#dataNasc').addClass('is-valid');
			$('#dataNasc').removeClass('is-invalid');
		}

		//email
		if($('#email').val() == ''){
			$('#email').addClass('is-invalid');
			$('#email').removeClass('is-valid');
			isValid = false;
		}else{
			$('#email').addClass('is-valid');
			$('#email').removeClass('is-invalid');
		}

		//cep
		if($('#cep').val() == ''){
			$('#cep').addClass('is-invalid');
			$('#cep').removeClass('is-valid');
			isValid = false;
		}else{
			$('#cep').removeClass('is-invalid');
			$('#cep').addClass('is-valid');
		}

		//cidade
		if($('#cidade').val() == ''){
			$('#cidade').addClass('is-invalid');
			$('#cidade').removeClass('is-valid');
			isValid = false;
		}else{
			$('#cidade').addClass('is-valid');
			$('#cidade').removeClass('is-invalid');
		}

		//logradouro
		if($('#logradouro').val() == ''){
			$('#logradouro').addClass('is-invalid');
			$('#logradouro').removeClass('is-valid');
			isValid = false;
		}else{
			$('#logradouro').addClass('is-valid');
			$('#logradouro').removeClass('is-invalid');
		}

		//numero endereco
		if($('#numeroend').val() == ''){
			$('#numeroend').addClass('is-invalid');
			$('#numeroend').removeClass('is-valid');
			isValid = false;
		}else{
			$('#numeroend').addClass('is-valid');
			$('#numeroend').removeClass('is-invalid');
		}

		//estado
		if($('#uf').val() == ''){
			$('#uf').addClass('is-invalid');
			$('#uf').removeClass('is-valid');
			isValid = false;
		}else{
			$('#uf').addClass('is-valid');
			$('#uf').removeClass('is-invalid');
		}

		//telefone
		if($('#telefone').val() == ''){
			$('#telefone').addClass('is-invalid');
			$('#telefone').removeClass('is-valid');
			isValid = false;
		}else{
			$('#telefone').addClass('is-valid');
			$('#telefone').removeClass('is-invalid');
		}

		return isValid
	}

});




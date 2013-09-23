/*JavaScript do Cadastro*/

$(document).ready(function() {
	jQuery(function($){
		$("#campoData").mask("99/99/9999", {placeholder: " "});
		$("#campoTelefone1").mask("(99) 9999-9999", {placeholder: " "});
		$("#campoTelefone2").mask("(99) 9999-9999", {placeholder: " "});
		$("#campoCep").mask("99.999-999", {placeholder: " "});
		$("#campoSenha").mask("***-****", {placeholder: " "});
		$("#campoCpf").mask("999.999.999-99", {placeholder: " "});
		$("#cpfLogin").mask("999.999.999-99", {placeholder: " "});
	});
});

function VerificaInteiro(caracter) {

	if(window.event) { // Internet Explorer
		var tecla = event.keyCode;
	}
	else { // Firefox
		var tecla = caracter.which;
	}

	if((tecla != 0) && (tecla != 8) && (tecla != 48) && (tecla != 49) && (tecla != 50) && (tecla != 51) && (tecla != 52) && (tecla != 53) && (tecla != 54) && (tecla != 55) && (tecla != 56) && (tecla != 57)) { 
		return false;
	}
	else 
	{ 
		return true; 
	}
}

function VerificaData(digData) 
{
	var bissexto = 0;
	var data = digData; 
	var tam = data.length;
	if (tam == 10) 
	{
		var dia = data.substr(0,2)
		var mes = data.substr(3,2)
		var ano = data.substr(6,4)
		if ((ano > 1900)||(ano < 2100))
		{
			switch (mes) 
			{
				case '01':
				case '03':
				case '05':
				case '07':
				case '08':
				case '10':
				case '12':
					if  (dia <= 31) 
					{
						return true;
					}
					break
				
				case '04':		
				case '06':
				case '09':
				case '11':
					if  (dia <= 30) 
					{
						return true;
					}
					break
				case '02':
					/* Validando ano Bissexto / fevereiro / dia */ 
					if ((ano % 4 == 0) || (ano % 100 == 0) || (ano % 400 == 0)) 
					{ 
						bissexto = 1; 
					} 
					if ((bissexto == 1) && (dia <= 29)) 
					{ 
						return true;				 
					} 
					if ((bissexto != 1) && (dia <= 28)) 
					{ 
						return true; 
					}			
					break;		
			}
		}
	}	
	return false;
}

function VerificaNomeProprioComNumeros(caracter) {

	if(window.event) { // Internet Explorer
		var tecla = event.keyCode;
	}
	else { // Firefox
		var tecla = caracter.which;
	}

	//alert(tecla);

	if((tecla != 0) && (tecla != 8) && (tecla != 48) && (tecla != 49) && 
			(tecla != 50) && (tecla != 51) && (tecla != 52) && (tecla != 53) && 
			(tecla != 54) && (tecla != 55) && (tecla != 56) && (tecla != 57) && 
			(tecla != 113) && (tecla != 97) && (tecla != 98) && (tecla != 122) && 
			(tecla != 119) && (tecla != 115) &&	(tecla != 120) && (tecla != 101) && 
			(tecla != 100) && (tecla != 99) && 	(tecla != 114) && (tecla != 102) && 
			(tecla != 118) && (tecla != 116) && (tecla != 103) && (tecla != 121) && 
			(tecla != 104) && (tecla != 110) && (tecla != 117) && (tecla != 106) && 
			(tecla != 109) && (tecla != 105) && (tecla != 107) && (tecla != 111) && 
			(tecla != 108) && (tecla != 112) && (tecla != 231) && (tecla != 81) && 
			(tecla != 65) && (tecla != 90) && (tecla != 87) && 	(tecla != 83) && 
			(tecla != 88) && (tecla != 69) && (tecla != 68) && (tecla != 67) &&
			(tecla != 82) && (tecla != 10) && (tecla != 86) && (tecla != 84) && 
			(tecla != 70) && (tecla != 71) && (tecla != 66) && 	(tecla != 89) && 
			(tecla != 72) && (tecla != 78) && (tecla != 85) && (tecla != 74) && 
			(tecla != 77) && (tecla != 73) && (tecla != 75) && (tecla != 79) && 
			(tecla != 76) && (tecla != 80) && (tecla != 199) &&	(tecla != 32) &&
			(tecla != 126) && (tecla != 170) && (tecla != 186) &&
			(tecla != 225) && (tecla != 226) && (tecla != 233) && (tecla != 234) && 
			(tecla != 237) && (tecla != 238) && (tecla != 243) && (tecla != 244) && 
			(tecla != 250) && (tecla != 251) && (tecla != 193) && (tecla != 201) && 
			(tecla != 205) && (tecla != 211) && (tecla != 218) && (tecla != 227) && 
			(tecla != 245) && (tecla != 194) && (tecla != 195) && (tecla != 213) &&
			(tecla != 202) && (tecla != 209) && (tecla != 241) && (tecla != 206) && (
					tecla != 212) && (tecla != 219)) { 

		return false;

	}
	else 
	{ 
		return true; 
	}
}

function VerificaNomeProprioComBarraeTraco(caracter) {

	if(window.event) { // Internet Explorer
		var tecla = event.keyCode;
	}
	else { // Firefox
		var tecla = caracter.which;
	}

	//alert(tecla);

	if((tecla != 0) && (tecla != 8) && (tecla != 113) && (tecla != 97) && 
			(tecla != 98) && (tecla != 122) && (tecla != 119) && (tecla != 115) &&
			(tecla != 120) && (tecla != 101) && (tecla != 100) && (tecla != 99) && 
			(tecla != 114) && (tecla != 102) && (tecla != 118) && (tecla != 116) && 
			(tecla != 103) && (tecla != 121) && (tecla != 104) && (tecla != 110) && 
			(tecla != 117) && (tecla != 106) && (tecla != 109) && (tecla != 105) && 
			(tecla != 107) && (tecla != 111) && (tecla != 108) && (tecla != 112) && (tecla != 231) && 
			(tecla != 81) && (tecla != 65) && (tecla != 90) && (tecla != 87) && 
			(tecla != 83) && (tecla != 88) && (tecla != 69) && (tecla != 68) && 
			(tecla != 67) && (tecla != 82) && (tecla != 10) && (tecla != 86) && 
			(tecla != 84) && (tecla != 70) && (tecla != 71) && (tecla != 66) &&
			(tecla != 89) && (tecla != 72) && (tecla != 78) && (tecla != 85) && 
			(tecla != 74) && (tecla != 77) && (tecla != 73) && (tecla != 75) && 
			(tecla != 79) && (tecla != 76) && (tecla != 80) && (tecla != 199) &&
			(tecla != 32) && (tecla != 45) && (tecla != 47) && (tecla != 126) &&
			(tecla != 225) && (tecla != 226) && (tecla != 233) && (tecla != 234) && 
			(tecla != 237) && (tecla != 238) && (tecla != 243) && (tecla != 244) && 
			(tecla != 250) && (tecla != 251) && (tecla != 193) && (tecla != 201) && 
			(tecla != 205) && (tecla != 211) && (tecla != 218) && (tecla != 227) && 
			(tecla != 245) && (tecla != 194) && (tecla != 195) && (tecla != 213) &&
			(tecla != 202) && (tecla != 209) && (tecla != 241) && (tecla != 206) && (
					tecla != 212) && (tecla != 219)) { 

		return false;

	}
	else 
	{ 
		return true; 
	}
}

function VerificaNomeProprio(caracter) {

	if(window.event) { // Internet Explorer
		var tecla = event.keyCode;
	}
	else { // Firefox
		var tecla = caracter.which;
	}

	//alert(tecla);

	if((tecla != 0) && (tecla != 8) && (tecla != 113) && (tecla != 97) && 
			(tecla != 98) && (tecla != 122) && (tecla != 119) && (tecla != 115) &&
			(tecla != 120) && (tecla != 101) && (tecla != 100) && (tecla != 99) && 
			(tecla != 114) && (tecla != 102) && (tecla != 118) && (tecla != 116) && 
			(tecla != 103) && (tecla != 121) && (tecla != 104) && (tecla != 110) && 
			(tecla != 117) && (tecla != 106) && (tecla != 109) && (tecla != 105) && 
			(tecla != 107) && (tecla != 111) && (tecla != 108) && (tecla != 112) && (tecla != 231) && 
			(tecla != 81) && (tecla != 65) && (tecla != 90) && (tecla != 87) && 
			(tecla != 83) && (tecla != 88) && (tecla != 69) && (tecla != 68) && 
			(tecla != 67) && (tecla != 82) && (tecla != 10) && (tecla != 86) && 
			(tecla != 84) && (tecla != 70) && (tecla != 71) && (tecla != 66) && 
			(tecla != 89) && (tecla != 72) && (tecla != 78) && (tecla != 85) && 
			(tecla != 74) && (tecla != 77) && (tecla != 73) && (tecla != 75) && 
			(tecla != 79) && (tecla != 76) && (tecla != 80) && (tecla != 199) &&
			(tecla != 32) && (tecla != 45) && (tecla != 126) &&
			(tecla != 225) && (tecla != 226) && (tecla != 233) && (tecla != 234) && 
			(tecla != 237) && (tecla != 238) && (tecla != 243) && (tecla != 244) && 
			(tecla != 250) && (tecla != 251) && (tecla != 193) && (tecla != 201) && 
			(tecla != 205) && (tecla != 211) && (tecla != 218) && (tecla != 227) && 
			(tecla != 245) && (tecla != 194) && (tecla != 195) && (tecla != 213) &&
			(tecla != 202) && (tecla != 209) && (tecla != 241) && (tecla != 206) && (
					tecla != 212) && (tecla != 219)) { 

		return false;

	}
	else 
	{ 
		return true; 
	}
}

function verEmail(email){
    var exclude=/[^@\-\.\w]|^[_@\.\-]|[\._\-]{2}|[@\.]{2}|(@)[^@]*\1/;
    var check=/@[\w\-]+\./;
    var checkend=/\.[a-zA-Z]{2,3}$/;
    if(((email.search(exclude) != -1)||(email.search(check)) == -1)||(email.search(checkend) == -1)){return false;}
    else {return true;}
}

function validarInscricao(){
	
	var cpf = document.form_incricao.cpf.value;
	
	cpf = cpf.replace(".","");
	cpf = cpf.replace(".","");
	cpf = cpf.replace("-","");
	
	var dataNascimento = document.form_incricao.data_nascimento.value;
	dataNascimento = dataNascimento.replace("/","");
	dataNascimento = dataNascimento.replace("/","");
	
	if(cpf==""){
		alert("Digite o CPF!");
		document.form_incricao.txtcpf.focus();
	}else
	
	if(vercpf(cpf)==false){
		alert("CPF inválido!");
		document.form_incricao.txtcpf.focus();
		document.form_incricao.txtcpf.value = "";
	}else{
		
		if(document.form_incricao.nome.value==""){
			alert("Digite o Nome!");
			document.form_incricao.txtnome.focus();
		}else 
			
		if(dataNascimento==""){
			alert("Digite a Data de Nascimento!");
			document.form_incricao.data_nascimento.focus();
		}else
		
		if(VerificaData(document.form_incricao.data_nascimento.value)==false){
			alert("Data Inválida");
			document.form_incricao.data_nascimento.focus();
		}else
			
		if(document.form_incricao.rg.value==""){
			alert("Digite o RG!");
			document.form_incricao.rg.focus();
		}else 

		if(document.form_incricao.orgaoExpedidor.value==""){
			alert("Digite o Orgão Expedidor do RG!");
			document.form_incricao.orgaoExpedidor.focus();
		}else
		
		if(document.form_incricao.telefone1.value==""){
			alert("Digite o 1º Telefone!");
			document.form_incricao.telefone1.focus();
		}else
		
		if(document.form_incricao.email.value==""){
			alert("Digite o Email!");
			document.form_incricao.email.focus();
		}else
		
		if(verEmail(document.form_incricao.email.value)==false){
			alert("Email Inválido!");
			document.form_incricao.email.focus();
		}else
			
		if(document.form_incricao.senha.value==""){
			alert("Digite a Senha!");
			document.form_incricao.senha.focus();
		}else
	
		if(document.form_incricao.txtRepitaSenha.value==""){
			alert("Repita a Senha!");
			document.form_incricao.txtRepitaSenha.focus();
		}else
		
		if(document.form_incricao.senha.value != document.form_incricao.txtRepitaSenha.value){
		
			alert("A repetição de senha está incorreta!");
			document.form_incricao.txtRepitaSenha.value = "";
			document.form_incricao.txtRepitaSenha.focus();
		
		}else{

			document.form_incricao.submit();

		}

	}	
	
	//alert(cpf);
	/*
	 */
}

function vercpf (cpf) 
{if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999")
	return false;
add = 0;
for (i=0; i < 9; i ++)
	add += parseInt(cpf.charAt(i)) * (10 - i);
rev = 11 - (add % 11);
if (rev == 10 || rev == 11)
	rev = 0;
if (rev != parseInt(cpf.charAt(9)))
	return false;
add = 0;
for (i = 0; i < 10; i ++)
	add += parseInt(cpf.charAt(i)) * (11 - i);
rev = 11 - (add % 11);
if (rev == 10 || rev == 11)
	rev = 0;
if (rev != parseInt(cpf.charAt(10)))
	return false;
return true;}
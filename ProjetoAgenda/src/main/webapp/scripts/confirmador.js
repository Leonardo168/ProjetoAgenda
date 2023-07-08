/**
 * Confirmacao de exclusao
 * @author Leonardo Gomes da Silva
 * @param id
 */

function confirmar(id) {
	let resposta = confirm("Tem certeza que deseja escluir este contato?")
	if (resposta === true) {
		window.location.href = "delete?id=" + id
	}
}
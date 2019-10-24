package proitdevelopers.com.bloomberg.communs

import proitdevelopers.com.bloomberg.viewModel.NoticiaViewModel


public lateinit var noticiaViewModel: NoticiaViewModel

lateinit var TAG :String
const val ID: String = "ID"
const val TITULO: String = "TITULO"
const val DESCRICAO: String = "DESCRICAO"
const val CONTEUDO: String = "CONTEUDO"
const val FOTO: String = "FOTO"
const val VIDEO: String = "VIDEO"
const val DATA: String = "DATA"
const val FONTE: String = "FONTE"
const val CATEGORIA: String = "CATEGORIA"
const val DURACAO: String = "DURACAO"

//--------------Constantes Mensagens
const val msgRegistroEfetuado = "Registro efetuado com sucesso."
const val msgSemInternet = "A rede 3G ou WI-FI não possui tranferência de dados."
const val msgTimeOut = "O tempo de comunicação excedeu. Possivelvente a internet está lenta."
const val msgAlgumProblema = "Algum problema aconteceu.Relata o problema."
const val msgQuasePronto = "Quase Pronto...!"
const val msgQVerificando = "Verificando...!"
const val MSG_ERRO_VAZIO_CAMPO = "Preencha o campo"
const val MSG_ERRO_CAMPOS_DIFERENTES = "Os campos devem ser iguais"
const val MSG_ERRO_EMAIL = "Preencha o campo com um email"
const val MSG_ERRO_SENHA_FRACA = "Senha fraca"

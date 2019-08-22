package proitdevelopers.com.bloomberg.modelo

data class Bolsa(var servico: String,var valor:String, var estado: String,var variacao:String)

object ValoresBolsa{
    val bolsas = listOf<Bolsa>(
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("USD","900.1","ESCEU","-2.79"),
        Bolsa("UAD","7.900.51","SUBIU","-0.129"),
        Bolsa("AOA","4.3001","SUBIU","-40.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("AOA","2.900.51","SUBIU","-0.79")
    )
}
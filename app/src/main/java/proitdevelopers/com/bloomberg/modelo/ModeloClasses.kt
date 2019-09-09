package proitdevelopers.com.bloomberg.modelo 

data class Bolsa(var servico: String,var valor:String, var estado: String,var variacao:String)

data class Noticia(var titulo: String, var descricao:String,var conteudo:String,
                   var foto:String,var video:String,var data:String,
                   var fonte:String,var categoria:String)

data class Topico(var topico:String)

object valorTopico{
    val topicos = listOf<Topico>(
        Topico("Hyperdrive"),
        Topico("Crypto"),
        Topico("Climate Changed"),
        Topico("Wealth"),
        Topico("Last"),
        Topico("Personal Finance"),
        Topico("World"),
        Topico("Stocks")

    )
}

object valoresNoticias{
    val noticias = listOf<Noticia>(
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx"),
        Noticia("Licenciatura 2019","Universidade Metodista de Angola lança 650 licenciados",
            "xxxxxxxxxxxxxxxxxxxxxx","xxxxxxxxxxxxxxxx","xxxxxxxxx","xxxx","xxx","xxxx")
    )
}

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
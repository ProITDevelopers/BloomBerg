package proitdevelopers.com.bloomberg.modelo

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class Bolsa(var servico: String,var valor:String, var estado: String,var variacao:String)

data class Noticia(var titulo: String, var descricao:String,var conteudo:String,
                   var foto:String,var video:String,var data:String,
                   var fonte:String,var categoria:String,var duracao: String)

/*@Parcelize
data class Noticias(var titulo: String, var descricao:String,var conteudo:String,
                    var foto:String,var video:String,var data:String,
                    var fonte:String,var categoria:String,var duracao: String): Parcelable

@Parcelize
class NoticiasArray: ArrayList<Noticias>(), Parcelable*/

data class Topico(var topico:String)

object valoresNoticias{
    val noticias = mutableListOf(
        Noticia("Apple, Foxconn Broke a Chinese Labor Law to Build Latest iPhones","Universidade Metodista de Angola lança 650 licenciados",
            "Os investidores estão avaliando suas opções após a surpresa da fabricação nos EUA e dados decepcionantes na Europa no início desta semana. Isso confirma as preocupações sobre a desaceleração do crescimento mundial em meio à guerra comercial EUA-China, provocando outra rodada de especulações sobre o valor pelo qual o Federal Reserve poderá cortar as taxas de juros este ano.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/i9A1AsEqqWRI/v1/800x-1.jpg","https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/Basic%20Flamenco%20Techniques.mp4?alt=media&token=75d02dee-3614-4b86-a7b2-8972dcfb3899","19-09-2019","Jornal Mercado","Negócio","4:10"),
        Noticia("Futuros de ações dos EUA avançam em meio a apostas: estímulo aos mercados","Universidade Metodista de Angola lança 650 licenciados",
            "O gás israelense começará a fluir para o Egito no início do próximo ano, com o objetivo de atingir gradualmente uma capacidade anual de quase 7 bilhões de metros cúbicos de ambas as piscinas até o verão de 2022. As partes alteraram ainda mais o contrato original para remover quaisquer flutuações na quantidade de combustível a ser entregue ao Egito.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/i_SaF6tKXGWw/v0/210x157.jpg","https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/Curso%20Dedilhado%20Express%20-%20Video%201%20de%204%20-%20TB.mp4?alt=media&token=984e2acd-6d39-49e3-956c-dbbcb13fa38e","09-09-2019","xxx","Mercado","3:10"),
        Noticia("Old Mutual novamente impede que seu CEO volte ao trabalho","Universidade Metodista de Angola lança 650 licenciados",
            "O Stoxx Europe 600 caiu, caminhando para o pior desempenho de dois dias desde agosto, já que todos os setores, exceto as viagens, ficaram vermelhos. Os contratos nos três principais índices de ações norte-americanos sinalizaram um início fraco em Nova York, depois que os índices subjacentes caíram no dia anterior, quando a bitola da manufatura dos EUA registrou a leitura mais fraca desde o final da última recessão. Na sessão asiática, todos os principais mercados acionários caíram.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/ibMOJ_I_JbWY/v0/800x-1.jpg","https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/May%20tells%20Bercow%20'at%20least%20someone%20got%20a%20landslide'.mp4?alt=media&token=3a49ac49-3b3d-45ec-98cc-1fa55057f27f","12-09-2019","xxx","Cultura","2:10"),
        Noticia("Principais produtores de cacau deixam marfinenses intrigados com o ministério do arroz","Universidade Metodista de Angola lança 650 licenciados",
            "In a separate statement on Wednesday, Delek said the companies and their Egyptian partner East Gas Co. transferred more than 70% of the \$518 million cost to buy a controlling stake in the cross-border pipeline that will facilitate this deal.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/iQ.8n1gHz7gs/v0/800x-1.jpg","https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/Nigel%20Farage%20warns%20Brexit%20deal%20hands%20ALL%20power%20to%20EU%20and%20leaves%20UK%20at%20mercy%20of%20B.mp4?alt=media&token=a7ed97b8-1661-46e2-92ad-fefe10483922","05-09-2019","xxx","Politica","7:13"),
        Noticia("Naspers Amsterdam Foray alivia dores para os gestores de fundos da África do Sul","Universidade Metodista de Angola lança 650 licenciados",
            "Em comunicado separado na quarta-feira, Delek disse que as empresas e seu parceiro egípcio East Gas Co. transferiram mais de 70% do custo de US \$ 518 milhões para comprar uma participação de controle no oleoduto que facilitará esse acordo.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/i5lFgR6JDpqc/v0/420x315.jpg","https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/V%C3%ADdeos%20curtos%20e%20engra%C3%A7ados%2018.mp4?alt=media&token=0503c8d8-8a11-41fb-978e-6bd9fe8585e4","3-09-2019","xxx","O Páis","9:55"),
        Noticia("Ferrari lança dois novos modelos de aranha em um ano recorde de lançamento","Universidade Metodista de Angola lança 650 licenciados",
            "The change in pipeline ownership, expected this month, will give the companies exclusive rights to lease and operate the pipeline and more power to avoid disruptions in their dealings with Egypt.","https://everydaychimp.com/wp-content/uploads/2018/09/15592981482_98f87f0d06_o-1.jpg","https://firebasestorage.googleapis.com/v0/b/jogo-hoje.appspot.com/o/Basic%20Flamenco%20Techniques.mp4?alt=media&token=75d02dee-3614-4b86-a7b2-8972dcfb3899","09-09-2019","xxx","Últimos acontecimentos","3:40"),
        Noticia("Bugatti na busca de financiamento por Stablemate de 4 lugares para Quíron","Universidade Metodista de Angola lança 650 licenciados",
            "Delek is examining ways to boost production capacity of Leviathan, Israel’s largest field, in order to service potential deals with companies such as Royal Dutch Shell Plc or Spain’s Union Fenosa SA, which own stakes in Egypt’s two liquefied natural gas plants.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/izF8wnTMCFyI/v0/800x600.jpg","https://appaws.s3.us-east-2.amazonaws.com/video/1569407751166_Enaltecido%20contributo%20do%20PR%20no%20crescimento%20e%20afirma%C3%A7%C3%A3o%20de%20Angola%20_%20TV%20Zimbo%20_.mp4","09-09-2019","xxx","Mercado","1:10:13"),
        Noticia("Parlamento britânico será suspenso na segunda-feira: atualização do Brexit","Universidade Metodista de Angola lança 650 licenciados",
            "O Zimbábue acusou os EUA de ignorância depois que a Alfândega e Proteção de Fronteiras dos EUA anunciou que está bloqueando as importações de diamantes em bruto dos campos de Marange porque eles foram produzidos com trabalho forçado.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/i9OdcPyiJnTU/v0/600x-1.jpg","https://appaws.s3.us-east-2.amazonaws.com/video/1569406673352_Chefe%20de%20Estado%20angolano%20presenteado%20com%20obra%20de%20Dino%20Salvatore.mp4","09-09-2019","xxx","Cultura","4:56"),
        Noticia("Uma recessão na fabricação pode custar a Trump um segundo mandato","Universidade Metodista de Angola lança 650 licenciados",
            "The world needs a more precise way to describe wealth. “Millionaire” is too broad, covering everyone from random pikers with a scant \$1 million in net worth all the way up to people just shy of billionaire status. “Billionaire” has the same problem. There’s a huge difference between your local anonymous rich person who just clears the \$1 billion mark and, say, Jeffrey Bezos or Bill Gates. The language down the scale of wealth is even more impoverished. “Thousandaire” isn’t even an accepted word, let alone “hundredaire.” Yet that’s the net worth space where most of us live.","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ84D7-6KpPQ2NBrZ4Y_5TJQ0X3kW9TqyqKeXGIpc7pAmbpCkAbfw","https://appaws.s3.us-east-2.amazonaws.com/video/1569405739845_ABC%20de%20Educa%C3%A7%C3%A3o%20Financeira%20-%20Identifica%C3%A7%C3%A3o%20dos%20Clientes%20de%20Produtos%20e%20Servi%C3%A7os%20F.mp4","09-09-2019","xxx","Política","2:30"),
        Noticia("O novo presidente do Alibaba diz que ele precisa reinventar o varejo antes que alguém o faça","Universidade Metodista de Angola lança 650 licenciados",
            "Lebanon is transfixed by reports that Prime Minister Saad Hariri gave \$16 million to a South African model with whom he was romantically linked. The money transfers began in 2013, when Hariri was not in office, and no laws appear to have been broken. For some, it is a welcome diversion from a deepening economic crisis, for others, a reminder of the yawning gap between Lebanese, who are struggling with a serious currency crunch, and their leaders.","https://assets.bwbx.io/images/users/iqjWHBFdfxIU/iY8Fm6z5Hq7w/v1/1000x-1.jpg","https://appaws.s3.us-east-2.amazonaws.com/video/1569404136196_ABC%20de%20Educa%C3%A7%C3%A3o%20Financeira%20-%20Branqueamento%20de%20Capitais%20e%20Financiamento%20ao%20Terror.mp4","09-09-2019","xxx","Negócio","6:17")
    )
}

object ValoresBolsa{
    val bolsas = listOf<Bolsa>(
        Bolsa("AOA","2.900.51","SUBIU","-0.79"),
        Bolsa("USD","900.1","DESCEU","-2.79"),
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
# prompt-openai

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.jar.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.native.enabled=true
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.native.enabled=true -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/prompt-openai-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

## Provided Code

### REST

Easily start your REST Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)





# demo-prompts-ia-fabiano
Prova de conceito sobre prompts de ia generativa, criar iterações com as feramentas de ia via Java.
- Java 21
- GrallVM
- Quarkus, Mailer, arc, rest  
- Jsoup
- LangChain4j
- MailHog


# prompt-1 - endpoint /read
Possibilita crawling de um texto em uma pagina html qualquer, atrvés da propriedade css/class na qual o texto foi publicado,
separa, prepara e soma, faz anotações dos principais pontos e resume com uma narrativa de explicação sobre o assunto, 
realiza tradução do resultado para potuguês pt-br.


- Para testar, necessário ter uma conta na openIA(chatGTP), com crédito pegar sua "project-api-key" e setar no propeties
do projeto.
- Também necessário verificar se a página na qual o texto é publicado existe e se a "class" definida aqui no projeto, 
ainda existe.
- validar se a url montada no inicio do processo precisa de algum ajuste. (Dica pegue a url e acesse a pagina verifique
se o artigo ainda existe, o artigo vem em constante atualização, se preferir user outra pagina de sua escolha).


curl do endpoint:
curl --location 'localhost:8080/read' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'https%3A%2F%2Fwww.redhat.com%2Fen%2Fblog%2Fthe-power-of-ai-is-open='


O texto foi publicado em: 06/11/2023
escrito por: Ashesh Badani
url: https://www.redhat.com/en/blog/the-power-of-ai-is-open

* $ response com o artigo:
O artigo discute a crescente importância da inteligência artificial (IA) no panorama empresarial atual, ressaltando a 
urgência com que as empresas devem adotar e operacionalizar essa tecnologia para se manterem competitivas. Durante a 
Red Hat Summit 2023, Matt Hicks destacou que a IA é uma questão central tanto no setor de TI quanto em outras áreas, e 
que é fundamental que as empresas integrem a IA em suas operações. Essa integração pode melhorar a produtividade, a 
tomada de decisões e gerar novas oportunidades de crescimento.

O texto apresenta diversas vantagens da IA, como a possibilidade de aumentar a eficiência e promover uma melhor 
experiência ao cliente. Também enfatiza que as empresas não podem se dar ao luxo de adiar sua adoção, pois isso pode 
acarretar em custos operacionais mais altos e na perda de insights valiosos.

Os passos para equipar as organizações para o uso eficaz da IA incluem:
1. Operacionalizar casos de uso de IA desde a ideia até a produção, com a colaboração de equipes diversas.
2. Utilizar ferramentas existentes que promovam agilidade na entrega de aplicações baseadas em IA.
3. Cumprir com os padrões de segurança, regulamentares e de governança.

A Red Hat está posicionada para ajudar as empresas a adotar a IA com soluções que incorporam tecnologias de código 
aberto, promovendo a colaboração e a inovação. A empresa oferece suporte no desenvolvimento, implantação e gestão de 
aplicações habilitadas para IA em diversas nuvens.

**Principais Conclusões:**
- A IA é essencial para a competitividade e inovação nas empresas.
- A adoção da IA deve ser abrangente e integrada em múltiplas funções de negócios.
- Red Hat oferece soluções para integração e escalabilidade da IA, permitindo que as empresas aproveitem melhor essa 
tecnologia. É crucial que as empresas atuem proativamente para adaptar-se às necessidades em constante mudança de 
seus clientes por meio da IA.


Fonte do exemplo:
https://developers.redhat.com/articles/2024/02/07/how-use-llms-java-langchain4j-and-quarkus#create_the_application





# prompt 2 -  endpoint /email-me-a-poem
É um gerador de poemas, passamos os topicos e a quantidade de linhas, ele cria um poema, traduz para português e envia 
por email. 
Configurado uma ferramenta das extensões Quarkus para indicar ao prompt como ele envia o email.


- Através da interface "MyAiService", a aplicação sabe como enviar o email, eu indico o "tools" que é a classe que 
sabe enviar o mailer que é o seviço que emula o envio de email pelo Quarkus. 

Para rodar:
Se você teve sucesso em configurar sua chave de projeto openIa, basta o projeto estar em execução e chamar o endpoint 
indicado (Ajuste o texto para sua preferência e a quantidade de linhas).
Para indicar se quer tradução em outro idioma, ajuste no metodo "writeAPoem()".
A classe PoemAIService, foi desabilitada pois ela só tinha a função de gerar o poema, com a atualização da feature, a api
gera o poema e envia por email.
- Detalhe importante, para visualizar o email, nesse projeto há 2 maneiras, via mailer, uma simulação é registrada no 
console log da IDE, configure a anotação no propeties para "true" ou com o MailHog que tem interface gráfica para receber
os e-mails simulados, lembre de configurar a img no docker e setar o atributo de config do mailer para "false".

- Comando para baixar a imagem docker local, na instalação do MailHog
docker run -d -p 8025:8025 -p 1025:1025 mailhog/mailhog

curl do endpoint:
curl --location 'localhost:8080/email-me-a-poem?topiPoem=Corinthians%2C%20vida%2C%20amor%2C%20eterno%2C%20Doutor%2C%20sa%C3%BAde.%20c%C3%A9u&qtdLine=10'

Fonte do exemplo:
https://docs.quarkiverse.io/quarkus-langchain4j/dev/index.html


* $ email recebido no MailHog:
From	sendMeALetter@quarkus.io
Subject	A poem for you
To	sendMeALetter@quarkus.io

Corinthians, amor eterno em cada jogada,
O céu se ilumina, a vida é uma balada.
Doutor da alegria, saúde em cada canto,
Torcida vibrante, um amor sem quebranto.

Nas vitórias e derrotas, a união prevalece,
Em cada coração corinthiano, a fé nunca esmorece.
Vida que pulsa, nas arquibancadas ressoa,
Corinthians, o orgulho que sempre entoa.

Caminhando juntos, eternamente unidos,
No amor do futebol, somos todos vencidos.
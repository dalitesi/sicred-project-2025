# Teste Sicredi

Este é o repositório do teste Sicredi. Aqui, você encontrará informações úteis sobre o projeto, como como configurá-lo e utilizá-lo.

## Descrição

Este projeto realiza testes automatizados em APIs, utilizando as seguintes tecnologias:

- Java
- Framework: JUnit (JUnit 4.12)
- Bibliotecas: Rest Assured (io.rest-assured 5.3.1), Jackson DataBind (com.fasterxml.jackson.core jackson-databind 2.12.5)
- Apache Maven

## Configuração

Pré-requisitos:

Java Development Kit (JDK):

Para configurar o projeto em sua máquina local, siga estas etapas:

1. Clone este repositório:

   git clone https://gitlab.com/dalitesi/sicred-project.git

2. Use o comando a seguir para atualizar as bibliotecas do seu projeto:

mvn dependency:purge-local-repository

3. Em seguida, execute o seguinte comando para resolver as dependências e atualizar as bibliotecas para as versões mais recentes:

mvn dependency:resolve

Como executar o projeto através da linha de comando:

1. Faça o Download do Apache Maven:

2. Acesse o site oficial do Apache Maven (https://maven.apache.org/download.cgi) e baixe a versão mais recente do Maven, que é um arquivo compactado (formato zip). Geralmente, encontrado o arquivo no formato apache-maven-x.x.x-bin.zip, onde "x.x.x" é a versão atual.

3. Extraia o Maven:

Escolha um diretório no qual você deseja instalar o Maven. Recomenda-se usar um local sem espaços no caminho, como C:\apache-maven, por exemplo. Em seguida, siga estas etapas:

4. Navegue até o diretório onde você baixou o arquivo zip do Maven.
   Extraia o conteúdo do arquivo zip para o diretório escolhido.
   Configurar as Variáveis de Ambiente:

5. Agora você precisa adicionar o diretório bin do Maven ao seu PATH para que você possa usar o comando mvn de qualquer local no terminal.

6. Clique com o botão direito do mouse no ícone "Este Computador" no seu desktop e selecione "Propriedades".
   6.1 Clique em "Configurações avançadas do sistema" no painel esquerdo.
   6.2 Na guia "Avançado", clique em "Variáveis de ambiente".
   6.3 Na seção "Variáveis de sistema", encontre a variável "Path" e clique em "Editar...".
   6.4 Clique em "Novo" e adicione o caminho para a pasta bin do Maven. Por exemplo, C:\apache-maven\bin.
   6.5 Clique em "OK" para salvar as alterações.
   6.6 Verificar a Instalação:

7. Abra um novo terminal ou prompt de comando e execute o seguinte comando para verificar se o Maven foi instalado corretamente:

mvn -version

8. Navegue até o Diretório do Projeto:
   Abra um terminal ou prompt de comando e navegue até o diretório raiz do seu projeto, onde o arquivo pom.xml está localizado.

Compilar o Projeto:
Execute o comando mvn compile para compilar o projeto. Isso garante que as classes de teste e dependências sejam compiladas corretamente.


mvn compile

Executar os Testes:
Você pode usar o comando mvn test para executar os testes do projeto. Isso acionará a execução dos testes JUnit que usam RestAssured.

mvn test

# Librography

<p align="center">
  <img src="images/libraryLogo.png" alt="Librography Logo" width="150"/>
</p>

**Um Sistema de Gerenciamento de Bibliotecas gratuito e de código aberto, escrito em Java.**

---

**[English Version](README.en.md)** <!-- Criar este arquivo posteriormente -->

---

Librography é uma solução de software desktop projetada para auxiliar no gerenciamento de bibliotecas de pequeno e médio porte. Ele oferece um conjunto de ferramentas para catalogar acervos, gerenciar usuários (leitores), controlar empréstimos, devoluções, calcular multas e gerar relatórios, tudo através de uma interface gráfica construída em Java Swing.

## ✨ Recursos Principais

*   **📚 Gerenciamento de Catálogo de Livros:**
    *   Adicione, edite e remova livros do acervo.
    *   Detalhes incluem ISBN, título, autor(es), editora, ano de publicação, edição, volume, número de páginas e capa do livro.
    *   Busca e filtros para fácil localização de títulos.
*   **👤 Gerenciamento de Usuários/Leitores:**
    *   Cadastro completo de leitores com informações pessoais, de contato e foto.
    *   Geração de carteirinhas com código de barras e QR Code.
*   **🔄 Sistema de Empréstimos e Devoluções:**
    *   Registro detalhado de empréstimos vinculados a usuários e livros.
    *   Controle de datas de empréstimo e previsão de devolução.
    *   Processo simplificado para registro de devoluções.
*   **💰 Gerenciamento de Multas:**
    *   Cálculo automático de multas com base em atrasos na devolução.
    *   Registro e acompanhamento de pagamentos de multas.
*   **💳 Geração de Identificação:**
    *   Criação de cartões de identificação para usuários com códigos de barras e QR codes para agilizar operações.
    *   Geração de etiquetas com código de barras/QR Code para os livros.
*   **📊 Relatórios (Módulo em Desenvolvimento/Aprimoramento):**
    *   Capacidade de gerar relatórios sobre o acervo, usuários, empréstimos em dia, empréstimos atrasados e multas.
    *   (Baseado nos arquivos `MyReports` e itens da lista TODO original).
*   **📥 Importação de Dados:**
    *   Importe listas de livros e usuários a partir de arquivos Excel (.xlsx), facilitando a migração de dados existentes.
    *   (Baseado na pasta `excel files`).
*   **🛠️ Suporte a Bancos de Dados:**
    *   Atualmente suporta MySQL e PostgreSQL.
    *   Scripts de criação de banco de dados e tabelas fornecidos para facilitar a configuração inicial.
    *   (Planos para suporte a SQLite mencionados no TODO original).
*   **💻 Multiplataforma:**
    *   Desenvolvido para ser executado em sistemas operacionais Windows, Linux e macOS.
    *   Scripts de instalação e configuração específicos para cada plataforma estão disponíveis no repositório (`Setup-Windows`, `Setup-Linux`, `Setup-MacOs`).
*   **🌐 Internacionalização (Planejado):**
    *   O projeto tem planos para suportar múltiplos idiomas.

## 🚀 Instalação

**Pré-requisitos Gerais:**

*   **Java Runtime Environment (JRE):** Versão 8 ou superior. Você pode baixar do [site oficial do Java](https://www.java.com/).
*   **Sistema de Gerenciamento de Banco de Dados:**
    *   **MySQL:** Versão 5.x ou superior, ou **MariaDB** (compatível).
    *   **PostgreSQL:** Versão 9.x ou superior.
    *   O banco de dados precisa estar instalado e um usuário com permissões para criar bancos e tabelas deve estar configurado.

### Windows

1.  **Clone o repositório** ou baixe o ZIP:
    `git clone https://github.com/flashlan/Librography.git`
2.  Navegue até a pasta `Setup-Windows`.
3.  **Configuração do Banco de Dados (MySQL):**
    *   Edite o arquivo `Setup-Windows/setupMysql.bat` se necessário, ajustando caminhos ou credenciais do MySQL.
    *   Execute `Setup-Windows/setupMysql.bat` para criar o banco de dados `librography` e as tabelas necessárias. Este script pode tentar instalar o MySQL se não o encontrar (verifique o conteúdo do script para detalhes).
    *   Como alternativa, você pode executar manualmente o script SQL encontrado em `Setup-Windows/script.sql` no seu servidor MySQL.
4.  **Configuração do Aplicativo:**
    *   Os arquivos de configuração do aplicativo (`DBPAss`, `DBUser`, `ipserver`) geralmente são gerenciados pelo próprio aplicativo na primeira execução ou podem estar localizados em um diretório como `GoldenOwl.app/config/` (verifique a lógica do app). Assegure que o aplicativo consiga se conectar ao banco de dados configurado.
5.  **Executando o Librography:**
    *   Execute o arquivo `dist/Librography.jar`: `java -jar dist/Librography.jar`
    *   Pode haver um `Librography.bat` na raiz ou em `Setup-Windows` para facilitar a execução.

### Linux

1.  **Clone o repositório:**
    `git clone https://github.com/flashlan/Librography.git`
    `cd Librography`
2.  **Scripts de Configuração (Recomendado):**
    *   Navegue até a pasta `Setup-Linux`.
    *   Torne os scripts executáveis: `chmod +x *.sh`.
    *   **Para MySQL/MariaDB:**
        *   Revise e execute `Setup-Linux/createmysql.sh`. Este script tentará criar o banco de dados e o usuário.
        *   Pode ser necessário ajustar o script `Setup-Linux/script.sql` ou executá-lo manualmente se o `createmysql.sh` não cobrir tudo.
    *   **Para PostgreSQL:**
        *   (Presumindo que exista um script similar ou que `Setup-Windows/script-postgresql.sql` possa ser adaptado e executado manualmente).
    *   Execute `Setup-Linux/setup-files.sh` para organizar arquivos de configuração (verifique o conteúdo do script para entender sua função).
3.  **Configuração do Aplicativo:**
    *   Similar ao Windows, assegure que o aplicativo possa encontrar e acessar os arquivos de configuração do banco de dados (ex: em `~/.goldenOwl/` como mencionado no TODO, ou `GoldenOwl.app/config/`).
4.  **Executando o Librography:**
    *   Execute o arquivo `dist/Librography.jar`: `java -jar dist/Librography.jar`
    *   O script `Setup-Linux/Librography.sh` pode ser usado como um lançador.

### macOS

1.  **Clone o repositório:**
    `git clone https://github.com/flashlan/Librography.git`
    `cd Librography`
2.  **Configuração Manual:**
    *   A pasta `Setup-MacOs` contém um script `setup.sh` e uma estrutura `Librography.app`.
    *   Revise o `Setup-MacOs/setup.sh`. Ele parece ser projetado para copiar a aplicação para a pasta `/Applications` e pode realizar outras configurações.
        `chmod +x Setup-MacOs/setup.sh`
        `./Setup-MacOs/setup.sh`
    *   A configuração do banco de dados (MySQL ou PostgreSQL) precisará ser feita manualmente, similar ao Linux, usando os scripts SQL (`script.sql` ou `script-postgresql.sql`).
3.  **Executando o Librography:**
    *   Após a execução do `setup.sh`, você deve encontrar `Librography.app` na sua pasta de Aplicativos.
    *   Alternativamente, execute via terminal: `java -jar dist/Librography.jar`.

**Nota Importante sobre Configuração do Banco de Dados:**
Os arquivos de configuração da conexão (usuário, senha, IP do servidor do BD) são cruciais. O aplicativo parece procurar por eles em `GoldenOwl.app/config/`. Certifique-se de que esses arquivos (`DBUser`, `DBPAss`, `ipserver`, `DBType`) estejam corretamente preenchidos após a configuração do seu banco de dados.

## 📖 Como Usar

1.  **Inicie o Aplicativo:** Utilize o método de execução correspondente ao seu sistema operacional.
2.  **Primeira Execução:**
    *   O sistema pode solicitar a configuração da conexão com o banco de dados se ainda não estiver estabelecida. Forneça o tipo de banco (MySQL/PostgreSQL), endereço do servidor, nome do banco de dados (`librography`), usuário e senha.
3.  **Interface Principal:**
    *   **Cadastro de Livros:** Navegue até a seção de livros para adicionar novos títulos, preenchendo todos os campos relevantes. Você pode incluir uma imagem da capa.
    *   **Cadastro de Leitores:** Acesse a área de usuários/leitores para registrar novos membros, incluindo seus dados e foto.
    *   **Realizar Empréstimos:** Na seção de empréstimos, selecione o leitor e o(s) livro(s) a serem emprestados. O sistema registrará a data.
    *   **Registrar Devoluções:** Quando um livro for devolvido, utilize a função de devolução para atualizar seu status e calcular possíveis multas.
    *   **Consultas e Relatórios:** Explore as opções de busca e geração de relatórios para obter informações sobre o acervo e as atividades da biblioteca.

<p align="center">
  <!-- Adicionar um screenshot da interface principal aqui -->
  <!-- Exemplo: <img src="link_para_screenshot_da_interface.png" alt="Interface Principal do Librography" width="700"/> -->
  <strong>[Screenshot da Interface Principal do Librography aqui]</strong>
</p>

## 🛠️ Executando a Partir do Código Fonte (Para Desenvolvedores)

1.  **Clone o repositório:**
    `git clone https://github.com/flashlan/Librography.git`
2.  **Abra o Projeto:**
    *   Este é um projeto NetBeans. Abra-o com o Apache NetBeans IDE.
    *   As dependências (arquivos JAR) estão localizadas nas pastas `lib/` e `dist/lib/`. Certifique-se de que o NetBeans as reconheça.
3.  **Configuração do Banco de Dados:**
    *   Siga as instruções de instalação do banco de dados para sua plataforma (MySQL ou PostgreSQL).
    *   Certifique-se que os arquivos de configuração em `GoldenOwl.app/config/` (ou onde o código-fonte os espera) apontem para seu banco de dados local.
4.  **Build e Execução:**
    *   O projeto utiliza Ant para o processo de build (arquivo `build.xml`).
    *   Você pode compilar e executar o projeto diretamente pelo NetBeans. A classe principal (com o método `main`) provavelmente está em um dos pacotes dentro de `src/br/com/projeto/view/` ou similar (ex: `JFPrincipal`).

## 🤝 Como Contribuir

Suas contribuições são bem-vindas para tornar o Librography ainda melhor!

1.  **Faça um Fork** do repositório no GitHub.
2.  **Crie uma Branch:** `git checkout -b minha-melhoria`
3.  **Desenvolva:** Faça suas alterações e adicione novas funcionalidades ou correções.
4.  **Teste:** Garanta que suas alterações funcionam e não quebram funcionalidades existentes.
5.  **Faça o Commit:** `git commit -m "Adiciona minha incrível melhoria"`
6.  **Envie para o seu Fork:** `git push origin minha-melhoria`
7.  **Abra um Pull Request:** Detalhe as mudanças que você fez.

**Antes de contribuir, por favor considere:**

*   Verificar as [Issues](https://github.com/flashlan/Librography/issues) para tarefas existentes ou para reportar novos bugs.
*   Seguir os padrões de código existentes no projeto.
*   Discutir mudanças maiores abrindo uma issue primeiro.

### Lista de Tarefas (Roadmap)

A lista original de `TODO` e `BUGS` do projeto pode ser encontrada no [README antigo](https://github.com/flashlan/Librography/blob/master/README.md?prior_to_this_commit) ou distribuída pelas issues do projeto. Alguns itens notáveis incluem:

*   Implementar busca de capas de livros online.
*   Módulo pago de consulta por protocolo Z39.50.
*   Melhorar o instalador do Windows e criar um para macOS.
*   Internacionalização completa.
*   Conversão da sintaxe do banco de dados para SQLite.

## 📝 Licença

Ainda não há um arquivo `LICENSE` formal neste repositório. Recomenda-se adicionar um, como a **GNU General Public License (GPL)**, comum para softwares de código aberto, ou **MIT License** se preferir algo mais permissivo.

**Até que uma licença seja formalmente adicionada, o código é disponibilizado sob os termos padrão de direitos autorais do proprietário.**

## 🙏 Agradecimentos

*   A todos que dedicarem tempo para usar, testar ou contribuir com o Librography.
*   Às diversas bibliotecas Java de código aberto que indiretamente apoiam este projeto.

---

*Este README foi elaborado com base na estrutura de arquivos e no README anterior. Sinta-se à vontade para sugerir melhorias ou correções!*

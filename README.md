#### A Free and Opensource Library Manager writen in Java

#### Um Gerenciador de Bibliotecas grátis e opensource escrito em Java

------

### Clone this repository 

**HTTPS:** 
git clone https://git.librography.org/ci/Librography.git

or 

git clone https://github.com/flashlan/Librography.git

**SSH:** 
git clone git@git.librography.org:ci/Librography.git

or 

git clone  git@github.com:flashlan/Librography.git



### Creating a new repository on the command line

```
touch README.md
git init

git add README.md
git commit -m "first commit"
git remote add origin https://git.librography.com/flashlan/Librography.git
git push -u origin master
```

### Pushing an existing repository from the command line

```
git remote add origin https://git.librography.com/flashlan/Librography.git
git push -u origin master
```

## Librography

---------

Library Manager System writen in Java-Swing available for Windows, Linux and MacOs.

-------

**TODO:**

* implementar busca de capas de livros
* modulo pago de conslta por protocolo z39.50;
* Licensa de Software
* Modulo de serial key. 
* Botao "Sobre";
* modulo  de registro de isbn
* Procurar/setar	Instalador do Windows
* Procurar instalador MacOS
* pasta de configuracoes em "~/.goldenOwl"/ criado por script Pacote deb, rpm
* App fist run para ver os e crair pastas de acordo
* ~~instalador mysql + script criação banco de dados em first run (create if no exits)~~
* ON CONFLICT DO NOTHING import xlsx em janela de opcoes  de importacao
* relatorios de emprestimos e devolucoes
* limitar consultas de select * para especificas nos códigos.
* suporte a internacionalização
* build for ant
* convert syntax: mysql to postgresql -> sqlite

---
**BUGS:**

* remover mensagens terminal (aparecem no linux)
* remover VISIBLE e linha com create table if no exist
* carateres estranhios mysql
* Regra de negocio emprestimo? atrasado mas marca como dias restantes e ta gerando multa(com mais de 15 dias)
* livro cadastro dublica se alterar isbn.
* Botão salver em cartao faz nada (abrir explorer na url).

---
Softwares:
https://cloud.librography.org/index.php/s/3kQZ9DkmAGaCWw6


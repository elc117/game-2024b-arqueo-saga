# Arqueo Saga
Um jogo desenvolvido por Isadora Fenner Spohr, do curso de Sistemas de Informação da UFSM.    
Para jogar: https://ifspohr.itch.io/arqueo-saga

## Sobre o jogo
Arqueo Saga é um jogo de match-3, similar ao popular Candy Crush. O objetivo do jogo é quebrar todos os blocos especiais em cada fase. Ao concluir uma fase, o jogador desbloqueia informações sobre um fóssil da região da Quarta Colônia, e pode responder a uma pergunta para ganhar mais pontos.

## Desenvolvimento
O jogo foi desenvolvido utilizando a biblioteca libGDX, materiais de aula disponibilizados pela professora e fontes variadas, referenciadas na seção de referências.

A primeira parte a ser desenvolvida foi a tela de menu, de instruções e de seleção de fases. Exceto pelo background [1] do jogo, todos as demais imagens foram feitas utilizando o site Canva [2].

Em seguida, foi desenvolvida a lógica inicial do jogo, contando com funções para verificar se é possível realizar a troca dos blocos, para encontrar os matches, para quebrar os blocos e também para preencher novamente o tabuleiro. Como referência para a organização das classes, foi utilizado o projeto Candy Crush Clone [4], e o projeto BagualoQuiz [5].

A partir disso, foi criada a tela de jogo e foram inciados os testes da jogabilidade. A maior parte das funções da lógica do jogo foi alterada ou substituída. Quando o cerne do jogo estava funcionando corretamente, foram implementadas as animações. Para isso, foi necessário "quebrar" algumas das funções de quebrar os blocos, visto que é necessário que haja renderização da tela entre alguns passos para proporcionar o movimento dos blocos.

As últimas partes desenvolvidas foram o quiz, cujas questões estão em um arquivo JSON, e a adição de música e efeitos sonoros.

## [Diagrama de Classes](/t2paradigmas.md)

O diagrama de classes foi gerado automaticamente pela IDE IntelliJ.

## Referências
[1] - Imagem de background - https://www.vecteezy.com/free-photos/jurassic-park-background \
[2] - Criação das imagens do jogo - https://www.canva.com/ \
[3] - Paleta de cores - https://coolors.co/ebbab9-a72608-ffd151-5ca4a9-054a29 \
[4] - Projeto utilizado como referência - https://github.com/TobseF/Candy-Crush-Clone \
[5] - Projeto utilizado como referência - https://github.com/elc117/game-2023b-jogoenzo/tree/main \
Referências de documentação: \
[6] - https://libgdx.com/wiki/start/a-simple-game#input-controls \
[7] - https://libgdx.com/wiki/start/simple-game-extended \
[8] - https://libgdx.com/wiki/input/event-handling \
Referências para os infográficos sobre os dinossauros: \
[8] - https://www.ufsm.br/2019/02/14/nova-especie-de-dinossauro-pode-ser-o-mais-antigo-parente-do-tiranossauro-que-viveu-no-brasil \
[9] - https://www.ufsm.br/2019/07/05/pesquisadores-da-ufsm-propoem-nova-classificacao-para-fossil-de-233-de-milhoes-de-anos \
[10] - https://pt.wikipedia.org/wiki/Staurikosaurus#Descri%C3%A7%C3%A3o \
[11] - https://www.ufsm.br/2023/07/12/fossil-de-dinossauro-jovem \
[12] - https://www.ufsm.br/2019/11/11/pesquisadores-da-ufsm-e-usp-apresentam-o-dinossauro-predador-mais-completo-descoberto-no-brasil \
[13] - https://www.instagram.com/cappaufsm/p/C9n4DRLyiwi/?img_index=3 \
[14] - https://www.ufsm.br/2019/09/06/cientistas-da-usp-e-ufsm-publicam-estudo-sobre-cranio-de-dinossauro-descoberto-em-santa-maria \
Música e efeitos sonoros: \
[15] - https://www.zapsplat.com/music/game-sound-negative-incorrect-end-simple-retro-descending-glide/ \
[16] - https://www.zapsplat.com/music/game-sound-cube-game-win-collect-or-bonus-1/ \
[17] - https://www.zapsplat.com/music/game-sound-bright-and-positive-good-for-end-success-or-level-up/ \
[18] - https://pixabay.com/users/phantasticbeats-15310407/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=168459 

## Layout das fases
1)  0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 1 1 1 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 


2)  0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    1 1 1 1 1 1 1 1 1 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 


3)  0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 2 1 2 0 0 0 \
    0 0 0 1 1 1 0 0 0 \
    0 0 0 2 1 2 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 \
    0 0 0 0 0 0 0 0 0 


4)  0 0 0 0 0 0 0 0 0  
    0 0 0 0 0 0 0 0 0  
    0 0 0 0 1 0 0 0 0  
    0 0 0 2 2 2 0 0 0  
    0 0 1 2 1 2 1 0 0  
    0 0 0 2 2 2 0 0 0  
    0 0 0 0 1 0 0 0 0  
    0 0 0 0 0 0 0 0 0  
    0 0 0 0 0 0 0 0 0


5) 1 2 2 2 0 2 2 2 1  
   2 0 0 0 0 0 0 0 2  
   2 0 0 0 0 0 0 0 2  
   2 0 0 0 0 0 0 0 2  
   0 0 0 0 3 0 0 0 0  
   2 0 0 0 0 0 0 0 2  
   2 0 0 0 0 0 0 0 2  
   2 0 0 0 0 0 0 0 2  
   1 2 2 2 0 2 2 2 1

## libGDX
A [libGDX](https://libgdx.com/) project generated with [gdx-liftoff](https://github.com/libgdx/gdx-liftoff).

This project was generated with a template including simple application launchers and an empty `ApplicationListener` implementation.

### Platforms

- `core`: Main module with the application logic shared by all platforms.
- `lwjgl3`: Primary desktop platform using LWJGL3; was called 'desktop' in older docs.
- `html`: Web platform using GWT and WebGL. Supports only Java projects.

### Gradle

This project uses [Gradle](https://gradle.org/) to manage dependencies.
The Gradle wrapper was included, so you can run Gradle tasks using `gradlew.bat` or `./gradlew` commands.
Useful Gradle tasks and flags:

- `--continue`: when using this flag, errors will not stop the tasks from running.
- `--daemon`: thanks to this flag, Gradle daemon will be used to run chosen tasks.
- `--offline`: when using this flag, cached dependency archives will be used.
- `--refresh-dependencies`: this flag forces validation of all dependencies. Useful for snapshot versions.
- `build`: builds sources and archives of every project.
- `cleanEclipse`: removes Eclipse project data.
- `cleanIdea`: removes IntelliJ project data.
- `clean`: removes `build` folders, which store compiled classes and built archives.
- `eclipse`: generates Eclipse project data.
- `html:dist`: compiles GWT sources. The compiled application can be found at `html/build/dist`: you can use any HTTP server to deploy it.
- `html:superDev`: compiles GWT sources and runs the application in SuperDev mode. It will be available at [localhost:8080/html](http://localhost:8080/html). Use only during development.
- `idea`: generates IntelliJ project data.
- `lwjgl3:jar`: builds application's runnable jar, which can be found at `lwjgl3/build/libs`.
- `lwjgl3:run`: starts the application.
- `test`: runs unit tests (if any).

Note that most tasks that are not specific to a single project can be run with `name:` prefix, where the `name` should be replaced with the ID of a specific project.
For example, `core:clean` removes `build` folder only from the `core` project.

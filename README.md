
# RPG de Terminal em Java

Este projeto é um RPG simples desenvolvido em Java, jogado totalmente pelo terminal. Ele foi criado com o objetivo de praticar conceitos de orientação a objetos, modularização e interação com o usuário via console.

## Estrutura do Projeto
- **App.java**: Ponto de entrada do jogo. Gerencia o menu, criação de personagem e início do combate.
- **Personagem.java**: Define o personagem do jogador, com atributos como nome, classe e vida.
- **Inimigo.java**: Representa os inimigos enfrentados durante o jogo.
- **Combate.java**: Responsável pela lógica de combate entre personagem e inimigo.
- **TipoClasse.java**: Enum para os tipos de classe disponíveis (Guerreiro, Paladino, Mago).
- **Classe.java**: Especialização de personagem, podendo ter métodos próprios.

## Funcionalidades
- Criação de personagem com nome e escolha de classe.
- Sistema de combate simples entre personagem e inimigo.
- Exibição de mensagens de vitória ou derrota.
- Estrutura modular, facilitando a expansão do projeto.

## Crescimento do Projeto
O projeto começou com a criação básica do personagem e menu de seleção. Em seguida, foram adicionados:
- Enum para tipos de classe.
- Sistema de combate separado em uma classe própria.
- Classe de inimigo com atributos e métodos para receber dano.
- Mensagens automáticas de vitória e derrota.

A estrutura foi pensada para facilitar a adição de novas funcionalidades, como itens, evolução de personagem, múltiplos inimigos, missões e sistema de inventário.

## Como Executar
### Pré-requisitos
- Ter o **Java JDK** instalado (versão 8 ou superior).

### Via Terminal
1. Abra o terminal e navegue até a pasta `src` do projeto:
   ```bash
   cd src
   ```
2. Compile o arquivo principal (o compilador buscará as outras classes automaticamente):
   ```bash
   javac App.java
   ```
3. Execute o jogo:
   ```bash
   java App
   ```

### Via IDE (VS Code, IntelliJ, Eclipse)
1. Abra a pasta raiz do projeto (`Ex001`) na sua IDE de preferência.
2. Navegue até o arquivo `src/App.java`.
3. Clique no botão de **Run** ou use o atalho de execução da IDE.

## Próximos Passos
- Adicionar sistema de itens e inventário.
- Implementar evolução de personagem (níveis, experiência).
- Criar múltiplos inimigos e batalhas sequenciais.
- Adicionar narrativa e missões.

---

Este projeto é ideal para quem está aprendendo Java e deseja praticar lógica, orientação a objetos e interação com o usuário.

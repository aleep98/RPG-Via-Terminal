# RPG de Terminal em Java

Projeto de RPG simples em Java, jogado no terminal, focado em pratica de orientacao a objetos, separacao em camadas e regras de combate.

## Estrutura atual
- `src/main/java/App.java`: ponto de entrada, criacao do personagem e inicio do combate.
- `src/main/java/com/model/Personagem.java`: dados do jogador (vida, vida maxima, ataque, defesa, nivel, xp, bolsa).
- `src/main/java/com/model/Inimigo.java`: dados e comportamento basico do inimigo.
- `src/main/java/com/model/Bolsa.java`: inventario inicial e operacoes de item.
- `src/main/java/com/model/Items.java`: item de cura e item de ataque temporario.
- `src/main/java/com/model/TipoClasse.java`: classes do jogador (Guerreiro, Paladin, Mage).
- `src/main/java/com/model/TipoInimigos.java`: tipos de inimigo.
- `src/main/java/com/service/CombateController.java`: fluxo interativo de turno no terminal.
- `src/main/java/com/service/CombateService.java`: regras de combate (ataque, defesa, fuga, item, turno do inimigo).
- `src/main/java/com/service/NivelService.java`: ganho de XP e subida de nivel.
- `src/test/java/com/test/GameSmokeTests.java`: testes basicos sem framework.

## Funcionalidades
- Criacao de personagem com validacao de classe.
- Combate por turno com opcoes:
  - atacar
  - defender
  - usar item
  - fugir
- Sistema de bolsa com itens iniciais.
- Efeito de item de cura e ataque temporario.
- Sistema de XP e nivel:
  - +50 XP por vitoria
  - aumento de nivel quando atinge o XP necessario
  - aumento de vida maxima, vida atual e ataque ao subir de nivel

## Requisitos
- JDK 8 ou superior.

## Como executar o jogo
Na raiz do projeto (`Ex001`):

```bash
mvn compile exec:java
```

Opcionalmente, sem Maven:

```bash
javac --release 8 -d bin src/main/java/App.java src/main/java/com/model/*.java src/main/java/com/service/*.java
java -cp bin App
```

## Como executar os testes basicos
Na raiz do projeto (`Ex001`):

```bash
javac --release 8 -d bin src/main/java/App.java src/main/java/com/model/*.java src/main/java/com/service/*.java src/test/java/com/test/*.java
java -cp bin com.test.GameSmokeTests
```

Se tudo estiver correto, a saida termina com:

```text
[OK] Todos os testes basicos passaram.
```

## Observacoes
- O projeto ainda pode evoluir para persistencia de progresso, multiplos combates encadeados e testes com framework (JUnit).
- Arquivos compilados devem ficar em `bin/`.

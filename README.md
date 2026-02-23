# RPG Terminal - Tap Souls + Firelink Shrine

Projeto em Java de RPG incremental no terminal, inspirado em clicker/idle com combate por turnos, progressao por fases e hub estilo Firelink Shrine.

## Visao geral
- Combate com dano por toque + DPS por tick.
- Progressao por estagio/fase com inimigos escalando e boss na fase 10.
- HUD visual no terminal com barras de vida e ultimo evento.
- Hub Firelink Shrine entre combates para descansar, fazer upgrades e comprar itens.
- Sistema de ouro, XP, nivel e upgrades permanentes.

## Estrutura principal
- `src/main/java/App.java`: loop principal da partida, progressao de fases e integracao de servicos.
- `src/main/java/com/model/Personagem.java`: atributos de combate incremental (ATK, DPS, critico, ouro), nivel e upgrades.
- `src/main/java/com/model/Inimigo.java`: inimigo com escala por estagio/fase e recompensas de XP/ouro.
- `src/main/java/com/model/TipoInimigos.java`: tabela de inimigos e formulas de balanceamento.
- `src/main/java/com/model/Items.java`: itens consumiveis/bonificadores com nivel e custo de evolucao.
- `src/main/java/com/model/Bolsa.java`: inventario inicial e manipulacao de itens.
- `src/main/java/com/service/CombateController.java`: fluxo do combate e resultado (vitoria, derrota, retorno ao santuario).
- `src/main/java/com/service/CombateHudRenderer.java`: renderizacao do HUD no terminal.
- `src/main/java/com/service/CombateService.java`: regras de dano, turno inimigo e recompensa.
- `src/main/java/com/service/LojaService.java`: upgrades permanentes e evolucao de itens.
- `src/main/java/com/service/SantuarioService.java`: Firelink Shrine (descanso, forja e mercador).
- `src/main/java/com/service/NivelService.java`: ganho de XP e subida de nivel.
- `src/main/java/com/repository/PersonagemRepository.java`: persistencia do personagem.
- `src/test/java/com/test/GameSmokeTests.java`: smoke tests basicos sem framework.

## Fluxo de jogo atual
1. Cria ou carrega personagem.
2. Entra no Firelink Shrine antes de cada fase.
3. Escolhe descansar, melhorar build, comprar itens ou partir para combate.
4. Luta contra inimigo da fase (boss na fase 10).
5. Em vitoria: recebe XP/ouro e avanca fase.
6. Em retorno ao santuario: nao avanca fase.
7. Em derrota: encerra partida.

## Requisitos
- JDK 8 ou superior.
- Maven 3.x.
- MySQL (quando usar persistencia real com o `DatabaseManager` configurado).

## Como executar
Na raiz do projeto:

```bash
mvn compile exec:java
```

## Como executar smoke tests
```bash
javac --release 8 -d bin src/main/java/App.java src/main/java/com/model/*.java src/main/java/com/service/*.java src/main/java/com/repository/*.java src/main/java/com/rpg/db/*.java src/test/java/com/test/*.java
java -cp bin com.test.GameSmokeTests
```

Saida esperada:

```text
[OK] Todos os testes basicos passaram.
```

## Proximos passos sugeridos
- Persistir atributos incrementais no banco (ouro, DPS, critico, upgrades).
- Migrar smoke tests para JUnit.
- Adicionar eventos/NPCs de Firelink e quests simples.

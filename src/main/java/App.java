import java.util.Random;
import java.util.Scanner;

import com.rpg.db.DatabaseManager;
import com.model.Inimigo;
import com.model.Personagem;
import com.model.TipoClasse;
import com.model.TipoInimigos;
import com.service.CombateController;
import com.service.CombateController.ResultadoCombate;
import com.service.CombateService;
import com.service.LojaService;
import com.service.NivelService;
import com.service.SantuarioService;
import com.repository.PersonagemRepository;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.init();
        PersonagemRepository personagemRepository = new PersonagemRepository(databaseManager);

        System.out.println("--------MENU DE CRIACAO DE PERSONAGEM--------");
        System.out.println("Digite o nome do personagem:");
        String nome = scanner.nextLine();

        Personagem personagem = personagemRepository.buscarPorNome(nome);
        if (personagem != null) {
            System.out.println("Personagem carregado do banco.");
            if (personagem.getVida() <= 0) {
                personagem.setVida(personagem.getVidaMaxima());
                personagemRepository.salvarOuAtualizar(personagem);
                System.out.println("Seu personagem estava derrotado e foi revivido com vida cheia.");
            }
        } else {
            System.out.println("Escolha a classe do personagem: ");
            System.out.println("1 - Guerreiro");
            System.out.println("2 - Paladin");
            System.out.println("3 - Mage");

            TipoClasse classEscolhida = null;

            while (classEscolhida == null) {
                int escolhaClasse;
                try {
                    escolhaClasse = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Entrada invalida. Digite 1, 2 ou 3.");
                    continue;
                }

                switch (escolhaClasse) {
                    case 1:
                        classEscolhida = TipoClasse.GUERREIRO;
                        break;
                    case 2:
                        classEscolhida = TipoClasse.PALADIN;
                        break;
                    case 3:
                        classEscolhida = TipoClasse.MAGE;
                        break;
                    default:
                        System.out.println("Classe invalida! Digite 1, 2 ou 3.");
                }
            }

            personagem = new Personagem(nome, classEscolhida);
            personagemRepository.salvarOuAtualizar(personagem);
        }

        personagem.informacoes();

        NivelService nivelService = new NivelService(personagemRepository);
        CombateService combateService = new CombateService(nivelService);
        CombateController combate = new CombateController(combateService, scanner);
        LojaService lojaService = new LojaService(scanner);
        SantuarioService santuarioService = new SantuarioService(scanner, lojaService);

        Random random = new Random();
        TipoInimigos[] tiposInimigos = TipoInimigos.values();
        int progressoFase = Math.max(1, personagem.getNivel());
        boolean partidaAtiva = true;

        while (partidaAtiva && personagem.getVida() > 0) {
            int estagio = ((progressoFase - 1) / 10) + 1;
            int indiceFase = ((progressoFase - 1) % 10) + 1;
            boolean faseBoss = indiceFase == 10;

            boolean continuarDoSantuario = santuarioService.abrirSantuario(personagem, estagio, indiceFase);
            personagemRepository.salvarOuAtualizar(personagem);
            if (!continuarDoSantuario) {
                partidaAtiva = false;
                break;
            }

            TipoInimigos tipoAleatorio = null;
            while (tipoAleatorio == null) {
                TipoInimigos candidato = tiposInimigos[random.nextInt(tiposInimigos.length)];
                if (candidato.isBoss() == faseBoss) {
                    tipoAleatorio = candidato;
                }
            }

            Inimigo inimigo = new Inimigo(tipoAleatorio, estagio, indiceFase);
            System.out.println("Estagio " + estagio + " | Fase " + indiceFase);

            ResultadoCombate resultado = combate.iniciarCombate(personagem, inimigo);
            personagemRepository.salvarOuAtualizar(personagem);

            if (resultado == ResultadoCombate.DERROTA) {
                partidaAtiva = false;
                break;
            }

            if (resultado == ResultadoCombate.FUGA) {
                System.out.println("Voce retornou para Firelink Shrine.");
                continue;
            }

            progressoFase++;
        }

        scanner.close();
    }

}

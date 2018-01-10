package battleships.integrationtests;

import battleships.communication.Messageable;
import battleships.communication.messages.Salvo;
import battleships.communication.messages.SalvoResult;
import battleships.communication.messages.WelcomeMessage;
import battleships.game.GameResult;
import battleships.ships.Fleet;
import battleships.ships.Ship;

import java.util.Arrays;

public class IntegProvider {
  private Messageable messageForPlayer0;
  private Messageable messageForPlayer1;

  private IntegProvider(Messageable messageForPlayer0, Messageable messageForPlayer1) {
    this.messageForPlayer0 = messageForPlayer0;
    this.messageForPlayer1 = messageForPlayer1;
  }

  public Messageable messageForPlayer0() {
    return messageForPlayer0;
  }

  public Messageable messageForPlayer1() {
    return messageForPlayer1;
  }

  static Object[][] provideSalvosForIT() {
    return new Object[][] {
        {new IntegProvider(new Salvo(Arrays.asList(2, 3, 4, 5)), new Salvo(Arrays.asList(90, 99, 34, 32)))},
        {new IntegProvider(new Salvo(Arrays.asList(34, 35, 36, 41, 42, 43, 50, 60, 70, 99, 89, 79)), new Salvo(Arrays.asList(90, 99, 34, 32)))},
        {new IntegProvider(new Salvo(Arrays.asList(10, 20, 30, 40, 55, 56, 57, 7, 8, 9)), new Salvo(Arrays.asList(30, 31, 32, 33, 45, 46, 47, 2, 4, 8)))},
    };
  }

  static Object[][] provideSalvoResultsForIT() {
    return new Object[][] {
        {new IntegProvider(new SalvoResult(Arrays.asList(2, 3, 4, 5), Arrays.asList(90, 99, 34, 32), GameResult.NONE),
            new SalvoResult(Arrays.asList(90, 99, 34, 32), Arrays.asList(2, 3, 4, 5), GameResult.NONE))},
        {new IntegProvider(new SalvoResult(Arrays.asList(1, 3, 4, 5), Arrays.asList(33, 34, 35), GameResult.DRAW),
            new SalvoResult(Arrays.asList(33, 34, 35), Arrays.asList(1, 3, 4, 5), GameResult.DRAW))},
        {new IntegProvider(new SalvoResult(Arrays.asList(78, 79, 90, 80, 70), Arrays.asList(10, 20, 30), GameResult.LOOSE),
            new SalvoResult(Arrays.asList(10, 20, 30), Arrays.asList(78, 79, 90, 80, 70), GameResult.WIN))},
    };
  }

  static Object[][] provideWelcomeMessageForIT() {
    return new Object[][] {
        {new IntegProvider(new WelcomeMessage("PlayerOne"), new WelcomeMessage("PlayerTwo"))},

        {new IntegProvider(new WelcomeMessage("Im death"), new WelcomeMessage("DestroyerOfWorlds"))},

        {new IntegProvider(new WelcomeMessage("Trollo"), new WelcomeMessage("Lolo"))},
    };
  }

  static Object[][] provideFleetsForIT() {
    return new Object[][] {
        {new IntegProvider(new Fleet(Arrays.asList(Ship.createShip(1, 2, 3), Ship.createShip(10, 20, 30))),
            new Fleet(Arrays.asList(Ship.createShip(45, 46, 47), Ship.createShip(1, 10, 20))))},

        {new IntegProvider(new Fleet(Arrays.asList(Ship.createShip(45, 55, 65, 75), Ship.createShip(1))),
            new Fleet(Arrays.asList(Ship.createShip(33, 43), Ship.createShip(90, 80, 70))))},

        {new IntegProvider(new Fleet(Arrays.asList(Ship.createShip(77, 78), Ship.createShip(34, 33))),
            new Fleet(Arrays.asList(Ship.createShip(33, 34, 35), Ship.createShip(80, 81, 82))))},
    };
  }


}

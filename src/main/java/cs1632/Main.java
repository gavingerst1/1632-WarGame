package cs1632;

import java.util.LinkedList;
import java.util.Queue;


public class Main {
    public static void main(String[] args) {
        PlayingCardDeck deck = new PlayingCardDeck();

        Queue<PlayingCard> player1 = new LinkedList<>();
        Queue<PlayingCard> player2 = new LinkedList<>();
        Queue<PlayingCard> playedCards = new LinkedList<>();

        //deal 26 cards to each player
        for (int i = 0; i < 26; i++) {
            player1.add(deck.nextCard());
            player2.add(deck.nextCard());
        }

        int rounds = 0;
        int winner = 0;
        boolean gameOver = false;

        while (!player1.isEmpty() && !player2.isEmpty()) {
            PlayingCard card1 = player1.remove();
            PlayingCard card2 = player2.remove();
            playedCards.add(card1);
            playedCards.add(card2);
            rounds++;

            System.out.println("Player 1 plays " + card1.cardName());
            System.out.println("Player 2 plays " + card2.cardName());

            while (card1.playingCardValue() == card2.playingCardValue()) {
                System.out.println("WAR!");

                // Each player puts three cards face down
                for (int i = 0; i < 3; i++) {
                    if (player1.isEmpty() || player2.isEmpty()) {
                        gameOver = true;
                        break;
                    }

                    playedCards.add(player1.remove());
                    playedCards.add(player2.remove());
                }

                if (gameOver) {
                    break;
                }

                if (player1.isEmpty() || player2.isEmpty()) {
                    gameOver = true;
                    break;
                }

                card1 = player1.remove();
                card2 = player2.remove();
                playedCards.add(card1);
                playedCards.add(card2);
                rounds++;

                System.out.println("Player 1 plays " + card1.cardName());
                System.out.println("Player 2 plays " + card2.cardName());
            }

            if (gameOver) {
                break;
            }

            if (card1.playingCardValue() > card2.playingCardValue()) {
                System.out.println("Player 1 wins this round.\n");
                player1.addAll(playedCards);
            } else {
                System.out.println("Player 2 wins this round.\n");
                player2.addAll(playedCards);
            }
            playedCards.clear();
        }

        if (player1.isEmpty() && player2.isEmpty()) {
            System.out.println("The game is a tie!");

        } else if (player1.isEmpty()) {
            winner = 2;
        } else if (player2.isEmpty()) {
            winner = 1;
        }

        if (winner != 0) {
            System.out.println("Player " + winner + " wins the game!");
        }
        System.out.println("Rounds played: " + rounds);
    }
}
package com.gooey.base;

import com.gooey.base.socket.ServerEvent;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;

public class Player extends BasePlayer {
    private List<Card> hand = new ArrayList<>();
    private volatile boolean standing = false;
    private volatile boolean settled = false;
    private volatile boolean doubleDown = false;
    private boolean split = false;
    private boolean insurance = false;
    private int balance;
    private int currentBet;
    private Sinks.Many<ServerEvent> sink;

    public Player(String id, String name) {
        super(id, name);
        this.balance = 1000; // Default balance
    }

    public void reset() {
        hand.clear();
        standing = false;
        settled = false;
        doubleDown = false;
        split = false;
        insurance = false;
        currentBet = 0;
    }

    public int getBalance() {
        return balance;
    }

    public int getCurrentBet() {
        return currentBet;
    }

    public int getNumCards() {
        return this.hand.size();
    }

    public Sinks.Many<ServerEvent> getSink() {
        return sink;
    }

    public void setSink(Sinks.Many<ServerEvent> sink) {
        this.sink = sink;
    }

    public void addCard(Card card) {
        hand.add(card);
    }

    public List<Card> getHand() {
        return hand;
    }

    public int calculateHandValue() {
        int value = 0;
        int aces = 0;
        for (Card card : hand) {
            if (card.getRank() == Rank.ACE) {
                aces++;
                value += 11;
            } else {
                value += card.getValue();
            }
        }
        while (value > 21 && aces > 0) {
            value -= 10; // Convert an ace from 11 to 1
            aces--;
        }
        return value;
    }

    public boolean hasAce() {
        for (Card card : hand) {
            if (card.getRank() == Rank.ACE) {
                return true;
            }
        }
        return false;
    }

    public boolean hasFirstCardAce() {
        if (hand.isEmpty()) {
            return false;
        }

        return hand.get(0).getRank() == Rank.ACE;
    }

    public synchronized boolean shouldStillDraw() {
        return (calculateHandValue() < 21 && !isStanding() && !isDoubleDown()) && !settled;
    }

    public synchronized boolean isSettled() {
        return settled;
    }

    public synchronized void setSettled(boolean settled) {
        this.settled = settled;
    }

    public synchronized void setStanding(boolean standing) {
        this.standing = standing;
    }

    public synchronized boolean isStanding() {
        return standing;
    }

    public synchronized void setDoubleDown(boolean doubleDown) {
        this.doubleDown = doubleDown;
    }

    public synchronized boolean isDoubleDown() {
        return doubleDown;
    }

    public boolean isBlackjack() {
        return (this.calculateHandValue() == 21 && this.getNumCards() == 2);
    }

    public boolean placeBet(int amount) {
        if (isDoubleDown()) {
            this.currentBet = 2 * amount;
            // No need to check if bet amount exceeds balance in here
            // since it is already checked before doubling down
        } else {
            if (amount > balance) {
                return false;
            }
            this.currentBet = amount;
        }
        return true;
    }

    public int winBet() {
        int winnings = currentBet;
        if (isBlackjack()) {
            winnings = (int) (currentBet * 1.5); // Blackjack pays 3 to 2
        } else {
            winnings = currentBet; // Others pay even money
        }
        this.balance += winnings;
        this.currentBet = 0; // Reset current bet to 0 once bet is settled
        return winnings;
    }

    public int loseBet() {
        int loss = currentBet;
        this.balance -= loss;
        this.currentBet = 0; // Reset current bet to 0 once bet is settled
        return loss;
    }

    public void push() { // In case of a tie
        this.currentBet = 0; // Reset current bet to 0 once bet is settled
    }
}

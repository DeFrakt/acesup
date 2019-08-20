# Ace's Up 
Solitare game. Built with a Java API backend, formated to run on multiple devices, soon to be an android game. 
Card deck built with OOP principles, formated with JSON style data for cross-platform and asynchronous playing.
MySQL database for tracking game play statistics.

# Rules

<pre>

1.) Deal cards by pushing blue card deck.

2.) If there are two or more cards of the same suit, discard all but the highest-ranked card of that suit. 
Aces rank high.

3.) Repeat step 2 until there are no more pairs of cards with the same suit.

4.) Whenever there are any empty spaces, you may choose the top card of another pile to move to the empty space. 
After you do this, go to Step 2.

5.) When there are no more cards to move or remove, deal out the next cards.

6.) Repeat Step 2, using only the visible, or top, cards on each of the four piles.

7.) When the last four cards have been dealt out and any moves made, the game is over. 
The fewer cards left in the tableau, the better. To win is to have only the four aces left. 
When the game ends, the number of discarded cards is your score.

Deal: push blue card (top-right corner)

Discard: push card you would like to remove

Move: hold and drag to empty pile
</pre>
